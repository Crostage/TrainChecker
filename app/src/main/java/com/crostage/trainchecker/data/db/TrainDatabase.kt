package com.crostage.trainchecker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.crostage.trainchecker.data.db.converter.StationListConverter
import com.crostage.trainchecker.data.db.converter.TicketListConverter
import com.crostage.trainchecker.data.db.dao.StationDao
import com.crostage.trainchecker.data.db.dao.StationResponseDao
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.model.station.StationEntity
import com.crostage.trainchecker.data.model.station.StationSearchResponse
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.utils.Constant.Companion.DB_NAME


@Database(
    entities = [
        StationSearchResponse::class,
        StationEntity::class,
        FavouriteEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(StationListConverter::class, TicketListConverter::class)
abstract class TrainDatabase : RoomDatabase() {

    abstract fun stationResponseDao(): StationResponseDao
    abstract fun trainDao(): TrainDao
    abstract fun lastStationsDao(): StationDao

    companion object {
        fun invoke(context: Context): TrainDatabase {
            return Room.databaseBuilder(context, TrainDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

