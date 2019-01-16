// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply {
    plugin("com.github.ben-manes.versions")
}

buildscript {
    repositories {
        google()
        // maven { url = uri("https://maven.fabric.io/public") }
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.androidGradlePlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        //classpath("com.google.gms:google-services:${Versions.googleServices}")
        //classpath("io.fabric.tools:gradle:${Versions.fabric}")
        classpath("org.jacoco:org.jacoco.core:${Versions.jacoco}")
        classpath("com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        maven { url = uri("https://maven.google.com") }
        mavenCentral()
        jcenter()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}