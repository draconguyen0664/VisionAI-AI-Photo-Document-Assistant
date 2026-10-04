$ErrorActionPreference = 'Stop'
gradle :backend:check :apps:kmp:composeApp:allTests :apps:kmp:composeApp:assembleDebug --no-daemon
docker compose config --quiet
Write-Host 'VisionAI verification completed.'
