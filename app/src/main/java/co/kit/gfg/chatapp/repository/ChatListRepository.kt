package co.kit.gfg.chatapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import co.kit.gfg.chatapp.database.MyDao
import co.kit.gfg.chatapp.devices.BLDevice
import co.kit.gfg.chatapp.mapper.ChatListMapper
import javax.inject.Inject

class ChatListRepository @Inject constructor(private val dao: MyDao) {
    @Inject
    lateinit var chatListMapper: ChatListMapper

    fun getConnectedDevices(): LiveData<List<BLDevice?>> {
        val connectedDevices = dao.getConnectedDevices()
        return connectedDevices.map { chatListMapper.mapFromEntityList(it) }
    }

    fun insertConnectedDevice(blDevice: BLDevice) {
        val device = chatListMapper.mapToEntity(blDevice)
        dao.insertConnectedDevices(device)
    }
}