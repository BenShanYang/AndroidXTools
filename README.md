# AndroidXTools
这是一个汇集了日常项目中常用的工具类和自定义控件的项目

[![](https://jitpack.io/v/BenShanYang/AndroidXTools.svg)](https://jitpack.io/#BenShanYang/AndroidXTools)


allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.BenShanYang:AndroidXTools:1.0.0'
}  


dependencies {
    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.2.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.vectordrawable:vectordrawable:1.1.0'
    implementation 'androidx.navigation:navigation-fragment:2.3.1'
    implementation 'androidx.navigation:navigation-ui:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

    implementation project(':toolslibrary')

    //QMUI 腾讯的UI框架
    implementation 'com.qmuiteam:qmui:2.0.0-alpha10'
}
