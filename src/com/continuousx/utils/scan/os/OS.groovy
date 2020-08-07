package com.continuousx.utils.scan.os

import org.apache.commons.lang3.SystemUtils

class OS {

    static final String OS_NAME
    static final String USER_NAME

    public OS() {
    }

    static {
        OS_NAME = SystemUtils.OS_NAME
        USER_NAME = SystemUtils.USER_NAME
    }

}
