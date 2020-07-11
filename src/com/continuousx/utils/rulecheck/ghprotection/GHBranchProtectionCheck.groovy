package com.continuousx.utils.rulecheck.ghprotection

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.utils.rulecheck.RuleBook
import com.continuousx.utils.rulecheck.RuleBookEntry
import com.continuousx.utils.rulecheck.RuleChecker
import com.continuousx.utils.rulecheck.RuleSet
import org.kohsuke.github.GHBranch

class GHBranchProtectionCheck implements RuleChecker {

    RuleBook ruleBook = new RuleBook()

    @Override
    @NonCPS
    boolean checkRules(RuleSet ruleSet, final Object checkedObject) {
        Objects.requireNonNull(ruleSet)
        Objects.requireNonNull(checkedObject)
        assert checkedObject instanceof GHBranch
        final GHBranch branch = checkedObject as GHBranch

        ruleSet.getRules().each {rule ->
            RuleBookEntry ruleBookEntry = new RuleBookEntry()
            ruleBookEntry.rule = rule
            ruleBookEntry.passed = rule.check(branch)
            ruleBookEntry.note = ruleBookEntry.passed ? rule.successfulNote : rule.condition
            ruleBook.addEntry ruleBookEntry
        }
        final List<RuleBookEntry> resultList = ruleBook.getEntryList().findAll { entry -> entry.passed == false }
        println "found passes: ${resultList.size()}"
        resultList.size() == 0
    }

}
