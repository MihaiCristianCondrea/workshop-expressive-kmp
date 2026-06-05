import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeCompiler)
}

group = "com.design.workshop.expressive"
version = "0.0.6"

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    android {
        namespace = "com.design.workshop.expressive.catalog"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    iosArm64()
    iosSimulatorArm64()

    js {
        browser()
    }

    wasmJs {
        browser()
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":shared-ui"))
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
        }

        jvmMain.dependencies {
            implementation(libs.compose.desktop.current.os)
        }
    }
}

tasks.register<JavaExec>("runDesktop") {
    group = "workshop"
    description = "Runs the WorkShop Expressive catalog as a local desktop app."
    mainClass.set("com.design.workshop.expressive.catalog.MainKt")
    val mainCompilation = kotlin.targets.getByName("jvm").compilations.getByName("main")
    classpath = files(mainCompilation.output.allOutputs, mainCompilation.runtimeDependencyFiles)
}
