import com.cloudbees.groovy.cps.NonCPS
import com.continuousx.utils.jenkins.JenkinsConfig

@NonCPS
def debug(String message) {
    sh "echo \033[0;33m[${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB} DEBUG]\033[0m ${message}"
}

@NonCPS
def info(String message) {
    echo "[${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB} INFO] ${message}"
}

@NonCPS
def warning(String message) {
    echo "[${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB} WARNING] ${message}"
}

@NonCPS
def error(String message) {
    echo "[${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB} ERROR] ${message}"
}

@NonCPS
def fatal(String message) {
    echo "[${JenkinsConfig.JENKINS_CONFIG_GLOBAL_LIBRARY_JENKINS_CX_SHARED_LIB} FATAL] ${message}"
}
