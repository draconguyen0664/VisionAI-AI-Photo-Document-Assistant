$ErrorActionPreference = 'Stop'
& mvn.cmd -B -ntp -f backend/pom.xml test
if ($LASTEXITCODE -ne 0) { throw 'Backend checks failed.' }
& .\gradlew.bat :apps:kmp:composeApp:testDebugUnitTest :apps:kmp:composeApp:assembleDebug --no-daemon
if ($LASTEXITCODE -ne 0) { throw 'Android checks failed.' }
docker compose config --quiet
if ($LASTEXITCODE -ne 0) { throw 'Compose configuration is invalid.' }
Write-Host 'VisionAI verification completed.'
