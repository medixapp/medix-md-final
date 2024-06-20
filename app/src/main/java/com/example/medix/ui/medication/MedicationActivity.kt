package com.example.medix.ui.medication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medix.data.Result
import com.example.medix.databinding.ActivityMedicationBinding
import com.example.medix.ui.ViewModelFactory

class MedicationActivity : AppCompatActivity() {
    private var binding: ActivityMedicationBinding? = null

    private val viewModel by viewModels<MedicationViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.topBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val obat = intent.getStringExtra(EXTRA_OBAT).toString()
        val medicationAdapter = MedicationAdapter()
        viewModel.getMedication(obat).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        medicationAdapter.submitList(result.data.data?.info)
                    }
                    is Result.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding?.rvMedication?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = medicationAdapter
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_OBAT = "extra_obat"
    }
}