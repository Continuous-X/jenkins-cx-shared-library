package com.continuousx.utils.rulecheck.ghprotection

import com.continuousx.utils.rulecheck.RuleChecker
import com.continuousx.utils.rulecheck.RuleSet
import org.kohsuke.github.GHBranchProtection

class GHBranchProtectionCheck implements RuleChecker {

    @Override
    boolean checkRules(RuleSet ruleSet, final GHBranchProtection ghBranchProtection) {
        ruleSet.getRules().each {rule ->
            rule.passed = rule.check(ghBranchProtection)
        }
        def result = ruleSet.getRules().find {rule -> rule.passed == false }
        println "found passes: ${result}"
    }

}
