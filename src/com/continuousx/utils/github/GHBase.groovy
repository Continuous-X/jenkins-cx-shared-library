package com.continuousx.utils.github

import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection
import org.kohsuke.github.GHOrganization
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub

class GHBase {

    private String ghOrganizationName
    private String ghRepositoryName
    private String ghUserToken

    GHBase(final String ghUrl, final String userToken) {
        Objects.requireNonNull(ghUrl)
        assert ghUrl.length() > 0
        Objects.requireNonNull(userToken)
        assert userToken.length() > 0
        GitURLParser gitURLParser = new GitURLParser(ghUrl)
        this.ghOrganizationName = gitURLParser.getOrgaName()
        this.ghRepositoryName = gitURLParser.getRepoName()
        this.ghUserToken = userToken
    }

    GitHub getConnection() {
        GitHub.connectUsingOAuth(this.ghUserToken)
    }

    GHOrganization getOrganization() {
        getConnection().getOrganization(ghOrganizationName)
    }

    GHRepository getRepository() {
        getOrganization().getRepository(ghRepositoryName)
    }

    GHBranchProtection getBranchProtection(final String branchName) {
        Objects.requireNonNull(branchName)
        assert branchName.length() > 0
        getRepositoryBranch(branchName).getProtection()
    }

    GHBranch getRepositoryBranch(final String branchName) {
        Objects.requireNonNull(branchName)
        assert branchName.length() > 0
        getRepository().getBranch(branchName)
    }

}
