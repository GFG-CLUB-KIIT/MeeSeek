package co.kit.gfg.chatapp.repository

import android.os.Message
import androidx.lifecycle.LiveData
import co.kit.gfg.chatapp.chat.MessageStatus
import co.kit.gfg.chatapp.database.MyDao
import co.kit.gfg.chatapp.database.entities.ChatMessage
import co.kit.gfg.chatapp.mapper.ChatMessageMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepository @Inject constructor(private val myDao: MyDao) {

    @Inject
    lateinit var chatMessageMapper: ChatMessageMapper

    fun getNewMessage(id: Int): LiveData<List<ChatMessage>> {
        return myDao.getLastMessage(id)
    }

    fun getAllMessage(chatId: String): Flow<List<co.kit.gfg.chatapp.chat.Message?>>? {
        val messages = myDao.getMessages(chatId)
        return messages.map { chatMessageMapper.mapFromEntityList(it) }
    }

    fun insert(message: co.kit.gfg.chatapp.chat.Message) {
        val chatMessage = chatMessageMapper.mapToEntity(message)
        chatMessage?.let { myDao.insertMessage(it) }
    }

    fun updateMyMessageStatus(status: MessageStatus, uId: String, message: String) {
        val state = when (status) {
            is MessageStatus.MessageStatusNone -> "0"
            is MessageStatus.MessageStatusSeen -> "2"
            is MessageStatus.MessageStatusSend -> "1"
        }
        myDao.updateMyMessageStatus(state, uId, message)
    }

    fun getMyFailedMessage(chatID: String): List<co.kit.gfg.chatapp.chat.Message?> {
        val failedMessage = myDao.getMyFailedMessages(chatID)
        return failedMessage.map { chatMessageMapper.mapFromEntity(it) }
    }
}