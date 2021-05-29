package constant

object TestLibraryDependency {
    object Test {
        object Version {
            const val jUnit: String = "5.6.2"
            const val kotest: String = "4.4.3"
            const val coroutineTest: String = "1.5.0"
            const val mockWebServer: String = "4.9.1"
        }

        const val unitApi: String = "org.junit.jupiter:junit-jupiter-api:${Version.jUnit}"
        const val unitEngine: String = "org.junit.jupiter:junit-jupiter-engine:${Version.jUnit}"
        const val kotest: String = "io.kotest:kotest-runner-junit5:${Version.kotest}"
        const val coroutinesTest: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutineTest}"
        const val mockWebServer: String =
            "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
    }
}