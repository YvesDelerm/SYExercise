package fr.ydelerm.sherpanyves.network

import dagger.Module
import dagger.Provides
import fr.ydelerm.sherpanyves.repository.MasterDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://jsonplaceholder.typicode.com/"

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun masterDataSource(apiService: ApiService): MasterDataSource {
        return NetworkDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}