plugins {
    id(Plugins.AndroidCommonLibrary)
}

dependencies {
    implementation(project(Modules.Extension))
    implementation(Deps.AndroidX.Material)
    implementation(Deps.AndroidX.ConstraintLayout)
    implementation(Deps.AndroidX.RecyclerView)
    implementation(Deps.AndroidX.Paging)
    implementation(Deps.Timber)
    implementation(Deps.Retrofit.Base)
    implementation(Deps.Moshi.Kotlin)
    implementation(Deps.SecurityCrypto)
    implementation(Deps.Room.Base)
}