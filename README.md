# kotlin-template
Kotlin project template following an MVVM approach.

The project comes with a basic **Dagger2** and **Architecture Components** implementation.

## External libraries used
* Dagger2: https://github.com/google/dagger
* RxJava2: https://github.com/ReactiveX/RxJava
* RxKotlin: https://github.com/ReactiveX/RxKotlin
* RxAndroid: https://github.com/ReactiveX/RxAndroid
* Retrofit (with OkHttp for logging): https://github.com/square/retrofit
* Moshi: https://github.com/square/moshi
* Timberkt: https://github.com/ajalt/timberkt

## The project has the following packages:

1. data: It contains all the data access and manipulation components, which are then aggregated in a single data manager class;
2. di: Components, modules and helper classes/interfaces for dependency injection;
3. ui: View classes (activities and fragments) along with their corresponding view models;
4. utils: Utility classes.

## Todo as soon as I have the time :) :
* RecyclerView;
* Add api call example (Retrofit and RxJava to fecth data, LiveData to populate the RecyclerView);
* Refactor DataManager to an Interactor specific to each Activity/Fragment;
* Refactor DatabaseHelper to a Repository-like structure;


## License
```
Copyright 2017 Ricardo Costeira

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
