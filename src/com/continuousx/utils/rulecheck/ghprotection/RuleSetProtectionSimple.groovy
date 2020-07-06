package com.continuousx.utils.rulecheck.ghprotection

import com.continuousx.utils.rulecheck.Rule
import com.continuousx.utils.rulecheck.RuleSet

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
