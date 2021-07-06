package co.kit.gfg.chatapp.mapper

import co.kit.gfg.chatapp.database.entities.ConnectedDevices
import co.kit.gfg.chatapp.devices.BLDevice
import javax.inject.Inject

class ChatListMapper @Inject constructor() : EntityMapper<ConnectedDevices, BLDevice> {
    override fun mapFromEntity(entity: ConnectedDevices): BLDevice {
        entity.apply {
            return BLDevice(
                deviceName = deviceName,
                deviceAddress = deviceAddress,
                date = date
            )
        }
    }

    override fun mapToEntity(domainModel: BLDevice): ConnectedDevices {
        domainModel.apply {
            return ConnectedDevices(
                id = 0,
                chatId = deviceAddress,
                deviceName = deviceName,
                deviceAddress = deviceAddress,
                date = date
            )
        }
    }

    fun mapFromEntityList(response: List<ConnectedDevices>): List<BLDevice?> =
        response.map { mapFromEntity(it) }

    fun mapToEntityList(response: List<BLDevice>): List<ConnectedDevices?> =
        response.map { mapToEntity(it) }

}