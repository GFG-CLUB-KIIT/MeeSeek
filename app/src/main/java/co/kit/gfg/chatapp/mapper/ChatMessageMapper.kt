package co.kit.gfg.chatapp.mapper

import co.kit.gfg.chatapp.Constants.Companion.MessageStatusNone
import co.kit.gfg.chatapp.Constants.Companion.MessageStatusSeen
import co.kit.gfg.chatapp.Constants.Companion.MessageStatusSend
import co.kit.gfg.chatapp.Constants.Companion.MessageTypeNone
import co.kit.gfg.chatapp.Constants.Companion.MessageTypeText
import co.kit.gfg.chatapp.chat.*
import co.kit.gfg.chatapp.database.entities.ChatMessage
import javax.inject.Inject

class ChatMessageMapper @Inject constructor() : EntityMapper<ChatMessage, Message?> {
    override fun mapFromEntity(entity: ChatMessage): Message? {
        return when (entity.type) {
            MessageTypeText -> Text(
                content = Content(
                    id = entity.id,
                    chatId = entity.chatId,
                    time = entity.time,
                    content = entity.content,
                    uId = entity.uId,
                    senderId = entity.senderId,
                    isMe = entity.isMe,
                    status = when (entity.status) {
                        "1" -> MessageStatus.MessageStatusSend(entity.id)
                        "2" -> MessageStatus.MessageStatusSeen(entity.id)
                        else -> MessageStatus.MessageStatusNone(entity.id)
                    }
                ),
                father = Father(entity.fatherId),
                child = null
            )
            else -> null
        }
    }

    override fun mapToEntity(domainModel: Message?): ChatMessage? {
        domainModel?.let { message ->
            return ChatMessage(
                id = message.content().id,
                chatId = message.content().chatId,
                senderId = message.content().senderId,
                time = message.content().time,
                content = message.content().content,
                uId = message.content().uId,
                isMe = message.content().isMe,
                status = when (message.content().status) {
                    is MessageStatus.MessageStatusNone -> MessageStatusNone
                    is MessageStatus.MessageStatusSeen -> MessageStatusSeen
                    is MessageStatus.MessageStatusSend -> MessageStatusSend
                },
                type = if (message is Text) MessageTypeText else MessageTypeNone,
                fatherId = message.father().id
            )
        }
        return null
    }

    fun mapFromEntityList(response: List<ChatMessage>): List<Message?> =
        response.map { mapFromEntity(it) }

    fun mapToEntityList(response: List<Message>): List<ChatMessage?> =
        response.map { mapToEntity(it) }
}