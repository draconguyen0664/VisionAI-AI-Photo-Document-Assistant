plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
    id("org.jetbrains.compose")
    id("com.android.application")
}
kotlin {
    androidTarget()
    iosX64(); iosArm64(); iosSimulatorArm64()
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")
        }
        androidMain.dependencies { implementation("androidx.activity:activity-compose:1.10.0") }
    }
}
android { namespace = "com.visionai.app"; compileSdk = 35; defaultConfig { applicationId = "com.visionai.app"; minSdk = 26; targetSdk = 35; versionCode = 1; versionName = "0.1.0" } }
