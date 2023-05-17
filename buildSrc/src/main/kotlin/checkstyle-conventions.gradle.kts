plugins {
    // https://checkstyle.org/
    // https://docs.gradle.org/current/userguide/checkstyle_plugin.html
    checkstyle
}
checkstyle {
    toolVersion = "9.3"
}

val checkstyleTasks = tasks.withType<Checkstyle>()
checkstyleTasks.configureEach {
    //classpath = files()
    configFile = file("${rootDir}/config/checkstyle/checkstyle.xml")
    minHeapSize.set("200m")
    maxHeapSize.set("1g")
    reports {
        xml.required.set(false)
        html.required.set(true)
        html.outputLocation.set(file("${rootProject.buildDir}/reports/checkstyle/${project.name}.checkstyle.html"))
        //html.stylesheet = resources.text.fromFile("${rootDir}/config/checkstyle/xsl/checkstyle-csv.xsl")
    }
    ignoreFailures = true
    isShowViolations = true
}

tasks.named<Checkstyle>("checkstyleMain") {
    // exclude("**/guava/**")
}
tasks.named<Checkstyle>("checkstyleTest") {
    // exclude("**/jdbc/shared/**")
}
