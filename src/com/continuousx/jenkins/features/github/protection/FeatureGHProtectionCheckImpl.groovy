package com.continuousx.jenkins.features.github.protection

import com.continuousx.jenkins.features.AbstractFeature
import com.continuousx.utils.github.GHBase
import com.continuousx.utils.jenkins.JenkinsConfig
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
            //noinspection GroovyAssignabilityCheck
            final GHBase github = new GHBase(jenkinsContext.env.GIT_URL, jenkinsContext.TOKEN)
            //noinspection GroovyAssignabilityCheck
            GHBranchProtection protection = github.getBranchProtection(jenkinsContext.env.GIT_BRANCH)
            jenkinsContext.log.info "Protection on '${jenkinsContext.env.GIT_BRANCH}': ${protection}"
        }
    }

}
