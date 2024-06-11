plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.project"
        minSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
<<<<<<< HEAD
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.github.fevziomurtekin:PayView:1.0.3")
    implementation("com.stripe:stripe-android:20.8.1")
    implementation("com.android.volley:volley:1.2.1")

=======
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation(libs.volley)
>>>>>>> 1360e89e427f7e11f9217899928763ab44c63caa
    implementation(libs.glide)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
