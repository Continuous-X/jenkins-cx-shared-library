package com.continuousx.utils.github.protection

import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.rulecheck.ghprotection.GHBranchProtectionCheck
import com.continuousx.utils.rulecheck.ghprotection.RuleSetProtectionSimple
import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection
import spock.lang.Specification
import spock.lang.Unroll

class GHBranchProtectionCheckTest extends Specification {

    GHBranchProtectionCheck check

    def setup() {
        check = new GHBranchProtectionCheck()
    }

    @Unroll
    def "should be checked RuleSetProtectionSimple is true"() {
        given:
        GHBranch ghBranchMock = Mock(GHBranch)
        ghBranchMock.getProtection() >> Mock(GHBranchProtection)
        ghBranchMock.getProtection().getRequiredReviews() >> requireReviews
        ghBranchMock.getProtection().requiredReviews >> requireReviews
        ghBranchMock.getProtection().getRequiredStatusChecks() >> requireStatusChecks
        ghBranchMock.getProtection().requiredStatusChecks >> requireStatusChecks

        expect:
        check.checkRules(ruleSet, ghBranchMock, Mock(PipelineLogger)) == expectedCheckResult

        where:
        ruleSet                       || requireReviews                           || requireStatusChecks                           || expectedCheckResult
        new RuleSetProtectionSimple() || Mock(GHBranchProtection.RequiredReviews) || Mock(GHBranchProtection.RequiredStatusChecks) || true
        new RuleSetProtectionSimple() || null                                     || Mock(GHBranchProtection.RequiredStatusChecks) || false
        new RuleSetProtectionSimple() || Mock(GHBranchProtection.RequiredReviews) || null                                          || false
    }


}
