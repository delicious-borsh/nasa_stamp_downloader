plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":imageprocessor"))
    implementation(project(":entity"))
    implementation(project(":logger"))
    implementation(project(":mail"))
    implementation(project(":path"))
    implementation(project(":stamp"))
    implementation(project(":cache"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("com.google.dagger:dagger:${Versions.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
}
