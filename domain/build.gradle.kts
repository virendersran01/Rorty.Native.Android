plugins {
    id(Plugins.AndroidCommonLibrary)
    id(Plugins.Hilt)
}

dependencies {
    implementation(project(Modules.Data))
    implementation(project(Modules.Framework))
    implementation(project(Modules.Extension))
    testImplementation(project(Modules.TestUtils))
    implementation(Deps.Hilt.Android)
    kapt(Deps.Hilt.AndroidCompiler)
    implementation(Deps.AndroidX.Paging)
}