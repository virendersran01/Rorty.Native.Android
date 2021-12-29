package commons

import Configs
import Plugins
import com.android.build.api.dsl.BuildType
import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApp : Plugin<Project> {
    override fun apply(project: Project) {
        project.applyPlugins()
        project.configureAndroid()
    }

    private fun Project.applyPlugins() {
        plugins.apply(Plugins.AndroidApplication)
        plugins.apply(Plugins.KotlinAndroid)
        plugins.apply(Plugins.KotlinKapt)
        plugins.apply(Plugins.KotlinParcelize)
        plugins.apply(Plugins.Hilt)
    }

    private fun Project.configureAndroid() = this.extensions.getByType(AppExtension::class).run {
        compileSdkVersion(Configs.CompileSdk)
        defaultConfig.apply {
            applicationId = Configs.Id
            minSdk = Configs.MinSdk
            targetSdk = Configs.TargetSdk
            versionCode = Configs.VersionCode
            versionName = Configs.VersionName
            multiDexEnabled = true
            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = Configs.AndroidJunitRunner
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        project.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
                allWarningsAsErrors = true
                freeCompilerArgs = Configs.FreeCompilerArgs
            }
        }

        packagingOptions.apply {
            resources {
                setExcludes(
                    setOf(
                        "META-INF/metadata.kotlin_module",
                        "META-INF/metadata.jvm.kotlin_module",
                        "META-INF/AL2.0",
                        "META-INF/LGPL2.1",
                        "META-INF/MANIFEST.MF",
                        "META-INF/proguard/coroutines.pro"
                    )
                )
            }
        }

        viewBinding.isEnabled = true

        buildTypes.apply {
            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                buildConfigStringField("BASE_URL", Configs.Release.BaseUrl)
                buildConfigStringField("DB_NAME", Configs.Release.DbName)
            }
            getByName("debug") {
                isTestCoverageEnabled = true
                proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                buildConfigStringField("BASE_URL", Configs.Debug.BaseUrl)
                buildConfigStringField("DB_NAME", Configs.Debug.DbName)
            }
        }
    }

    private fun BuildType.buildConfigStringField(name: String, value: String) {
        this.buildConfigField("String", name, "\"$value\"")
    }
}