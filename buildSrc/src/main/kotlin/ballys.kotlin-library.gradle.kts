import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("ballys.detekt-tasks")
    id("org.jetbrains.kotlin.kapt")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.kotlin.coroutine.core)
    detektPlugins(libs.detekt.formatting)
    implementation(libs.hilt.core)
    kapt(libs.hilt.kaptCompiler)
}
