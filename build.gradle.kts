import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val kotlin_version by extra("1.5.0")
    repositories.applyDefault()
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories.applyDefault()
    repositories {
        maven("https://jitpack.io")
    }
}

subprojects {
    applySpotless
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
            "-Xuse-experimental=" +
                    "kotlin.Experimental," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "kotlinx.coroutines.InternalCoroutinesApi," +
                    "kotlinx.coroutines.FlowPreview"
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}