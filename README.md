# Capstone News App

Capstone news app is a app I create for getting the news from public [NewsAPI](https://newsapi.org/).

## 🏠 Architectural Pattern - MVVM

---

First I have to say:

> There is no good or bad architecture, to be honest. There is only architecture you found easy to work with, maintain and test, and most importantly works well with your project.

There are many well-known architectural design patterns, for example, Model-View-Controller(MVC), Model-View-Presenter(MVP), and Model-View-ViewModel(MVVM). They have their pros and cons. MVC and MVP are good for small projects and for those who want a minimum viable product(MVP) up and running. However, MVC is known for its coupling of business logic with Model and View. This makes the APP hard to test and didn't follow the concept of separation of concern. MVP concentrates the business logic in the Presenter to decouple the Model from View to increase unit testability. However, the Presenter is still coupled with View(has reference to Activity and Fragment).

If you are aiming for larger project, future scalability and better testing. MVVM is a good choice.

### Mode-View-ViewModel(MVVM)

MVVM's main purpose is to achieve separation of concern through clear distinction bewteen the roles of each of it's layers. Which further solves the issue in MVC and MVP.

#### View

Display UI information to the user usually represent as **Activity** and **Fragment**. It's main responsibility is to _obeserve_ one or more ViewModels to update the UI accordingly.

#### Model

Retrieves information from your data source(local database, ex: **Room** or remote API) and exposes it to **ViewModel**. In Android, you usually create Models as **Kotlin data classes** to represent the information obtained from data source.

#### ViewModel

The ViewModel is the centerpiece of MVVM. It has all the business logic in it. ViewModel retrieves necessary information from the Model and applies operations to it(adapt information View needs to display) then expose it to the **View**.

Here I am using [**ViewModel**](https://developer.android.com/topic/libraries/architecture/viewmodel) which is an Android Architecture Component(part of Android Jetpack) to represent the ViewModel layer of MVVM. The ViewModel class is specially designed to manage and store information in a lifecycle-aware manner. This means that the data stored inside it can survive configuration/lifecycle changes like screen rotations.

### How does MVVM solves the issue in MVC and MVP?

MVVM solves the MVC issue by providing a better separation of concerns. Further more, MVVM solves the View and Presenter coupling issue in MVP by providing a **Binder** which usually exist in ViewModel layer.

What is **Binder**?

In Android you usually communicate data(state) between View and ViewModel with **Observables**, using library such as **LiveData**, **RxJava** or **DataBinding**. Here I am using **LiveData**. This is also known as **Binder** which decouple View from ViewModel. In other words Views can have a reference to ViewModels, but ViewModels have no information about the Views.

### MVVM advatages and disadvatage:

MVVM has better separation of concern, makes the code easier to test and easy to scale overtime. However, the downside is it may be too complex for a simple UI project.

---

View/custom view

Navigation:
Activity - Intent
Fragment - Jetpack navigation

Networking: Retrofit

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
- Databases: where Room generate the approapriate classes and methods. Room also take care of the vesioning of the database and also migrations if developers update the database.

## Dependency Injection(DI)

benifit of DI?
why Hilt?
Hilt

## Compose UI

## 🧪 Testing

Using MockK for unit test.

UI test: Expresso

## Future work:
