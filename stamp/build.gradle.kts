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
    implementation(project(":mail"))
    implementation(project(":path"))
    implementation(project(":cache"))

    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation("com.squareup.retrofit2:converter-scalars:2.7.2")
}
