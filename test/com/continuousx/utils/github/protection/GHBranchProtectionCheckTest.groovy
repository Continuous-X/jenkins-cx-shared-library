package com.continuousx.utils.github.protection

import com.continuousx.utils.rulecheck.ghprotection.GHBranchProtectionCheck
import com.continuousx.utils.rulecheck.ghprotection.RuleSetProtectionSimple
import org.kohsuke.github.GHBranchProtection
import spock.lang.Specification

class GHBranchProtectionCheckTest extends Specification {

    def "should be checked is true"() {
        given:
        final GHBranchProtectionCheck check = new GHBranchProtectionCheck()

        when:
        final boolean result = check.checkRules(new RuleSetProtectionSimple(), Mock(GHBranchProtection))

        then:
        assert result == true
    }


}
