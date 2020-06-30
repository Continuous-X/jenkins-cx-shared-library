package com.continuousx.jenkins.features.github.protection

import com.continuousx.jenkins.features.AbstractFeature

class FeatureGHProtectionCheckImpl extends AbstractFeature {

    public static final String PLUGINS_TXT_FILENAME = 'plugins.txt'
    public static final String POM_XML_FILENAME = 'pom.xml'


    @SuppressWarnings('GroovyUntypedAccess')
    protected FeatureGHProtectionCheckImpl(final def jenkinsContext, final FeatureGHProtectionCheckConfig featureConfig) {
        super(jenkinsContext,
                ['github-api'],
                featureConfig)
    }

    @Override
    void runFeatureImpl() {

    }

}
