package com.continuousx.jenkins

interface PipelineMock {

    def sh(String command)
    def echo(String echo)
    def readFile(def file)

}
