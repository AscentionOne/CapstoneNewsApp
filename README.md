# Capstone News App

Capstone news app is an app I create during Kodeco 2022 Android Bootcamp. The main purpose of the app is to get the news from public [NewsAPI](https://newsapi.org/) and present it to the user.

# Feature

## Screenshots

<img src="/images/screenshots/screenshot_1.png" alt="Screen shot of Capstone News App" height="480"/>
<img src="/images/screenshots/screenshot_3.png" alt="Screen shot of Capstone News App" height="480"/>

## Architecture

I am using MVVM as the app architecture. MVVM is the most famous architecture currently for Android app development. It is suitable for a larger project, future scalability, and code testability since it has a clear separation of responsibility in each layer(Model, View, and ViewModel layers).

## UI

For the view, I am using pure Activity with XML layout. I also adopt the Compose UI to the app, a new way of building modern UI in Android.

## Networking

While fetching the remote API I use Retrofit, a type-safe REST client for Android. The advantage of using Retrofit is it automatically serializes the JSON response and also works well with other popular converters, for example, Gson and Moshi. Here I am using Moshi. Retrofit handles threading automatically and works well with Kotlin Coroutine (support suspend function).

## Kotlin Coroutine

Asynchronous programming is also very important for the modern app. I use Kotlin Coroutine to manage asynchronous tasks. Coroutine let you write asynchronous code in a synchronous way that is easy to read and understand. It lets you run a block of code (ex: network request) in a particular thread without blocking the UI thread.

## Data Persistence

For data persistence, I am using Room as the single source of truth. Room is an abstract layer on top of SQLite and works well with Coroutine.

## Dependency Injection

Since the app may have dependencies, for example, ViewModel may have a dependency on the repository, and the repository may have dependencies on remote API and local database. This is where Hilt comes into play. Hilt is a dependency injection library built on top of Dagger and is supported by Google. It lets developers write less boilerplate code and makes the app easier to test.

## Testing

Testing is a very important part of the app development process. You want to make sure your code works well before releasing it to the public. This is also true when you are working on a large project with different teams. You do not want others to debug your code and waste everyone's valuable time.For testing, I am using MockK, a mocking library for Kotlin, for unit testing. Espresso for UI testing.

## Future work:

# License

MIT License. See [license](LICENSE) for more information.
