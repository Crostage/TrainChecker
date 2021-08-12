package com.crostage.trainchecker.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.crostage.trainchecker.model.station.Station


@Database(
    entities = [Station::class],
    version = 1,
    exportSchema = false
)
abstract class TrainDatabase : RoomDatabase() {
    abstract fun trainDao() : TrainDao

    companion object {
        private var instance: TrainDatabase? = null
        private const val DB_NAME = "stations.db"
        private val LOCK = Any()

        fun invoke(context: Context): TrainDatabase {
            synchronized(LOCK) {
                instance?.let { return it }  // db!=null
                return Room.databaseBuilder(context, TrainDatabase::class.java, DB_NAME)
                    .build() // db == null
            }
        }
    }


}