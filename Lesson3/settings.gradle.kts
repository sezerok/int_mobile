pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lesson3"
include(":app")
include(":FavouriteBook")
include(":systemintentsapp")
include(":simplefragmentapp")
include(":sharer")
include(":mireaproject")
