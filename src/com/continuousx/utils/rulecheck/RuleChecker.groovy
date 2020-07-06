package com.continuousx.utils.rulecheck

import org.kohsuke.github.GHBranchProtection

interface RuleChecker {

    boolean checkRules(RuleSet ruleSet, final GHBranchProtection ghBranchProtection)

}
