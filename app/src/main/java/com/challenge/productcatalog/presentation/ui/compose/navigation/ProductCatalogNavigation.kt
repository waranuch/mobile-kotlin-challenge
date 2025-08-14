package com.challenge.productcatalog.presentation.ui.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.challenge.productcatalog.presentation.ui.compose.cart.CartScreen
import com.challenge.productcatalog.presentation.ui.compose.cart.CartUiState
import com.challenge.productcatalog.presentation.ui.compose.productlist.ProductListScreen
import com.challenge.productcatalog.presentation.ui.compose.productlist.ProductListUiState

/**
 * Navigation setup for Compose-based screens
 * 
 * This demonstrates:
 * - Navigation Compose integration
 * - Screen routing and arguments
 * - State management across screens
 * - Type-safe navigation
 */

// Navigation routes
object ProductCatalogScreens {
    const val PRODUCT_LIST = "product_list"
    const val PRODUCT_DETAIL = "product_detail/{productId}"
    const val CART = "cart"
    const val CHECKOUT = "checkout"
    const val ORDER_CONFIRMATION = "order_confirmation"
    
    // Helper functions for navigation with arguments
    fun productDetail(productId: Long) = "product_detail/$productId"
}

@Composable
fun ProductCatalogNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ProductCatalogScreens.PRODUCT_LIST,
        modifier = modifier
    ) {
        // Product List Screen
        composable(ProductCatalogScreens.PRODUCT_LIST) {
            ProductListScreen(
                uiState = ProductListUiState(), // In real app, this would come from ViewModel
                onSearchQueryChange = { /* Handle search */ },
                onProductClick = { product ->
                    navController.navigate(ProductCatalogScreens.productDetail(product.id))
                },
                onAddToCart = { /* Handle add to cart */ },
                onCartClick = {
                    navController.navigate(ProductCatalogScreens.CART)
                },
                onFavoriteToggle = { /* Handle favorite toggle */ },
                onRefresh = { /* Handle refresh */ }
            )
        }
        
        // Product Detail Screen (placeholder - would be implemented similarly)
        composable(ProductCatalogScreens.PRODUCT_DETAIL) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toLongOrNull() ?: 0L
            
            // ProductDetailScreen would be implemented here
            // For now, showing a placeholder
            ProductDetailPlaceholder(
                productId = productId,
                onBackClick = { navController.popBackStack() },
                onAddToCart = { /* Handle add to cart */ },
                onCartClick = { navController.navigate(ProductCatalogScreens.CART) }
            )
        }
        
        // Cart Screen
        composable(ProductCatalogScreens.CART) {
            CartScreen(
                uiState = CartUiState(), // In real app, this would come from ViewModel
                onQuantityChange = { _, _ -> /* Handle quantity change */ },
                onRemoveItem = { /* Handle remove item */ },
                onCheckoutClick = {
                    navController.navigate(ProductCatalogScreens.CHECKOUT)
                },
                onContinueShoppingClick = {
                    navController.popBackStack(ProductCatalogScreens.PRODUCT_LIST, false)
                }
            )
        }
        
        // Checkout Screen (placeholder)
        composable(ProductCatalogScreens.CHECKOUT) {
            CheckoutPlaceholder(
                onBackClick = { navController.popBackStack() },
                onPlaceOrder = {
                    navController.navigate(ProductCatalogScreens.ORDER_CONFIRMATION) {
                        // Clear back stack up to product list
                        popUpTo(ProductCatalogScreens.PRODUCT_LIST)
                    }
                }
            )
        }
        
        // Order Confirmation Screen (placeholder)
        composable(ProductCatalogScreens.ORDER_CONFIRMATION) {
            OrderConfirmationPlaceholder(
                onContinueShoppingClick = {
                    navController.popBackStack(ProductCatalogScreens.PRODUCT_LIST, false)
                }
            )
        }
    }
}

// Placeholder screens for demonstration
@Composable
private fun ProductDetailPlaceholder(
    productId: Long,
    onBackClick: () -> Unit,
    onAddToCart: () -> Unit,
    onCartClick: () -> Unit
) {
    // This would be replaced with actual ProductDetailScreen implementation
    androidx.compose.foundation.layout.Column {
        androidx.compose.material3.Text("Product Detail Screen - ID: $productId")
        androidx.compose.material3.Button(onClick = onBackClick) {
            androidx.compose.material3.Text("Back")
        }
        androidx.compose.material3.Button(onClick = onAddToCart) {
            androidx.compose.material3.Text("Add to Cart")
        }
        androidx.compose.material3.Button(onClick = onCartClick) {
            androidx.compose.material3.Text("Go to Cart")
        }
    }
}

@Composable
private fun CheckoutPlaceholder(
    onBackClick: () -> Unit,
    onPlaceOrder: () -> Unit
) {
    androidx.compose.foundation.layout.Column {
        androidx.compose.material3.Text("Checkout Screen")
        androidx.compose.material3.Button(onClick = onBackClick) {
            androidx.compose.material3.Text("Back to Cart")
        }
        androidx.compose.material3.Button(onClick = onPlaceOrder) {
            androidx.compose.material3.Text("Place Order")
        }
    }
}

@Composable
private fun OrderConfirmationPlaceholder(
    onContinueShoppingClick: () -> Unit
) {
    androidx.compose.foundation.layout.Column {
        androidx.compose.material3.Text("Order Confirmed!")
        androidx.compose.material3.Button(onClick = onContinueShoppingClick) {
            androidx.compose.material3.Text("Continue Shopping")
        }
    }
}
