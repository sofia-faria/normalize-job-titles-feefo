### Feefo Technical Assessment - normalizeJobTitles

This project uses Maven, SpringBoot and has an embedded database (H2).

Setup para dev:
- Use JAVA 11
- Compile with maven
  - mvn clean install 
  - mvn -pl persistence spring-boot:run
- Run MoviesChallengeApplication springboot application


Test APIs:
  - To normalize string: {api/jobtitle/normalize} with BODY: 
    - {"job":"Java engineer"}
  - You can also test the other CRUD operations (path "api/jobtitle")
