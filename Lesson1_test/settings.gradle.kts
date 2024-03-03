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

rootProject.name = "Lesson1"
include(":zadanie_2")
include(":zadanie_1")
include(":zadanie_3")
include(":control_lesson1")
include(":zadanie_4")
include(":buttonclicker")
