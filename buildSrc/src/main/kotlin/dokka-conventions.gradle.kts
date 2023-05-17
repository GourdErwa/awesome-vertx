plugins {
    java
    id("org.jetbrains.dokka")
}


java {
    // 未解决：中文乱码
    withJavadocJar()
}

val dokkaJar by tasks.registering(Jar::class) {
    group = LifecycleBasePlugin.BUILD_GROUP
    description = "Assembles a jar archive containing javadoc"
    from(tasks.dokkaJavadoc)
    archiveClassifier.set("javadoc")
}

configurations[JavaPlugin.JAVADOC_ELEMENTS_CONFIGURATION_NAME].outgoing.artifact(dokkaJar)
