plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")
}

android {
    namespace = Config.applicationId
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation (Libs.AndroidX.core)
    implementation (Libs.AndroidX.appCompat)
    implementation (Libs.Google.material)
    implementation (Libs.AndroidX.constraintLayout)

    //test
    testImplementation (Libs.TestLibs.junit)
    androidTestImplementation (Libs.TestLibs.junitExtTest)
    androidTestImplementation (Libs.TestLibs.espresso)

    // Room
    implementation (Libs.Room.runtime)
    kapt (Libs.Room.compiler)
    implementation (Libs.Room.ktx)

    // Coroutines
    implementation (Libs.Coroutines.coroutineCore)
    implementation (Libs.Coroutines.coroutineAndroid)

    // Lifecycle Scopes
    implementation (Libs.AndroidX.vmLifeCycle)
    implementation (Libs.AndroidX.liveCycleRuntime)
    implementation (Libs.AndroidX.liveData)
    implementation (Libs.AndroidX.lifeCycleExtension)

    // Retrofit
    implementation (Libs.NetworkLib.retrofit)
    implementation (Libs.NetworkLib.gsonConv)

    implementation (Libs.NetworkLib.okhttp)

    // Navigation Components
    implementation (Libs.AndroidX.navigation)
    implementation (Libs.AndroidX.navigationUI)

    // Glide
    implementation (Libs.Glide.glide)
    kapt (Libs.Glide.glideCompailer)

    //Dagger-hilt
    implementation (Libs.Hilt.android)
    kapt (Libs.Hilt.compiler)
    implementation(Libs.Hilt.plugin)

    //Encrypted SharedPref
    implementation (Libs.AndroidX.encryptedSharedPref)
}