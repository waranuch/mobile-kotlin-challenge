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

2. **Search Functionality**
   - Add a search bar to filter products by name
   - Implement real-time search with debouncing
   - Show "no results" state when applicable

3. **Product Detail Screen**
   - Show detailed product information (image, name, description, price, rating)
   - Navigate from product list to detail screen
   - Add/remove product from favorites

4. **Favorites Management**
   - Allow users to mark products as favorites
   - Persist favorites locally (SharedPreferences, Room, or SQLite)
   - Show favorite status in both list and detail views

5. **Error Handling**
   - Handle network errors gracefully
   - Show appropriate error messages to users
   - Implement retry mechanisms where appropriate

### Technical Requirements
- **Language**: Kotlin (100%)
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 or latest
- **Architecture**: MVVM pattern recommended (MVP acceptable)
- **Networking**: Retrofit + OkHttp (or Ktor)
- **Image Loading**: Glide, Picasso, or Coil
- **Navigation**: Navigation Component or traditional fragment navigation

### API Specification
Use the following REST API endpoints:

**Base URL**: `https://fakestoreapi.com/`

**Endpoints**:
- `GET /products` - Get all products
- `GET /products/{id}` - Get specific product
- `GET /products/categories` - Get all categories
- `GET /products/category/{category}` - Get products in category

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

## Bonus Features (Nice to Have)
If you complete the core features early, consider implementing:

- **Category Filtering**: Filter products by categories
- **Sort Options**: Sort by price, rating, or name
- **Pull to Refresh**: Refresh product list
- **Dark Mode Support**: Implement theme switching
- **Unit Tests**: Write tests for ViewModels and repositories
- **Offline Support**: Cache data for offline viewing
- **Animations**: Add meaningful animations and transitions

## Project Structure
Your project should follow a clean architecture pattern. Suggested structure:

```
app/
├── src/main/java/com/yourname/productcatalog/
│   ├── data/
│   │   ├── api/           # API interfaces and models
│   │   ├── local/         # Local data storage
│   │   └── repository/    # Repository implementations
│   ├── domain/
│   │   ├── model/         # Domain models
│   │   ├── repository/    # Repository interfaces
│   │   └── usecase/       # Business logic use cases
│   ├── presentation/
│   │   ├── ui/
│   │   │   ├── productlist/
│   │   │   ├── productdetail/
│   │   │   └── favorites/
│   │   ├── viewmodel/
│   │   └── adapter/
│   └── utils/             # Utility classes
```

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
- Material Design guidelines adherence
- Responsive layouts for different screen sizes
- Proper loading states and user feedback
- Intuitive navigation

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

## Questions?
If you have any questions about the requirements or encounter issues with the API, please reach out to [contact-email] within the first hour of starting the challenge.

Good luck! 🚀

---
*This challenge is designed to showcase your Android development skills in a realistic project scenario. Focus on writing clean, maintainable code that demonstrates your understanding of mobile development best practices.*
