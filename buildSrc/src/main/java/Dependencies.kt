object Modules {
    const val core = ":core"
    const val logging = ":logging"
    const val feature = ":feature"
}

object Versions {
    // Plugins
    const val androidGradlePlugin = "3.3.0"
    const val gradleVersionsPlugin = "0.20.0"

    // Kotlin version
    const val kotlin = "1.3.11"

    // Android libraries
    const val support = "1.0.2"
    const val supportAnnotations = "1.0.1"
    const val coreKtx = "1.0.1"
    const val materialDesign = "1.1.0-alpha02"
    const val constraintLayout = "1.1.3"
    const val coroutines = "1.0.1"
    const val room = "2.1.0-alpha03"
    const val navigation = "1.0.0-alpha09"
    const val lifecycleExtenstions = "2.0.0-rc01"
    const val dataBinding = "3.2.1"
    const val recyclerView = "1.0.0"

    // Testing
    const val junit = "4.12"
    const val espresso = "3.1.1"
    const val testRunner = "1.1.1"
    const val jacoco = "0.8.2"

    // Logging and error handling
    const val timber = "4.7.1"
    const val fabric = "1.26.1"
    const val firebaseCore = "16.0.6"
    const val crashlytics = "2.9.7"
    const val googleServices = "4.2.0"
    const val leakCanary = "1.6.2"

    // Dependency injection
    // TODO 2.17 and above generates a compiler error that has nothing to do with Dagger. More info in
    // TODO https://github.com/google/dagger/issues/1245
    const val dagger = "2.16"

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
    const val leakCanaryDebug = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val leakCanaryRelease =
        "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
    const val leakCanaryFragments =
        "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.leakCanary}"

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
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtenstions}"
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