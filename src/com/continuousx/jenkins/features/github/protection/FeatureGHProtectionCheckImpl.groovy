package com.continuousx.jenkins.features.github.protection

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.utils.github.GHBase
import com.continuousx.utils.jenkins.JenkinsConfig
import com.continuousx.utils.rulecheck.ghprotection.GHBranchProtectionCheck
import com.continuousx.utils.rulecheck.ghprotection.RuleSetProtectionSimple
import org.kohsuke.github.GHBranchProtection

class FeatureGHProtectionCheckImpl extends AbstractFeature {

    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureGHProtectionCheckImpl(final def jenkinsContext, final FeatureGHProtectionCheckConfig featureConfig) {
        super(jenkinsContext,
                ['github-api','credentials-binding'],
                featureConfig)
    }

    @SuppressWarnings('GroovyUntypedAccess')
    @Override
    void runFeatureImpl() {
        jenkinsContext.withCredentials([jenkinsContext.usernamePassword(credentialsId: JenkinsConfig.JENKINS_CONFIG_CREDENTIAL_ID_GITHUB_API, usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
            final boolean protectionResult = new GHBranchProtectionCheck().checkRules(new RuleSetProtectionSimple(), this.jenkinsContext.env.GIT_BRANCH)
            jenkinsContext.log.info "Protection on '${jenkinsContext.env.GIT_BRANCH}': ${protectionResult}"
        }
    }

}
