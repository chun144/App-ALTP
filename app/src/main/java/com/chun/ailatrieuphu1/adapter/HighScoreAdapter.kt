package com.chun.ailatrieuphu1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chun.ailatrieuphu1.databinding.HighScoreFragmentBinding
import com.chun.ailatrieuphu1.databinding.ItemScoreBinding
import com.chun.ailatrieuphu1.model.HighScore

class HighScoreAdapter : RecyclerView.Adapter<HighScoreAdapter.HighScoreViewHolder> {
    private val inter: IHighScore

    constructor(inter: IHighScore) {
        this.inter = inter
    }

    //trả về số lượng item data cần hiển thị
    override fun getItemCount(): Int {
        return inter.getCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreViewHolder {
        return HighScoreViewHolder(
            ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HighScoreViewHolder, position: Int) {
        holder.binding.data = inter.getData(position)

        holder.binding.root.setOnClickListener {
            inter.onCLickItem(holder.adapterPosition)
        }
    }

    interface IHighScore {
        fun getCount(): Int
        fun getData(position: Int): HighScore
        fun onCLickItem(position: Int)
    }


    class HighScoreViewHolder : RecyclerView.ViewHolder {
        val binding: ItemScoreBinding

        constructor(binding: ItemScoreBinding) : super(binding.root) {
            this.binding = binding
        }
    }
}