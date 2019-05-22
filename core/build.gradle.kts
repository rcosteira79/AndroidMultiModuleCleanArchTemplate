apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "api"(project(Modules.logging))

    "api"(Libraries.coroutines)
    "api"(Libraries.coroutinesAndroid)
    "api"(AndroidLibraries.materialDesign)
    "api"(AndroidLibraries.navigationCore)
    "api"(AndroidLibraries.navigationUI)
    "implementation"(AndroidLibraries.lifecycleExtensions)

    //"implementation"(Libraries.firebaseCore)
    //"implementation"(Libraries.crashlytics)

    "api"(Libraries.retrofit)
    "api"(Libraries.retrofitConverter)
    "api"(Libraries.retrofitCoroutineAdapter)
    "api"(Libraries.okHttp)
    "api"(Libraries.okHttpLoggingInterceptor)

    "debugImplementation"(Libraries.leakCanary)
}