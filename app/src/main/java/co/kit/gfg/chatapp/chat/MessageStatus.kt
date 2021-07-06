package co.kit.gfg.chatapp.chat

sealed class MessageStatus {
    data class MessageStatusSend(var id: Int? = null) : MessageStatus()
    data class MessageStatusSeen(var id: Int? = null) : MessageStatus()
    data class MessageStatusNone(var id: Int? = null) : MessageStatus()
}