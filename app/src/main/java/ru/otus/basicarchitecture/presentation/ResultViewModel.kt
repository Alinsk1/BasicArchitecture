package ru.otus.basicarchitecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.otus.basicarchitecture.domain.UserAddress
import ru.otus.basicarchitecture.domain.UserName
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val wizardCache: WizardCache
): ViewModel() {

    private var _userName = MutableLiveData<UserName>()
    val userName: LiveData<UserName>
        get() = _userName

    private var _userAddress = MutableLiveData<UserAddress>()
    val userAddress: LiveData<UserAddress>
        get() = _userAddress

    private var _interests = MutableLiveData<List<String>>()
    val interests: LiveData<List<String>>
        get() = _interests

    fun loadUserInfo(){
        loadUserName()
        loadUserAddress()
        loadInterests()
    }

    private fun loadUserName(){
        _userName.value = wizardCache.userName
    }

    private fun loadUserAddress(){
        _userAddress.value = wizardCache.userAddress
    }

    private fun loadInterests(){
        _interests.value = wizardCache.interests
    }
}