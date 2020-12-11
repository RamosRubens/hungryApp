package br.com.receitas.module

import android.content.Context
import androidx.room.Room
import br.com.receitas.repository.local.database.HungryDatabase
import br.com.receitas.repository.network.service.ReceitaService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HungryModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HungryAppUrl

    @Provides
    fun provideRetrofit(@HungryAppUrl baseUrl: String): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        val gsonConfig = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideHungryRetrofitService(retrofit: Retrofit): ReceitaService = retrofit.create(ReceitaService::class.java)

    @HungryAppUrl
    @Provides
    fun provideBaseUrl() = "https://projeto-receitas-app.herokuapp.com/api/"

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HungryDatabase =
        Room.databaseBuilder(context, HungryDatabase::class.java, "HungryDB")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideHungryDAO(db: HungryDatabase) = db.ReceitasDAO()
}
