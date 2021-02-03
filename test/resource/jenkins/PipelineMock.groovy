package resource.jenkins

interface PipelineMock {

    PipelineCurrentBuildMock currentBuild = new PipelineCurrentBuildMock()
    PipelineEnvMock env = new PipelineEnvMock()
    PipelineLogMock log = new PipelineLogMock()

    def sh(String command)
    def echo(String echo)
    def readFile(def file)
    String pwd()
    void influxDbPublisher(String selectedTarget, Object customDataMap, String customDataMapTags)

}
