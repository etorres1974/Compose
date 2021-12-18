package br.com.vendas

import android.app.Application
import br.com.vendas.di.androidModule
import br.com.vendas.di.repositories
import br.com.vendas.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() = startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@MyApplication)
        modules(androidModule())
    }

}