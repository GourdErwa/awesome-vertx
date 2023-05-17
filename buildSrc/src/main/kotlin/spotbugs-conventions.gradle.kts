plugins {
    id("com.github.spotbugs")
}

spotbugs {
    // Below statement is for Renovate Bot since it does not support toolVersion.set("..") pattern yet
    toolVersion.set("4.7.3")
    providers.gradleProperty("spotbugs.version")
        .takeIf { it.isPresent }
        ?.let { this.toolVersion.set(it) }
    ignoreFailures.set(false)
    showStackTraces.set(true)
    showProgress.set(true)
    //effort.set(com.github.spotbugs.snom.Effort.DEFAULT)
    //reportLevel.set(com.github.spotbugs.snom.Confidence.DEFAULT)
    //visitors.set(listOf("FindSqlInjection", "SwitchFallthrough"))
    //omitVisitors.set(listOf("FindNonShortCircuit"))
    reportsDir.set(file("${rootProject.buildDir}/reports/spotbugs/${project.name}"))
    //includeFilter.set(file("include.xml"))
    //excludeFilter.set(file("exclude.xml"))
    //baselineFile.set(file("baseline.xml"))
    onlyAnalyze.set(listOf("com.hoteamsoft.*", "com.hoteam.*"))
    maxHeapSize.set("1g")
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask> {
    reports {
        reports.maybeCreate("html").required.set(true)
    }
}
dependencies {
    spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.12.0")
}
