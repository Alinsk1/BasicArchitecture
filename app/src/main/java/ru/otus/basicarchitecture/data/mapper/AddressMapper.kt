package ru.otus.basicarchitecture.data.mapper

import ru.otus.basicarchitecture.data.dto.AddressDataDto
import ru.otus.basicarchitecture.domain.UserAddress

class AddressMapper {

    fun mapDtoToEntity(dto: AddressDataDto) = UserAddress(
        fullAddress = dto.fullAddress ?: "",
        country = dto.country ?: "",
        city = dto.city ?: "",
        street = dto.street ?: "",
        house = dto.house ?: "",
        block = dto.block ?: ""
    )

}