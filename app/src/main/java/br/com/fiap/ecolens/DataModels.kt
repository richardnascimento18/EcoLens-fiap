package br.com.fiap.ecolens

data class LocationResponse(
    val results: List<LocationItem>
)

data class LocationItem(
    val id: Int,
    val name: String,
    val coordinates: Coordinates?
)

data class Coordinates(
    val latitude: Double?,
    val longitude: Double?
)

data class LocationDetailResponse(
    val results: List<LocationDetail>
)

data class LocationDetail(
    val id: Int,
    val name: String,
    val country: Country,
    val sensors: List<Sensor>
)

data class Sensor(
    val id: Int
)

data class Country(
    val name: String
)

data class MeasurementResponse(
    val results: List<Measurement>
)

data class Measurement(
    val value: Double,
    val parameter: Parameter,
    val period: Period?
)

data class Parameter(
    val name: String,
    val units: String
)

data class Period(
    val datetimeFrom: DateTime
)

data class DateTime(
    val utc: String
)