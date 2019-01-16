# android-kotlin-template
Android Kotlin project template following an MVVM approach.

The project comes with a basic **Dagger2** and **Architecture Components** implementation.

## External libraries used
* Dagger2: https://github.com/google/dagger
* Retrofit (with OkHttp for logging): https://github.com/square/retrofit
* Moshi: https://github.com/square/moshi
* Timber: https://github.com/JakeWharton/timber
* Firebase Crashlytics: https://firebase.google.com/docs/crashlytics/get-started#android
* Gradle Versions Plugin: https://github.com/ben-manes/gradle-versions-plugin

## The project has the following packages:

1. data: It contains all the data access and manipulation components, which are then aggregated in a single data manager class;
2. di: Components, modules and helper classes/interfaces for dependency injection;
3. ui: View classes (activities and fragments) along with their corresponding view models;
4. utils: Utility classes.

## In progress
* Revamp project to multi module application following a Clean Architecture approach
* Delete DataManager and start using repositories for each use case;

## Todo as soon as I have the time :) (in order):

* RecyclerView and Data Binding example;
* Add api call example (Retrofit and coroutines to fecth data, LiveData to populate the RecyclerView);

## License
```
Copyright 2019 Ricardo Costeira

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
