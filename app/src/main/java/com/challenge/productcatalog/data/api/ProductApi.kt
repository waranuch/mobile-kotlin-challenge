package com.challenge.productcatalog.data.api

import com.challenge.productcatalog.data.api.model.CartRequest
import com.challenge.productcatalog.data.api.model.CartResponse
import com.challenge.productcatalog.data.api.model.ProductResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * API interface for FakeStore API
 * Base URL: https://fakestoreapi.com/
 */
interface ProductApi {
    
    // Product endpoints
    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>
    
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<ProductResponse>
    
    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>
    
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): Response<List<ProductResponse>>
    
    // Cart endpoints
    @GET("carts")
    suspend fun getAllCarts(): Response<List<CartResponse>>
    
    @GET("carts/{id}")
    suspend fun getCartById(@Path("id") id: Long): Response<CartResponse>
    
    @POST("carts")
    suspend fun createCart(@Body cartRequest: CartRequest): Response<CartResponse>
    
    @PUT("carts/{id}")
    suspend fun updateCart(@Path("id") id: Long, @Body cartRequest: CartRequest): Response<CartResponse>
    
    @DELETE("carts/{id}")
    suspend fun deleteCart(@Path("id") id: Long): Response<CartResponse>
    
    @GET("carts/user/{userId}")
    suspend fun getUserCarts(@Path("userId") userId: Long): Response<List<CartResponse>>
}
