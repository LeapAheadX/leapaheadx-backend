@echo off

REM Remove the existing bin/ directory
rmdir /s /q bin

REM Create a new bin/ directory
mkdir bin

REM Run the SQL script to create the database
mysql -h hostname -u username -ppassword < path/to/create_database.sql

REM Build the project with Maven
mvn clean install

REM Copy the jar file to the bin/ directory
copy target\*.jar bin\

REM Run the SQL script to populate the database
mysql -h hostname -u username -ppassword database_name < path/to/populate_database.sql