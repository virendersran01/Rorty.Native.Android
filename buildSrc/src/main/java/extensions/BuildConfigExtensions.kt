package extensions

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.LibraryBuildType

/**
 * Extension to adds a new string field to the generated BuildConfig class.
 *
 * @param name the name of the field
 * @param value the string value of the field
 */
fun ApplicationBuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

fun LibraryBuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

/**
 * Extension to adds a new integer field to the generated BuildConfig class.
 *
 * @param name the name of the field
 * @param value the int value of the field
 */
fun ApplicationBuildType.buildConfigIntField(name: String, value: Int) {
    this.buildConfigField("int", name, value.toString())
}

fun LibraryBuildType.buildConfigIntField(name: String, value: Int) {
    this.buildConfigField("int", name, value.toString())
}

/**
 * Extension to adds a new boolean field to the generated BuildConfig class.
 *
 * @param name the name of the field
 * @param value the boolean value of the field
 */
fun ApplicationBuildType.buildConfigBooleanField(name: String, value: Boolean) {
    this.buildConfigField("boolean", name, value.toString())
}

fun LibraryBuildType.buildConfigBooleanField(name: String, value: Boolean) {
    this.buildConfigField("boolean", name, value.toString())
}