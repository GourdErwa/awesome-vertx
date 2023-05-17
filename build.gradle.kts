import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  `java-conventions`
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "io.gourd"
version = libs.versions.rootProject.get()

repositories {
  maven {
    url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
    mavenContent {
      snapshotsOnly()
    }
  }
  mavenCentral()
}


val vertxVersion = "4.4.3-SNAPSHOT"
val mainVerticleName = "io.gourd.awesome.vertx.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform(libs.vertx.bom))
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-mysql-client")
  implementation("io.vertx:vertx-mssql-client")
  implementation("io.vertx:vertx-rx-java3")
  implementation("io.vertx:vertx-redis-client")
  implementation("io.vertx:vertx-reactive-streams")
  implementation("io.vertx:vertx-jdbc-client")
  implementation("io.vertx:vertx-oracle-client")
  implementation("io.vertx:vertx-cassandra-client")
  implementation("io.vertx:vertx-sql-client-templates")
  implementation("io.vertx:vertx-pg-client")
  implementation("io.vertx:vertx-db2-client")
  implementation("io.vertx:vertx-mongo-client")
  implementation("com.ongres.scram:client:2.1")

  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:${libs.versions.junit5}")
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
