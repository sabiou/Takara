package dev.farouk.takara.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.farouk.takara.data.preferences.PrefStore
import dev.farouk.takara.data.preferences.PrefStoreImpl

/**
 * Created by Farouk on 28/12/2020.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class DataStoreModule {
    @Binds
    abstract fun bindDataStore(prefStoreImpl: PrefStoreImpl): PrefStore
}