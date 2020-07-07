package com.continuousx.utils.rulecheck.ghprotection.rules

import com.continuousx.utils.rulecheck.Rule
import org.kohsuke.github.GHBranchProtection

class RuleProtectionActive implements Rule {

    String condition = 'Branch Protection must be activate'
    String successfulNote = 'Branch Protection is activated'

    @Override
    boolean check(Object checkedObject) {
        Objects.requireNonNull(checkedObject)
        assert checkedObject instanceof GHBranchProtection
        false
    }

}
