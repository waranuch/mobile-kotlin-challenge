package com.challenge.productcatalog.presentation.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.challenge.productcatalog.presentation.ui.compose.navigation.ProductCatalogNavigation
import com.challenge.productcatalog.presentation.ui.theme.ProductCatalogTheme

/**
 * Compose-based MainActivity
 * 
 * This demonstrates:
 * - Full Compose UI implementation
 * - Edge-to-edge display
 * - Material Design 3 theming
 * - Navigation setup
 * - Activity integration with Compose
 * 
 * NOTE: This is an alternative to the existing MainActivity.
 * Candidates can choose between:
 * 1. Traditional View-based approach (existing MainActivity + XML layouts)
 * 2. Modern Compose approach (this ComposeMainActivity)
 * 3. Hybrid approach (mixing both as needed)
 */

class ComposeMainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display for modern Android experience
        enableEdgeToEdge()
        
        setContent {
            ProductCatalogTheme {
                ProductCatalogApp()
            }
        }
    }
}

@Composable
private fun ProductCatalogApp() {
    val navController = rememberNavController()
    
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            ProductCatalogNavigation(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCatalogAppPreview() {
    ProductCatalogTheme {
        ProductCatalogApp()
    }
}

/*
IMPLEMENTATION NOTES FOR CANDIDATES:

1. **Activity Choice**: You can use either:
   - ComposeMainActivity (this file) for full Compose implementation
   - MainActivity (existing) for traditional View-based approach
   - Hybrid approach mixing both

2. **Manifest Configuration**: To use this activity, update AndroidManifest.xml:
   <activity
       android:name=".presentation.ui.compose.ComposeMainActivity"
       android:exported="true"
       android:theme="@style/Theme.ProductCatalog">
       <intent-filter>
           <action android:name="android.intent.action.MAIN" />
           <category android:name="android.intent.category.LAUNCHER" />
       </intent-filter>
   </activity>

3. **ViewModel Integration**: In a real implementation, you would:
   - Inject ViewModels using Hilt or manual DI
   - Collect state using collectAsStateWithLifecycle()
   - Handle side effects with LaunchedEffect

   Example:
   @Composable
   fun ProductListScreen(
       viewModel: ProductListViewModel = hiltViewModel()
   ) {
       val uiState by viewModel.uiState.collectAsStateWithLifecycle()
       
       LaunchedEffect(Unit) {
           viewModel.loadProducts()
       }
       
       ProductListContent(
           uiState = uiState,
           onEvent = viewModel::handleEvent
       )
   }

4. **State Management**: Consider using:
   - StateFlow for UI state
   - SharedFlow for one-time events
   - State hoisting for reusable composables

5. **Testing**: Compose screens can be tested with:
   - ComposeTestRule for UI testing
   - Preview functions for visual verification
   - StateFlow testing for ViewModel logic

6. **Performance**: Optimize with:
   - Stable/Immutable data classes
   - LazyColumn for large lists
   - remember for expensive computations
   - derivedStateOf for computed state
*/
