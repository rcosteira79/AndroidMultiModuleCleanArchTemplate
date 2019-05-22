# AndroidMultiModuleCleanArchTemplate

Android template project following a multi module approach with clean architecture.

## External libraries used

- Dagger2: https://github.com/google/dagger
- Retrofit (with OkHttp for logging): https://github.com/square/retrofit
- Moshi: https://github.com/square/moshi
- Kotlin Coroutines Adapter: https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter
- Timber: https://github.com/JakeWharton/timber
- Firebase Crashlytics: https://firebase.google.com/docs/crashlytics/get-started#android
- Gradle Versions Plugin: https://github.com/ben-manes/gradle-versions-plugin

## The project has the following modules:

1. app: Base module for app launch. Responsible for dependency graph creation and instantiating the only activity in the whole project.
2. buildSrc: Module used by Gradle to manage dependencies;
3. core: Module with common code;
4. Logging: Module responsible for managing the logging system and Crashlytics;
5. recyclerviewexample: Module that demonstrates a recycler view implementation with Architecture Components, Coroutines and Retrofit

## In progress

- recyclerviewexample module

## Todo as soon as I have the time :) (in order):

- Add template module with navigation logic to any existent example modules

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
