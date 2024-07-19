import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
}

group = "com.nkgrnkgr"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	jcenter()
	mavenCentral()
}

val ktlint by configurations.creating

dependencies {
	implementation("org.jsoup:jsoup:1.18.1")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	ktlint("com.pinterest:ktlint:0.51.0-FINAL")
}


task("ktlint", JavaExec::class) {
	group = "verification"
	description = "Check Kotlin code style."
	main = "com.pinterest.ktlint.Main"
	classpath = configurations.getByName("ktlint")
	args("src/**/*.kt")
}

tasks.named("check") {
	dependsOn( ktlint )
}

task("ktlintFormat", JavaExec::class) {
	group = "formatting"
	description = "Fix Kotlin code style deviations."
	main = "com.pinterest.ktlint.Main"
	classpath = configurations.getByName("ktlint")
	args("-F", "src/**/*.kt")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
