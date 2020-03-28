package com.example.chatbot.ui.main.recyclerview.base

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BaseObservable
import com.example.chatbot.data.MessageData
import com.example.chatbot.data.OwnerType
import com.example.chatbot.data.OwnerType.BOT
import com.example.chatbot.data.OwnerType.USER
import com.example.chatbot.R.color.cardview_color_bot as botBackground
import com.example.chatbot.R.color.cardview_color_user as userBackground

abstract class BaseBindingModel(item: MessageData) : BaseObservable() {
    val message = item.message
    val date = item.date
    private val innerIconVisibility: Boolean = item.showInnerIcon
    private val ownerType = item.messageOwner

    open fun shouldShownInnerIcon(ownerType: OwnerType) = if (innerIconVisibility) {
        if (ownerType == this.ownerType) {
            VISIBLE
        } else {
            GONE
        }
    } else {
        GONE
    }

    fun favIconBotVisibility() = if (ownerType == BOT) VISIBLE else GONE
    fun favIconUserVisibility() = if (ownerType == USER) VISIBLE else GONE
    fun backgroundColor() = if (ownerType == USER) userBackground else botBackground

}