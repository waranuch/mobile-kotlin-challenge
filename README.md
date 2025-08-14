# Mobile Kotlin Code Challenge - Product Catalog App

## Overview
Welcome to the Mobile Kotlin Code Challenge! You will be building a **Product Catalog App** that allows users to browse products, search for items, view details, and manage their favorites.

This challenge is designed to assess your skills in Android development, Kotlin programming, UI/UX implementation, and software architecture patterns.

## Time Limit
**3-4 hours** (We recommend focusing on core functionality first, then adding enhancements if time permits)

## Challenge Requirements

### Core Features (Must Have)
1. **Product List Screen**
   - Display a list of products with image, name, price, and rating
   - Implement smooth scrolling with RecyclerView
   - Show loading states and error handling
   - Add "Add to Cart" buttons on product items

2. **Search Functionality**
   - Add a search bar to filter products by name
   - Implement real-time search with debouncing
   - Show "no results" state when applicable

3. **Product Detail Screen**
   - Show detailed product information (image, name, description, price, rating)
   - Navigate from product list to detail screen
   - Add/remove product from favorites
   - Add "Add to Cart" with quantity selector
   - Show stock status and availability

4. **Shopping Cart**
   - View all items added to cart with quantities
   - Modify item quantities (increase/decrease/remove)
   - Calculate and display subtotal, tax, and total
   - Persist cart data locally
   - Empty cart state with call-to-action

5. **Checkout Process**
   - Multi-step checkout flow (Address → Payment → Review)
   - Form validation for shipping address
   - Payment method selection (mock)
   - Order summary and confirmation
   - Success screen with order details

6. **Favorites Management**
   - Allow users to mark products as favorites
   - Persist favorites locally (SharedPreferences, Room, or SQLite)
   - Show favorite status in both list and detail views

7. **Error Handling**
   - Handle network errors gracefully
   - Show appropriate error messages to users
   - Implement retry mechanisms where appropriate

### Technical Requirements
- **Language**: Kotlin (100%)
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 or latest
- **Architecture**: MVVM pattern recommended (MVP acceptable)
- **UI Framework**: Choose one of:
  - **Jetpack Compose** (recommended) - Modern declarative UI
  - **Traditional XML Views** - Classic Android UI approach
  - **Hybrid** - Mix of both approaches
- **Networking**: Retrofit + OkHttp (or Ktor)
- **Image Loading**: 
  - **For Compose**: Coil (recommended) or Glide
  - **For XML Views**: Glide, Picasso, or Coil
- **Navigation**: Navigation Component (supports both Compose and Fragment navigation)

### API Specification
Use the following REST API endpoints:

**Base URL**: `https://fakestoreapi.com/`

**Endpoints**:
- `GET /products` - Get all products
- `GET /products/{id}` - Get specific product
- `GET /products/categories` - Get all categories
- `GET /products/category/{category}` - Get products in category
- `GET /carts` - Get all carts
- `GET /carts/{id}` - Get specific cart
- `POST /carts` - Add new cart
- `PUT /carts/{id}` - Update cart
- `DELETE /carts/{id}` - Delete cart

**Product Object Structure**:
```json
{
  "id": 1,
  "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
  "price": 109.95,
  "description": "Your perfect pack for everyday use...",
  "category": "men's clothing",
  "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
  "rating": {
    "rate": 3.9,
    "count": 120
  }
}
```

**Cart Object Structure**:
```json
{
  "id": 1,
  "userId": 1,
  "date": "2020-03-02T00:00:00.000Z",
  "products": [
    {
      "productId": 1,
      "quantity": 4
    },
    {
      "productId": 2,
      "quantity": 1
    }
  ]
}
```

## Bonus Features (Nice to Have)
If you complete the core features early, consider implementing:

**E-commerce Enhancements:**
- **Category Filtering**: Filter products by categories with chip selections
- **Sort Options**: Sort by price, rating, name, or newest
- **Product Variants**: Size/color selection for products
- **Wishlist Sharing**: Share favorite products
- **Order History**: View past orders and reorder items
- **Product Reviews**: Add/view user reviews and ratings

**Technical Enhancements:**
- **Pull to Refresh**: Refresh product and cart data
- **Dark Mode Support**: Complete theme switching
- **Offline Support**: Cache products and cart for offline viewing
- **Push Notifications**: Order updates and promotional notifications
- **Analytics**: Track user interactions and cart abandonment
- **Unit Tests**: Comprehensive testing for ViewModels and repositories
- **Animations**: Smooth transitions, cart animations, and micro-interactions

## Project Structure
Your project should follow a clean architecture pattern. Suggested structure:

```
app/
├── src/main/java/com/yourname/productcatalog/
│   ├── data/
│   │   ├── api/           # API interfaces and models
│   │   ├── local/         # Local data storage (Room, SharedPrefs)
│   │   └── repository/    # Repository implementations
│   ├── domain/
│   │   ├── model/         # Domain models (Product, Cart, Order)
│   │   ├── repository/    # Repository interfaces
│   │   └── usecase/       # Business logic use cases
│   ├── presentation/
│   │   ├── ui/
│   │   │   ├── productlist/     # Traditional XML-based screens
│   │   │   ├── productdetail/
│   │   │   ├── cart/
│   │   │   ├── checkout/
│   │   │   ├── favorites/
│   │   │   ├── compose/         # Compose-based screens (optional)
│   │   │   │   ├── productlist/ # Compose ProductList implementation
│   │   │   │   ├── cart/        # Compose Cart implementation  
│   │   │   │   ├── theme/       # Compose theme system
│   │   │   │   └── navigation/  # Compose navigation setup
│   │   │   └── theme/           # Traditional XML themes
│   │   ├── viewmodel/           # ViewModels for each feature
│   │   └── adapter/             # RecyclerView adapters (for XML views)
│   └── utils/                   # Utility classes and extensions
```

