import gradle.kotlin.dsl.accessors._0a49d88bd304faddeaf61cd52aaeaea7.kapt
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("ballys.detekt-tasks")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdk = BallysBuildConfig.targetAndCompileSdk

    defaultConfig {
        minSdk = BallysBuildConfig.minSdk
        vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["test"].java.srcDir("src/test/kotlin")
    sourceSets["androidTest"].java.srcDir("src/androidTest/kotlin")

    ksp {
        arg("compose-destinations.mode", "navgraphs")
        arg(
            "compose-destinations.moduleName",
            name.replace("-[a-z]".toRegex()) { it.value.last().uppercase() }.capitalize()
        )
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    libraryVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    BallysBuildConfig.FlavorDimensions.all
        .map { it.name }
        .forEach {
            flavorDimensions += it
        }
    productFlavors {
        BallysBuildConfig.Environments.all.forEach { environment ->
            create(environment.name) {
                dimension = environment.flavorDimension.name
            }
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    implementation(libs.hilt.android)
    implementation(libs.hilt.composeNavigation)
    ksp(libs.compose.destinations.ksp)
    kapt(libs.hilt.kaptCompiler)
    kapt(libs.hilt.androidxCompiler)
    implementation(libs.timber.logger)
}
