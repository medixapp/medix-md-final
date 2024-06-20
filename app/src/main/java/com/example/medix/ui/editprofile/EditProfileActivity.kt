package com.example.medix.ui.editprofile

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.medix.data.Result
import com.example.medix.databinding.ActivityEditProfileBinding
import com.example.medix.ui.ViewModelFactory
import com.example.medix.ui.reduceFileImage
import com.example.medix.ui.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class EditProfileActivity : AppCompatActivity() {
    private var binding: ActivityEditProfileBinding? = null
    private var currentImageUri: Uri? = null
    private val viewModel by viewModels<EditProfileViewModel>{
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.topBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.btnEdit?.setOnClickListener{
            startGallery()
        }
        binding?.btnSave?.setOnClickListener{
            updateProfile()
        }
    }

    private fun updateProfile(){
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            val description = binding?.etDescProfile?.text.toString()
            binding?.progressBar?.visibility = View.VISIBLE
            val requestBody = description.toRequestBody("text/plain".toMediaType())
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "profileImage",
                imageFile.name,
                requestImageFile
            )
            viewModel.getSession().observe(this) { user ->
                viewModel.getUpdateProfile(user.token, requestBody, multipartBody).observe(this) { result ->
                    when (result){
                        is Result.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(this, result.data.message.toString(), Toast.LENGTH_SHORT).show()
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        is Result.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Log.e("UpdateProfile", "Error: ${result.error}")
                            Toast.makeText(this, "Error: ${result.error}", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null){
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding?.ivProfile?.setImageURI(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}