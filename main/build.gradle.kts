plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
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

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}
