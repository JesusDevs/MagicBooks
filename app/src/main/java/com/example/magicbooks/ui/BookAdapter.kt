package com.example.magicbooks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicbooks.databinding.ItemBookBinding
import com.example.magicbooks.pojo.BookResponseItem

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookVH>() {
    private var listBookItem = listOf<BookResponseItem>()

    private var selectedItem = MutableLiveData<BookResponseItem>()
    fun selectedItem() = selectedItem
    fun update(list: List<BookResponseItem>) {

        listBookItem = list
        notifyDataSetChanged()
    }

    inner class BookVH(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(bookResponseItem: BookResponseItem) {

            with(binding) {
                tituloEd.text = bookResponseItem.title
                autorEd.text = bookResponseItem.author
                autorEd2.text = bookResponseItem.country
                autorEd3.text = bookResponseItem.language

                Glide.with(imgViewBook)
                    .load(bookResponseItem.imageLink)
                    .centerCrop()
                    .into(imgViewBook)
            }



            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            selectedItem.value = listBookItem[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH {
        return BookVH(ItemBookBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        val bookItem = listBookItem[position]
        holder.bind(bookItem)

    }

    override fun getItemCount(): Int = listBookItem.size
}
