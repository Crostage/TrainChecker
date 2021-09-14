package com.crostage.trainchecker.data.network.adapter

import com.crostage.trainchecker.data.model.rout.Response
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.internal.bind.TreeTypeAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.reflect.Type

//@RunWith(MockitoJUnitRunner::class)
class RouteResponseDeserializerTest {

//    lateinit var deserializer:RouteResponseDeserializer
//
//    val g = TreeTypeAdapter()
//
//    @Before
//    fun setUp() {
//         deserializer = RouteResponseDeserializer()
//       val x = Gson().toJson(JSON_RESPONSE)
//    }
//
//
//
//
//    @Test
//    fun testDeserialize() {
//        deserializer.deserialize()
//    }
//
//
//    companion object {
//        const val JSON_RESPONSE =
//            "{\"ReqExpressZK\":1994014,\"ReqLocalRecv\":\"14.09.2021 14:46:17\",\"ReqLocalSend\":\"14.09.2021 14:46:17\",\"ReqAddress\":\"MZD:5431\",\"Train\":{\"Route\":{\"Station\":[\"МОСКВА КАЗ\",\"СУХУМ\"],\"CodeFrom\":2000003,\"CodeTo\":4200300},\"Number\":\"306М\"},\"Version\":\"2.7.65\",\"Routes\":{\"Stop\":[{\"Station\":\"МОСКВА КАЗ\",\"Distance\":0,\"Days\":\"00\",\"DepTime\":\"19:50\",\"Code\":2000003},{\"ArvTime\":\"22:56\",\"Station\":\"РЯЗАНЬ 2\",\"Distance\":198,\"Days\":\"00\",\"WaitingTime\":\"0005\",\"DepTime\":\"23:01\",\"Code\":2000080},{\"ArvTime\":\"00:01\",\"Station\":\"КОРАБЛИНО\",\"Distance\":289,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"00:03\",\"Code\":2000090},{\"ArvTime\":\"00:24\",\"Station\":\"РЯЖСК 1\",\"Distance\":315,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"00:26\",\"Code\":2000056},{\"ArvTime\":\"01:31\",\"Station\":\"МИЧУРИН В\",\"Distance\":411,\"Days\":\"01\",\"WaitingTime\":\"0012\",\"DepTime\":\"01:43\",\"Code\":2014418},{\"ArvTime\":\"02:26\",\"Station\":\"ГРЯЗИ ВОР\",\"Distance\":471,\"Days\":\"01\",\"WaitingTime\":\"0005\",\"DepTime\":\"02:31\",\"Code\":2014390},{\"ArvTime\":\"03:38\",\"Station\":\"УСМАНЬ\",\"Distance\":523,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"03:40\",\"Code\":2014484},{\"ArvTime\":\"04:35\",\"Station\":\"ВОРОНЕЖ 1\",\"TailForward\":\"\",\"Distance\":587,\"Days\":\"01\",\"WaitingTime\":\"0045\",\"InvertingTrain\":\"\",\"DepTime\":\"05:20\",\"Code\":2014001},{\"ArvTime\":\"07:20\",\"Station\":\"ЛИСКИ\",\"TailForward\":\"\",\"Distance\":685,\"Days\":\"01\",\"WaitingTime\":\"0021\",\"DepTime\":\"07:41\",\"Code\":2014120},{\"ArvTime\":\"09:34\",\"Station\":\"РОССОШЬ\",\"TailForward\":\"\",\"Distance\":801,\"Days\":\"01\",\"WaitingTime\":\"0030\",\"DepTime\":\"10:04\",\"Code\":2014540},{\"ArvTime\":\"11:11\",\"Station\":\"ЗАЙЦЕВКА\",\"TailForward\":\"\",\"Distance\":880,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"11:13\",\"Code\":2014144},{\"ArvTime\":\"11:52\",\"Station\":\"КУТЕЙНИКОВО\",\"TailForward\":\"\",\"Distance\":929,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"11:54\",\"Code\":2064668},{\"ArvTime\":\"12:44\",\"Station\":\"МИЛЛЕРОВО\",\"TailForward\":\"\",\"Distance\":995,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"12:46\",\"Code\":2064375},{\"ArvTime\":\"13:58\",\"Station\":\"КАМЕНСКАЯ\",\"TailForward\":\"\",\"Distance\":1066,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"14:00\",\"Code\":2064570},{\"ArvTime\":\"14:25\",\"Station\":\"ЛИХАЯ\",\"TailForward\":\"\",\"Distance\":1090,\"Days\":\"01\",\"WaitingTime\":\"0014\",\"DepTime\":\"14:39\",\"Code\":2064605},{\"ArvTime\":\"15:05\",\"Station\":\"ЗВЕРЕВО\",\"TailForward\":\"\",\"Distance\":1114,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"15:07\",\"Code\":2064185},{\"ArvTime\":\"15:30\",\"Station\":\"СУЛИН\",\"TailForward\":\"\",\"Distance\":1133,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"15:32\",\"Code\":2064215},{\"ArvTime\":\"16:08\",\"Station\":\"ШАХТНАЯ\",\"TailForward\":\"\",\"Distance\":1162,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"16:10\",\"Code\":2064201},{\"ArvTime\":\"16:51\",\"Station\":\"НОВОЧЕРКАС\",\"TailForward\":\"\",\"Distance\":1203,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"DepTime\":\"16:53\",\"Code\":2064230},{\"ArvTime\":\"17:47\",\"Station\":\"РОСТОВ ГЛ\",\"TailForward\":\"\",\"Distance\":1254,\"Days\":\"01\",\"WaitingTime\":\"0020\",\"DepTime\":\"18:07\",\"Code\":2064001},{\"ArvTime\":\"19:29\",\"Station\":\"СТАРОМИН Т\",\"TailForward\":\"\",\"Distance\":1360,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"InvertingTrain\":\"\",\"DepTime\":\"19:31\",\"Code\":2064235},{\"ArvTime\":\"20:10\",\"Station\":\"КАНЕВСКАЯ\",\"TailForward\":\"\",\"Distance\":1411,\"Days\":\"01\",\"WaitingTime\":\"0002\",\"InvertingTrain\":\"\",\"DepTime\":\"20:12\",\"Code\":2064245},{\"ArvTime\":\"22:03\",\"Station\":\"КРАСНОДАР1\",\"TailForward\":\"\",\"Distance\":1538,\"Days\":\"01\",\"WaitingTime\":\"0014\",\"InvertingTrain\":\"\",\"DepTime\":\"22:17\",\"Code\":2064800},{\"ArvTime\":\"00:04\",\"Station\":\"ГОР КЛЮЧ\",\"TailForward\":\"\",\"Distance\":1603,\"Days\":\"02\",\"WaitingTime\":\"0044\",\"InvertingTrain\":\"\",\"DepTime\":\"00:48\",\"Code\":2064057},{\"ArvTime\":\"02:18\",\"Station\":\"ТУАПСЕ ПАС\",\"TailForward\":\"\",\"Distance\":1687,\"Days\":\"02\",\"WaitingTime\":\"0012\",\"InvertingTrain\":\"\",\"DepTime\":\"02:30\",\"Code\":2064140},{\"ArvTime\":\"03:09\",\"Station\":\"ЛАЗАРЕВСК\",\"TailForward\":\"\",\"Distance\":1716,\"Days\":\"02\",\"WaitingTime\":\"0010\",\"InvertingTrain\":\"\",\"DepTime\":\"03:19\",\"Code\":2064030},{\"ArvTime\":\"04:03\",\"Station\":\"ЛОО\",\"TailForward\":\"\",\"Distance\":1749,\"Days\":\"02\",\"WaitingTime\":\"0003\",\"InvertingTrain\":\"\",\"DepTime\":\"04:06\",\"Code\":2064020},{\"ArvTime\":\"04:32\",\"Station\":\"СОЧИ\",\"TailForward\":\"\",\"Distance\":1767,\"Days\":\"02\",\"WaitingTime\":\"0007\",\"InvertingTrain\":\"\",\"DepTime\":\"04:39\",\"Code\":2064130},{\"ArvTime\":\"04:58\",\"Station\":\"ХОСТА\",\"TailForward\":\"\",\"Distance\":1782,\"Days\":\"02\",\"WaitingTime\":\"0002\",\"InvertingTrain\":\"\",\"DepTime\":\"05:00\",\"Code\":2064010},{\"ArvTime\":\"05:11\",\"Station\":\"АДЛЕР\",\"TailForward\":\"\",\"Distance\":1790,\"Days\":\"02\",\"WaitingTime\":\"0030\",\"InvertingTrain\":\"\",\"DepTime\":\"05:41\",\"Code\":2064150},{\"ArvTime\":\"05:55\",\"Station\":\"ИМЕР КУРОРТ\",\"TailForward\":\"\",\"Distance\":1798,\"Days\":\"02\",\"WaitingTime\":\"0002\",\"InvertingTrain\":\"\",\"DepTime\":\"05:57\",\"Code\":2064308},{\"ArvTime\":\"06:03\",\"Station\":\"ВЕСЕЛОЕ\",\"TailForward\":\"\",\"Distance\":1800,\"Days\":\"02\",\"WaitingTime\":\"0060\",\"InvertingTrain\":\"\",\"DepTime\":\"07:03\",\"Code\":2064094},{\"Station\":\"ВЕСЕЛОЕ(ГР)\",\"TailForward\":\"\",\"Distance\":1802,\"InvertingTrain\":\"\",\"Sign\":\"ТАРИФНАЯ ГРАНИЦА\",\"Code\":\"0042201\"},{\"ArvTime\":\"07:34\",\"Station\":\"ЦАНДРЫПШ\",\"TailForward\":\"\",\"Distance\":1811,\"Days\":\"02\",\"WaitingTime\":\"0030\",\"InvertingTrain\":\"\",\"DepTime\":\"08:04\",\"Code\":4200377},{\"ArvTime\":\"08:34\",\"Station\":\"ГАГРА\",\"TailForward\":\"\",\"Distance\":1832,\"Days\":\"02\",\"WaitingTime\":\"0002\",\"InvertingTrain\":\"\",\"DepTime\":\"08:36\",\"Code\":4200379},{\"ArvTime\":\"09:44\",\"Station\":\"ГУДАУТА\",\"TailForward\":\"\",\"Distance\":1870,\"Days\":\"02\",\"WaitingTime\":\"0002\",\"InvertingTrain\":\"\",\"DepTime\":\"09:46\",\"Code\":4200360},{\"ArvTime\":\"10:14\",\"Station\":\"НОВЫЙ АФОН\",\"TailForward\":\"\",\"Distance\":1889,\"Days\":\"02\",\"WaitingTime\":\"0002\",\"InvertingTrain\":\"\",\"DepTime\":\"10:16\",\"Code\":4200550},{\"ArvTime\":\"10:43\",\"Station\":\"СУХУМ\",\"TailForward\":\"\",\"Distance\":1905,\"Days\":\"02\",\"InvertingTrain\":\"\",\"Code\":4200300}],\"Title\":\"ОСНОВНОЙ МАРШРУТ\",\"Route\":{\"Station\":[\"МОСКВА КАЗ\",\"СУХУМ\"],\"CodeFrom\":2000003,\"CodeTo\":4200300}},\"Type\":\"TrainRoute\",\"ReqExpressDateTime\":\"14.09.2021 00:00\"}"
//    }
}



