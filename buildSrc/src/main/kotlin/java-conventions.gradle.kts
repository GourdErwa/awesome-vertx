import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.java

plugins {
    idea
    java
    id("spotless-conventions")
    id("checkstyle-conventions")
    id("spotbugs-conventions")
    id("jacoco-conventions")
    // id("dokka-conventions")
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.isIncremental = true
    options.isFork = true
    options.isFailOnError = false
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
