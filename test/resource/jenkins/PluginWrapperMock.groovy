package resource.jenkins

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode

class PluginWrapperMock {

    String shortName
    String displayName
    String version
    String getShortName() {
        return shortName
    }
    String getDisplayName() {
        return displayName
    }
    String getVersion() {
        return version
    }
}
