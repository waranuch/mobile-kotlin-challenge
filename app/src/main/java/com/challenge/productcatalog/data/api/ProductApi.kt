package com.challenge.productcatalog.data.api

import com.challenge.productcatalog.data.api.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API interface for FakeStore API
 * Base URL: https://fakestoreapi.com/
 */
interface ProductApi {
    
    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>
    
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<ProductResponse>
    
    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>
    
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): Response<List<ProductResponse>>
}
