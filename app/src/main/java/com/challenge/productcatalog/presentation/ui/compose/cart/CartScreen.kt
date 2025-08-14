package com.challenge.productcatalog.presentation.ui.compose.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.productcatalog.R
import com.challenge.productcatalog.domain.model.Cart
import com.challenge.productcatalog.domain.model.CartItem
import com.challenge.productcatalog.domain.model.Product
import com.challenge.productcatalog.domain.model.Rating
import com.challenge.productcatalog.presentation.ui.theme.ProductCatalogTheme
import java.text.NumberFormat
import java.util.Locale

/**
 * Compose implementation of Cart Screen
 * 
 * Features:
 * - Cart items list with quantity controls
 * - Order summary with pricing breakdown
 * - Empty cart state
 * - Checkout button
 * - Remove items functionality
 * - Total calculations
 */

data class CartUiState(
    val cart: Cart = Cart(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    uiState: CartUiState,
    onQuantityChange: (CartItem, Int) -> Unit,
    onRemoveItem: (CartItem) -> Unit,
    onCheckoutClick: () -> Unit,
    onContinueShoppingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            title = { 
                Text(
                    text = stringResource(R.string.shopping_cart),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
        
        when {
            uiState.isLoading -> {
                LoadingState(modifier = Modifier.fillMaxSize())
            }
            
            uiState.cart.isEmpty -> {
                EmptyCartState(
                    onContinueShoppingClick = onContinueShoppingClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            else -> {
                CartContent(
                    cart = uiState.cart,
                    onQuantityChange = onQuantityChange,
                    onRemoveItem = onRemoveItem,
                    onCheckoutClick = onCheckoutClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun CartContent(
    cart: Cart,
    onQuantityChange: (CartItem, Int) -> Unit,
    onRemoveItem: (CartItem) -> Unit,
    onCheckoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Cart items list
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = cart.items,
                key = { it.product.id }
            ) { cartItem ->
                CartItemCard(
                    cartItem = cartItem,
                    onQuantityChange = { quantity -> onQuantityChange(cartItem, quantity) },
                    onRemove = { onRemoveItem(cartItem) }
                )
            }
        }
        
        // Order summary
        OrderSummary(
            cart = cart,
            onCheckoutClick = onCheckoutClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun OrderSummary(
    cart: Cart,
    onCheckoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.order_summary),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            // Subtotal
            PriceRow(
                label = stringResource(R.string.subtotal),
                amount = cart.subtotal,
                style = MaterialTheme.typography.bodyLarge
            )
            
            // Tax
            PriceRow(
                label = stringResource(R.string.tax),
                amount = cart.tax,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            
            // Total
            PriceRow(
                label = stringResource(R.string.total),
                amount = cart.total,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Checkout button
            Button(
                onClick = onCheckoutClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.proceed_to_checkout),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun PriceRow(
    label: String,
    amount: Double,
    style: androidx.compose.ui.text.TextStyle,
    fontWeight: FontWeight = FontWeight.Normal,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = style,
            fontWeight = fontWeight,
            color = color
        )
        Text(
            text = formatPrice(amount),
            style = style,
            fontWeight = fontWeight,
            color = color
        )
    }
}

@Composable
private fun LoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyCartState(
    onContinueShoppingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingBag,
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = stringResource(R.string.empty_cart),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = stringResource(R.string.empty_cart_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            OutlinedButton(
                onClick = onContinueShoppingClick,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.continue_shopping),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

private fun formatPrice(price: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    return numberFormat.format(price)
}

// Preview composables
@Preview(showBackground = true, name = "Cart with Items")
@Composable
private fun CartScreenPreview() {
    ProductCatalogTheme {
        CartScreen(
            uiState = CartUiState(
                cart = Cart(
                    items = listOf(
                        CartItem(
                            product = Product(
                                id = 1,
                                title = "Sample Product 1",
                                price = 99.99,
                                description = "Description",
                                category = "electronics",
                                image = "",
                                rating = Rating(4.5, 120)
                            ),
                            quantity = 2
                        ),
                        CartItem(
                            product = Product(
                                id = 2,
                                title = "Sample Product 2",
                                price = 149.99,
                                description = "Description",
                                category = "clothing",
                                image = "",
                                rating = Rating(4.0, 85)
                            ),
                            quantity = 1
                        )
                    )
                )
            ),
            onQuantityChange = { _, _ -> },
            onRemoveItem = {},
            onCheckoutClick = {},
            onContinueShoppingClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Empty Cart")
@Composable
private fun EmptyCartScreenPreview() {
    ProductCatalogTheme {
        CartScreen(
            uiState = CartUiState(),
            onQuantityChange = { _, _ -> },
            onRemoveItem = {},
            onCheckoutClick = {},
            onContinueShoppingClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
private fun LoadingCartScreenPreview() {
    ProductCatalogTheme {
        CartScreen(
            uiState = CartUiState(isLoading = true),
            onQuantityChange = { _, _ -> },
            onRemoveItem = {},
            onCheckoutClick = {},
            onContinueShoppingClick = {}
        )
    }
}