**UI Implementation Options:**
- **Option 1**: Pure Compose implementation using `ComposeMainActivity`
- **Option 2**: Traditional XML views using `MainActivity`  
- **Option 3**: Hybrid approach mixing both as needed

## UI Framework Guide

### 🚀 **Jetpack Compose Implementation** (Recommended)
We've provided starter Compose implementations to demonstrate modern Android development:

**What's Included:**
- `ComposeMainActivity.kt` - Compose-based main activity
- `ProductListScreen.kt` - Complete product list with search and cart functionality
- `CartScreen.kt` - Shopping cart with quantity controls and checkout
- `ProductCatalogNavigation.kt` - Navigation setup for Compose screens
- Material Design 3 theme system with dark mode support
- Loading, error, and empty states
- Preview functions for UI development

**Key Features Demonstrated:**
- Declarative UI with Compose
- State hoisting and management
- Navigation Compose integration
- Material Design 3 components
- AsyncImage for network images
- Pull-to-refresh with accompanist
- Responsive design patterns

**To Use Compose Implementation:**
1. Update `AndroidManifest.xml` to use `ComposeMainActivity` as launcher
2. Implement ViewModels to provide state to Compose screens  
3. Add dependency injection for data repositories
4. Extend the provided screens as needed

### 📱 **Traditional XML Views Implementation**
Classic Android development approach with the existing setup:

**What's Included:**
- `MainActivity.kt` - Traditional activity with toolbar
- XML layouts for all screens (product list, cart, checkout, etc.)
- Navigation Component with fragment-based navigation
- RecyclerView adapters and ViewBinding setup
- Material Design styling with themes

### 🔄 **Hybrid Approach**
Mix both approaches as needed:
- Use Compose for new screens or complex UI
- Keep XML views for simpler screens or team familiarity
- Interop between Compose and Views using ComposeView

## Evaluation Criteria

### Code Quality (30%)
- Clean, readable, and maintainable code
- Proper use of Kotlin features (null safety, data classes, etc.)
- Consistent code style and naming conventions
- Appropriate use of design patterns

### Architecture & Design (25%)
- Proper separation of concerns
- Implementation of MVVM/MVP pattern
- Repository pattern usage
- Dependency injection (optional but recommended)

### Functionality (25%)
- All core features working correctly
- Proper error handling and edge cases
- Smooth user experience
- App doesn't crash under normal usage

### UI/UX Implementation (20%)
- Material Design 3 guidelines adherence
- Responsive layouts for different screen sizes
- Proper loading states and user feedback
- Intuitive navigation
- **Compose-specific** (if chosen):
  - Effective state management and composition
  - Proper use of remember, LaunchedEffect, and side effects
  - Performance optimization with stable classes
  - Well-structured Composable hierarchy
- **XML Views-specific** (if chosen):
  - Efficient RecyclerView implementation
  - Proper ViewBinding/DataBinding usage
  - Fragment lifecycle management

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK with API 34+

### Getting Started
1. Fork or download this challenge repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

### API Setup
The challenge uses the free [Fake Store API](https://fakestoreapi.com/) - no authentication required.
Test the API endpoints in your browser or Postman before implementation.

## Submission Guidelines

### What to Submit
1. **Complete Android project** with all source code
2. **APK file** for testing (debug version is fine)
3. **Brief documentation** (README) explaining:
   - Architecture decisions you made
   - Libraries you chose and why
   - Any challenges faced and how you solved them
   - Features implemented vs. skipped (if any)
   - Time spent on the challenge

### Submission Format
- Create a ZIP file containing your project
- Name the file: `[YourName]_ProductCatalog_Challenge.zip`
- Email to the provided address or upload to the specified platform

### Don't Submit
- `.git` folder
- `build/` directories
- `.idea/` folder (keep only `.idea/codeStyles/` if customized)
- Any API keys or sensitive data

## Tips for Success

1. **Start with the basics**: Get the product list working first
2. **Plan your architecture**: Spend 10-15 minutes planning before coding
3. **Handle errors early**: Implement error handling as you go
4. **Keep it simple**: Focus on clean, working code over complex features
5. **Test frequently**: Run your app often to catch issues early
6. **Document decisions**: Brief comments on architectural choices help

### Time Allocation Guidance

Suggest this breakdown to candidates:
- **30 minutes**: Project setup and API exploration
- **90 minutes**: Core functionality (product list, navigation, detail screen)
- **45 minutes**: Search and favorites features
- **60 minutes**: Shopping cart implementation
- **45 minutes**: Basic checkout flow (address form + order summary)
- **30 minutes**: Error handling and polish
- **20 minutes**: Bonus features (if time permits)

**Note**: Cart and checkout can be implemented with local storage only - API integration is optional for the cart endpoints.

## Questions?
If you have any questions about the requirements or encounter issues with the API, please reach out to [contact-email] within the first hour of starting the challenge.

Good luck! 🚀

---
*This challenge is designed to showcase your Android development skills in a realistic project scenario. Focus on writing clean, maintainable code that demonstrates your understanding of mobile development best practices.*
