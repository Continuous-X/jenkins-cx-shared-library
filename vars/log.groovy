import com.cloudbees.groovy.cps.NonCPS

def debug(String message) {
    echo "DEBUG: ${message}"
}

@NonCPS
def info(String message) {
    echo "INFO: ${message}"
}

def warning(String message) {
    echo "WARNING: ${message}"
}

def error(String message) {
    echo "ERROR: ${message}"
}