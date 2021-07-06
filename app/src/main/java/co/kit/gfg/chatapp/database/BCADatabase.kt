package co.kit.gfg.chatapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.kit.gfg.chatapp.database.entities.ChatMessage
import co.kit.gfg.chatapp.database.entities.ChildMessageEntity
import co.kit.gfg.chatapp.database.entities.ConnectedDevices

@Database(
    entities = [
        ChatMessage::class,
        ConnectedDevices::class,
        ChildMessageEntity::class
    ], version = 1
)


abstract class BCADatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}