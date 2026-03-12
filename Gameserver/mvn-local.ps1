param(
    [Parameter(ValueFromRemainingArguments = $true)]
    [string[]]$MavenArgs
)

$workspaceJdk8 = (Resolve-Path (Join-Path $PSScriptRoot "..\.tools\jdk8\jdk8u482-b08") -ErrorAction SilentlyContinue)

$javaHomeCandidates = @(
    $workspaceJdk8,
    "C:\Program Files\Java\jdk-21.0.10",
    "C:\Program Files\Java\latest"
) | Where-Object { $_ }

$mavenCandidates = @(
    "C:\Program Files\JetBrains\IntelliJ IDEA 2025.3.2\plugins\maven\lib\maven3\bin\mvn.cmd"
)

$javaHome = $javaHomeCandidates | Where-Object { Test-Path $_ } | Select-Object -First 1
if (-not $javaHome) {
    throw "No supported JDK installation was found."
}

$mavenCmd = $mavenCandidates | Where-Object { Test-Path $_ } | Select-Object -First 1
if (-not $mavenCmd) {
    throw "No supported Maven launcher was found."
}

$settingsFile = "C:\Users\Administrator\.m2\settings.xml"
$localRepository = "C:\Users\Administrator\.m2\repository"

if (-not (Test-Path $settingsFile)) {
    throw "Maven settings file was not found: $settingsFile"
}

$env:JAVA_HOME = $javaHome
$env:MAVEN_OPTS = "-Duser.home=C:\Users\Administrator -Dmaven.repo.local=$localRepository"

if (-not $MavenArgs -or $MavenArgs.Count -eq 0) {
    $MavenArgs = @("-DskipTests", "compile")
}

Push-Location $PSScriptRoot
try {
    & $mavenCmd -s $settingsFile @MavenArgs
}
finally {
    Pop-Location
}
