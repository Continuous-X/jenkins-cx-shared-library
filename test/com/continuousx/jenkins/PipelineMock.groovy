package com.continuousx.jenkins

class PipelineMock {
    String commandCalled
    String echoCalled
    Map readFile

    def sh(String command) {
        commandCalled = command
    }

    def echo(String echo) {
        echoCalled = echo
    }

    def readFile(def file) {
        readFile = file
        return
    }

}
