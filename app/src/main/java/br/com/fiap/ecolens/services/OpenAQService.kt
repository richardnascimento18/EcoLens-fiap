package br.com.fiap.ecolens.services

import br.com.fiap.ecolens.LocationDetailResponse
import br.com.fiap.ecolens.LocationResponse
import br.com.fiap.ecolens.MeasurementResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenAQService {
    @GET("v3/locations")
    suspend fun getLocations(
        @Query("limit") limit: Int = 1000
    ): LocationResponse

    @GET("v3/locations/{id}")
    suspend fun getLocationDetails(
        @Path("id") id: Int
    ): LocationDetailResponse

    @GET("v3/sensors/{sensor_id}/measurements")
    suspend fun getMeasurements(
        @Path("sensor_id") sensorId: Int,
        @Query("limit") limit: Int = 500
    ): MeasurementResponse
}