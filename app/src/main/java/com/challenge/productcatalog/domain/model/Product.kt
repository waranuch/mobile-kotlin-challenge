package com.challenge.productcatalog.domain.model

/**
 * Domain model representing a product
 */
data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
    val isFavorite: Boolean = false
)

data class Rating(
    val rate: Double,
    val count: Int
)
