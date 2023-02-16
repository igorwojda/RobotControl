plugins {
    kotlin("jvm") version "1.8.10"
    `jvm-test-suite`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("io.insert-koin:koin-core:3.3.3")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("org.amshove.kluent:kluent:1.72")

    testImplementation("io.insert-koin:koin-test-junit5:3.3.3") {
        exclude("org.jetbrains.kotlin", "kotlin-test-junit")
    }
}

// Introducing Test Suites https://blog.gradle.org/introducing-test-suites
// https://docs.gradle.org/current/userguide/java_testing.html#sec:configuring_java_integration_tests
// Add integrationTest source set
testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project())
                implementation("io.insert-koin:koin-test-junit5:3.3.3") {
                    exclude("org.jetbrains.kotlin", "kotlin-test-junit")
                }
                implementation("org.amshove.kluent:kluent:1.72")
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

tasks.named("check") {
    dependsOn(testing.suites.named("integrationTest"))
}
