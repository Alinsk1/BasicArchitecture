package ru.otus.basicarchitecture


data class UserName(
    var name: String,
    var surname: String,
    var birthday: String
)

data class UserAddress(
    var country: String,
    var city: String,
    var address: String
)