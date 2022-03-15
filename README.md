# The One App Android

## Setup

1. APK is located at distribution folder.

2. I've put the API Bearer token ("Fo-aCrpsC3MmzzsCTApZ") to the gradle so anyone can build, though the correct way to do should be putting on a local properties file.

3. To retrieve a list of the movies, you first need to authenticate using a PIN or use your fingerprint if your phone has one.

4. The app uses offline-first mecanism so you just need to have an Internet connection in the first time you open the app because it will save the movies list into the database.


## Tech-stack

This project uses minSdk 21.

* Tech-stack
    * Kotlin - Project language
    * Koin - dependency injection framework
    * Retrofit - used for networking
    * Coroutines + Flow - a lightweight way to handle with asynchronous programming in kotlin
    * Jetpack
        * LiveData - notify views about change
        * Lifecycle - perform an action when lifecycle state changes
        * ViewModel - store and manage UI-related data in a lifecycle conscious way
    * Ktlint - static code analysis tool

* Architecture
    * Clean Architecture (modules divided by features and layers)
    * MVVM (presentation layer)
* Tests
    * JUnit4
    * Mockk
    * Kotest

## Architecture

I divided the project into modules (layers and feature modules) in order to try to decouple
as most as I could. The modularization approach has some benefits like:

- better separation of concerns;
- features can be developed in parallel eg. by different teams and they don't know each other, therefore they are easily decoupled.
- each feature can be developed in isolation, independently from other features;
- easier to test each component separately;
- easy to scale since every layer is very well divided;
- faster compile time;

### Module types and module dependencies

- `app` module - module where application class is started and also contains MainActivity, which is responsible for the authentication part of the app
- `core` module - the inner library module. Contains shared classes such as extensions and constants. It also exposes dependencies to higher-level modules.
- `domain` module - the core module of the project. It should not know about the Android framework. It is independent of other layers. It contains all the business rules (use case, entities, etc)
- `data` module - a library responsible to manage application data and exposes these data sources as repositories to the domain module.
- `presentation` - modules are the feature modules (character_search and character_details) - responsible for what user sees on the screen (UI).

### Domain Module:
Components:
- **UseCase** - contains business logic
- **Entity** - defines the core structure of the data that will be used within the application. This is the source of truth for application data.
- **Repository interface** - required to keep the `domain` layer independent from the `data layer`

### Data Module:
- **Repository** - is exposing data to the `domain` layer and also transform the data. The purpose of these operations is to create high-quality data source for the `domain` layer, not to perform any business logic (`domain` layer `use case` responsibility).
- **Mapper** - maps `data model (Data Transfer Object)` to `domain model` (to keep `domain` layer independent from the `data` layer).
- **RetrofitService** - responsible for calling the Starwars API
- **DTO(Data Transfer Object)** - defines the structure of the data retrieved from the network (used Gson to parse data)

#### Presentation layer
- **View (Activity)** - presents data on the screen and pass user interactions to View Model. ViewModel has to take the decisions, so it should be easier to unit test.
- **ViewModel** - exposed data through `LiveData` where state changes to the view and deals with user interactions.

2. Why Koin?
I've always used Koin for my projects since it's very easy to setup in comparison with Dagger. It's a service Locator framework
and it could lead to crashes in runtime since it locates for classes in runtime. Dagger, in the other hand, does not have this problem since it
generates classes in compile-time.

3. Why coroutines?
I personally like to use coroutines with Flow since it's a feature provided by the Kotlin language
therefore it's very simple to use it and also the syntax looks very natural since you can use kotlin
extensions to handle with asynchronous operations. I also used try/catch error handling in order
to emit Result of operations so my view could observe it.
## Improvements

1. UI Testing
2. MockWebServer
3. Proguard rules
4. CI
