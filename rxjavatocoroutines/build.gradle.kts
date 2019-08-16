apply {
    from("$rootDir/android-library-build.gradle")
}

dependencies {
    "implementation"(project(Modules.core))

    "implementation"(Libraries.rxJava)
    "implementation"(Libraries.rxAndroid)

    "implementation"(AndroidLibraries.constraintLayout)
}
