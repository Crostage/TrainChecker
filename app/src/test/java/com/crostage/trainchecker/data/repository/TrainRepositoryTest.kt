package com.crostage.trainchecker.data.repository

import com.crostage.trainchecker.data.converter.ConverterConst.Companion.FAVOURITE_ENTITY
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.data.network.TestNetworkConst.Companion.TRAIN
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

    private lateinit var repository: TrainRepository

    @Before
    fun setUp() {
        repository = TrainRepository(trainDao, converter, listConverter)
    }

//    @Test
//    fun testGetFavouriteLiveData(){
//        //todo
//        every {
//            trainDao.getFavouriteLiveData()
//        } returns MutableLiveData(listOf(FAVOURITE_ENTITY))
//
//        every { listConverter.convert(listOf(FAVOURITE_ENTITY)) } returns listOf(TRAIN)
//        mockkClass(Transformations::class)
//        every { Transformations.map(MutableLiveData(listOf(FAVOURITE_ENTITY))){
//            listConverter.convert(it)
//        } } returns MutableLiveData(listOf(TRAIN))
//
//       val liveData = repository.getFavouriteLiveData()
//
//        assert(liveData==MutableLiveData(listOf(TRAIN)))
//
//    }

    @Test
    fun testGetFavouriteList() {
        every { trainDao.getFavouriteList() } returns listOf(FAVOURITE_ENTITY)
        every { listConverter.convert(listOf(FAVOURITE_ENTITY)) } returns listOf(TRAIN)
        val list = repository.getFavouriteList()
        assert(list == listOf(TRAIN))
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