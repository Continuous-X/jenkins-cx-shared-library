package com.continuousx.utils.rulecheck.ghprotection.rules

import com.continuousx.utils.rulecheck.Rule
import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection

class RuleProtectionRequireStatusCheck implements Rule {

    String condition = 'Branch Protection Option Require must be activate - [Require status checks to pass before merging]'
    String successfulNote = 'Branch Protection - Require status checks is activated'

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
        branchProtection?.requiredStatusChecks != null
    }

}
