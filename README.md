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
