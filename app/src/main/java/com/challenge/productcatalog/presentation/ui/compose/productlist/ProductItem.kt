package com.challenge.productcatalog.presentation.ui.compose.productlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.challenge.productcatalog.R
import com.challenge.productcatalog.domain.model.Product
import com.challenge.productcatalog.domain.model.Rating
import com.challenge.productcatalog.presentation.ui.theme.ProductCatalogTheme
import com.challenge.productcatalog.presentation.ui.theme.RatingStar
import java.text.NumberFormat
import java.util.Locale

/**
 * ProductItem composable for displaying individual products in a list
 * 
 * Features:
 * - Product image with loading/error states
 * - Product title, price, and rating
 * - Favorite toggle functionality
 * - Add to cart button
 * - Material Design 3 styling
 * - Responsive layout
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
    onAddToCart: () -> Unit,
    onFavoriteToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isImageLoading by remember { mutableStateOf(true) }
    var isImageError by remember { mutableStateOf(false) }
    
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Product Image
            ProductImage(
                imageUrl = product.image,
                contentDescription = product.title,
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
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Title
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                // Price
                Text(
                    text = formatPrice(product.price),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                
                // Rating
                ProductRating(
                    rating = product.rating,
                    modifier = Modifier.padding(top = 4.dp)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Action buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Favorite button
                    IconButton(
                        onClick = onFavoriteToggle,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (product.isFavorite) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = if (product.isFavorite) {
                                stringResource(R.string.remove_from_favorites)
                            } else {
                                stringResource(R.string.add_to_favorites)
                            },
                            tint = if (product.isFavorite) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                    
                    // Add to cart button
                    FilledTonalButton(
                        onClick = onAddToCart,
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.add_to_cart),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductImage(
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
private fun ProductRating(
    rating: Rating,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = RatingStar,
            modifier = Modifier.size(16.dp)
        )
        
        Text(
            text = String.format("%.1f", rating.rate),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Text(
            text = "(${rating.count})",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun formatPrice(price: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    return numberFormat.format(price)
}

@Preview(name = "Regular Product", showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductCatalogTheme {
        ProductItem(
            product = Product(
                id = 1,
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                description = "Your perfect pack for everyday use and walks in the forest.",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = Rating(3.9, 120),
                isFavorite = false
            ),
            onClick = {},
            onAddToCart = {},
            onFavoriteToggle = {}
        )
    }
}

@Preview(name = "Favorite Product", showBackground = true)
@Composable
private fun FavoriteProductItemPreview() {
    ProductCatalogTheme {
        ProductItem(
            product = Product(
                id = 2,
                title = "Mens Cotton Jacket",
                price = 55.99,
                description = "Great outerwear jackets for Spring/Autumn/Winter.",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
                rating = Rating(4.7, 500),
                isFavorite = true
            ),
            onClick = {},
            onAddToCart = {},
            onFavoriteToggle = {}
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true)
@Composable
private fun ProductItemDarkPreview() {
    ProductCatalogTheme(darkTheme = true) {
        ProductItem(
            product = Product(
                id = 3,
                title = "Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor",
                price = 999.99,
                description = "49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR.",
                category = "electronics",
                image = "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg",
                rating = Rating(2.2, 140),
                isFavorite = false
            ),
            onClick = {},
            onAddToCart = {},
            onFavoriteToggle = {}
        )
    }
}
