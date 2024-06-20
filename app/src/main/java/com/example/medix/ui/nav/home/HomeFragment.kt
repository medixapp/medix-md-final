package com.example.medix.ui.nav.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.medix.databinding.FragmentHomeBinding
import com.example.medix.ui.ViewModelFactory
import com.example.medix.ui.disease.DiseaseActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.btnPrediction?.setOnClickListener{
            val text = _binding?.etSymptoms?.text.toString()
            val intent = Intent(requireContext(), DiseaseActivity::class.java)
            intent.putExtra(DiseaseActivity.EXTRA_GEJALA, text)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}