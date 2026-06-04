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
            api(libs.compose.icon.collections.remix)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
        }
    }
}
