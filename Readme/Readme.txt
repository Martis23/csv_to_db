1 STEP: Install h2 database. Run file and install: h2-setup-2022-06-13.exe in h2-setup-2022-06-13.zip

2 STEP: Install JAVA JDK link: https://www.oracle.com/java/technologies/downloads/#jdk19-windows

3 STEP: Install Apache MAVEN. File: apache-maven-3.9.0-bin.zip
Must know that the command:mvn works after FULLY!!! installing Apache MAVEN.
Run in terminal: mvn --version 
to confirm you have fully installed MAVEN
Apache MAVEN tutorial is here:https://www.youtube.com/watch?v=km3tLti4TCM

4 STEP: For working with application use IntelliJ IDEA. Link: https://www.jetbrains.com/idea/download/#section=windows 

5 STEP: Load repository csv_to_db with IntelliJ IDEA and run from IntelliJ terminal command:mvn spring-boot:run 

6 STEP: After running MAVEN in INTELLIJ IDEA you should see the program run in your internet browser with a link: http://localhost:8080/

7 STEP: IMPORTANT!!! The importing databases fields should be without extra commas. Examples of proper CSV files are in folder: Sample csv files to add to database. 
If extra commas will be present in data fields of excel file expect errors in program, 
because data is added with String SQL queries so extra comma misinformation in queries results to errors. 

8 STEP: If there is an entry named group in the top line change it (example:grouperis) because JAVA thinks it's GROUP SQL command not a group entry of the first line.

9 STEP: If there is an error like database is used then restart your computer.


Please enjoy my software yours Martynas C.