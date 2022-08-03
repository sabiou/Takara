package dev.farouk.takara.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.farouk.takara.data.db.CandidateRepository
import dev.farouk.takara.data.model.Candidate
import javax.inject.Inject

/**
 * Created by Farouk on 19/12/2020.
 */
@HiltViewModel
class CandidatesViewModel @Inject constructor(
    candidateRepository: CandidateRepository
): ViewModel() {
    // candidates
    val candidates: LiveData<List<Candidate>> = candidateRepository.getCandidates().asLiveData()
}