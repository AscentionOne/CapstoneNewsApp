# Capstone News App

**Capstone news** app is an app I create during **Kodeco 2022 Android Bootcamp**. This app is to illustrate what I had learned though out the Bootcamp. **Capstone news** gets the news from public [NewsAPI](https://newsapi.org/) and present it to the user.

This app is created in Kotlin combined with View-based and Compose UIs.

# Feature

## Screenshots

<img src="/images/screenshots/screenshot_1.png" alt="Screen shot of Capstone News App" height="480"/><img src="/images/screenshots/screenshot_3.png" alt="Screen shot of Capstone News App" height="480"/>

## 🏠 Architecture

I am using MVVM as the app architecture. MVVM is the most famous architecture currently for Android app development. It is suitable for a larger project, future scalability, and code testability since it has a clear separation of responsibility in each layer(Model, View, and ViewModel layers).

## ✨ UI

For the UI, I am combining View-based UIs and Compose UI. The purpose is to practice how to migrate and transits to Compose from traditional Views. Compose is a new way of building modern UI in Android. Compose have interoperability APIs to let developers add Compose to View-based design easier. For the Views, I am using pure Activity with XML layout.

## 🌐 Networking

While fetching the news from remote API I use `Retrofit`, a type-safe REST client for Android. The advantage of using Retrofit is it automatically serializes the JSON response and works well with other popular converters, for example, `Gson` and `Moshi`. Here I am using `Moshi`. Moreover, it removes lots of boilerplate code when implementing network requests. Retrofit handles threading automatically and also works well with Kotlin Coroutine (support `suspend` function).

## 🎠 Kotlin Coroutine

Asynchronous programming is also very important for the modern app. I use Kotlin Coroutine to manage asynchronous tasks. Coroutine let you write asynchronous code in a synchronous way that is easy to read and understand. It lets you run a block of code (ex: network request) in a particular thread without blocking the UI thread(Main thread).

## 📚 Data Persistence

For local database, I am using Room as the single source of truth. Room is an abstract layer on top of SQLite and also works well with Coroutine. It is also part of Android Jetpack architecture components.

## 💉 Dependency Injection

Since the app may have dependencies between UI state and data layer. For example, `ViewModel` may have a dependency on the `Repository`, and the Repository may have dependencies on remote API and local database. This is where Hilt comes into play. Hilt is a dependency injection library built on top of Dagger and is supported by Google. It lets developers write less boilerplate code and makes the app easier to test.

## 🧪 Testing

Testing is a very important part of the app development process. You want to make sure your code works well before releasing it to the public. This is also true when you are working on a large project with different teams. You do not want others to debug your code and waste everyone's valuable time.

**Unit testing**: I am using MockK, a mocking library for Kotlin.

**UI testing**: Espresso is used for View-based UI testing, here in the app is Activity. For testing Compose UI, Compose provides its own testing library and is interoperable with Espresso.

## Future work:

- Migrate the UIs to use pure Compose UIs
- Add more functionalities. For example:
  - Able to bookmark and favorite news articles
  - Has user settings page

# License

MIT License. See [license](LICENSE) for more information.
