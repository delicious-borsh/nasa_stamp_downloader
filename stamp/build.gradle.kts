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

    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation("com.squareup.retrofit2:converter-scalars:2.7.2")

    implementation("com.google.api-client:google-api-client:2.0.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
    implementation("com.google.apis:google-api-services-gmail:v1-rev20220404-2.0.0")

}
