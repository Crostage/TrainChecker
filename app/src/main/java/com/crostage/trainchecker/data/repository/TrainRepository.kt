package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.converter.IConverter
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.domain.repository.ITrainRepository
import com.crostage.trainchecker.data.model.train.TrainEntity
import com.crostage.trainchecker.domain.model.Train
import javax.inject.Inject

class TrainRepository @Inject constructor(
    private val trainDao: TrainDao,
    private val converter: IConverter<FavouriteEntity, Train>,
) : ITrainRepository {

    override fun getFavouriteLiveData(): LiveData<List<Train>> {
        val trainEntityLiveData: LiveData<List<FavouriteEntity>> = trainDao.getFavouriteLiveData()
        return Transformations.map(trainEntityLiveData) { list ->
            list.map { converter.convert(it) }
        }
    }

    override fun getFavouriteList(): List<Train> {
        return trainDao.getFavouriteList().map { converter.convert(it) }
    }

    override fun insertTrain(train: Train) {
        trainDao.insertTrain(converter.revers(train))
    }

    override fun removeTrain(train: Train) {
        trainDao.removeTrain(converter.revers(train))
    }


}