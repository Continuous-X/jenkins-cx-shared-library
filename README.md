# jenkins-cx-shared-library
shared library of jenkins-cx

## metrics
use influxdb
``docker run -p 8086:8086 -v %HOMEPATH%\.influxdb:/var/lib/influxdb influxdb``
use grafana
``docker run -d -p 3000:3000 grafana/grafana``