package com.continuousx.utils.github

import org.kohsuke.github.GHBranch
import org.kohsuke.github.GHBranchProtection
import org.kohsuke.github.GHOrganization
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub

class GHBase {

    String ghOrganizationName
    String ghRepositoryName

    GHBase(final String ghUrl) {
        Objects.requireNonNull(ghUrl)
        assert ghUrl.length() > 0
        GitURLParser gitURLParser = new GitURLParser(ghUrl)
        this.ghOrganizationName = gitURLParser.getOrgaName()
        this.ghRepositoryName = gitURLParser.getRepoName()
    }

    GitHub getConnection(final String token) {
        Objects.requireNonNull(token)
        GitHub.connectUsingOAuth(token)
    }

    GHOrganization getOrganization(final String token) {
        Objects.requireNonNull(token)
        getConnection(token).getOrganization(ghOrganizationName)
    }

    GHRepository getRepository(final String token) {
        Objects.requireNonNull(token)
        getOrganization(token).getRepository(ghRepositoryName)
    }

    GHBranchProtection getBranchProtection(final String token, final String branchName) {
        Objects.requireNonNull(token)
        Objects.requireNonNull(branchName)
        assert branchName.length() > 0
        getRepositoryBranch(token,branchName).getProtection()
    }

    GHBranch getRepositoryBranch(final String token, final String branchName) {
        Objects.requireNonNull(token)
        Objects.requireNonNull(branchName)
        assert branchName.length() > 0
        getRepository(token).getBranch(branchName)
    }

}
