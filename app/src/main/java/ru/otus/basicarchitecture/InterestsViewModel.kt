package ru.otus.basicarchitecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InterestsViewModel @Inject constructor(
    private val wizardCache: WizardCache
): ViewModel() {

    private var _canContinue = MutableLiveData<Boolean>()
    val canContinue: LiveData<Boolean>
        get() = _canContinue

    private var _listOfInterests = MutableLiveData<List<String>>()
    val listOfInterests: LiveData<List<String>>
        get() = _listOfInterests

    private val interests = listOf(
        "Reading", "Music", "Movies", "Travel", "Cooking",
        "Sports", "Technology", "Photography", "Art", "Fashion",
        "Fitness", "Gaming", "Science", "History", "Nature",
        "Animals", "Books", "Programming", "Design", "Dance",
        "Food", "Cars", "Space", "Education", "Politics",
        "Health", "Business", "Finance", "Writing", "Comedy"
    )

    fun checkInterests(){
        if (wizardCache.interests.size <= 0){
            _canContinue.value = false
        } else {
            _canContinue.value = true
        }
    }

    fun setInterests(interests: List<String>){
        wizardCache.interests = interests
    }

    fun loadListOfInterests(){
        _listOfInterests.value = interests
    }
}