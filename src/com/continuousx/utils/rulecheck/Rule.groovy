package com.continuousx.utils.rulecheck

import org.kohsuke.github.GHBranchProtection

interface Rule {

    String name
    boolean passed

    boolean check(GHBranchProtection ghBranchProtection)

}
