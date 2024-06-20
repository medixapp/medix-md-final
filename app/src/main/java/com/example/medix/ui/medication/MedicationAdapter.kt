package com.example.medix.ui.medication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medix.data.api.response.InfoItem
import com.example.medix.databinding.ItemMedicationBinding

class MedicationAdapter: ListAdapter<InfoItem, MedicationAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemMedicationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(medicationItem: InfoItem) {
            binding.tvTitlemedi.text = medicationItem.medicine
            binding.tvDescmedi.text = medicationItem.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMedicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val medication = getItem(position)
        holder.bind(medication)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<InfoItem> =
            object : DiffUtil.ItemCallback<InfoItem>() {
                override fun areItemsTheSame(
                    oldItem: InfoItem,
                    newItem: InfoItem
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: InfoItem,
                    newItem: InfoItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}