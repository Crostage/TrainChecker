package com.crostage.trainchecker.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Класс поискового запроса, используется для перехода между фрагментами
 *
 * @property cityFrom город отправления
 * @property codeFrom код города отправления
 * @property cityTo город прибытия
 * @property codeTo код города прибытия
 * @property date дата отправления
 */
@Parcelize
class Search(
     val cityFrom: String,
     val codeFrom: Int,
     val cityTo: String,
     val codeTo: Int,
     val date: String,
) : Parcelable
