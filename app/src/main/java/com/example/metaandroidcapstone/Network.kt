package com.example.metaandroidcapstone

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


object NetworkHelper{
    val HttpClient = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("application","json"))
        }
    }
}

@Serializable
data class MenuNetwork(
    val menu:List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id:Int,
    @SerialName("title")
    val title:String,
    @SerialName("description")
    val description:String,
    @SerialName("price")
    val price:String,
    @SerialName("image")
    val image:String,
    @SerialName("category")
    val category:String
){
    fun networkToRoomMenu():MenuItemRoom = MenuItemRoom(
        id = id,
        title = title,
        description = description,
        price = price,
        image = image,
        category = category
    )
}