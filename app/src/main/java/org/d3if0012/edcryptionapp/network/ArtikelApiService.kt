package org.d3if0012.edcryptionapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0012.edcryptionapp.model.Article
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://raw.githubusercontent.com/" + "naffonly/EDcryptionapp/assessment03/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object ArticleApi {
    val service: ArtikelApiService by lazy {
        retrofit.create(ArtikelApiService::class.java)
    }

    fun getImgUrl(imageId: String): String {
        return "$BASE_URL$imageId.png"
    }


}


interface ArtikelApiService {
    @GET("about_encrytion.json")

    suspend fun getArticle(): List<Article>

}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
