package com.example.medix.ui.disease

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medix.data.Result
import com.example.medix.databinding.ActivityDiseaseBinding
import com.example.medix.ui.ViewModelFactory

class DiseaseActivity : AppCompatActivity() {
    private var binding: ActivityDiseaseBinding? =  null
    private val viewModel by viewModels<DiseaseViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.topBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val gejala = intent.getStringExtra(EXTRA_GEJALA).toString()
        val diseaseAdapter = DiseaseAdapter()
        viewModel.predictEmbedding(gejala).observe(this){ result ->
            if (result != null) {
                when(result) {
                    is Result.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        diseaseAdapter.submitList(result.data.data)
                    }
                    is Result.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding?.rvDisease?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = diseaseAdapter
        }

    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_GEJALA = "extra_gejala"
    }
}