// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.VersionCheck)
}

tasks.withType<Test>().configureEach {
    reports.html.required.set(false)
    reports.junitXml.required.set(true)
    maxParallelForks = Runtime.getRuntime().availableProcessors()
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "7.3.1"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}