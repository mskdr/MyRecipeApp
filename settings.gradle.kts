pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement{
    // i couldn't find the solution
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories{
        google()
        mavenCentral()
    }
}
rootProject.name = "MyRecipeApp"
include(":app")