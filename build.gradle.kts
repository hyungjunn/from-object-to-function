plugins {
    kotlin("jvm") version "2.1.10"
}

group = "io.github.hyungjunn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:0.28.1")
    testImplementation("com.ubertob.pesticide:pesticide-core:1.6.6")
    testImplementation("io.strikt:strikt-core:0.31.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
