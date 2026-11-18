@echo off
if not exist bin mkdir bin
javac -d bin src\*.java
if errorlevel 1 (
  echo Compilation failed.
  pause
  exit /b 1
)
java -cp bin Main
