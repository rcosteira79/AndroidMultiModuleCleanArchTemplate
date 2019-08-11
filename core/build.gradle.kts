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

    "api"(AndroidLibraries.room)
    "kapt"(AndroidLibraries.roomCompiler)
    "api"(AndroidLibraries.roomCoroutines)
    "api"(AndroidLibraries.roomRxJava)

    //"implementation"(Libraries.firebaseCore)
    //"implementation"(Libraries.crashlytics)

    "api"(Libraries.retrofit)
    "api"(Libraries.retrofitConverter)
    "api"(Libraries.okHttp)
    "api"(Libraries.okHttpLoggingInterceptor)
    "api"(Libraries.rxJava2Adapter)

    "implementation"(Libraries.rxAndroid)
    "implementation"(Libraries.rxJava)

    "debugImplementation"(Libraries.leakCanary)
}
