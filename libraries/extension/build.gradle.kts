plugins {
    id(Plugins.AndroidCommonLibrary)
}

dependencies {
    implementation(Deps.AndroidX.Material)
    implementation(Deps.AndroidX.ConstraintLayout)
    implementation(Deps.AndroidX.RecyclerView)
    implementation(Deps.AndroidX.Paging)
    implementation(Deps.Lifecycle.ViewModel)
    implementation(Deps.Lifecycle.Runtime)
    implementation(Deps.Moshi.Kotlin)
    implementation(Deps.Timber)
}