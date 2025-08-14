package com.challenge.productcatalog.data.api.model

import com.google.gson.annotations.SerializedName

/**
 * API response model for product data
 */
data class ProductResponse(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("price")
    val price: Double,
    
    @SerializedName("description")
    val description: String,
    
    @SerializedName("category")
    val category: String,
    
    @SerializedName("image")
    val image: String,
    
    @SerializedName("rating")
    val rating: RatingResponse
)

data class RatingResponse(
    @SerializedName("rate")
    val rate: Double,
    
    @SerializedName("count")
    val count: Int
)
