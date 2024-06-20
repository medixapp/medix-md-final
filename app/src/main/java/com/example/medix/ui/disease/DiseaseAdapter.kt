package com.example.medix.ui.disease

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medix.data.api.response.DataItemPredict
import com.example.medix.databinding.ItemDiseaseBinding
import com.example.medix.ui.medication.MedicationActivity

class DiseaseAdapter: ListAdapter<DataItemPredict, DiseaseAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemDiseaseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diseaseItem: DataItemPredict) {
            binding.tvTitledisease.text = diseaseItem.label
            binding.tvDescdis.text = diseaseItem.desc
            binding.tvDescPenyebab.text = diseaseItem.penyebab
            val confidencePercentage = (diseaseItem.confidence * 100).toInt()
            binding.tvConfidence.text = "$confidencePercentage%"

            binding.btnMedication.setOnClickListener{
                val intent = Intent(itemView.context, MedicationActivity::class.java)
                intent.putExtra(MedicationActivity.EXTRA_OBAT, diseaseItem.label)
//                Toast.makeText(itemView.context, diseaseItem.label, Toast.LENGTH_SHORT).show()
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemDiseaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val disease = getItem(position)
        holder.bind(disease)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DataItemPredict> =
            object : DiffUtil.ItemCallback<DataItemPredict>() {
                override fun areItemsTheSame(
                    oldItem: DataItemPredict,
                    newItem: DataItemPredict
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: DataItemPredict,
                    newItem: DataItemPredict
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}