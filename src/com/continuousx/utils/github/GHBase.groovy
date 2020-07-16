package com.continuousx.utils.github

import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection
import org.kohsuke.github.GHOrganization
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub

class GHBase {

    final static String GH_COMMIT_STATE_CONTEXT_SHARED_LIB = 'jenkins-cx-shared-lib'

    static GitHub getConnetctionOAuth(final String ghToken) {
        Objects.requireNonNull(ghToken)
        GitHub.connectUsingOAuth(ghToken)
    }

    static GHOrganization getOrganization(final String ghUrl, final GitHub connection) {
        Objects.requireNonNull(ghUrl)
        Objects.requireNonNull(connection)
        connection.getOrganization(new GitURLParser(ghUrl).getOrgaName())
    }

    static GHRepository getRepository(final String ghUrl, final GitHub connection) {
        Objects.requireNonNull(ghUrl)
        Objects.requireNonNull(connection)
        getOrganization(ghUrl, connection).getRepository(new GitURLParser(ghUrl).getRepoName())
    }

    static GHBranch getRepositoryBranch(final String branchName, final String ghUrl, final GitHub connection) {
        Objects.requireNonNull(ghUrl)
        Objects.requireNonNull(connection)
        Objects.requireNonNull(branchName)
        assert branchName.length() > 0
        getRepository(ghUrl, connection).getBranch(branchName)
    }

}
