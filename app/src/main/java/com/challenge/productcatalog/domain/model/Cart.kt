package com.challenge.productcatalog.domain.model

import java.util.Date

/**
 * Domain model representing a shopping cart
 */
data class Cart(
    val id: Long = 0,
    val userId: Long = 1,
    val items: List<CartItem> = emptyList(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) {
    val totalItems: Int
        get() = items.sumOf { it.quantity }
    
    val subtotal: Double
        get() = items.sumOf { it.totalPrice }
    
    val tax: Double
        get() = subtotal * TAX_RATE
    
    val total: Double
        get() = subtotal + tax
    
    val isEmpty: Boolean
        get() = items.isEmpty()
    
    companion object {
        private const val TAX_RATE = 0.08 // 8% tax rate
    }
}

/**
 * Individual item in the shopping cart
 */
data class CartItem(
    val product: Product,
    val quantity: Int = 1,
    val selectedSize: String? = null,
    val selectedColor: String? = null
) {
    val totalPrice: Double
        get() = product.price * quantity
    
    val isValid: Boolean
        get() = quantity > 0
}
