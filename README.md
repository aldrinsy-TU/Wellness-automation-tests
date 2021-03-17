 How to Run this Automated Tests
 ==============================
 
 Application to install:
 1. OpenJdk ver 1.8 (https://github.com/ojdkbuild/ojdkbuild)
    
    - Extract the zip file into a folder, e.g. C:\Program Files\Java\ and it will create a jdk-8 folder (where the bin folder is a direct sub-folder). 
    - You may need Administrator privileges to extract the zip file to this location.

    Set a PATH:
     - Select Control Panel and then System.
     - Click Advanced and then Environment Variables.
     - Add the location of the bin folder of the JDK installation to the PATH variable in System Variables.
     - The following is a typical value for the PATH variable: C:\WINDOWS\system32;C:\WINDOWS;"C:\Program Files\Java\jdk-8\bin"

    Set JAVA_HOME:
     - Under System Variables, click New.
     - Enter the variable name as JAVA_HOME.
     - Enter the variable value as the installation path of the JDK (without the bin sub-folder).
     - Click OK.
     - Click Apply Changes.
     
     - Check that installation is successful by running "java -version" on the command prompt and see if it prints your newly installed JDK.
     
 2. Apache Maven (https://maven.apache.org/download.cgi)
    - Download and extract the zip file to a directory of your choice
    e.g. C>apache-maven
    - Set the MAVEN_HOME variable to maven installation folder (same as setting the JAVA_HOME)
    - Include the maven/bin directory in the PATH variable (same as setting the PATh for OpenJDK)
    
    Verify maven installation is complete.
     - Go to start menu and type cmd in application location search box.
     -  Press ENTER. A new command prompt will be opened.
     -  Type mvn -version in command prompt and hit ENTER.
     
 3. TortoiseSVN (https://tortoisesvn.net/downloads.html)
    - needs Admin privileges to install
    
 3. Intellij Community Edition for Devs/Automation Testers 
    (https://www.jetbrains.com/idea/download/#section=windows)
    - needs Admin privileges to install
    - Import the files as Maven Project
 
 B. Download the App on SVN:
    TortoiseSVN is a convenient SVN client for Windows that integrates with Windows Explorer.
 
     Go to Windows Explorer (hit WIN+E or right-click on the Start button and click Explore), and create a new folder in a location of your choice. 
     Right click on that folder and TortoiseSVN -> Create repository here.
     Right click on that folder again and click SVN Checkout; DO NOT CLICK "IMPORT".
     For the "URL of repository," put https://10.10.192.61:80/svn/QA_Scripts/twe-automation-tests_malou
     Click OK.
 
  How to run:
   Go to the directory where files was checked out/downloaded from SVN e.g.
     C:\TWE\QA_Scripts\twe-automation-tests>
   1. Run all tests on the specified environment (environment settings are found on serenity.conf)
      - Type "mvn clean verify -Denvironment=dev". Click Enter.
   2. Run on a specified environment specific functions only 
      e.g. mvn clean verify -Denvironment=dev -Dcucumber.options="--tags @function=login "
      Click Enter
   3. Run using a specific browser
      mvn clean verify -Dwebdriver.driver=firefox       
   4. Reports are found on the reports folder inside the directory. Open index.html on any browser.   
  
 
