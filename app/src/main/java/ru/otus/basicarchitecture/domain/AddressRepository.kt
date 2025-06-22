package ru.otus.basicarchitecture.domain

interface AddressRepository {
    suspend fun suggestAddress(query: String): List<UserAddress>
}