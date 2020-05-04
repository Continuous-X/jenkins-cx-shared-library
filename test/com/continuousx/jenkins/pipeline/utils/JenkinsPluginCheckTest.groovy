package com.continuousx.jenkins.pipeline.utils


import com.continuousx.jenkins.PipelineMock
import org.codehaus.groovy.runtime.powerassert.PowerAssertionError
import spock.lang.Specification

class JenkinsPluginCheckTest extends Specification {
    def "should be successful with needed plugin list"() {
        given:
        List<String> pluginListInstalled = ["test-plugin-1", "test-plugin-2", "test-plugin-3"]
        List<String> pluginListNeeded = ["test-plugin-1", "test-plugin-2", "test-plugin-3"]
        JenkinsPluginCheck pluginCheck = new JenkinsPluginCheck(new PipelineMock())

        when:
        boolean checkResult = pluginCheck.addInstalledPlugins(pluginListInstalled)
                .and()
                .addNeededPluginList(pluginListNeeded)
                .and()
                .isPluginListInstalled()

        then:
        assert checkResult
    }

    def "should be failed with needed plugin list"() {
        given:
        List<String> pluginListInstalled = ["test-plugin-1", "test-plugin-2", "test-plugin-3"]
        List<String> pluginListNeeded = ["test-plugin-1", "test-plugin-4"]
        JenkinsPluginCheck pluginCheck = new JenkinsPluginCheck(new PipelineMock())

        when:
        boolean checkResult = pluginCheck.addInstalledPlugins(pluginListInstalled)
                .and()
                .addNeededPluginList(pluginListNeeded)
                .and()
                .isPluginListInstalled()

        then:
        assert checkResult == false
    }

    def "should be failed with empty needed lugin list"() {
        given:
        List<String> pluginListInstalled = ["test-plugin-1", "test-plugin-2", "test-plugin-3"]
        List<String> pluginListNeeded = []
        JenkinsPluginCheck pluginCheck = new JenkinsPluginCheck(new PipelineMock())

        when:
        boolean checkResult = pluginCheck.addInstalledPlugins(pluginListInstalled)
                .and()
                .addNeededPluginList(pluginListNeeded)
                .and()
                .isPluginListInstalled()

        then:
        def ex = thrown(AssertionError)
    }

    def "should be failed when needed plugin list is null"() {
        given:
        List<String> pluginListInstalled = ["test-plugin-1", "test-plugin-2", "test-plugin-3"]
        JenkinsPluginCheck pluginCheck = new JenkinsPluginCheck(new PipelineMock())

        when:
        boolean checkResult = pluginCheck.addInstalledPlugins(pluginListInstalled)
                .and()
                .addNeededPluginList(null)
                .and()
                .isPluginListInstalled()

        then:
        def ex = thrown(NullPointerException)
    }

    def "should be failed when installed plugin list is null"() {
        given:
        List<String> pluginListNeeded = []
        JenkinsPluginCheck pluginCheck = new JenkinsPluginCheck(new PipelineMock())

        when:
        boolean checkResult = pluginCheck.addInstalledPlugins(null)
                .and()
                .addNeededPluginList(pluginListNeeded)
                .and()
                .isPluginListInstalled()

        then:
        def ex = thrown(PowerAssertionError)
    }

}
