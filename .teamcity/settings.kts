import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.10"

project {

    buildType(BuildA)
    buildType(BuildB)
    buildType(BuildC)
}

object BuildA : BuildType({
    name = "build A"

    artifactRules = "target/*.jar"
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            name = "test"
            goals = "clean test"
        }
        script {
            scriptContent = "sleep 180"
        }
        maven {
            name = "pack"
            goals = "package"
        }
    }
})

object BuildB : BuildType({
    name = "build B"

    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            name = "tst"
            goals = "clean test"
        }
        script {
            scriptContent = "sleep 180"
        }
        maven {
            name = "pck"
            goals = "package"
        }
    }
})

object BuildC : BuildType({
    name = "build C"
    description = "compose"

    type = BuildTypeSettings.Type.COMPOSITE
    maxRunningBuilds = 1

    vcs {
        showDependenciesChanges = true
    }

    steps {
        script {
            scriptContent = "echo 'build config C, build chain'"
        }
    }

    dependencies {
        snapshot(BuildA) {
        }
        snapshot(BuildB) {
        }
    }
})
