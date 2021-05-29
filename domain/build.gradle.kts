import constant.TestLibraryDependency.Test

plugins {
    kotlinLibrary
}

dependencies {
    testImplementation(Test.unitApi)
    testRuntimeOnly(Test.unitEngine)
    testImplementation(Test.kotest)
    testImplementation(Test.coroutinesTest)
}