package com.example.chatbot.ui.main.recyclerview

import android.view.View
import com.example.chatbot.data.MessageData
import com.example.chatbot.data.OwnerType
import com.example.chatbot.ui.main.recyclerview.base.BaseBindingModel

class MessageUserBindingModel(messageData: MessageData) : BaseBindingModel(messageData) {
    override fun shouldShownInnerIcon(ownerType: OwnerType) = View.GONE
}
