package com.example.themovie.model.domain.trending

data class TrendingResponse(
    val page: Int,
    val results: List<Trending>,
    val total_pages: Int,
    val total_results: Int
)