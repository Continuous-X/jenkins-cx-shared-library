package com.continuousx.utils.rulecheck.ghprotection.rules

import com.continuousx.utils.rulecheck.Rule
import org.kohsuke.github.GHBranch

class RuleProtectionActive implements Rule {

    String condition = 'Branch Protection must be activate'
    String successfulNote = 'Branch Protection is activated'

    @Override
    boolean check(final Object checkedObject) {
        Objects.requireNonNull(checkedObject)
        assert checkedObject instanceof GHBranch
        final GHBranch branch = checkedObject as GHBranch
        try {
            branch.getProtection()
        } catch (final IOException ignored) {
            return false
        }
        true
    }

}
