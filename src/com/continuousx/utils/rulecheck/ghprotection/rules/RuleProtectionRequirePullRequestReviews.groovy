package com.continuousx.utils.rulecheck.ghprotection.rules

import com.continuousx.utils.rulecheck.Rule
import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection

class RuleProtectionRequirePullRequestReviews implements Rule {

    String condition = 'Branch Protection Option Require must be activate - [Require pull request reviews before merging]'
    String successfulNote = 'Branch Protection - Require pull request reviews is activated'

    @Override
    boolean check(final Object checkedObject) {
        Objects.requireNonNull(checkedObject)
        assert checkedObject instanceof GHBranch
        final GHBranch branch = checkedObject as GHBranch
        GHBranchProtection branchProtection
        try {
            branchProtection = branch.getProtection()
        } catch (final IOException ignored) {
            return false
        }
        branchProtection.requiredReviews != null
    }

}
