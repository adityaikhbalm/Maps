import constant.LibraryDependency.AppComponent
import constant.LibraryDependency.ArchitectureComponent
import constant.LibraryDependency.Coroutines
import constant.LibraryDependency.Map
import constant.LibraryDependency.View

plugins {
    androidLibrary
    baseGradlePlugin
}

android {
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://maps.googleapis.com/maps/api/\"")
        buildConfigField("String", "API_KEY", "\"AIzaSyAeyMUJgKAhnNXbILHONb1um72CNzELFRY\"")
    }
}

dependencies {
    implementAll(ArchitectureComponent.components)
    implementAll(AppComponent.components)
    implementAll(Coroutines.components)
    implementAll(View.components)
    implementAll(Map.components)
}