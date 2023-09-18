object BallysBuildConfig {

    const val targetAndCompileSdk = 34
    const val minSdk = 24

    object Environments {
        val dev = Environment(name = "dev")
        val prod = Environment(name = "prod")

        val all = listOf(dev, prod)
    }

    object FlavorDimensions {
        val environment = FlavorDimension("environment")

        val all = listOf(environment)
    }

    val flavorDimension = FlavorDimension("environment")
}

val Environment.flavorDimension
    get() = BallysBuildConfig.FlavorDimensions.environment
