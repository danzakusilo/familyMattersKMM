package com.danya.app.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module

internal val mainModule = module {

}


val ktorClientModule = module {
    single<HttpClient> {
        HttpClient {
            install(Logging)
            install(ContentNegotiation)
        }
    }
}