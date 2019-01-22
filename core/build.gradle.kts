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

    "implementation"(Libraries.retrofit)
    "implementation"(Libraries.retrofitConverter)
    "implementation"(Libraries.okHttp)
    "implementation"(Libraries.okHttpLoggingInterceptor)

    "debugImplementation"(Libraries.leakCanaryDebug)
    "releaseImplementation"(Libraries.leakCanaryRelease)
    "debugImplementation"(Libraries.leakCanaryFragments)
}