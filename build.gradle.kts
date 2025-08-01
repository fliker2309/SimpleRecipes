task("clean") {
    doLast {
        delete(rootProject.buildDir)
    }
}