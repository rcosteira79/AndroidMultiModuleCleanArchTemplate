apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "implementation"(Libraries.timber)
    //"implementation"(Libraries.crashlytics)
}

