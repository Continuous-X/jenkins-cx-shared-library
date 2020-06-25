package com.continuousx.utils.github

class BranchExpressionChecker {

    @SuppressWarnings("GroovyFallthrough")
    static boolean checkBranchExpression(final BranchType allowedBranches, final String currentBranch) {
        switch (allowedBranches) {
            case BranchType.NOT_MASTER:
                if (currentBranch != "master") return true
                break
            case BranchType.MASTER:
                if (currentBranch == "master") return true
                break
            case BranchType.ALL:
                return true
        }
        return false
    }
}
