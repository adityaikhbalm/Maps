package plugin

import com.android.build.gradle.BaseExtension
import constant.*
import org.gradle.api.Project
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureDefaultPlugins() {
    plugins.apply("kotlin-android")
}

private typealias AndroidBaseExtension = BaseExtension

internal fun Project.configureAndroidApp() =  this.extensions.getByType<AndroidBaseExtension>().run {
    compileSdkVersion(AndroidConfig.Version.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.Version.minSdkVersion)
        targetSdkVersion(AndroidConfig.Version.targetSdkVersion)
    }
    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    project.tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    buildFeatures.viewBinding = true
}

internal fun Project.configureDependencies() = this.dependencies {
    add("implementation", LibraryDependency.DI.koin)
}