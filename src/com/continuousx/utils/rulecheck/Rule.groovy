package com.continuousx.utils.rulecheck

interface Rule {

    String name = getClass().getName()
    String condition
    String successfulNote

    boolean check(Object checkedObject)

}
