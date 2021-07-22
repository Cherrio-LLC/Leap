package com.cherrio.leap.data.remote

sealed class Result<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(error: String, data: T? = null) : Result<T>(data, error)

    override fun toString(): String {
       return when(this){
            is Loading -> "Loading..."
           is Success -> "$data"
           is Error -> "$error"
        }
    }
}