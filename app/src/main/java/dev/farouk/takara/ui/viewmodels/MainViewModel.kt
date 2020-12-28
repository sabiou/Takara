package dev.farouk.takara.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dev.farouk.takara.data.preferences.PrefStore
import kotlinx.coroutines.launch

/**
 * Created by Farouk on 27/12/2020.
 */
class MainViewModel @ViewModelInject constructor(private val prefStore: PrefStore): ViewModel() {
    val darkThemeEnabled = prefStore.isNightMode().asLiveData()

    fun toggleNightMode() {
        viewModelScope.launch {
            prefStore.toggleNightMode()
        }
    }
}