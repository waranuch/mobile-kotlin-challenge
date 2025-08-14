package com.challenge.productcatalog.data.api.model

import com.google.gson.annotations.SerializedName

/**
 * API response model for cart data
 */
data class CartResponse(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("userId")
    val userId: Long,
    
    @SerializedName("date")
    val date: String,
    
    @SerializedName("products")
    val products: List<CartProductResponse>
)

/**
 * API response model for cart product items
 */
data class CartProductResponse(
    @SerializedName("productId")
    val productId: Long,
    
    @SerializedName("quantity")
    val quantity: Int
)

/**
 * Request model for creating/updating a cart
 */
data class CartRequest(
    @SerializedName("userId")
    val userId: Long,
    
    @SerializedName("date")
    val date: String,
    
    @SerializedName("products")
    val products: List<CartProductRequest>
)

/**
 * Request model for cart product items
 */
data class CartProductRequest(
    @SerializedName("productId")
    val productId: Long,
    
    @SerializedName("quantity")
    val quantity: Int
)
