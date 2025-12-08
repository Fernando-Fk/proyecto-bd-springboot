# Script para generar JWT_SECRET en Base64 (mínimo 32 bytes)
# Ejecuta este script en PowerShell para obtener tu clave secreta

# Genera 32 bytes aleatorios y los convierte a Base64
$bytes = New-Object byte[] 32
$rng = [System.Security.Cryptography.RandomNumberGenerator]::Create()
$rng.GetBytes($bytes)
$base64Secret = [Convert]::ToBase64String($bytes)

Write-Host "=========================================" -ForegroundColor Green
Write-Host "Tu JWT_SECRET (copia este valor):" -ForegroundColor Yellow
Write-Host $base64Secret -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Green
Write-Host ""
Write-Host "Este valor tiene exactamente 32 bytes (256 bits)" -ForegroundColor Gray
Write-Host "Úsalo en Render como variable de entorno JWT_SECRET" -ForegroundColor Gray

