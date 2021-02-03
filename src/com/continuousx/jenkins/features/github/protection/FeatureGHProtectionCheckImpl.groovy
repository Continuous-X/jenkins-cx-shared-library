package com.continuousx.jenkins.features.github.protection

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.jenkins.logger.PipelineLogger
import com.continuousx.utils.github.GHBase
import com.continuousx.utils.jenkins.JenkinsConfig
import com.continuousx.utils.rulecheck.ghprotection.GHBranchProtectionCheck
import com.continuousx.utils.rulecheck.ghprotection.RuleSetProtectionSimple
import org.kohsuke.github.GHBranch

class FeatureGHProtectionCheckImpl extends AbstractFeature {

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureGHProtectionCheckImpl(final def jenkinsContext, final FeatureGHProtectionCheckConfig featureConfig, final PipelineLogger logger) {
        super(jenkinsContext,
                ['github-api', 'credentials-binding'],
                featureConfig,
                logger)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runFeatureImpl() {
        this.logger.logInfo "start Protection check on '${this.jenkinsContext.env.GIT_BRANCH}'"
        this.jenkinsContext.withCredentials([this.jenkinsContext.usernamePassword(credentialsId: JenkinsConfig.JENKINS_CONFIG_CREDENTIAL_ID_GITHUB_API, usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
            final GHBranch branch = GHBase.getRepositoryBranch(this.jenkinsContext.env.GIT_BRANCH as String, this.jenkinsContext.env.GIT_URL as String, GHBase.getConnetctionOAuth(this.jenkinsContext.TOKEN as String))
            final boolean protectionResult = new GHBranchProtectionCheck().checkRules(new RuleSetProtectionSimple(), branch, logger)
            this.logger.logInfo "Protection on '${this.jenkinsContext.env.GIT_BRANCH}': ${protectionResult}"
        }
    }

}
