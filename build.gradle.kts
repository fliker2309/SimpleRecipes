buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.11.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.56.2")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.9.1")
    }
}
plugins {
    id("com.google.devtools.ksp") version "1.9.22-1.0.16" apply false
}

task("clean") {
    doLast {
        delete(rootProject.buildDir)
    }
}
