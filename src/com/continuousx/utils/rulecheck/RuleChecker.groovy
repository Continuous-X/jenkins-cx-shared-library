package com.continuousx.utils.rulecheck

import com.continuousx.jenkins.logger.PipelineLogger

interface RuleChecker {

    boolean checkRules(RuleSet ruleSet, final Object checkedObject, final PipelineLogger logger)

}
