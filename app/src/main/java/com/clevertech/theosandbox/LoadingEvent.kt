package com.clevertech.theosandbox

import kotlinx.coroutines.flow.*

sealed interface LoadingEvent<out T> {

    object Loading : LoadingEvent<Nothing>

    sealed interface Result<out T> : LoadingEvent<T>

    data class Success<out T>(val data: T) : Result<T>

    @JvmInline
    value class Error(val reason: ErrorReason) : Result<Nothing>
}

/**
 * If result is successful apply given transformation, provide result as is otherwise.
 */
inline fun <T, R> LoadingEvent<T>.map(transform: (T) -> R): LoadingEvent<R> = when (this) {
    is LoadingEvent.Error -> this
    is LoadingEvent.Loading -> this
    is LoadingEvent.Success -> LoadingEvent.Success(transform(this.data))
}

/**
 * If current LoadingEvent returns successful result, chain another call.
 * Useful when you need to use result of a first call to request more data.
 */
inline fun <T, R> LoadingEvent<T>.chain(
    transform: (T) -> Flow<LoadingEvent<R>>
): Flow<LoadingEvent<R>> = when (this) {
    is LoadingEvent.Error -> flowOf(this)
    is LoadingEvent.Loading -> flowOf(this)
    is LoadingEvent.Success -> transform(this.data)
}

/**
 * Apply give transformation to successful result, if it is still null - treat it as an error.
 */
inline fun <T, R> LoadingEvent<T>.mapNotNull(transform: (T) -> R?): LoadingEvent<R> = when (this) {
    is LoadingEvent.Error -> this
    is LoadingEvent.Loading -> this
    is LoadingEvent.Success -> {
        val transformed = transform(this.data)
        if (transformed != null) {
            LoadingEvent.Success(transformed)
        } else {
            LoadingEvent.Error(ErrorReason.Unspecified())
        }
    }
}

/**
 * Treat nullable result as an error.
 */
fun <T> LoadingEvent<T?>.orErrorIfNull(): LoadingEvent<T> = when (this) {
    is LoadingEvent.Error -> this
    is LoadingEvent.Loading -> this
    is LoadingEvent.Success -> {
        if (this.data != null) {
            LoadingEvent.Success(this.data)
        } else {
            LoadingEvent.Error(ErrorReason.Unspecified())
        }
    }
}

fun <T> Flow<LoadingEvent<T>>.completeAfterFirstResult() = transformWhile {
    emit(it)
    it !is LoadingEvent.Result
}

inline fun <T> Flow<T>.onFirst(crossinline block: suspend (T) -> Unit) = flow {
    collectIndexed { index, value ->
        if (index == 0) {
            block(value)
        }
        emit(value)
    }
}
