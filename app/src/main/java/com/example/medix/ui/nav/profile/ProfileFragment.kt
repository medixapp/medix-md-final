package com.example.medix.ui.nav.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.medix.R
import com.example.medix.data.Result
import com.example.medix.databinding.FragmentProfileBinding
import com.example.medix.ui.ViewModelFactory
import com.example.medix.ui.editprofile.EditProfileActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private var INI_TOKEN: String = ""
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.getProfile(INI_TOKEN)
            Toast.makeText(requireContext(), "Profile terubah", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.btnBahasa?.setOnClickListener{
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
                INI_TOKEN = user.token
                viewModel.getProfile(user.token).observe(viewLifecycleOwner){ result ->
                    if (result!=null){
                        when(result) {
                            is Result.Loading -> {
                                _binding?.progressBar?.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                _binding?.progressBar?.visibility = View.GONE
                                if (result.data.user?.profileImage == null) {
                                    _binding?.ivProfile?.setImageResource(R.drawable.user)
                                } else {
                                    Glide.with(requireContext())
                                        .load(result.data.user.profileImage)
                                        .into(_binding!!.ivProfile)
                                }
                                _binding?.tvUsername?.text = result.data.user?.username
                                _binding?.tvEmail?.text = result.data.user?.email
                                _binding?.tvDesc?.text = result.data.user?.description
                            }
                            is Result.Error -> {
                                _binding?.progressBar?.visibility = View.GONE
                            }
                        }
                    }
                }
        }



        _binding?.btnEdit?.setOnClickListener{
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            resultLauncher.launch(intent)
        }

        _binding?.btnLogout?.setOnClickListener{
            viewModel.logout()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}