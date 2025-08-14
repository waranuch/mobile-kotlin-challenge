package com.challenge.productcatalog.domain.model

import java.util.Date

/**
 * Domain model representing an order
 */
data class Order(
    val id: String = "",
    val userId: Long = 1,
    val items: List<OrderItem> = emptyList(),
    val shippingAddress: Address,
    val paymentMethod: PaymentMethod,
    val status: OrderStatus = OrderStatus.PENDING,
    val subtotal: Double,
    val tax: Double,
    val shippingCost: Double = DEFAULT_SHIPPING_COST,
    val total: Double,
    val createdAt: Date = Date(),
    val estimatedDelivery: Date? = null
) {
    companion object {
        const val DEFAULT_SHIPPING_COST = 9.99
    }
}

/**
 * Individual item in an order
 */
data class OrderItem(
    val productId: Long,
    val productName: String,
    val productImage: String,
    val price: Double,
    val quantity: Int,
    val selectedSize: String? = null,
    val selectedColor: String? = null
) {
    val totalPrice: Double
        get() = price * quantity
}

/**
 * Shipping address information
 */
data class Address(
    val firstName: String = "",
    val lastName: String = "",
    val addressLine1: String = "",
    val addressLine2: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val country: String = "United States",
    val phoneNumber: String = ""
) {
    val fullName: String
        get() = "$firstName $lastName".trim()
    
    val isValid: Boolean
        get() = firstName.isNotBlank() && 
                lastName.isNotBlank() && 
                addressLine1.isNotBlank() && 
                city.isNotBlank() && 
                state.isNotBlank() && 
                zipCode.isNotBlank()
}

/**
 * Payment method information
 */
data class PaymentMethod(
    val type: PaymentType = PaymentType.CREDIT_CARD,
    val cardNumber: String = "",
    val expiryMonth: Int = 0,
    val expiryYear: Int = 0,
    val cvv: String = "",
    val cardHolderName: String = "",
    val billingAddress: Address? = null
) {
    val maskedCardNumber: String
        get() = if (cardNumber.length >= 4) {
            "**** **** **** ${cardNumber.takeLast(4)}"
        } else {
            cardNumber
        }
    
    val isValid: Boolean
        get() = when (type) {
            PaymentType.CREDIT_CARD -> cardNumber.length >= 13 && 
                                     expiryMonth in 1..12 && 
                                     expiryYear >= 2024 && 
                                     cvv.length in 3..4 && 
                                     cardHolderName.isNotBlank()
            PaymentType.PAYPAL -> true
            PaymentType.APPLE_PAY -> true
            PaymentType.GOOGLE_PAY -> true
        }
}

/**
 * Available payment types
 */
enum class PaymentType(val displayName: String) {
    CREDIT_CARD("Credit Card"),
    PAYPAL("PayPal"),
    APPLE_PAY("Apple Pay"),
    GOOGLE_PAY("Google Pay")
}

/**
 * Order status states
 */
enum class OrderStatus(val displayName: String) {
    PENDING("Order Pending"),
    CONFIRMED("Order Confirmed"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled")
}
