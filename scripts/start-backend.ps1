$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path $PSScriptRoot -Parent
Push-Location $projectRoot
try {
    & mvn.cmd -B -ntp -f backend/pom.xml package
    if ($LASTEXITCODE -ne 0) { throw 'Backend build failed.' }
    & java -jar backend/target/visionai-backend-0.1.0.jar --spring.profiles.active=dev
    if ($LASTEXITCODE -ne 0) { throw 'Backend exited with an error.' }
} finally {
    Pop-Location
}
