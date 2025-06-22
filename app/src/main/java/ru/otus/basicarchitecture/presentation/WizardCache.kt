package ru.otus.basicarchitecture.presentation

import ru.otus.basicarchitecture.domain.UserAddress
import ru.otus.basicarchitecture.domain.UserName
import javax.inject.Inject

class WizardCache @Inject constructor(){
    var userName: UserName = UserName("", "", "")
    var userAddress: UserAddress = UserAddress("", "", "", "", "", "")
    var interests: List<String> = listOf()
}