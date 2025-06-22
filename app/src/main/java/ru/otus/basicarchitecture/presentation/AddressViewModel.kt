package ru.otus.basicarchitecture.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.domain.AddressRepository
import ru.otus.basicarchitecture.domain.AddressSuggestUseCase
import ru.otus.basicarchitecture.domain.UserAddress
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val wizardCache: WizardCache,
    private val addressRepository: AddressRepository
): ViewModel() {

    private val addressSuggestUseCase = AddressSuggestUseCase(addressRepository)

    private var _canContinue = MutableLiveData<Boolean>(false)
    val canContinue: LiveData<Boolean>
        get() = _canContinue

    private var _listUserAddress = MutableLiveData<List<UserAddress>>()
    val listUserAddress: LiveData<List<UserAddress>>
        get() = _listUserAddress

    private var _errorNetwork = MutableLiveData<Boolean>()
    val errorNetwork: LiveData<Boolean>
        get() = _errorNetwork

    private var _errorEmptyAddress = MutableLiveData<Boolean>()
    val errorEmptyAddress: LiveData<Boolean>
        get() = _errorEmptyAddress

    fun validateData() {
        var successful = true
        successful = checkEmptyFields()
        if (successful == false){
            _canContinue.value = false
            return
        }
        _canContinue.value = true
    }

    fun setAddress(fullAddress: String) {
        wizardCache.userAddress.fullAddress = fullAddress
    }

    fun searchAddress(query: String) {
        viewModelScope.launch {
            try {
                val result = addressSuggestUseCase.invoke(query)
                _listUserAddress.postValue(result)
                Log.d("AddressViewModel", result.toString())
            } catch (e: Exception) {
                _errorNetwork.postValue(true)
            }
        }
    }


    private fun checkEmptyFields(): Boolean{
        var successful = true
        if (wizardCache.userAddress.fullAddress == ""){
            _errorEmptyAddress.value = true
            successful = false
        } else{
            _errorEmptyAddress.value = false
        }
        return successful
    }
}