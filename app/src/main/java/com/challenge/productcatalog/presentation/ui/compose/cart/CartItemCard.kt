package com.challenge.productcatalog.presentation.ui.compose.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.challenge.productcatalog.R
import com.challenge.productcatalog.domain.model.CartItem
import com.challenge.productcatalog.domain.model.Product
import com.challenge.productcatalog.domain.model.Rating
import com.challenge.productcatalog.presentation.ui.theme.ProductCatalogTheme
import java.text.NumberFormat
import java.util.Locale

/**
 * CartItemCard composable for displaying individual cart items
 * 
 * Features:
 * - Product image with loading states
 * - Product title and price
 * - Quantity controls (increase/decrease)
 * - Remove item functionality
 * - Total price calculation
 * - Material Design 3 styling
 */

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isImageLoading by remember { mutableStateOf(true) }
    var isImageError by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Product Image
            CartItemImage(
                imageUrl = cartItem.product.image,
                contentDescription = cartItem.product.title,
                isLoading = isImageLoading,
                isError = isImageError,
                onLoadingStateChange = { loading, error ->
                    isImageLoading = loading
                    isImageError = error
                },
                modifier = Modifier.size(80.dp)
            )
            
            // Product Details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Header row with title and remove button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = cartItem.product.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    IconButton(
                        onClick = onRemove,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.remove_item),
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                // Unit price
                Text(
                    text = formatPrice(cartItem.product.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                // Quantity controls and total price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Quantity controls
                    QuantityControl(
                        quantity = cartItem.quantity,
                        onQuantityChange = onQuantityChange,
                        modifier = Modifier.wrapContentWidth()
                    )
                    
                    // Total price for this item
                    Text(
                        text = formatPrice(cartItem.totalPrice),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun CartItemImage(
    imageUrl: String,
    contentDescription: String,
    isLoading: Boolean,
    isError: Boolean,
    onLoadingStateChange: (Boolean, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            onLoading = { onLoadingStateChange(true, false) },
            onSuccess = { onLoadingStateChange(false, false) },
            onError = { onLoadingStateChange(false, true) }
        )
        
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            }
            
            isError -> {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Image",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuantityControl(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            // Decrease button
            IconButton(
                onClick = { 
                    if (quantity > 1) {
                        onQuantityChange(quantity - 1)
                    }
                },
                enabled = quantity > 1,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = stringResource(R.string.decrease_quantity),
                    modifier = Modifier.size(16.dp),
                    tint = if (quantity > 1) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                    }
                )
            }
            
            // Quantity text
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.widthIn(min = 24.dp)
            )
            
            // Increase button
            IconButton(
                onClick = { onQuantityChange(quantity + 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.increase_quantity),
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
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
@Preview(showBackground = true, name = "Regular Cart Item")
@Composable
private fun CartItemCardPreview() {
    ProductCatalogTheme {
        CartItemCard(
            cartItem = CartItem(
                product = Product(
                    id = 1,
                    title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    price = 109.95,
                    description = "Your perfect pack for everyday use and walks in the forest.",
                    category = "men's clothing",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                    rating = Rating(3.9, 120)
                ),
                quantity = 2
            ),
            onQuantityChange = {},
            onRemove = {}
        )
    }
}

@Preview(showBackground = true, name = "Single Quantity Item")
@Composable
private fun SingleQuantityCartItemPreview() {
    ProductCatalogTheme {
        CartItemCard(
            cartItem = CartItem(
                product = Product(
                    id = 2,
                    title = "Mens Cotton Jacket",
                    price = 55.99,
                    description = "Great outerwear jackets for Spring/Autumn/Winter.",
                    category = "men's clothing",
                    image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
                    rating = Rating(4.7, 500)
                ),
                quantity = 1
            ),
            onQuantityChange = {},
            onRemove = {}
        )
    }
}

@Preview(showBackground = true, name = "Dark Theme")
@Composable
private fun CartItemCardDarkPreview() {
    ProductCatalogTheme(darkTheme = true) {
        CartItemCard(
            cartItem = CartItem(
                product = Product(
                    id = 3,
                    title = "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor",
                    price = 999.99,
                    description = "49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR.",
                    category = "electronics",
                    image = "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg",
                    rating = Rating(2.2, 140)
                ),
                quantity = 3
            ),
            onQuantityChange = {},
            onRemove = {}
        )
    }
}
