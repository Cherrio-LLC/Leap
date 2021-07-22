package com.cherrio.leap.data.model

data class MediaStack(
    val pagination: Pagination,
    val data: List<Data>
)

data class Pagination(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Int,
)

data class Data(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val image: String,
    val published_at: String
)
