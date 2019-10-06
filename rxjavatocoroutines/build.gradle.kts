apply { from("$rootDir/android-library-build.gradle") }

dependencies {
    "implementation"(project(Modules.core))
    "androidTestImplementation"(project(Modules.core))

    "implementation"(Libraries.rxJava)
    "implementation"(Libraries.rxAndroid)

    "implementation"(AndroidLibraries.constraintLayout)
    "androidTestImplementation"(TestingLibraries.coroutinesTest)
}
