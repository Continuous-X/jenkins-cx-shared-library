package com.continuousx.utils.rulecheck.ghprotection

import com.continuousx.utils.rulecheck.RuleBook
import com.continuousx.utils.rulecheck.RuleBookEntry
import com.continuousx.utils.rulecheck.RuleChecker
import com.continuousx.utils.rulecheck.RuleSet
import org.kohsuke.github.GHBranchProtection

class GHBranchProtectionCheck implements RuleChecker {

    RuleBook ruleBook = new RuleBook()

    @Override
    boolean checkRules(RuleSet ruleSet, final GHBranchProtection ghBranchProtection) {
        Objects.requireNonNull(ruleSet)
        Objects.requireNonNull(ghBranchProtection)
        ruleSet.getRules().each {rule ->
            RuleBookEntry ruleBookEntry = new RuleBookEntry()
            ruleBookEntry.rule = rule
            ruleBookEntry.passed = rule.check(ghBranchProtection)
            ruleBookEntry.note = ruleBookEntry.passed ? rule.successfulNote : rule.condition
            ruleBook.addEntry ruleBookEntry
        }
        List<RuleBookEntry> resultList = ruleBook.getEntryList().findAll {entry -> entry.passed == false }
        println "found passes: ${resultList.size()}"
        resultList.size() > 0 ? false : true
    }

}
