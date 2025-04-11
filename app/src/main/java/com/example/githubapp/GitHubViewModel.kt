package com.example.githubapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val fetchUserReposUseCase: FetchUserReposUseCase
) : ViewModel() {

    private val _repos = MutableStateFlow<List<Repo>>(emptyList())
    val repos: StateFlow<List<Repo>> = _repos.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadRepos(username: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null // Clear previous error
            try {
                Log.d("GitHubViewModel", "Fetching repos for: $username")
                val result = fetchUserReposUseCase(username)
                Log.d("GitHubViewModel", "Fetched ${result.size} repos")
                _repos.value = result
                if (result.isEmpty()) {
                    _error.value = "No repositories found for \"$username\""
                }
            } catch (e: Exception) {
                Log.e("GitHubViewModel", "Error loading repos: ${e.message}", e)
                _repos.value = emptyList()
                _error.value = "Failed to load repositories: ${e.localizedMessage}"
            } finally {
                _loading.value = false
            }
        }
    }
}
