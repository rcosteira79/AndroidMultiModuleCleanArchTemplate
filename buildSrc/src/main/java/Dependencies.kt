object Modules {
    const val core = ":core"
    const val logging = ":logging"
    const val feature = ":feature"
}

object Versions {
    // Plugins
    const val androidGradlePlugin = "3.3.0"
    const val gradleVersionsPlugin = "0.21.0"

    // Kotlin version
    const val kotlin = "1.3.31"
    const val coroutines = "1.2.1"

    // Android libraries
    const val support = "1.1.0-alpha05"
    const val supportAnnotations = "1.1.0-rc01"
    const val coreKtx = "1.2.0-alpha01"
    const val materialDesign = "1.1.0-alpha06"
    const val constraintLayout = "2.0.0-beta1"
    const val room = "2.1.0-beta01"
    const val navigation = "1.0.0"
    const val dataBinding = "3.2.1"
    const val recyclerView = "1.1.0-alpha05"
    const val lifecycleExtensions = "2.0.0"


    // Testing
    const val junit = "4.12"
    const val espresso = "3.2.0-beta01"
    const val testRunner = "1.2.0-beta01"
    const val jacoco = "0.8.4"

    // Logging and error handling
    const val timber = "4.7.1"
    const val fabric = "1.29.0"
    const val firebaseCore = "16.0.9"
    const val crashlytics = "2.10.0"
    const val googleServices = "4.2.0"
    const val leakCanary = "2.0-alpha-1"

    // Dependency injection
    const val dagger = "2.22.1"

    // Network
    const val retrofit = "2.5.0"
    const val okHttp = "3.12.1"
}

object Android {
    const val applicationId = "com.rcosteira.androidkotlintemplate"
    const val minSDK = 16
    const val compileSDK = 28
    const val targetSDK = 28
    const val appVersionCode = 1
    const val appVersionName = "1.0.0"
}

object Libraries {
    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    // Leak canary
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
}

object AndroidLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val supportAnnotations = "androidx.annotation:annotation:${Versions.supportAnnotations}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomCoroutines = "androidx.room:room-coroutines:${Versions.room}"
    const val navigationCore =
        "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val dataBinding = "androidx.databinding:databinding-compiler:${Versions.dataBinding}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
}

object TestingLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val roomTestHelpers = "androidx.room:room-testing:${Versions.room}"
    const val navigationTesting =
        "android.arch.navigation:navigation-testing:${Versions.navigation}"
}