plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.google.dagger:dagger:${Versions.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
}