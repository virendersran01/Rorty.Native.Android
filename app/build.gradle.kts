plugins {
    id(Plugins.AndroidApp)
}

dependencies {
    // modules
    implementation(project(Modules.Data))
    implementation(project(Modules.Domain))
    implementation(project(Modules.Component))
    implementation(project(Modules.Extension))
    implementation(project(Modules.Framework))
    implementation(project(Modules.Navigation))
    testImplementation(project(Modules.TestUtils))

    // deps
    implementation(Deps.AndroidX.CoreKtx)
    implementation(Deps.AndroidX.AppCompat)
    implementation(Deps.AndroidX.Material)
    implementation(Deps.AndroidX.ConstraintLayout)
    implementation(Deps.AndroidX.RecyclerView)
    implementation(Deps.AndroidX.SwipeRefreshLayout)

    implementation(Deps.Hilt.Android)
    kapt(Deps.Hilt.AndroidCompiler)

    implementation(Deps.Coroutine.Core)
    implementation(Deps.Coroutine.Android)

    implementation(Deps.Lifecycle.ViewModel)
    implementation(Deps.Lifecycle.Runtime)

    implementation(Deps.AndroidX.ActivityKtx)
    implementation(Deps.AndroidX.FragmentKtx)
    implementation(Deps.Coil)
    implementation(Deps.AndroidX.Paging)
    implementation(Deps.Timber)

    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.EspressoCore)
}