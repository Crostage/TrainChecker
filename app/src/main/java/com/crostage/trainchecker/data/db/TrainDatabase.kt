package com.crostage.trainchecker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.crostage.trainchecker.data.model.station.Station
import com.crostage.trainchecker.data.model.train.Train
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.utils.Constant.Companion.DB_NAME


@Database(
    entities = [StationSearchResponse::class, Train::class, Station::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StationListConverter::class, TicketListConverter::class)
abstract class TrainDatabase : RoomDatabase() {
    abstract fun stationResponseDao(): StationResponseDao
    abstract fun trainDao(): TrainDao
    abstract fun lastStationsDao(): StationDao

    companion object {
        private var instance: TrainDatabase? = null
        private val LOCK = Any()

        fun invoke(context: Context): TrainDatabase {
            synchronized(LOCK) {
                instance?.let { return it }  // db!=null
                return Room.databaseBuilder(context, TrainDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build() // db == null
            }
        }
    }


}