package com.crostage.trainchecker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.crostage.trainchecker.ConstForTest.Companion.FAVOURITE_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.LIST_FAVOURITE_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Train
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrainRepositoryTest {
    private val trainDao: TrainDao = mockk()
    private val converter: IConverter<FavouriteEntity, Train> = mockk()
    private val listConverter: IConverter<List<FavouriteEntity>, List<Train>> = mockk()
    private val liveDataTran: LiveData<List<Train>> = mockk()

    private lateinit var repository: TrainRepository

    @Before
    fun setUp() {
        repository = TrainRepository(trainDao, converter, listConverter)
    }

    @Test
    fun testGetFavouriteLiveData() {
        every {
            trainDao.getFavouriteLiveData()
        } returns MutableLiveData(LIST_FAVOURITE_ENTITY)

        every { listConverter.convert(LIST_FAVOURITE_ENTITY) } returns LIST_TRAIN


        every {
            MutableLiveData(LIST_FAVOURITE_ENTITY).map {
                listConverter.convert(it)
            }
        } returns liveDataTran

        val liveData = repository.getFavouriteLiveData()

        assert(liveData == liveDataTran)

    }


    @Test
    fun testInsertFavourite() {
        every { converter.revers(TRAIN) } returns FAVOURITE_ENTITY
        every { trainDao.insertFavourite(FAVOURITE_ENTITY) } just Runs

        repository.insertFavourite(TRAIN)

        verify { trainDao.insertFavourite(FAVOURITE_ENTITY) }
    }

    @Test
    fun testRemoveFavourite() {
        every { converter.revers(TRAIN) } returns FAVOURITE_ENTITY
        every { trainDao.removeFavourite(FAVOURITE_ENTITY) } just Runs

        repository.removeFavourite(TRAIN)

        verify { trainDao.removeFavourite(FAVOURITE_ENTITY) }
    }

}