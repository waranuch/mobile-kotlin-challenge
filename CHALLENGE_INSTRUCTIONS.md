# Mobile Kotlin Challenge - Instructions for Recruiters and Hiring Managers

## How to Use This Challenge

This repository contains a comprehensive Kotlin Android development challenge designed to assess candidates' skills in mobile app development. The challenge involves building a **Product Catalog App** with core e-commerce features.

### Challenge Overview
- **Duration**: 3-4 hours
- **Skill Level**: Mid to Senior Android Developer
- **Focus Areas**: Kotlin, Android SDK, MVVM architecture, REST API integration, UI/UX

### What's Included in This Repository

1. **Complete Android Project Structure**
   - Pre-configured Gradle files with all necessary dependencies
   - Basic navigation setup using Android Navigation Component
   - Material Design 3 theming and colors
   - Starter layouts for key screens (product list, product detail)
   - API models and interfaces for the FakeStore API

2. **Comprehensive Documentation**
   - `README.md` - Main challenge requirements and specifications
   - `SETUP_GUIDE.md` - Detailed setup instructions for Java and Android Studio
   - `CHALLENGE_INSTRUCTIONS.md` - This file for recruiters

3. **Pre-built Infrastructure**
   - Network layer setup (Retrofit, OkHttp)
   - Image loading configuration (Glide)
   - Room database configuration for local storage
   - Navigation graph with safe args

### How to Administer the Challenge

#### Before the Interview
1. **Send Repository**: Share this repository with the candidate 24-48 hours before the coding session
2. **Environment Setup**: Ask them to follow the `SETUP_GUIDE.md` to install Java and Android Studio
3. **API Testing**: Have them test the FakeStore API endpoints to ensure connectivity

#### During the Challenge
1. **Time Management**: Give candidates 3-4 hours total
2. **Priorities**: Emphasize core functionality over bonus features
3. **Questions**: Allow clarification questions within the first 30 minutes
4. **Submission**: Ask for both source code and a working APK

#### What to Look For

**Core Implementation (Must Have)**
- [ ] Product list displaying items from API
- [ ] Search functionality with real-time filtering
- [ ] Product detail screen with navigation
- [ ] Favorites functionality with local persistence
- [ ] Proper error handling and loading states

**Code Quality Indicators**
- [ ] Clean MVVM/MVP architecture
- [ ] Proper separation of concerns
- [ ] Efficient use of Kotlin features
- [ ] Appropriate use of coroutines for async operations
- [ ] Proper lifecycle management

**UI/UX Assessment**
- [ ] Material Design compliance
- [ ] Responsive layouts
- [ ] Smooth animations and transitions
- [ ] Proper loading states and error handling
- [ ] Accessibility considerations

**Advanced Features (Bonus)**
- [ ] Unit tests for ViewModels
- [ ] Repository pattern implementation
- [ ] Dependency injection (Hilt/Dagger)
- [ ] Dark mode support
- [ ] Pull-to-refresh functionality
- [ ] Category filtering
- [ ] Offline support

### Evaluation Rubric

#### Excellent (9-10 points)
- All core features implemented flawlessly
- Clean, well-structured code following Android best practices
- Excellent error handling and user feedback
- Beautiful, responsive UI with smooth interactions
- Bonus features implemented

#### Good (7-8 points)
- All core features working with minor issues
- Generally clean code with good architecture
- Basic error handling implemented
- UI follows Material Design with minor inconsistencies
- One or two bonus features

#### Satisfactory (5-6 points)
- Most core features working
- Decent code structure with some architectural issues
- Limited error handling
- Basic UI that functions but lacks polish
- Focus on functionality over aesthetics

#### Needs Improvement (1-4 points)
- Some core features missing or broken
- Poor code organization or architecture
- Minimal error handling
- UI issues or poor user experience
- Incomplete implementation

### Common Pitfalls to Watch For

**Red Flags**
- Hardcoded values instead of using API data
- No error handling for network requests
- Memory leaks (not canceling coroutines)
- Blocking UI thread with long operations
- Poor naming conventions or code organization

**Green Flags**
- Proper use of ViewModels and LiveData/Flow
- Efficient RecyclerView implementation
- Proper fragment/activity lifecycle handling
- Good use of Kotlin language features
- Thoughtful UI/UX considerations

### Follow-up Discussion Topics

After reviewing the code, consider discussing:

1. **Architecture Decisions**: Why did they choose their specific architecture?
2. **Performance Considerations**: How would they optimize for large datasets?
3. **Testing Strategy**: How would they add comprehensive testing?
4. **Scalability**: How would they extend this for a production app?
5. **Error Handling**: How would they improve error handling and user feedback?

### Alternative API Option

If the FakeStore API is unavailable, you can provide this mock data structure:

```json
{
  "products": [
    {
      "id": 1,
      "title": "Sample Product",
      "price": 99.99,
      "description": "This is a sample product description.",
      "category": "electronics",
      "image": "https://via.placeholder.com/300",
      "rating": {
        "rate": 4.5,
        "count": 120
      }
    }
  ]
}
```

### Time Allocation Guidance

Suggest this breakdown to candidates:
- **30 minutes**: Project setup and API exploration
- **90 minutes**: Core functionality (product list, navigation, detail screen)
- **60 minutes**: Search and favorites features
- **30 minutes**: Error handling and polish
- **30 minutes**: Bonus features (if time permits)

### Contact Information

For any questions about administering this challenge, please contact [your-email@company.com].

---

*This challenge is designed to be fair, comprehensive, and representative of real-world Android development tasks. The pre-built infrastructure allows candidates to focus on demonstrating their skills rather than spending time on boilerplate setup.*
