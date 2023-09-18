
plugins {
    id("io.gitlab.arturbosch.detekt")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    detekt {
        config = files("$rootDir/config/detekt.yml")
        buildUponDefaultConfig = true
        parallel = true
    }
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>(name = "detektAutoCorrect") {
    detekt {
        config = files("$rootDir/config/detekt.yml")
        buildUponDefaultConfig = true
        parallel = true
        autoCorrect = true
    }
    dependsOn("detekt")
}
