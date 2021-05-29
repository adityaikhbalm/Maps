import constant.LibraryDependency.Network
import constant.TestLibraryDependency.Test

plugins {
    kotlinLibrary
    kotlinKapt
}

dependencies {
    implementAll(Network.components)

    testImplementation(Test.unitApi)
    testRuntimeOnly(Test.unitEngine)
    testImplementation(Test.kotest)
    testImplementation(Test.mockWebServer)

    kapt(Network.AnnotationProcessor.moshi)
}