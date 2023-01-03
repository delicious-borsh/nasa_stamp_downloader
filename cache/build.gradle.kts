plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":entity"))
    implementation(project(":logger"))
    implementation(project(":path"))

    implementation("com.google.code.gson:gson:2.10")

    implementation("com.google.dagger:dagger:${Versions.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
}