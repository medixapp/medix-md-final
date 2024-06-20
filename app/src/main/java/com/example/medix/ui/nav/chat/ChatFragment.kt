package com.example.medix.ui.nav.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.medix.R
import com.example.medix.databinding.FragmentChatBinding
import com.example.medix.ui.ViewModelFactory

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null

    private val viewModel by viewModels<ChatViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root


        viewModel.chatbot.observe(viewLifecycleOwner){ chatbot ->
            _binding?.progressBar?.visibility = View.GONE
            val itemView2 = inflater.inflate(R.layout.item_reply, null)
            val textView2 = itemView2.findViewById<TextView>(R.id.tv_reply)
            textView2.text = chatbot.answer
            _binding?.llChat?.addView(itemView2)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        _binding?.ibSend?.setOnClickListener {
            val question = _binding?.etChat?.text.toString()
            val inflater = LayoutInflater.from(requireContext())
            val itemView = inflater.inflate(R.layout.item_chat, null)
            val textView = itemView.findViewById<TextView>(R.id.tv_chat)
            textView.text = _binding?.etChat?.text
            _binding?.llChat?.addView(itemView)
            viewModel.postReview(question)
            _binding?.etChat?.setText("")
            _binding?.tiChat?.hint = ""
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}