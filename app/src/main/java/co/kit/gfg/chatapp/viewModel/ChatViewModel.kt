package co.kit.gfg.chatapp.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import co.kit.gfg.chatapp.chat.*
import co.kit.gfg.chatapp.repository.ChatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ChatViewModel @ViewModelInject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private var failedMessages: List<Message?>? = null
    private var allMessages: Flow<List<Message?>>? = null

    fun insertMessage(
        writeMessage: String,
        chatId: String,
        uid: String,
        senderId: String,
        isMe: Boolean,
        fatherId: Int,
        status: MessageStatus
    ) {
        val message = Text(
            content = Content(
                0,
                chatId,
                getTimeCurrent(),
                uid,
                writeMessage,
                senderId,
                isMe,
                status
            ),
            father = Father(fatherId),
            child = null
        )
        CoroutineScope(Dispatchers.IO).launch {
            repository.insert(message)
        }

    }

    private fun getTimeCurrent(): String {
        val currentDateTime = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' h:mm a");
        return dateFormat.format(currentDateTime.time).toString()
    }

    fun updateMyMessageStatus(status: MessageStatus, uid: String, messagge: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateMyMessageStatus(status, uid, messagge)
        }
    }

    fun getMyFailedMessages(chatId: String): List<Message?> {
        return repository.getMyFailedMessage(chatId)
    }

    fun getAllMessages(chatID: String): Flow<List<Message?>>? {
        return repository.getAllMessage(chatID)?.distinctUntilChanged()
    }
}