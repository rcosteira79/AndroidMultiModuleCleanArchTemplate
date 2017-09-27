# kotlin-template
Kotlin project template following an MVVM approach.

The project comes with a basic **Dagger2** and **Architecture Components** implementation.

### External libraries used
* Dagger2;
* RxJava2;
* RxKotlin;
* RxAndroid;
* Retrofit;
* OkHttp;
* Moshi;
* Timberkt.

### The project has the following packages:

1. data: It contains all the data access and manipulation components, which are then aggregated in a single data manager class (a.k.a. repository);
2. di: Components, modules and helper classes/interfaces for dependency injection
3. ui: View classes (fragments) along with their corresponding view models;
4. utils: Utility classes (AppConstants, etc);



**This project was heavily based on [Google's Github browser example](https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample)**
