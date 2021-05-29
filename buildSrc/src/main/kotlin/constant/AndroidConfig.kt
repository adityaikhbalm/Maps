package constant

object AndroidConfig {
    object Version {
        private const val versionMajor = 1
        private const val versionMinor = 0
        private const val versionPatch = 0

        const val versionCode = versionMajor * 100 + versionMinor * 10 + versionPatch
        const val versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

        const val compileSdkVersion = 30
        const val targetSdkVersion = 30
        const val minSdkVersion = 21
    }

    object Android {
        const val applicationId: String = "com.adityaikhbalm.pricehunter"
        const val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
        const val testInstrumentationRunnerArgument: String = "de.mannodermaus.junit5.AndroidJUnit5Builder"
    }
}

interface BuildType {
    companion object {
        const val DEBUG: String = "debug"
        const val RELEASE: String = "release"
    }

    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true

    const val applicationIdSuffix: String = ".debug"
    const val versionNameSuffix: String = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean = true
    override val isTestCoverageEnabled: Boolean = false
}