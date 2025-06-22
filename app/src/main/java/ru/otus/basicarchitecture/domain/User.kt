package ru.otus.basicarchitecture.domain


data class UserName(
    var name: String,
    var surname: String,
    var birthday: String
)

data class UserAddress(
    var country: String,
    var city: String,
    var street: String,
    var house: String,
    var block: String,
    var fullAddress: String
)