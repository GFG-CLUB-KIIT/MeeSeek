package co.kit.gfg.chatapp.di

import android.content.Context
import androidx.room.Room
import co.kit.gfg.chatapp.Constants.Companion.DATABASE_NAME
import co.kit.gfg.chatapp.database.BCADatabase
import co.kit.gfg.chatapp.database.MyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BCADatabase {
        return Room.databaseBuilder(
            context, BCADatabase::class.java, DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(dataBase: BCADatabase): MyDao {
        return dataBase.myDao()
    }
}