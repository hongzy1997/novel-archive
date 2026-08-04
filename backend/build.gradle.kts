plugins {
    java
    // Spring Boot
    id("org.springframework.boot") version "4.1.0"
    // Spring Dependency Management
    id("io.spring.dependency-management") version "1.1.7"
    // Doma Annotation Processor
    id("org.domaframework.doma.compile") version "4.0.3"
}

group = "com.hongzy"
version = "0.0.1-SNAPSHOT"

java {
    // 使用するJavaバージョン
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    // ライブラリの取得先
    mavenCentral()
}

dependencies {

    // =========================
    // Spring Boot
    // =========================

    // Spring MVC
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    // Spring MVC Test
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    // JDBC
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    // =========================
    // Oracle
    // =========================

    // Oracle JDBC Driver
    runtimeOnly("com.oracle.database.jdbc:ojdbc11")


    // =========================
    // Doma
    // =========================

    implementation("org.seasar.doma.boot:doma-spring-boot-starter:3.0.0")
    annotationProcessor("org.seasar.doma:doma-processor:3.11.1")


    // =========================
    // Lombok
    // =========================

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
    // JUnit 5を使用
    useJUnitPlatform()
}