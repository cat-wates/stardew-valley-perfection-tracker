plugins {
    id("java")
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}
val springShellVersion = "3.4.2"

group = "org.perfection"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.shell:spring-shell-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.shell:spring-shell-dependencies:$springShellVersion")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<org.gradle.api.tasks.JavaExec>("bootRun") {
    standardInput = System.`in`
}