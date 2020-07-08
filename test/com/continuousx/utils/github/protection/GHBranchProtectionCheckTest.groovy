package com.continuousx.utils.github.protection

import com.continuousx.utils.rulecheck.ghprotection.GHBranchProtectionCheck
import com.continuousx.utils.rulecheck.ghprotection.RuleSetProtectionSimple
import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection
import spock.lang.Specification

class GHBranchProtectionCheckTest extends Specification {

    def "should be checked is true"() {
        given:
        GHBranch ghBranchMock = ghBranch
        ghBranchMock.getProtection() >> Mock(GHBranchProtection)
        ghBranchMock.getProtection().requiredReviews >> requiredReviews

        final GHBranchProtectionCheck check = new GHBranchProtectionCheck()

        when:
        final boolean result = check.checkRules(new RuleSetProtectionSimple(), ghBranchMock)

        then:
        assert result == expectedCheckResult

        where:
        ghBranch       || ghBranchPRotection       || requiredReviews                          || expectedCheckResult
        Mock(GHBranch) || Mock(GHBranchProtection) || Mock(GHBranchProtection.RequiredReviews) || true
    }


}
