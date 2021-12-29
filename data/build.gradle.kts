plugins {
    id(Plugins.AndroidCommonLibrary)
    id(Plugins.Hilt)
}

dependencies {
    implementation(project(Modules.Framework))
    implementation(project(Modules.Extension))
    testImplementation(project(Modules.TestUtils))
    implementation(Deps.Hilt.Android)
    kapt(Deps.Hilt.AndroidCompiler)
    implementation(Deps.Room.Base)
    kapt(Deps.Room.Compiler)
    implementation(Deps.Moshi.Kotlin)
    implementation(Deps.Moshi.LazyAdapter)
    implementation(Deps.Retrofit.Base)
    implementation(Deps.Retrofit.Moshi)
    implementation(Deps.Okhttp.Base)
    implementation(Deps.Okhttp.LoggingInterceptor)
    debugImplementation(Deps.Chucker.Library)
    releaseImplementation(Deps.Chucker.NoLibrary)
    implementation(Deps.Room.Base)
    kapt(Deps.Room.Compiler)
}