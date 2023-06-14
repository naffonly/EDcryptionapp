package org.d3if0012.edcryptionapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/"+"naffonly/EDcryptionapp/assessment03/static-api/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object ArticleApi {
    val service: ArtikelApiService by lazy {
        retrofit.create(ArtikelApiService::class.java)
    }
}

interface ArtikelApiService{
    @GET("about_encrytion.json")
    suspend fun getArticle(): String

}