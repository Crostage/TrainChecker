package com.crostage.trainchecker.data.model.rout.RouteResultVol2

import com.crostage.trainchecker.data.model.rout.TrainStop

data class RouteResultVol2(
    val GtExpress_Response: GtExpressResponse,
)

data class GtExpressResponse(
    val Routes: List<Route>,
)

data class Route(
    val Stop: List<TrainStop>,
)