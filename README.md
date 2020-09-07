# MVVM, Dependency Injection, Coroutines and Repository Pattern Application 


LealAPP is in charge of loading some transactions from the server along with the user data and display them in a Drawer and on the main screen.
The user can swipe to delete the transactions from the main content area, refresh the transacions or delete them all.

## Installation
Clone this repository and import into **Android Studio** then click on **run** with a phone or emulator connected to your computer.

```bash
git clone https://github.com/ndmunoz10/LealAppTest.git
```

## Architecture

![MVVM3](https://i.ibb.co/xF2BhrG/Leal-Architecture.png)

## Why this way?

It's a goal for every developer to have a clean architecture, easy to escalate, easy to change, easy to test, etc. That's what this architecture is doing.
Since everything is separated in many layers, everything has a single responsibility and every feature is easy to locate for new developers that want to contribute to this proyect.
Since we're also taking advantage of the Inversion of Control principle, testing the code becomes really easy and increasing code coverage won't be a problem.

## Why using these library components?
- In the case of persistance, there are many solutions out there that can satisfy this requirement. However, Room from Jetpack has demonstrated its power on Android Development and how easy
it is to use and implement. It makes migrations along with SQL Statements and more how everything should be, easy.

- It is also important to provide the best user experience when developing software, that's why it's important not to do things such as blocking the main thread and try to keep
it as light as possible. To accomplish this, Kotlin Coroutines (as light weight threads) were used because they provide ways of selecting the type of heavy work that can be done
in the application in different types of threads and then notifying the UI (Main Thread) when these tasks are completed. This ensures that the main thread is never block and that it must change
only

- To improve how easy it is to test code, there must be some principle of Dependency Inversion, this means that it's not the object who determines how its dependencies are instantiated
but it's its parent's reponsibility. This way, instances are easy to mock therefore the code is easy to test, that's why we used a Hilt, a library also part of Jetpack 
for Dependency Injection which is one of the Design Patterns that fulfill this task (Service Locator is another one).

## Built With

1. CircleImageView from hdodenhof
2. Coroutines from Kotlin
3. Glide from Bumptech
4. Hilt from Jetpack
5. LiveData from Jetpack
6. Material from Material Design
7. Mockito from Mockito for testing
8. Retrofit from Square
9. Room from Jetpack
10. Shimmer from Facebook
11. ViewModel from Jetpack

## Additinal Feature
# Try Dark Mode!

## Maintainers

* **Nicolás David Muñoz Cuervo**
