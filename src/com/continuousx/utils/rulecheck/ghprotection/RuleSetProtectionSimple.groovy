package com.continuousx.utils.rulecheck.ghprotection

import com.continuousx.utils.rulecheck.Rule
import com.continuousx.utils.rulecheck.RuleSet
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionActive
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionRequirePullRequestReviews

class RuleSetProtectionSimple implements RuleSet {

    @Override
    List<Rule> getRules() {
        List<Rule> ruleSet = [
                new RuleProtectionActive(),
                new RuleProtectionRequirePullRequestReviews()
        ]
        ruleSet
    }

}
