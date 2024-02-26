import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.services)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm()

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        commonMain.dependencies {
            implementation("co.touchlab:kermit:2.0.3")
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)
            implementation(libs.composeImageLoader)
            implementation(libs.napier)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.core)
            implementation(libs.ktor.log)
            implementation(libs.ktor.cnegotiation)
            implementation(libs.ktor.json)
            implementation(libs.composeIcons.featherIcons)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.multiplatformSettings)
            implementation(libs.koin.core)
            implementation(libs.kotlin.mvi)
            implementation(libs.kotlin.mvi.main)
            implementation(libs.kstore)
            implementation(libs.kmmfirebase.auth)
            implementation(libs.kmmfirebase.firestore)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kstore.file)
        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activityCompose)
            implementation(libs.compose.uitooling)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kstore.file)
            implementation(libs.android.crypto)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.common)
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kstore.file)
            implementation(libs.kmmfirebase.jvm)
        }

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.kstore.storage)
            implementation(npm("yarn", ""))
        }

    }
}

android {
    namespace = "com.danya.family"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        applicationId = "com.danya.family"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.danya.app.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}

buildConfig {
    buildConfigField("FirebaseApiKey", "AIzaSyDwdhQ7MabioIAVpyuDmns-I03EcLiyTFM")
    buildConfigField("firebaseAuthDomain", "familymatters-38c07.firebaseapp.com")
    buildConfigField("firebaseProjectId", "familymatters-38c07")
    buildConfigField("firebaseStorageBucket", "familymatters-38c07.appspot.com")
    buildConfigField("firebaseApplicationId", "1=6720102597=web=a30b3b8887c47349a4d0c")
}
