package com.continuousx.utils.github

import com.cloudbees.groovy.cps.NonCPS

class GitURLParser {

    private String gitUrlRegex = "^(http|https|git|ssh)(:\\/\\/|@)([^\\/:]+)[\\/:]([^\\/:]+)\\/(.+)[.git]?\$"
    private String gitUrl

    GitURLParser(final String gitUrl) {
        Objects.requireNonNull(gitUrl)
        assert gitUrl.length() > 0
        this.gitUrl = gitUrl
    }

    String getProtocol() {
        def regexMatcher = (gitUrl =~ gitUrlRegex)
        return (regexMatcher ? regexMatcher[0][1] : null)
    }

    String getHostName() {
        def regexMatcher = (gitUrl =~ gitUrlRegex)
        return (regexMatcher ? regexMatcher[0][3] : null)
    }

    @NonCPS
    String getOrgaName() {
        def regexMatcher = (gitUrl =~ gitUrlRegex)
        return (regexMatcher ? regexMatcher[0][4] : null)
    }

    @NonCPS
    String getRepoName() {
        def regexMatcher = (gitUrl =~ gitUrlRegex)
        def repo = (regexMatcher ? regexMatcher[0][5] : null)
        return repo?.replace('.git', '')
    }

}
