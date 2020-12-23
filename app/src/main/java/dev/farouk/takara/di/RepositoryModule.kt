package dev.farouk.takara.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import dev.farouk.takara.data.db.CandidateRepository
import javax.inject.Qualifier

/**
 * Created by Farouk on 20/12/2020.
 */
@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    /*@Provides
    fun localCandidatesRepository(repository: CandidateRepository): CandidateRepository {
        return repository
    }*/
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalRepository