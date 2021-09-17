package com.crostage.trainchecker.data.model.seat

/**
 * Модель ответа от сервера по вагона
 *
 * @property cars список вагонов [CarDto]
 */
data class CarResponse(
    val cars: List<CarDto>?,
)