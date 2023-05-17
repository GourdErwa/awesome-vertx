plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("**/build/")
    }
    java {
        importOrder()
        removeUnusedImports()
        palantirJavaFormat()
        trimTrailingWhitespace()
    }
}

// 代码格式校验前默认进行格式化处理
val checkstyle = tasks.findByName("checkstyle")
val spotlessCheck = tasks.getByName("spotlessCheck")
val spotlessApply = tasks.getByName("spotlessApply")

afterEvaluate {
    if (checkstyle != null) {
        checkstyle.dependsOn(spotlessCheck)
    }
    spotlessCheck.dependsOn(spotlessApply)
}
