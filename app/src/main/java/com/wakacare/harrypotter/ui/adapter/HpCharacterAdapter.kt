package com.wakacare.harrypotter.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wakacare.harrypotter.R
import com.wakacare.harrypotter.databinding.CharacterItemListBinding
import com.wakacare.harrypotter.logic.CharacterModelItem
import com.wakacare.harrypotter.logic.Datafactory
import com.wakacare.harrypotter.ui.SingleMovie
import com.bumptech.glide.Glide


class HpCharacterAdapter(
    val context : Context,
    var itemList : List<CharacterModelItem>
) : RecyclerView.Adapter<HpCharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(internal val binding: CharacterItemListBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding : CharacterItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.character_item_list,
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        try {
            val model : CharacterModelItem = itemList[position]
            val currentItem = itemList[position]
            val binding = holder.binding

            setOnClickListeners(binding,currentItem)
            setDetails(binding,model)

        }catch (e:Exception){
            Log.e("Adapter", "onBindViewHolder: ",e )
        }
    }

    private fun setOnClickListeners(
        binding: CharacterItemListBinding,
        currentItem: CharacterModelItem
    ) {
        try {
            binding.cardview.setOnClickListener {
                // Define the intent
                Datafactory.setCharacterDetails(currentItem)
                val intent = Intent(context, SingleMovie::class.java)

               // Start the new activity
               context.startActivity(intent)

            }
        } catch (e:Exception){
            throw e
        }
    }

    private fun setDetails(binding: CharacterItemListBinding, model: CharacterModelItem) {
        try {
            binding.dorm.text = model.house
            binding.age.text = model.dateOfBirth
            binding.name.text = model.name
            binding.species.text = model.species

            Glide.with(context)
                .load(model.image)
                .placeholder(R.drawable.placeholder)
                .into(binding.image)
        } catch (e:Exception){
            throw e
        }
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    // Update the list of characters and notify the adapter of the data changes
    fun submitList(newCharacterList: List<CharacterModelItem>) {
        itemList = newCharacterList
        notifyDataSetChanged()
    }

}