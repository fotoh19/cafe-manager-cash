plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    namespace = "com.note.cafemanagercash"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.note.cafemanagercash"
        minSdk = 24
        targetSdk = 33
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:21.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")

    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.7")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.7")

    implementation("androidx.activity:activity-ktx:1.9.1")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("androidx.room:room-common:2.6.1")
}