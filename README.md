# SFTAssignment

## Requirements Asked: 
1. Show a Splash screen with white background and desired image in centre for 5 sec. [done]
2. After 5 secs ,from splash screen user has to be navigated to a page which has a list [done]
3. In List page, each row will have a image, Title and description [done]
4. Each row should be the right height to display its own content and no taller. No content should be clipped. This means some rows will be larger than others. [done]
5. Don't download all images at once, only as needed. [done]
6. Refresh function, either a refresh button or use pull down to refresh. [done]
7. On click of each cell, display a dialog which shows the description. [done]
8. You can use a third-party Json parser to parse this if desired. [done]
9. Use MVVM Architecture [done]


## Tech stack used for the app
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) 
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) 
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Paging 3 - For recycler view pagination.
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - for REST APIs and network calls.
- [Glide](https://github.com/bumptech/glide) - For image loading.
- Swipe to refresh layout.

## Architecture
This app is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)
