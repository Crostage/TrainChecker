import java.io.Serializable

data class Seat(
    val freeSeats: Int, //свободные места
    val tariff: Int, //тариф в рублях
    val type: String, //тип места сокращенный
    val typeLoc: String, //тип места полный
) : Serializable