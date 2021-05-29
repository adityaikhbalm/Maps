import constant.LibraryDependency.DI

plugins {
    kotlin
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(DI.core)
}