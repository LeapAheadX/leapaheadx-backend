@echo off

REM Remove the existing bin/ directory
rmdir /s /q bin

REM Create a new bin/ directory
mkdir bin

REM Build the project with Maven
mvn clean install

REM Copy the jar file to the bin/ directory
copy target\*.jar bin\
