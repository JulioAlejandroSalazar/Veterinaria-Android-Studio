package com.example.data.remote

object VeterinarioImageClient {

    private const val BASE_IMAGE_URL = "https://randomuser.me/api/portraits/"

    fun getImageUrl(id: Int): String {
        val genero = if (id % 2 == 0) "men" else "women"
        val numero = (id % 99) + 1
        return "$BASE_IMAGE_URL$genero/$numero.jpg"
    }
}
