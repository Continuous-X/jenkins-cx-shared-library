package com.continuousx.utils.rulecheck.ghprotection

import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.rulecheck.RuleBook
import com.continuousx.utils.rulecheck.RuleBookEntry
import com.continuousx.utils.rulecheck.RuleChecker
import com.continuousx.utils.rulecheck.RuleSet
import org.kohsuke.github.GHBranch

class GHBranchProtectionCheck implements RuleChecker {

    RuleBook ruleBook = new RuleBook()

    @Override
    @NonCPS
    boolean checkRules(RuleSet ruleSet, final Object checkedObject, final PipelineLogger logger) {
        Objects.requireNonNull(ruleSet)
        Objects.requireNonNull(checkedObject)
        logger.logInfo"use ruleset '${ruleSet.getClass().getName()}' and check '${checkedObject.getClass().getName()}'"
        assert checkedObject instanceof GHBranch
        final GHBranch branch = checkedObject as GHBranch

        ruleSet.getRules().each {rule ->
            RuleBookEntry ruleBookEntry = new RuleBookEntry()
            ruleBookEntry.rule = rule
            ruleBookEntry.passed = rule.check(branch)
            ruleBookEntry.note = ruleBookEntry.passed ? rule.successfulNote : rule.condition
            logger.logDebug """
rulebook entry
rule: ${ruleBookEntry.rule.toString()}
passed: ${ruleBookEntry.passed}
note: ${ruleBookEntry.note}
"""
            ruleBook.addEntry ruleBookEntry
        }
        final List<RuleBookEntry> resultList = ruleBook.getEntryList().findAll { entry -> entry.passed == false }
        logger.logInfo "found passes: ${resultList.size()}"
        resultList.size() == 0
    }

}
