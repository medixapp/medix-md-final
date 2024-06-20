package com.example.medix.ui.nav.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medix.data.Result
import com.example.medix.databinding.FragmentArticleBinding
import com.example.medix.ui.ViewModelFactory

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<ArticleViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val articleAdapter = ArticleAdapter()
        viewModel.getAllArticle().observe(viewLifecycleOwner) { result ->
            if (result!=null) {
                when (result){
                    is Result.Loading -> {
                        _binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        _binding?.progressBar?.visibility = View.GONE
                        articleAdapter.submitList(result.data.data)
                    }
                    is Result.Error -> {
                        _binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding?.rvArticle?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articleAdapter
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}