import com.android.build.api.dsl.LibraryExtension
import com.gs.convention.ExtensionType
import com.gs.convention.configureBuildTypes
import com.gs.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                // TODO: Configure targetSdk, compileSdk, minSdkVersion from libs or project properties
                // defaultConfig.targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                configureKotlinAndroid(this)

                // Defina o consumer proguard files se necessário para módulos de biblioteca
                // defaultConfig.consumerProguardFiles("consumer-rules.pro")

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.LIBRARY // Assumindo que ExtensionType.LIBRARY existe ou será criado
                )
            }

            // Adicionar dependências comuns de biblioteca aqui, se houver
            // Exemplo:
            // dependencies {
            //     "testImplementation"(kotlin("test"))
            // }
        }
    }
}
