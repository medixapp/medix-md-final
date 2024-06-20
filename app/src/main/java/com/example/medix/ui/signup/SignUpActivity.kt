package com.example.medix.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.medix.data.Result
import com.example.medix.databinding.ActivitySignUpBinding
import com.example.medix.ui.ViewModelFactory
import com.example.medix.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: SignUpViewModel by viewModels {
            factory
        }
        binding?.btnSignup?.setOnClickListener{
            val username = binding?.etUsername?.text.toString()
            val email = binding?.etEmail?.text.toString()
            val password = binding?.etPassword?.text.toString()
            viewModel.postUserSignUp(username, email, password).observe(this) { result ->
                if (result != null){
                    when (result){
                        is Result.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(this, result.data.message.toString(), Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                        is Result.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding?.tvMovesignup?.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}