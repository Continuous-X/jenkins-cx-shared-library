import com.cloudbees.groovy.cps.NonCPS

@NonCPS
def debug(String message) {
    echo "DEBUG: ${message}"
}

def info(String message) {
    echo "INFO: ${message}"
}

@NonCPS
def warning(String message) {
    echo "WARNING: ${message}"
}

@NonCPS
def error(String message) {
    echo "ERROR: ${message}"
}

@NonCPS
def fatal(String message) {
    echo "FATAL: ${message}"
}
