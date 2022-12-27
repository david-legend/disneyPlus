package com.davidcobbina.disneyplus.ui.screens.home_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.PRINCESS_AVATAR_CATEGORY
import com.davidcobbina.disneyplus.data.repositories.MoviesRepository
import com.davidcobbina.disneyplus.data.remote.model.Movie
import com.davidcobbina.disneyplus.data.local.model.AvatarCategory
import com.davidcobbina.disneyplus.data.local.model.AvatarProfile
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.data.repositories.AuthRepository
import com.davidcobbina.disneyplus.data.repositories.LocalResourcesRepository
import com.davidcobbina.disneyplus.navigation.USER_PROFILE_ID_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TRENDING = "8230787"
const val STAR_WARS = "8230783"
const val MARVEL = "8230785"
const val DISNEY = "8229678"
const val DEFAULT_SELECTED_CATEGORY = PRINCESS_AVATAR_CATEGORY

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val localResourcesRepository: LocalResourcesRepository,
    moviesRepository: MoviesRepository,

    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    private val profileId = checkNotNull(savedStateHandle.get<Int>(USER_PROFILE_ID_ARGUMENT))

    private val homeScreenEventChannel = Channel<HomeScreenEvent>()
    val homeScreenEvents = homeScreenEventChannel.receiveAsFlow()

    private val _userProfile =
        MutableStateFlow(UserProfile(avatar = R.drawable.image_placeholder))
    val userProfile: StateFlow<UserProfile> get() = _userProfile


    private val _avatars = MutableStateFlow(emptyList<AvatarProfile>())
    val avatars: StateFlow<List<AvatarProfile>> get() = _avatars

    private val _avatarCategories = MutableStateFlow(emptyList<AvatarCategory>())
    val avatarCategories: StateFlow<List<AvatarCategory>> get() = _avatarCategories

    private val _avatarProfileMap = MutableStateFlow(emptyMap<String, List<AvatarProfile>>())

    init {
        viewModelScope.launch {

            _avatarProfileMap.value = localResourcesRepository.getProfiles()
            _avatars.value = _avatarProfileMap.value[DEFAULT_SELECTED_CATEGORY]!!
            _avatarCategories.value = _avatarProfileMap.value.keys.map {
                AvatarCategory(
                    title = it,
                    isSelected = it == DEFAULT_SELECTED_CATEGORY
                )
            }
        }
    }

    private val _recommendedMoviesLoading = MutableStateFlow(false)
    val recommendedMoviesLoading: StateFlow<Boolean>
        get() = _recommendedMoviesLoading

    private val _recommendedMovies = MutableStateFlow(emptyList<Movie>())
    val recommendedMovies: StateFlow<List<Movie>>
        get() = _recommendedMovies

    private val _marvelMoviesLoading = MutableStateFlow(false)
    val marvelMoviesLoading: StateFlow<Boolean>
        get() = _marvelMoviesLoading


    private val _marvelMovies = MutableStateFlow(emptyList<Movie>())
    val marvelMovies: StateFlow<List<Movie>>
        get() = _marvelMovies

    private val _starWarsMoviesLoading = MutableStateFlow(false)
    val starWarsMoviesLoading: StateFlow<Boolean>
        get() = _starWarsMoviesLoading

    private val _starWarsMovies = MutableStateFlow(emptyList<Movie>())
    val starWarsMovies: StateFlow<List<Movie>>
        get() = _starWarsMovies

    private val _trendingMoviesLoading = MutableStateFlow(false)
    val trendingMoviesLoading: StateFlow<Boolean>
        get() = _trendingMoviesLoading

    private val _trendingMovies = MutableStateFlow(emptyList<Movie>())
    val trendingMovies: StateFlow<List<Movie>>
        get() = _trendingMovies

    init {
        viewModelScope.launch {
            _recommendedMoviesLoading.value = true
            _marvelMoviesLoading.value = true
            _starWarsMoviesLoading.value = true
            _trendingMoviesLoading.value = true

            _userProfile.value = authRepository.getUserProfile(profileId)

            val rMovies = moviesRepository.getMovieFeed(DISNEY)
            _recommendedMovies.value = rMovies
            _recommendedMoviesLoading.value = false

            _marvelMovies.value = moviesRepository.getMovieFeed(MARVEL)
            _marvelMoviesLoading.value = false

            _starWarsMovies.value = moviesRepository.getMovieFeed(STAR_WARS)
            _starWarsMoviesLoading.value = false

            _trendingMovies.value = moviesRepository.getMovieFeed(TRENDING)
            _trendingMoviesLoading.value = false
        }
    }

    fun updateCategory(category: String) {
        _avatars.value = _avatarProfileMap.value[category]!!
        val updatedCategories = arrayListOf<AvatarCategory>()
        for (cat in _avatarCategories.value) {
            updatedCategories.add(cat.copy(isSelected = category == cat.title))
        }
        _avatarCategories.value = updatedCategories
    }

    fun updateProfile(avatar: Int) {
        viewModelScope.launch {
            _userProfile.update { profile -> profile.copy(avatar = avatar) }
            authRepository.insertProfile(_userProfile.value)
        }
    }

    fun onCategoryChanged(category: String) = viewModelScope.launch {
        homeScreenEventChannel.send(HomeScreenEvent.ChangeAvatarCategory(category))
    }

    fun onAvatarUpdate(avatar: Int) = viewModelScope.launch {
        homeScreenEventChannel.send(HomeScreenEvent.UpdateAvatar(avatar))
    }

    sealed class HomeScreenEvent {
        data class ChangeAvatarCategory(val category: String) : HomeScreenEvent()
        data class UpdateAvatar(val avatar: Int) : HomeScreenEvent()
    }
}