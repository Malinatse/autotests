# Instriments
* Intelij IDEA
* JDK
* Junit
* Docker
* Selenium
* Selenide
* Selenoid
# Environment insallation
## Docker
## Maven
1. Ensure **`JAVA_HOME`** environment variable is set and points to your JDK installation
2. Go to [Maven Download Page](https://maven.apache.org/download.cgi) ad download an achive
3. Add the unpacked distribution’s bin directory to your user **`MAVEN_HOME`** environment variable by opening up the system properties (WinKey + Pause), selecting the “Advanced” tab, and the “Environment Variables” button, then adding or selecting the **`MAVEN_HOME`** variable in the user variables with the value `C:\Program Files\apache-maven-3.8.1\bin`. The same dialog can be used to set **`JAVA_HOME`** to the location of your JDK, e.g. `C:\Program Files\Java\jdk1.7.0_51` 
4. Add **`%MAVEN_HOME%\bin`** to the **`Path`** environment variable

## Selenoid
1. Make sure you have recent [Docker](https://www.docker.com/) version installed.
2. Download [Configuration Manager](https://aerokube.com/cm/latest/) (Selenoid quick installation binary) for your platform from [releases](https://github.com/aerokube/cm/releases) page.
3. Extract the downloaded file, rename it to `cm` and add it to the **`Path`** environment variable

# Tests execution
## Maven
### To run tests and generate Allure report:
* run `mvn clean test`
* run `mvn test -Dgroups="<GroupClass>"` to run selected test group
    ex: `mvn clean compile && mvn clean test -Dtest=LoginTest`

### To run tests and generate Allure report on server:
* `mvn clean test -Dtest=LoginTest -Dchromeoptions.args=headless` to run selected test group
* `mvn clean test -Dchromeoptions.args=headless` to all tests

# Tests reporting
### To see an Allure report:
* run `mvn allure:serve`

# Selenoid CLI
### To run Selenoid server:
* run `cm selenoid start --vnc` to start Selenoid server
### To run Selenoid [UI](http://localhost:8080/)
* run `cm selenoid-ui start`
