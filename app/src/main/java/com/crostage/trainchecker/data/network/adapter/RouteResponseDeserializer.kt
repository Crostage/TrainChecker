package com.crostage.trainchecker.data.network.adapter

import com.crostage.trainchecker.data.model.rout.Response
import com.crostage.trainchecker.data.model.rout.Routes
import com.crostage.trainchecker.data.model.rout.RoutesError
import com.google.gson.*
import java.lang.reflect.Type


class RouteResponseDeserializer : JsonDeserializer<Response> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): Response? {

        val errorElement = json?.asJsonObject?.get("Error")
        if (errorElement != null) {
            val error: RoutesError? = context?.deserialize(
                errorElement.asJsonObject,
                RoutesError::class.java
            )
            return Response(mutableListOf(), error)

        }

        val routesElement = json?.asJsonObject?.get("Routes")
        if (routesElement != null) {
            if (routesElement.isJsonArray) {

                val route: Array<Routes>? =
                    context?.deserialize(
                        routesElement.asJsonArray,
                        Array<Routes>::class.java
                    )

                return route?.get(0)?.routList?.let { Response(it, null) }

            } else if (routesElement.isJsonObject) {

                val routes: Routes? = context?.deserialize(
                    routesElement.asJsonObject, Routes::class.java
                )

                return routes?.routList?.let { Response(it, null) }
            }
        }

        return Response(mutableListOf(), null)
    }


}