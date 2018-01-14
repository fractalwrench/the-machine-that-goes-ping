## Ping Machine
Makes your machine go PING when an Android build completes. Read [the blog post](https://fractalwrench.co.uk/posts/the-machine-that-goes-ping/) for a guide on how the plugin works.

## Installation
```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.fractalwrench:PingPlugin:1.0.0"
  }
}

apply plugin: "com.fractalwrench.pingmachine"
```

## Usage
Run any gradle task that assembles an APK variant, and the machine will ping.

## Audio
Contains Audio licensed under the CC Attribution License
https://freesound.org/people/thomasevd/sounds/125374/
https://creativecommons.org/licenses/by/3.0/
