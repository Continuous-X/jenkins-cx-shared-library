package com.continuousx.utils.rulecheck.ghprotection

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.utils.rulecheck.Rule
import com.continuousx.utils.rulecheck.RuleSet
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionActive
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionRequirePullRequestReviews
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionRequireStatusCheck

class RuleSetProtectionSimple implements RuleSet {

    @Override
    @NonCPS
    List<Rule> getRules() {
        List<Rule> ruleSet = [
                new RuleProtectionActive(),
                new RuleProtectionRequirePullRequestReviews(),
                new RuleProtectionRequireStatusCheck()
        ]
        ruleSet
    }

}
