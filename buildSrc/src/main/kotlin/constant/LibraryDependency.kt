package constant

interface Libraries {
    val components: List<String>
}

object LibraryDependency {
    object ArchitectureComponent: Libraries {
        object Version {
            const val coreKtx: String = "1.5.0-rc02"
            const val lifeCycle: String = "2.3.1"
        }

        private const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"
        private const val lifeCycle: String = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifeCycle}"
        private const val viewModel: String =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.lifeCycle}"

        override val components: List<String>
            get() = listOf(coreKtx, lifeCycle, viewModel)
    }

    object AppComponent : Libraries {
        object Version {
            const val appCompat: String = "1.3.0-rc01"
            const val activity: String = "1.3.0-alpha07"
            const val fragment: String = "1.3.2"
        }

        private const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"
        private const val activity: String = "androidx.activity:activity-ktx:${Version.activity}"
        private const val fragment: String = "androidx.fragment:fragment-ktx:${Version.fragment}"

        override val components: List<String>
            get() = listOf(appCompat, activity, fragment)
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.5.0"
        }

        private const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        private const val android: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, android)
    }

    object DI {
        object Version {
            const val koin: String = "3.0.2"
        }

        const val core: String = "io.insert-koin:koin-core:${Version.koin}"
        const val koin: String = "io.insert-koin:koin-android:${Version.koin}"
    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.9.1"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.12.0"
        }

        object AnnotationProcessor {
            const val moshi: String = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        private const val retrofitMoshi: String =
            "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        private const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String> = listOf(
            okhttp, loggingInterceptor, retrofit, retrofitMoshi, moshi
        )
    }

    object View : Libraries {
        object Version {
            const val constraintLayout: String = "2.0.4"
            const val material: String = "1.3.0"
            const val recyclerView: String = "1.2.0"
            const val overScroll: String = "1.1.0"
        }

        private const val constraintLayout: String = "androidx.constraintlayout:constraintlayout:${Version.overScroll}"
        private const val material: String = "com.google.android.material:material:${Version.material}"
        private const val recyclerview: String = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        private const val overScroll: String = "me.everything:overscroll-decor-android:${Version.overScroll}"

        override val components: List<String>
            get() = listOf(constraintLayout, material, recyclerview, overScroll)
    }

    object Map : Libraries {
        object Version {
            const val map: String = "17.0.0"
            const val mapKtx: String = "3.0.0"
        }

        private const val map: String = "com.google.android.gms:play-services-maps:${Version.map}"
        private const val location: String = "com.google.android.gms:play-services-location:${Version.map}"
        private const val mapKtx: String = "com.google.maps.android:maps-ktx:${Version.mapKtx}"

        override val components: List<String>
            get() = listOf(map, location, mapKtx)
    }
}