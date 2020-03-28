package com.example.chatbot.data

data class MessageData(
    val messageOwner: OwnerType,
    val message: String,
    val date: String, // USE PREFORMATTED TEXT ("hh:mm")
    val showInnerIcon: Boolean
)