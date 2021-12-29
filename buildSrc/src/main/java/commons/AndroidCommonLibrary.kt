package commons

import Configs
import Deps
import Plugins
import com.android.build.api.dsl.BuildType
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidCommonLibrary : Plugin<Project> {
    override fun apply(project: Project) {
        // Apply Required Plugins.
        project.plugins.apply(Plugins.AndroidLibrary)
        project.plugins.apply(Plugins.KotlinAndroid)
        project.plugins.apply(Plugins.KotlinKapt)
        project.plugins.apply(Plugins.KotlinParcelize)

        // Configure common android build parameters.
        val androidExtension = project.extensions.getByName("android")
        val consumerFile = "consumer-rules.pro"
        if (androidExtension is LibraryExtension) {
            androidExtension.apply {
                compileSdk = Configs.CompileSdk
                defaultConfig.apply {
                    minSdk = Configs.MinSdk
                    targetSdk = Configs.TargetSdk
                    versionCode = Configs.VersionCode
                    versionName = Configs.VersionName
                    multiDexEnabled = true
                    vectorDrawables.useSupportLibrary = true
                    testInstrumentationRunner = Configs.AndroidJunitRunner
                    consumerProguardFiles(consumerFile)
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
                                "META-INF/LGPL2.1"
                            )
                        )
                    }
                }

                viewBinding.isEnabled = true

                buildTypes.apply {
                    getByName("release") {
                        proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                        buildConfigStringField("BASE_URL", Configs.Release.BaseUrl)
                        buildConfigStringField("DB_NAME", Configs.Release.DbName)
                    }
                    getByName("debug") {
                        proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                        buildConfigStringField("BASE_URL", Configs.Debug.BaseUrl)
                        buildConfigStringField("DB_NAME", Configs.Debug.DbName)
                    }
                }

                // Adds required dependencies for all modules.
                project.dependencies.apply {
                    add("implementation", Deps.AndroidX.CoreKtx)
                    add("implementation", Deps.Coroutine.Core)
                    add("implementation", Deps.Coroutine.Android)

                    add("testImplementation", Deps.Test.Junit)
                    add("androidTestImplementation", Deps.Test.JunitExt)
                    add("androidTestImplementation", Deps.Test.EspressoCore)
                }

            }
        }
    }

    private fun BuildType.buildConfigStringField(name: String, value: String) {
        this.buildConfigField("String", name, "\"$value\"")
    }
}