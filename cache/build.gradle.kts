plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}
repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":entity"))
    implementation(project(":logger"))
    implementation(project(":path"))

    implementation("com.google.code.gson:gson:2.10")
}