package com.crostage.trainchecker.data.network.adapter

import com.crostage.trainchecker.model.data.rout.RoutesError
import com.crostage.trainchecker.model.data.rout.Response
import com.crostage.trainchecker.model.data.rout.Routes
import com.crostage.trainchecker.utils.ErrorConnections
import com.crostage.trainchecker.utils.ServerSendError
import com.google.gson.*
import java.lang.reflect.Type


class ResponseDeserializer : JsonDeserializer<Response> {

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
            throw ServerSendError(error?.content)

        }

        val routesElement = json?.asJsonObject?.get("Routes")
        if (routesElement != null) {
            if (routesElement.isJsonArray) {

                val route: Array<Routes>? =
                    context?.deserialize(
                        routesElement.asJsonObject,
                        Array<Routes>::class.java
                    )

                return route?.get(0)?.routList?.let { Response(it) }

            } else if (routesElement.isJsonObject) {

                val routes: Routes? = context?.deserialize(
                    routesElement.asJsonObject, Routes::class.java
                )

                return routes?.routList?.let { Response(it) }
            }
        }

        return Response(mutableListOf())
    }


}