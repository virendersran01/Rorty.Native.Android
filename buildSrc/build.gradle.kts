plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        register("AndroidApp") {
            id = "AndroidApp"
            implementationClass = "commons.AndroidApp"
        }
        register("AndroidCommonLibrary") {
            id = "AndroidCommonLibrary"
            implementationClass = "commons.AndroidCommonLibrary"
        }
    }
}

object Versions {
    const val GRADLE = "7.1.0-beta05"
    const val KOTLIN = "1.6.10"
    const val HILT = "2.40.1"
    const val VERSION_CHECKER = "0.39.0"
}

object Deps {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val HILT_GRADLE = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
    const val VERSION_CHECKER = "com.github.ben-manes:gradle-versions-plugin:${Versions.VERSION_CHECKER}"
}

dependencies {
    implementation(Deps.ANDROID_GRADLE)
    implementation(Deps.KOTLIN_GRADLE)
    implementation(Deps.HILT_GRADLE)
    implementation(Deps.VERSION_CHECKER)
}