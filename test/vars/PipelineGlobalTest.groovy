package vars

class PipelineGlobalTest extends PipelineSpockTestBase {

    def "Jenkinsfile validation errors should fail the job"() {

        given:
        pipelineTestHelper.registerAllowedMethod('validateDeclarativePipeline', [String.class], { false } )

        when:
        println new File('.').getAbsolutePath()
        runScript('test/vars/Jenkinsfile')

        then:
        printCallStack()
        assertJobStatusFailure()
    }

}