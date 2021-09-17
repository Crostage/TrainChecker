package com.crostage.trainchecker.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.crostage.trainchecker.utils.Constant.Companion.DB_NAME
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verifySequence
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class TrainDatabaseTest {

    private val context: Context = mockk()
    private val builder: RoomDatabase.Builder<TrainDatabase> = mockk()
    private val myDb: TrainDatabase = mockk()

    @Test
    fun testInvoke() {
        mockkStatic(Room::class)
        every { Room.databaseBuilder(context, TrainDatabase::class.java, DB_NAME) } returns builder
        every { builder.fallbackToDestructiveMigration() } returns builder
        every { builder.build() } returns myDb

        val roomDb = TrainDatabase.invoke(context)

        verifySequence {
            Room.databaseBuilder(context, TrainDatabase::class.java, DB_NAME)
            builder.fallbackToDestructiveMigration()
            builder.build()
        }
        assertEquals(roomDb, myDb)
    }

}