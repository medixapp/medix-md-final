package com.example.medix.ui.detailarticle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.medix.data.Result
import com.example.medix.databinding.ActivityDetailArticleBinding
import com.example.medix.ui.ViewModelFactory

class DetailArticleActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailArticleViewModel>{
        ViewModelFactory.getInstance(this)
    }

    private var binding: ActivityDetailArticleBinding? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding?.root)


        setSupportActionBar(binding?.topBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val idUser = intent.getIntExtra(EXTRA_ID, 0)

            viewModel.getDetailArticles(idUser).observe(this) { result ->
                if (result != null) {
                    when(result) {
                        is Result.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.root?.let {
                                binding?.ivDetailArticle?.let { it1 ->
                                    Glide.with(it.context)
                                        .load(result.data.data?.article?.image)
                                        .into(it1)
                                }
                            }
                            binding?.tvTitleDetailArticle?.text = result.data.data?.article?.title.toString()
                            binding?.tvDescArticle?.text = result.data.data?.article?.content.toString()
                        }
                        is Result.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val EXTRA_ID = "extra_id"
    }
}