package resource.jenkins

import org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper

interface PipelineMock {

    PipelineCurrentBuildMock currentBuild = new PipelineCurrentBuildMock()
    PipelineEnvMock env = new PipelineEnvMock()

    PipelineEnvMock env()
    RunWrapper currentBuild()
    def sh(String command)
    def echo(String echo)
    def readFile(def file)
    String pwd()
    void influxDbPublisher(String selectedTarget, Object customDataMap, String customDataMapTags)

}
