plugins {
  kotlin("jvm")
  id("fabric-loom")
  `maven-publish`
  java
  id("com.diffplug.spotless") version "7.0.0.BETA4"
}

spotless {
  kotlin { ktfmt().googleStyle() }
  kotlinGradle { ktfmt().googleStyle() }
  java {
    cleanthat()
    googleJavaFormat()
    formatAnnotations()
  }
}

group = property("maven_group")!!

version = property("mod_version")!!

repositories {
  maven {
    name = ("impactdevelopment-repo")
    url = uri("https://impactdevelopment.github.io/maven/")
  }
}

dependencies {
  minecraft("com.mojang:minecraft:${property("minecraft_version")}")
  mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
  modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")

  modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
  modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

  implementation("com.github.ZeroMemes:Alpine:3.1.0")

  implementation("io.github.spair:imgui-java-binding:1.86.12")
  implementation("io.github.spair:imgui-java-lwjgl3:1.86.12")

  implementation("io.github.spair:imgui-java-natives-windows:1.86.12")
  implementation("io.github.spair:imgui-java-natives-linux:1.86.12")
  implementation("io.github.spair:imgui-java-natives-macos:1.86.12")
}

tasks {
  processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
      expand(getProperties())
      expand(mutableMapOf("version" to project.version))
    }
  }

  jar { from("LICENSE") }

  publishing {
    publications {
      create<MavenPublication>("mavenJava") {
        artifact(remapJar) { builtBy(remapJar) }
        artifact(kotlinSourcesJar) { builtBy(remapSourcesJar) }
      }
    }

    // select the repositories you want to publish to
    repositories {
      // uncomment to publish to the local maven
      // mavenLocal()
    }
  }

  compileKotlin { kotlinOptions.jvmTarget = "21" }
}

java {
  // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
  // if it is present.
  // If you remove this line, sources will not be generated.
  withSourcesJar()
}

// configure the maven publication
