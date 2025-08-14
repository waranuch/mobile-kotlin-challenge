package com.challenge.productcatalog.presentation.ui.compose.productlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.productcatalog.R
import com.challenge.productcatalog.domain.model.Product
import com.challenge.productcatalog.domain.model.Rating
import com.challenge.productcatalog.presentation.ui.theme.ProductCatalogTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Compose implementation of ProductList Screen
 * 
 * Demonstrates modern Android UI development with:
 * - Jetpack Compose
 * - Material Design 3
 * - State management
 * - LazyColumn for performance
 * - Pull-to-refresh
 * - Search functionality
 */

data class ProductListUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val cartItemCount: Int = 0
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    uiState: ProductListUiState,
    onSearchQueryChange: (String) -> Unit,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onCartClick: () -> Unit,
    onFavoriteToggle: (Product) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading)
    
    Column(modifier = modifier.fillMaxSize()) {
        // Top App Bar with search and cart
        TopAppBar(
            title = { Text(stringResource(R.string.app_name)) },
            actions = {
                // Cart icon with badge
                Box {
                    IconButton(onClick = onCartClick) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = stringResource(R.string.cart)
                        )
                    }
                    if (uiState.cartItemCount > 0) {
                        Badge(
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Text(
                                text = uiState.cartItemCount.toString(),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        )
        
        // Search bar
        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        
        // Product list with pull-to-refresh
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh
        ) {
            when {
                uiState.isLoading && uiState.products.isEmpty() -> {
                    LoadingState(modifier = Modifier.fillMaxSize())
                }
                
                uiState.error != null && uiState.products.isEmpty() -> {
                    ErrorState(
                        error = uiState.error,
                        onRetry = onRefresh,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                uiState.products.isEmpty() -> {
                    EmptyState(modifier = Modifier.fillMaxSize())
                }
                
                else -> {
                    ProductList(
                        products = uiState.products,
                        onProductClick = onProductClick,
                        onAddToCart = onAddToCart,
                        onFavoriteToggle = onFavoriteToggle,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.search_products)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.cd_search_icon)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { keyboardController?.hide() }
        )
    )
}

@Composable
private fun ProductList(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onFavoriteToggle: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductItem(
                product = product,
                onClick = { onProductClick(product) },
                onAddToCart = { onAddToCart(product) },
                onFavoriteToggle = { onFavoriteToggle(product) }
            )
        }
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.loading),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun ErrorState(
    error: String,
    onRetry: () -> Unit,
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
            Text(
                text = stringResource(R.string.error_loading_products),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
private fun EmptyState(
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
            Text(
                text = stringResource(R.string.no_products_found),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.no_search_results_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreview() {
    ProductCatalogTheme {
        ProductListScreen(
            uiState = ProductListUiState(
                products = listOf(
                    Product(
                        id = 1,
                        title = "Sample Product",
                        price = 99.99,
                        description = "This is a sample product",
                        category = "electronics",
                        image = "",
                        rating = Rating(4.5, 120)
                    )
                ),
                cartItemCount = 2
            ),
            onSearchQueryChange = {},
            onProductClick = {},
            onAddToCart = {},
            onCartClick = {},
            onFavoriteToggle = {},
            onRefresh = {}
        )
    }
}

@Preview(name = "Loading State", showBackground = true)
@Composable
private fun LoadingStatePreview() {
    ProductCatalogTheme {
        ProductListScreen(
            uiState = ProductListUiState(isLoading = true),
            onSearchQueryChange = {},
            onProductClick = {},
            onAddToCart = {},
            onCartClick = {},
            onFavoriteToggle = {},
            onRefresh = {}
        )
    }
}

@Preview(name = "Error State", showBackground = true)
@Composable
private fun ErrorStatePreview() {
    ProductCatalogTheme {
        ProductListScreen(
            uiState = ProductListUiState(error = "Network connection failed"),
            onSearchQueryChange = {},
            onProductClick = {},
            onAddToCart = {},
            onCartClick = {},
            onFavoriteToggle = {},
            onRefresh = {}
        )
    }
}
