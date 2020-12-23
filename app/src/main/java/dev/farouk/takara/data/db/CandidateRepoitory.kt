package dev.farouk.takara.data.db

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Farouk on 20/12/2020.
 */
@Singleton
class CandidateRepository @Inject constructor(private val dao: CandidateDao) {
    fun getCandidates() = dao.getCandidates()
}