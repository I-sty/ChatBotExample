package com.example.chatbot.ui.main

import com.example.chatbot.base.BaseViewModel
import com.example.chatbot.data.MessageData
import com.example.chatbot.data.OwnerType
import com.thedeanda.lorem.LoremIpsum
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainViewModel : BaseViewModel() {
    fun getMessageList(): ArrayList<MessageData> {
        val itemCount = Random.nextInt(5) + 1
        val result = ArrayList<MessageData>()
        for (i in 0..itemCount) {
            result.add(getMessageItem())
        }
        return result
    }

    fun getMessageItem() =
        MessageData(getOwnerType(), getMessage(), getDate(), showInnerIcon())

    private fun getOwnerType() = if (Random.nextBoolean()) OwnerType.USER else OwnerType.BOT

    private fun getMessage(): String {
        val lorem = LoremIpsum.getInstance()
        return lorem.getWords(Random.nextInt(5) + 1, Random.nextInt(10) + 5)
    }

    private fun getDate(): String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.US)
        return sdf.format(System.currentTimeMillis())
    }

    private fun showInnerIcon() = Random.nextBoolean()
}
