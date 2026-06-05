import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
}

group = "com.design.workshop.expressive"
version = "0.0.6"

compose.resources {
    publicResClass = true
}

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    android {
        namespace = "com.design.workshop.expressive.ui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    iosArm64()
    iosSimulatorArm64()

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
            implementation(libs.compose.components.resources)
            api(libs.kotlinx.collections.immutable)
        }
    }
}
