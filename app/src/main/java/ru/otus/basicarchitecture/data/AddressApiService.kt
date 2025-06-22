package ru.otus.basicarchitecture.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.otus.basicarchitecture.data.dto.AddressRequestDto
import ru.otus.basicarchitecture.data.dto.AddressResponseDto

interface AddressApiService {

    @Headers("Content-Type: application/json")
    @POST("suggest/address")
    suspend fun suggestAddress(
        @Header("Authorization") token: String,
        @Body request: AddressRequestDto,
    ): Response<AddressResponseDto>
}