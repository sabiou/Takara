package dev.farouk.takara.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.farouk.takara.data.db.CandidateDao
import dev.farouk.takara.data.db.EventDao
import dev.farouk.takara.data.db.TakaraDatabase
import javax.inject.Singleton

/**
 * Created by Farouk on 20/12/2020.
 */
@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TakaraDatabase {
        return TakaraDatabase.getInstance(context)
    }

    @Provides
    fun provideCandidateDao(db: TakaraDatabase): CandidateDao {
        return db.candidateDao()
    }

    @Provides
    fun provideEventsDao(db: TakaraDatabase): EventDao {
        return db.eventDao()
    }
}