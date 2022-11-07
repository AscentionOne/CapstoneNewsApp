# Capstone News App

![alt text](/images/screenshots/screenshot_1.png 'Screen shot of Capstone News App')
![alt text](/images/screenshots/screenshot_1_rs.png 'Screen shot of Capstone News App')

# About

Capstone news app is an app I create during Kodeco 2022 Android Bootcamp. The main purpose of the app is to get the news from public [NewsAPI](https://newsapi.org/) and present it to the user.

I am using MVVM as the app architecture. MVVM is the most famous architecture currently for Android app development. It is suitable for a larger project, future scalability, and code testability since it has a clear separation of responsibility in each layer(Model, View, and ViewModel layers). For the view, I am using pure Activity with XML layout. I also adopt the Compose UI to the app, a new way of building modern UI in Android.

While fetching the remote API I use Retrofit, a type-safe REST client for Android. The advantage of using Retrofit is it automatically serializes the JSON response and also works well with other popular converters, for example, Gson and Moshi. Here I am using Moshi. Retrofit handles threading automatically and works well with Kotlin Coroutine (support suspend function).

Asynchronous programming is also very important for the modern app. I use Kotlin Coroutine to manage asynchronous tasks. Coroutine let you write asynchronous code in a synchronous way that is easy to read and understand. It lets you run a block of code (ex: network request) in a particular thread without blocking the UI thread.

For data persistence, I am using Room as the single source of truth. Room is an abstract layer on top of SQLite and works well with Coroutine.

Since the app may have dependencies, for example, ViewModel may have a dependency on the repository, and the repository may have dependencies on remote API and local database. This is where Hilt comes into play. Hilt is a dependency injection library built on top of Dagger and is supported by Google. It lets developers write less boilerplate code and makes the app easier to test.

Testing is a very important part of the app development process. You want to make sure your code works well before releasing it to the public. This is also true when you are working on a large project with different teams. You do not want others to debug your code and waste everyone's valuable time.For testing, I am using MockK, a mocking library for Kotlin, for unit testing. Espresso for UI testing.

add app image

## Table of content

[Architecture](#🏠-architectural-pattern---mvvm)

[Di](#dependency-injectiondi)

## 🏠 Architectural Pattern - MVVM

---

First I have to say:

> There is no good or bad architecture, to be honest. There is only architecture you found easy to work with, maintain and test, and most importantly works well with your project.

There are many well-known architectural design patterns, for example, Model-View-Controller(MVC), Model-View-Presenter(MVP), and Model-View-ViewModel(MVVM). They have their pros and cons. MVC and MVP are good for small projects and for those who want a minimum viable product(MVP) up and running. However, MVC is known for its coupling of business logic with Model and View. This makes the APP hard to test and didn't follow the concept of separation of concern. MVP concentrates the business logic in the Presenter to decouple the Model from View to increase unit testability. However, the Presenter is still coupled with View(has reference to Activity and Fragment).

If you are aiming for larger project, future scalability and better testing. MVVM is a good choice.

### Mode-View-ViewModel(MVVM)

MVVM's main purpose is to achieve separation of concern through clear distinction between the roles of each of it's layers. Which further solves the issue in MVC and MVP.

#### View

Display UI information to the user usually represent as **Activity** and **Fragment**. It's main responsibility is to _observe_ one or more ViewModels to update the UI accordingly.

#### Model

Retrieves information from your data source(local database, ex: **Room** or remote API) and exposes it to **ViewModel**. In Android, you usually create Models as **Kotlin data classes** to represent the information obtained from data source.

#### ViewModel

The ViewModel is the centerpiece of MVVM. It has all the business logic in it. ViewModel retrieves necessary information from the Model and applies operations to it(adapt information View needs to display) then expose it to the **View**.

Here I am using [**ViewModel**](https://developer.android.com/topic/libraries/architecture/viewmodel) which is an Android Architecture Component(AAC, part of Android Jetpack) to represent the ViewModel layer of MVVM. The ViewModel class is specially designed to manage and store information in a lifecycle-aware manner. This means that the data stored inside it can survive configuration/lifecycle changes like screen rotations.

benefits:

- Survives config changes: longer lifetime than the screen
- Jetpack integration
  - Jetpack Navigation: ViewModel is cached when the destination is in the back stack. Destroyed when it's not.
- Other libraries
  - Hilt - easy injects with `@HiltViewModel`

### How does MVVM solves the issue in MVC and MVP?

MVVM solves the MVC issue by providing a better separation of concerns. Further more, MVVM solves the View and Presenter coupling issue in MVP by providing a **Binder** which usually exist in ViewModel layer.

What is **Binder**?

In Android you usually communicate data(state) between View and ViewModel with **Observables**, using library such as **LiveData**, **RxJava** or **DataBinding**. Here I am using **LiveData**. This is also known as **Binder** which decouple View from ViewModel. In other words Views can have a reference to ViewModels, but ViewModels have no information about the Views.

### MVVM advantages and disadvantage:

MVVM has better separation of concern, makes the code easier to test and easy to scale overtime. However, the downside is it may be too complex for a simple UI project.

---

View/custom view

Navigation:
Activity - Intent
Fragment - Jetpack navigation

Networking: Retrofit
Retrofit 2 by default leverages OkHttp as the networking layer and is built on top of it.

## Threading: Coroutine

---

### LiveData:

Part of Android Jetpack. LiveData is a Observer pattern improved with lifecycle awareness. The awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

### Flow:

## Local database: Room

Room is a Jetpack component.
Usually contains three type of classes:

- Entities: represent the object that you want to store into the database. You define each Room entity as a class(data class) that is annotated with `@Entity` and must have a field annotated with `@PrimaryKey` as unique identifier for each entity.
- Data access objects: also known as DAOs. The DAO implements CRUD (Create-Read-Update-Delete) operations to database.
- Databases: where Room generate the appropriate classes and methods. Room also take care of the versioning of the database and also migrations if developers update the database.

## Dependency Injection(DI)

benefit of DI?
why Hilt?
Hilt

## Compose UI (Compose View)

interoperability with xml

## 🧪 Testing

Using MockK for unit test.

UI test: Espresso

## Future work:

Go through App fundamentals
content provider: pass the data from a app to another app

Intent:
1 MB limit
implecite intent
explicit intent

Activity - intent with parcelable
Fragment - through navgraph
