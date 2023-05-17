plugins {
    jacoco
}

jacoco {
    toolVersion = "0.8.9"
    providers.gradleProperty("jacoco.version")
        .takeIf { it.isPresent }
        ?.let { toolVersion = it.get() }

}

tasks.withType<JacocoReport> {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(file("${rootProject.buildDir}/reports/jacoco/${project.name}"))
        //html.outputLocation.set(file("${buildDir}/jacoco/jacocoHtml"))
    }
}
