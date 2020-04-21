package com.continuousx.jenkins.features.maven

import org.apache.maven.model.Parent

interface MavenPomFeature {
    MavenPomFeature readPomXmlContent(String pomXmlContent)
    MavenPomFeature and()
    MavenPomFeature convertDependencies2PluginsTxt()
    String getPluginsTxtContent()
    Parent getParent()
}