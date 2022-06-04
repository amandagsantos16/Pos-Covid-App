package com.amanda.poscovid.di

import com.amanda.poscovid.ui.viewModel.CovidViewModel
import com.amanda.poscovid.ui.viewModel.NoticiaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<CovidViewModel> { CovidViewModel(get()) }
    viewModel<NoticiaViewModel> { NoticiaViewModel(get()) }
}