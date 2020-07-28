package resource.jenkins

import org.jenkinsci.plugins.workflow.support.steps.build.RunWrapper

interface PipelineMock {

    PipelineLogMock log = new PipelineLogMock()
    PipelineEnvMock env = new PipelineEnvMock()
    public RunWrapper currentBuild

    def sh(String command)
    def echo(String echo)
    def readFile(def file)
    String pwd()
    void influxDbPublisher(String selectedTarget, Object customDataMap, String customDataMapTags)

}
