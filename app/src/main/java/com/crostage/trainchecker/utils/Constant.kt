package com.crostage.trainchecker.utils

/**
 * Класс с констанами приложения
 *
 */
class Constant {
    companion object {

        const val BASE_URL = "https://pass.rzd.ru/"
        const val TRAIN_ARG = "TRAIN_ARG"
        const val STATION_RESULT = "STATION"
        const val STATION_FROM_REQUEST_KEY = "STATION_FROM_REQUEST_KEY"
        const val STATION_TO_REQUEST_KEY = "STATION_TO_REQUEST_KEY"
        const val TRAIN_LAYER_ID = 5827
        const val ROUTE_LAYER_ID = 5804
        const val SEAT_LAYER_ID = 5764
        const val TAB_ITEM_COUNT = 2
        const val HEADER_PRAGMA = "Pragma"
        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val CACHE_SIZE = 5 * 1024 * 1024
        const val CACHE_CHILD = "HttpCache"

        const val DB_NAME = "stations_trains.db"
        const val TABLE_NAME_FAVOURITES = "favourites"
        const val TABLE_NAME_STATION_SEARCH = "station_search"
        const val TABLE_NAME_LAST_PICK = "last_pick_stations"

        const val FAVOURITE = "FAVOURITE"

        const val SAVED_STATE_TRAINS = "SAVED_STATE_TRAINS"
        const val SAVED_STATE_STOPS = "SAVED_STATE_STOPS"
        const val SAVED_STATE_CARS = "SAVED_STATE_CARS"
        const val SAVED_STATE_STATIONS = "SAVED_STATE_STATIONS"
        const val SEARCH_SAVED_STATE_FROM = "SEARCH_SAVED_STATE_FROM"
        const val SEARCH_SAVED_STATE_TO = "SEARCH_SAVED_STATE_TO"
        const val SEARCH_SAVED_STATE_DATE = "SEARCH_SAVED_STATE_DATE"

    }

}