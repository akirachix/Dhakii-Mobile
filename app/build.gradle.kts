plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
    id("kotlin-kapt") // Added for Room compiler
}

android {
    namespace = "com.akirachix.mamamindtrial"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.akirachix.mamamindtrial"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        jvmTarget = "1.8"
    }

<<<<<<< HEAD

=======
    buildFeatures {
        viewBinding = true
    }
>>>>>>> b49109630318d559956f844629120a8a1d7f3941
}

dependencies {
    // AndroidX libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat.v140)


    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Firebase dependencies
    implementation(platform(libs.firebase.bom))
    implementation (libs.androidx.appcompat.v140)
    implementation (libs.material.v140)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.auth)
<<<<<<< HEAD
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

=======
>>>>>>> b49109630318d559956f844629120a8a1d7f3941

    // Lifecycle components
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    
    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)
//    implementation(libs.kotlinx.coroutines.core)

    // Room database dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1") // Added kapt for Room compiler

    // Retrofit for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation(libs.logging.interceptor)

    // Additional dependencies
    implementation(libs.converter.gson)
}

fun kapt(s: String) {

}
