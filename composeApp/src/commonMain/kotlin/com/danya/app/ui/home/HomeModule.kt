package com.danya.app.ui.home

import org.koin.dsl.module

val homeModule = module {
    factory { HomeScreenModel() }
}