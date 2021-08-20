package com.crostage.trainchecker.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.crostage.trainchecker.model.data.train.Train
import com.crostage.trainchecker.model.domain.StationSearchResponse


@Database(
    entities = [StationSearchResponse::class, Train::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StationListConverter::class, TicketListConverter::class)
abstract class TrainDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
    abstract fun trainDao(): TrainDao

    companion object {
        private var instance: TrainDatabase? = null
        private const val DB_NAME = "stations_trains.db"
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