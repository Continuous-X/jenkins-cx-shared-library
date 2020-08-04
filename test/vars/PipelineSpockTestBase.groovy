package vars

import com.lesfurets.jenkins.unit.PipelineTestHelper
import com.lesfurets.jenkins.unit.RegressionTest
import spock.lang.Specification

class PipelineSpockTestBase extends Specification  implements RegressionTest {

    /**
     * Delegate to the test helper
     */
    @Delegate PipelineTestHelper pipelineTestHelper

    /**
     * Do the common setup
     */
    def setup() {

        // Set callstacks path for RegressionTest
        callStackPath = 'pipelineTests/groovy/tests/callstacks/'

        // Create and config the helper
        pipelineTestHelper = new PipelineTestHelper()
        pipelineTestHelper.init()
    }
}
