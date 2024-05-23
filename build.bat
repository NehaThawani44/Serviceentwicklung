REM Clean and build the project, skip tests if needed
mvn clean install

REM Alternatively, to skip the tests during the build process:
REM mvn clean install -DskipTests

echo Running the application...
java -jar target\*.jar --server.port=8080

REM Pause the script to view any output before closing
pause