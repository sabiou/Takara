package dev.farouk.takara.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.farouk.takara.data.db.CandidateRepository
import dev.farouk.takara.data.model.Candidate

/**
 * Created by Farouk on 19/12/2020.
 */
class CandidatesViewModel @ViewModelInject constructor(
    candidateRepository: CandidateRepository
): ViewModel() {
    // candidates
    val candidates: LiveData<List<Candidate>> = candidateRepository.getCandidates().asLiveData()
}