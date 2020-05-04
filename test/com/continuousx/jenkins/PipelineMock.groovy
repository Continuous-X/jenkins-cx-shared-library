package com.continuousx.jenkins

class PipelineMock {
    String commandCalled
    String echoCalled

    def sh(String command) {
        commandCalled = command
    }

    def echo(String echo) {
        echoCalled = echo
    }

}
