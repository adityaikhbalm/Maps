import constant.AndroidConfig

plugins {
    androidApplication
    baseGradlePlugin
    kotlinKapt
}

android {
    defaultConfig {
        applicationId = AndroidConfig.Android.applicationId
        versionCode = AndroidConfig.Version.versionCode
        versionName = AndroidConfig.Version.versionName
    }
}

dependencies {
    implementation(project(constant.ModulesDependency.featureMaps))
}