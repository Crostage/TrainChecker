package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Train
import com.crostage.trainchecker.domain.repository.ITrainRepository
import javax.inject.Inject

class TrainRepository @Inject constructor(
    private val trainDao: TrainDao,
    private val converter: IConverter<FavouriteEntity, Train>,
    private val listConverter: IConverter<List<FavouriteEntity>, List<Train>>,
) : ITrainRepository {

    override fun getFavouriteLiveData(): LiveData<List<Train>> {
        val trainEntityLiveData: LiveData<List<FavouriteEntity>> = trainDao.getFavouriteLiveData()
        return trainEntityLiveData.map {
            listConverter.convert(it)
        }

    }

    override fun getFavouriteList(): List<Train> {
        return listConverter.convert(trainDao.getFavouriteList())
    }

    override fun insertFavourite(train: Train) {
        trainDao.insertFavourite(converter.revers(train))
    }

    override fun removeFavourite(train: Train) {
        trainDao.removeFavourite(converter.revers(train))
    }


}