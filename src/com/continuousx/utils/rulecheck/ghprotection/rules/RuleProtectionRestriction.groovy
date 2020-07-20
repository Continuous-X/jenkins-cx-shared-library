package com.continuousx.utils.rulecheck.ghprotection.rules

import com.continuousx.utils.rulecheck.Rule
import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection

class RuleProtectionRestriction implements Rule {

    String condition = 'Branch Protection Option must be deactivate - [Restrict who can push to matching branches]'
    String successfulNote = 'Branch Protection - Require restriction is deactivated'

    @Override
    boolean check(final Object checkedObject) {
        Objects.requireNonNull(checkedObject)
        assert checkedObject instanceof GHBranch
        final GHBranch branch = checkedObject as GHBranch
        GHBranchProtection branchProtection
        try {
            branchProtection = branch.getProtection()
        } catch (final Exception ignored) {
            return false
        }
        branchProtection?.restrictions == null
    }

}
