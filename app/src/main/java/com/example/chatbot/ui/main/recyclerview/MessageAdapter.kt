package com.example.chatbot.ui.main.recyclerview

import com.example.chatbot.R
import com.example.chatbot.base.BaseAdapter
import com.example.chatbot.data.MessageData
import com.example.chatbot.data.OwnerType
import com.example.chatbot.databinding.RecyclerViewItemBinding

class MessageAdapter(
    layoutRes: Int = R.layout.recycler_view_item,
    onItemClickListener: OnItemClickListener?
) :
    BaseAdapter<RecyclerViewItemBinding, MessageData>(layoutRes, onItemClickListener) {

    override fun onBindViewHolder(holder: ViewHolder<RecyclerViewItemBinding>, position: Int) {
        when (data[position].messageOwner) {
            OwnerType.BOT -> holder.binding.bindingModel = MessageBotBindingModel(data[position])
            OwnerType.USER -> holder.binding.bindingModel = MessageUserBindingModel(data[position])
        }
    }
}
