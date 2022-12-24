plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}


repositories {
    mavenCentral()
}

dependencies {

    implementation("org.apache.pdfbox:pdfbox:2.0.20")
}