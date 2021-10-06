package com.pasukanlangit.id.cleanarch_ktorclient.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.ApiService
import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.sharedpref.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMasterKey(@ApplicationContext context: Context) : MasterKey {
        return MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context, masterKey: MasterKey) : SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "mysharedpref",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideSharedPrefDataSource(sharedPreferences: SharedPreferences) = SharedPref(sharedPreferences)

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        }

    @Provides
    @Singleton
    fun provideApiService(httpClient: HttpClient,@ApplicationContext context: Context) =
        ApiService(httpClient, context)
}