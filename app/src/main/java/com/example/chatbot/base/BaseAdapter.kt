package com.example.chatbot.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<B : ViewDataBinding, I>(private var layoutRes: Int) :
    RecyclerView.Adapter<BaseAdapter.ViewHolder<B>>() {

    private var onClickListener: View.OnClickListener? = null
    protected var data: ArrayList<I> = ArrayList()

    constructor(layoutRes: Int, onItemClickListener: OnItemClickListener?) : this(layoutRes) {
        if (onItemClickListener != null) {
            onClickListener = View.OnClickListener { view ->
                var index = -1
                val vh = view.tag ?: return@OnClickListener
                if (vh is ViewHolder<*>) {
                    index = vh.adapterPosition
                }
                onItemClickListener.onItemClick(view, index)
            }
        }
    }

    open fun setDataList(data: ArrayList<I>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun addNewItem(item: I) {
        with(this.data) {
            add(0, item)
            notifyItemInserted(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent.context), layoutRes,
            parent, false
        )
        return ViewHolder(binding, onClickListener)
    }

    override fun getItemCount() = data.size

    fun getItemAt(position: Int): I? = this.data[position]

    class ViewHolder<B : ViewDataBinding>(
        var binding: B,
        onItemClickListener: View.OnClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            if (onItemClickListener != null) {
                itemView.tag = this
                itemView.setOnClickListener(onItemClickListener)
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }
}