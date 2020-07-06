package com.continuousx.utils.rulecheck.ghprotection

import com.continuousx.utils.rulecheck.Rule
import org.kohsuke.github.GHBranchProtection

class RuleProtectionRequirePullRequestReviews implements Rule {

    String name = getClass().getName()

    @Override
    boolean check(GHBranchProtection ghBranchProtection) {
        return false
    }

}
