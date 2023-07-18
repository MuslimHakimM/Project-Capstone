package com.bangkit.healthyme.data.model.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.healthyme.R
import com.bangkit.healthyme.data.model.food.MakananItem
import com.bangkit.healthyme.databinding.ItemMakananBinding
import com.bangkit.healthyme.ui.activity.ActivityDetail
import com.bangkit.healthyme.ui.utils.FOOD
import com.bangkit.healthyme.ui.utils.FOOD2
import com.bangkit.healthyme.ui.utils.FOOD3
import com.bangkit.healthyme.ui.utils.FOOD4

class MakananAdapter : RecyclerView.Adapter<MakananAdapter.ViewHolder>() {
    private val listMakanan = ArrayList<MakananItem>()

    class ViewHolder(private val binding: ItemMakananBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(makanan: MakananItem) {
            binding.apply{
                imgItem.setImageResource(R.drawable.gambar4)

                Food.text = "Nama Food : ${makanan.nameFood}"
                Food2.text = "Kalori : ${makanan.kalori}"
                Food3.text = "Protein : ${makanan.protein}"
                Food4.text = "Lemak : ${makanan.lemak}"


                cardView.setOnClickListener {

                    val intent =Intent(itemView.context, ActivityDetail::class.java)

                    intent.putExtra(FOOD, makanan.nameFood)
                    intent.putExtra(FOOD2, makanan.kalori)
                    intent.putExtra(FOOD3, makanan.protein)
                    intent.putExtra(FOOD4, makanan.lemak)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMakananBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMakanan.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMakanan[position])
    }

    fun setMakanan(makanan: ArrayList<MakananItem>) {
        listMakanan.clear()
        listMakanan.addAll(makanan)
        notifyDataSetChanged()
    }
}