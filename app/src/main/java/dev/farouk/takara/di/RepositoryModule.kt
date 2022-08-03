package dev.farouk.takara.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

/**
 * Created by Farouk on 20/12/2020.
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    /*@Provides
    fun localCandidatesRepository(repository: CandidateRepository): CandidateRepository {
        return repository
    }*/
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalRepository