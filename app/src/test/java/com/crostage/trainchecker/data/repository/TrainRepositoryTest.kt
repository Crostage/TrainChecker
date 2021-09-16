package com.crostage.trainchecker.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.crostage.trainchecker.ConstForTest.Companion.FAVOURITE_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.LIST_FAVOURITE_ENTITY
import com.crostage.trainchecker.ConstForTest.Companion.LIST_TRAIN
import com.crostage.trainchecker.ConstForTest.Companion.TRAIN
import com.crostage.trainchecker.data.db.dao.TrainDao
import com.crostage.trainchecker.data.model.train.FavouriteEntity
import com.crostage.trainchecker.domain.converter.IConverter
import com.crostage.trainchecker.domain.model.Train
import io.mockk.*
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrainRepositoryTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val trainDao: TrainDao = mockk()
    private val converter: IConverter<FavouriteEntity, Train> = mockk()
    private val listConverter: IConverter<List<FavouriteEntity>, List<Train>> = mockk()

    private val favourites: Observer<List<Train>> = mockk()

    private lateinit var repository: TrainRepository

    @Before
    fun setUp() {
        repository = TrainRepository(trainDao, converter, listConverter)
    }

    @Test
    fun testGetFavouriteLiveData() {

        every { favourites.onChanged(any()) } just Runs

        mockkStatic(LiveData::class)
        every {
            trainDao.getFavouriteLiveData()
        } returns MutableLiveData(LIST_FAVOURITE_ENTITY)

        every { listConverter.convert(LIST_FAVOURITE_ENTITY) } returns LIST_TRAIN

        repository.getFavouriteLiveData().observeForever(favourites)

        verifySequence {
            trainDao.getFavouriteLiveData()
            listConverter.convert(LIST_FAVOURITE_ENTITY)
            favourites.onChanged(LIST_TRAIN)
        }

    }

    private val obser: Observable<List<FavouriteEntity>> = Observable.just(LIST_FAVOURITE_ENTITY)

    @Test
    fun testGetFavouriteObservable() {
        every { trainDao.getFavouriteObservable() } returns obser
        every { listConverter.convert(LIST_FAVOURITE_ENTITY) } returns LIST_TRAIN

        val result = repository.getFavouriteObservable().blockingFirst()

        assert(result == LIST_TRAIN)
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