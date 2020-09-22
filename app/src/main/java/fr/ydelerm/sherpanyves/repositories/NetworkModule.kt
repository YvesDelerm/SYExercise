package fr.ydelerm.sherpanyves.repositories

import dagger.Module
import dagger.Provides
import fr.ydelerm.sherpanyves.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://jsonplaceholder.typicode.com/"

@Module
class NetworkModule {

    @Provides
    fun repository(apiService: ApiService): Repository {
        return NetworkRepositoryImpl(apiService)
    }

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}