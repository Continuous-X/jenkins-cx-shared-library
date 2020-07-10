package com.continuousx.utils.rulecheck.ghprotection.rules

import com.continuousx.utils.rulecheck.Rule
import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection

class RuleProtectionRequireSignedCommits implements Rule {

    String condition = 'Branch Protection Option must be activate - [Require signed commits]'
    String successfulNote = 'Branch Protection - Require signed commits is activated'

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
        branchProtection?.requiredSignatures
    }

}
