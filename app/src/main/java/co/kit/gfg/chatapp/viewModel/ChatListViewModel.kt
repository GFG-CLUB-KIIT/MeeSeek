package co.kit.gfg.chatapp.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import co.kit.gfg.chatapp.devices.BLDevice
import co.kit.gfg.chatapp.repository.ChatListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatListViewModel @ViewModelInject constructor(
    private val repository: ChatListRepository
) : ViewModel() {

    fun getConnectedDevices() = repository.getConnectedDevices()

    fun insertConnectedDevice(blDevice: BLDevice) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertConnectedDevice(blDevice)
        }
    }
}