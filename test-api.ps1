# Script para probar la API de EduTrack Backend
# Uso: .\test-api.ps1
# O: .\test-api.ps1 -BaseUrl "https://edutrack-backend-830r.onrender.com"

param(
    [Parameter(Mandatory=$false)]
    [string]$BaseUrl = "https://edutrack-backend-830r.onrender.com"
)

Write-Host "🧪 Probando API EduTrack Backend" -ForegroundColor Cyan
Write-Host "URL Base: $BaseUrl" -ForegroundColor Yellow
Write-Host ""

# Función para hacer requests
function Test-Endpoint {
    param(
        [string]$Method,
        [string]$Endpoint,
        [hashtable]$Headers = @{},
        [object]$Body = $null
    )
    
    $url = "$BaseUrl$Endpoint"
    Write-Host "📡 $Method $Endpoint" -ForegroundColor Green
    
    try {
        $params = @{
            Uri = $url
            Method = $Method
            Headers = $Headers
            ContentType = "application/json"
            ErrorAction = "Stop"
        }
        
        if ($Body) {
            $params.Body = ($Body | ConvertTo-Json -Depth 10)
        }
        
        $response = Invoke-RestMethod @params
        Write-Host "✅ Éxito" -ForegroundColor Green
        $response | ConvertTo-Json -Depth 5
        Write-Host ""
        return $response
    }
    catch {
        Write-Host "❌ Error: $($_.Exception.Message)" -ForegroundColor Red
        if ($_.ErrorDetails.Message) {
            Write-Host "Detalles: $($_.ErrorDetails.Message)" -ForegroundColor Red
        }
        Write-Host ""
        return $null
    }
}

# 1. Verificar Swagger UI
Write-Host "=" * 60 -ForegroundColor Cyan
Write-Host "1. Verificando Swagger UI..." -ForegroundColor Cyan
Write-Host "=" * 60 -ForegroundColor Cyan
$swaggerUrl = "$BaseUrl/swagger-ui.html"
Write-Host "🌐 Abre en tu navegador: $swaggerUrl" -ForegroundColor Yellow
Write-Host ""

# 2. Probar registro de usuario
Write-Host "=" * 60 -ForegroundColor Cyan
Write-Host "2. Probando registro de usuario..." -ForegroundColor Cyan
Write-Host "=" * 60 -ForegroundColor Cyan

$registerData = @{
    firstname = "Test"
    lastname = "User"
    email = "test$(Get-Random)@example.com"
    password = "Test123456!"
    role = "STUDENT"
}

$registerResponse = Test-Endpoint -Method "POST" -Endpoint "/api/auth/register" -Body $registerData

if ($registerResponse) {
    $token = $registerResponse.token
    Write-Host "🎉 Usuario registrado exitosamente!" -ForegroundColor Green
    Write-Host "Token: $token" -ForegroundColor Yellow
    Write-Host ""
    
    # 3. Probar login
    Write-Host "=" * 60 -ForegroundColor Cyan
    Write-Host "3. Probando login..." -ForegroundColor Cyan
    Write-Host "=" * 60 -ForegroundColor Cyan
    
    $loginData = @{
        email = $registerData.email
        password = $registerData.password
    }
    
    $loginResponse = Test-Endpoint -Method "POST" -Endpoint "/api/auth/login" -Body $loginData
    
    if ($loginResponse) {
        $token = $loginResponse.token
        Write-Host "🎉 Login exitoso!" -ForegroundColor Green
        Write-Host "Token: $token" -ForegroundColor Yellow
        Write-Host ""
        
        # Headers con autenticación
        $authHeaders = @{
            "Authorization" = "Bearer $token"
        }
        
        # 4. Probar obtener usuario actual
        Write-Host "=" * 60 -ForegroundColor Cyan
        Write-Host "4. Probando obtener usuario actual..." -ForegroundColor Cyan
        Write-Host "=" * 60 -ForegroundColor Cyan
        Test-Endpoint -Method "GET" -Endpoint "/api/auth/me" -Headers $authHeaders
        
        # 5. Probar obtener estudiantes
        Write-Host "=" * 60 -ForegroundColor Cyan
        Write-Host "5. Probando obtener estudiantes..." -ForegroundColor Cyan
        Write-Host "=" * 60 -ForegroundColor Cyan
        Test-Endpoint -Method "GET" -Endpoint "/api/estudiantes" -Headers $authHeaders
        
        # 6. Probar obtener cursos
        Write-Host "=" * 60 -ForegroundColor Cyan
        Write-Host "6. Probando obtener cursos..." -ForegroundColor Cyan
        Write-Host "=" * 60 -ForegroundColor Cyan
        Test-Endpoint -Method "GET" -Endpoint "/api/cursos" -Headers $authHeaders
        
        # 7. Probar obtener inscripciones
        Write-Host "=" * 60 -ForegroundColor Cyan
        Write-Host "7. Probando obtener inscripciones..." -ForegroundColor Cyan
        Write-Host "=" * 60 -ForegroundColor Cyan
        Test-Endpoint -Method "GET" -Endpoint "/api/inscripciones" -Headers $authHeaders
        
        # 8. Probar obtener calificaciones
        Write-Host "=" * 60 -ForegroundColor Cyan
        Write-Host "8. Probando obtener calificaciones..." -ForegroundColor Cyan
        Write-Host "=" * 60 -ForegroundColor Cyan
        Test-Endpoint -Method "GET" -Endpoint "/api/calificaciones" -Headers $authHeaders
    }
}

Write-Host "=" * 60 -ForegroundColor Cyan
Write-Host "✅ Pruebas completadas!" -ForegroundColor Green
Write-Host "=" * 60 -ForegroundColor Cyan
Write-Host ""
Write-Host "💡 Para más información, consulta API_INFO.md" -ForegroundColor Yellow
Write-Host "🌐 Swagger UI: $swaggerUrl" -ForegroundColor Yellow


