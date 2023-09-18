package com.clevertech.theosandbox

sealed class ErrorReason {

    object NetworkConnection : ErrorReason()

    object MaintenanceOrForceUpdate : ErrorReason()

    // Backend send us an error message
    data class ConcreteError(
        val message: String? = null,
    ) : ErrorReason()

    data class ResponseBodyError<T>(
        val body: T? = null
    ) : ErrorReason()

    // We didn't receive any error from the backend
    data class Unspecified(val message: String? = null) : ErrorReason()
}
