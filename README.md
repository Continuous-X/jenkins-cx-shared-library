# jenkins-cx-shared-library
shared library of jenkins-cx

## Development Notes
- update parent pom
``./mvnw versions:update-parent -s .mvn/settings.xml``
- update used plugins
``./mvnw versions:display-plugin-updates -s .mvn/settings.xml``
- update dependencies
``./mvnw versions:display-dependency-updates -s .mvn/settings.xml``
- local build
``./mvnw clean install -s .mvn/settings.xml``

## metrics
use influxdb
``docker run -p 8086:8086 -v %HOMEPATH%\.influxdb:/var/lib/influxdb influxdb``
use grafana
``docker run -d -p 3000:3000 grafana/grafana``