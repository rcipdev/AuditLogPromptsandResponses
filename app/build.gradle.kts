plugins {
    alias(libs.plugins.androidApplication)
}

def localProperties = new Properties()
def localPropertiesFile = rootProject.file('local.properties')
if (localPropertiesFile.exists()) {
    localPropertiesFile.withInputStream { stream ->
        localProperties.load(stream)
    }
}

def openAIKey = localProperties['OPENAI_API_KEY'] ?: "UNKNOWN"
def encryptionKey = localProperties['ENCRYPTION_KEY'] ?: "UNKNOWN"

android {
    namespace = "com.example.auditlogpromptsandresponses"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.auditlogpromptsandresponses"
        minSdk = 30
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
            buildConfigField("String", "ENCRYPTION_KEY", "\"" + encryptionKey + "\"")
            buildConfigField("String","OPENAI_API_KEY", "\"" + openAIKey + "\"")
        }
        debug {
            buildConfigField("String", "ENCRYPTION_KEY","\"" + encryptionKey + "\"")
            buildConfigField("String","OPENAI_API_KEY", "\"" + openAIKey + "\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        buildConfig=true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}