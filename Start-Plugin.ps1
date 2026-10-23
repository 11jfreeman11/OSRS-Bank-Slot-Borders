$ErrorActionPreference = 'Stop'
Set-Location $PSScriptRoot
$workspace = Split-Path (Split-Path $PSScriptRoot -Parent) -Parent
$portableJdk = Get-ChildItem (Join-Path $workspace 'work/jdk11') -Directory -ErrorAction SilentlyContinue | Select-Object -First 1
if ($portableJdk) { $env:JAVA_HOME = $portableJdk.FullName }
$env:GRADLE_USER_HOME = Join-Path $workspace 'work/gradle-home'
& "$PSScriptRoot/gradlew.bat" run
exit $LASTEXITCODE
