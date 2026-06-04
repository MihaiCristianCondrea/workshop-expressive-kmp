import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
}

group = "com.design.workshop.expressive"
version = "1.0.0"

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    wasmJs {
        browser()
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            api(libs.compose.icon.collections.tabler)
            api(libs.compose.icon.collections.octicons)
            api(libs.compose.icon.collections.remix)
            api(libs.compose.icon.collections.boxicons)
            api(libs.compose.icon.collections.feather)
            api(libs.compose.icon.collections.ionicons)
            api(libs.compose.icon.collections.fontawesome)
            api(libs.compose.icon.collections.heroicons)
            api(libs.compose.icon.collections.simpleicons)
            api(libs.compose.icon.collections.twbs)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
        }
    }
}
