package com.clevertech.theosandbox

data class ErrorReasonException(
    val reason: ErrorReason
) : Exception()
