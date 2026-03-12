@echo off
setlocal

set "SCRIPT_DIR=%~dp0"
for %%I in ("%SCRIPT_DIR%..\.tools\jdk8\jdk8u482-b08") do set "WORKSPACE_JDK8=%%~fI"

set "JAVA_HOME=%WORKSPACE_JDK8%"
if not exist "%JAVA_HOME%\bin\java.exe" set "JAVA_HOME=C:\Program Files\Java\jdk-21.0.10"
if not exist "%JAVA_HOME%\bin\java.exe" set "JAVA_HOME=C:\Program Files\Java\latest"
if not exist "%JAVA_HOME%\bin\java.exe" (
  echo No supported JDK installation was found.
  exit /b 1
)

set "MAVEN_CMD=C:\Program Files\JetBrains\IntelliJ IDEA 2025.3.2\plugins\maven\lib\maven3\bin\mvn.cmd"
if not exist "%MAVEN_CMD%" (
  echo No supported Maven launcher was found.
  exit /b 1
)

set "SETTINGS_FILE=C:\Users\Administrator\.m2\settings.xml"
set "MAVEN_OPTS=-Duser.home=C:\Users\Administrator -Dmaven.repo.local=C:\Users\Administrator\.m2\repository"

if not exist "%SETTINGS_FILE%" (
  echo Maven settings file was not found: %SETTINGS_FILE%
  exit /b 1
)

if "%~1"=="" (
  call "%MAVEN_CMD%" -s "%SETTINGS_FILE%" -DskipTests compile
) else (
  call "%MAVEN_CMD%" -s "%SETTINGS_FILE%" %*
)
