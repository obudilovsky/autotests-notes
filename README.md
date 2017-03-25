# autotests-notes
Created automation tests using general stack of technologies: Java + WebDriver + JUnit + mvn

Task:
1. Create automation tests based on the requirements:
    - User should be able to log in, log out, change my account settings, add notes, delete notes.
    - User should be able to add an unlimited number of notes.
    - User should be able to add notes, only with a title, only with a description, but not empty.

General stack of technologies: 
- Java + WebDriver + JUnit + mvn

Preconditions:
-	Installed IntelliJ IDEA Community Edition 2016.3.5
-	Installed apache-maven-3.3.9
-	Installed Google Chrome browser
-	Installed Java JDK 1.8
- Installed nodejs (node-v6.10.1-x64.msi, link: https://nodejs.org/en/download/ )
-	Downloaded chromedriver.exe (Google Chrome Driver 2.28, link: http://www.seleniumhq.org/download/ )

Instructions:
-	Open "autotests-notes" project in IntelliJ IDEA
-	Set corrected path to "webdriver.chrome.driver" in "systemPropertyVariables" of pom.xml
- Execute "npm i" in terminal
- After all packages installed, execute "npm start" in terminal to run web application
-	Select "clean" and "test" lifecycles in Maven Projects panel and run Maven build
