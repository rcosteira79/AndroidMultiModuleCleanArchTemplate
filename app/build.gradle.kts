plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    //value("io.fabric")
    kotlin("kapt")
}

apply {
    from("$rootDir/jacoco.gradle")
}

android {
    compileSdkVersion(Android.compileSDK)

    dataBinding {
        isEnabled = true
    }

    defaultConfig {
        minSdkVersion(Android.minSDK)
        targetSdkVersion(Android.targetSDK)

        applicationId = Android.applicationId
        versionCode = Android.appVersionCode
        versionName = Android.appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            isUseProguard = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isShrinkResources = false
            isMinifyEnabled = false
            isDebuggable = true
            versionNameSuffix = "-debug"
            isTestCoverageEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.recyclerviewexample))
    implementation(project(Modules.rxjavatokotlinflows))

    implementation(Libraries.kotlinStdLib)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.coreKtx)
    implementation(AndroidLibraries.materialDesign)
    implementation(AndroidLibraries.supportAnnotations)

    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerAndroidSupport)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerAndroidProcessor)

    debugImplementation(Libraries.leakCanary)

    testImplementation(TestingLibraries.junit)
    testImplementation(TestingLibraries.testRunner)

}

/*
apply {
    plugin("com.google.gms.google-services")
}*/
