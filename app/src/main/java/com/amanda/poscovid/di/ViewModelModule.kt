package com.amanda.poscovid.di

import com.amanda.poscovid.ui.viewModel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<CovidViewModel> { CovidViewModel(get()) }
    viewModel<NoticiaViewModel> { NoticiaViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get(), get(), get()) }
    viewModel<CadastrarPsicologoViewModel> { CadastrarPsicologoViewModel(get(), get()) }
    viewModel<AgendaPacienteViewModel> { AgendaPacienteViewModel(get()) }
    viewModel<SelecionaPsicologoViewModel> { SelecionaPsicologoViewModel(get()) }
    viewModel<HorarioViewModel> { HorarioViewModel(get()) }
    viewModel<SelecionaHorarioViewModel> { SelecionaHorarioViewModel(get(), get()) }
    viewModel<HomeViewModel> { HomeViewModel(get(), get()) }
    viewModel<AgendaPsicologoViewModel> { AgendaPsicologoViewModel(get()) }
}