package com.continuousx.utils.rulecheck.ghprotection

import com.continuousx.utils.rulecheck.Rule
import com.continuousx.utils.rulecheck.RuleSet
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionActive
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionEnforceAdmin
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionRequirePullRequestReviews
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionRequireSignedCommits
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionRequireStatusCheck
import com.continuousx.utils.rulecheck.ghprotection.rules.RuleProtectionRestriction

class RuleSetProtectionHardened implements RuleSet {

    @Override
    List<Rule> getRules() {
        List<Rule> ruleSet = [
                new RuleProtectionActive(),
                new RuleProtectionRequirePullRequestReviews(),
                new RuleProtectionRequireStatusCheck(),
                new RuleProtectionRequireSignedCommits(),
                new RuleProtectionRestriction(),
                new RuleProtectionEnforceAdmin(),
        ]
        ruleSet
    }

}
