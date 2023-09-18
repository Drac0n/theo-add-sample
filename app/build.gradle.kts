plugins {
    id("ballys.android-application")
}

android {
    namespace = "com.clevertech.theosandbox"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.clevertech.theosandbox"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        android.buildFeatures.buildConfig = true

        buildConfigField(
            "String",
            "THEO_LICENSE",
            "\"sZP7IYe6T6fi3SCo0KIl3Zzr0l4gFSakCKC-TuAe36zz3SglIDxeIDfi0QB6FOPlUY3zWokgbgjNIOf9fK3KTu1lCL0cFSR_0La-3QPeCmzr0KI1FSRrTuaoCKxK3QP1CmfVfK4_bQgZCYxNWoryIQXzImf90SCr3u5_0uRi0u5i0Oi6Io4pIYP1UQgqWgjeCYxgflEc3lR_3SCz0lht3laiFOPeWok1dDrLYtA1Ioh6TgV6v6fVfKcqCoXVdQjLUOfVfGxEIDjiWQXrIYfpCoj-fgzVfKxqWDXNWG3ybojkbK3gflNWf6E6FOPVWo31WQ1qbta6FOPzdQ4qbQc1sD4ZFK3qWmPUFOPLIQ-LflNWfKXpIwPqdDa6Ymi6bo4pIXjNWYAZIY3LdDjpflNzbG4gFOPKIDXzUYPgbZf9Dkkj\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.splashscreen)
    implementation(libs.theo.sdk)
    implementation(libs.theo.sdk.ads)
    api(platform(libs.compose.bom))
    api(libs.bundles.compose)
    api(libs.bundles.coil)

    implementation(libs.compose.activity)
    implementation(libs.accompanist.systemuicontroller)

    api(libs.compose.destinations.core)
    api(libs.compose.destinations.animation.core)

}