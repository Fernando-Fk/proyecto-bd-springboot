# 📚 Información de tu API EduTrack Backend

## 🔗 URL de tu API

✅ **Tu API está desplegada y funcionando:**

```
https://edutrack-backend-830r.onrender.com
```

**Estado:** ✅ Live y funcionando correctamente
**Puerto:** 10000 (asignado automáticamente por Render)
**Base de datos:** ✅ Conectada (PostgreSQL 18.1)

## 📖 Documentación Swagger (Interfaz Web)

Accede a la documentación interactiva de Swagger:

🌐 **Swagger UI:**
```
https://edutrack-backend-830r.onrender.com/swagger-ui.html
```

O también puedes probar:
```
https://edutrack-backend-830r.onrender.com/swagger-ui/index.html
```

**¡Abre este enlace en tu navegador para ver y probar todos los endpoints!**

En Swagger podrás:
- Ver todos los endpoints disponibles
- Probar los endpoints directamente desde el navegador
- Ver los modelos de datos (DTOs)
- Autenticarte con JWT

## 🛣️ Endpoints Disponibles

### 🔐 Autenticación (`/api/auth`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Registrar nuevo usuario | No |
| POST | `/api/auth/login` | Iniciar sesión | No |
| GET | `/api/auth/me` | Obtener usuario actual | Sí (JWT) |

### 👨‍🎓 Estudiantes (`/api/estudiantes`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/estudiantes` | Listar todos los estudiantes | Sí (JWT) |
| GET | `/api/estudiantes/{id}` | Obtener estudiante por ID | Sí (JWT) |
| POST | `/api/estudiantes` | Crear nuevo estudiante | Sí (JWT) |
| PUT | `/api/estudiantes/{id}` | Actualizar estudiante | Sí (JWT) |
| DELETE | `/api/estudiantes/{id}` | Eliminar estudiante | Sí (JWT) |

### 📚 Cursos (`/api/cursos`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/cursos` | Listar todos los cursos | Sí (JWT) |
| GET | `/api/cursos/{id}` | Obtener curso por ID | Sí (JWT) |
| GET | `/api/cursos/profesor/{profesorId}` | Cursos de un profesor | Sí (JWT) |
| POST | `/api/cursos` | Crear nuevo curso | Sí (JWT) |
| PUT | `/api/cursos/{id}` | Actualizar curso | Sí (JWT) |
| DELETE | `/api/cursos/{id}` | Eliminar curso | Sí (JWT) |

### 📝 Inscripciones (`/api/inscripciones`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/inscripciones` | Listar todas las inscripciones | Sí (JWT) |
| GET | `/api/inscripciones/{id}` | Obtener inscripción por ID | Sí (JWT) |
| GET | `/api/inscripciones/estudiante/{estudianteId}` | Inscripciones de un estudiante | Sí (JWT) |
| GET | `/api/inscripciones/curso/{cursoId}` | Inscripciones de un curso | Sí (JWT) |
| POST | `/api/inscripciones` | Crear nueva inscripción | Sí (JWT) |
| DELETE | `/api/inscripciones/{id}` | Eliminar inscripción | Sí (JWT) |

### 📊 Calificaciones (`/api/calificaciones`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/calificaciones` | Listar todas las calificaciones | Sí (JWT) |
| GET | `/api/calificaciones/{id}` | Obtener calificación por ID | Sí (JWT) |
| GET | `/api/calificaciones/estudiante/{estudianteId}` | Calificaciones de un estudiante | Sí (JWT) |
| GET | `/api/calificaciones/curso/{cursoId}` | Calificaciones de un curso | Sí (JWT) |
| POST | `/api/calificaciones` | Crear nueva calificación | Sí (JWT) |
| PUT | `/api/calificaciones/{id}` | Actualizar calificación | Sí (JWT) |
| DELETE | `/api/calificaciones/{id}` | Eliminar calificación | Sí (JWT) |

## 🧪 Cómo Probar tu API

### 1. Verificar que está funcionando

Abre en tu navegador:
```
https://edutrack-backend-830r.onrender.com/swagger-ui.html
```

Si ves la interfaz de Swagger, tu API está funcionando correctamente. ✅

### 2. Probar con cURL (PowerShell)

#### Registrar un usuario:
```powershell
$url = "https://edutrack-backend-830r.onrender.com/api/auth/register"
$body = @{
    firstname = "Juan"
    lastname = "Pérez"
    email = "juan@example.com"
    password = "password123"
    role = "STUDENT"
} | ConvertTo-Json

Invoke-RestMethod -Uri $url -Method Post -Body $body -ContentType "application/json"
```

#### Iniciar sesión:
```powershell
$url = "https://edutrack-backend-830r.onrender.com/api/auth/login"
$body = @{
    email = "juan@example.com"
    password = "password123"
} | ConvertTo-Json

$response = Invoke-RestMethod -Uri $url -Method Post -Body $body -ContentType "application/json"
$token = $response.token
Write-Host "Token: $token"
```

#### Obtener estudiantes (con autenticación):
```powershell
$url = "https://edutrack-backend-830r.onrender.com/api/estudiantes"
$headers = @{
    "Authorization" = "Bearer $token"
}

Invoke-RestMethod -Uri $url -Method Get -Headers $headers
```

### 3. Probar con Postman

1. Importa la colección desde Swagger (botón "Export" en Swagger UI)
2. O crea una nueva request:
   - URL: `https://edutrack-backend-830r.onrender.com/api/auth/login`
   - Method: POST
   - Body (raw JSON):
     ```json
     {
       "email": "tu-email@example.com",
       "password": "tu-password"
     }
     ```
3. Guarda el token de la respuesta
4. Para requests autenticadas, agrega en Headers:
   - Key: `Authorization`
   - Value: `Bearer TU-TOKEN-AQUI`

## ✅ Verificación Rápida

Para verificar rápidamente que tu API está funcionando:

1. **Health Check**: Intenta acceder a Swagger UI
2. **Endpoint público**: Prueba `/api/auth/register` o `/api/auth/login`
3. **Logs en Render**: Ve a la sección "Logs" en tu servicio de Render para ver si hay errores

## 🔍 Notas Importantes

- **Autenticación**: La mayoría de endpoints requieren un token JWT
- **CORS**: Está configurado para permitir todos los orígenes (`*`)
- **Base de datos**: Conectada a PostgreSQL en Render
- **Puerto**: Render asigna el puerto automáticamente (normalmente 10000)

## 🐛 Si tu API no responde

1. Verifica que el servicio esté "Live" (verde) en Render
2. Revisa los logs en Render Dashboard
3. Verifica que las variables de entorno estén configuradas:
   - `JWT_SECRET`
   - `DATABASE_URL`
4. Asegúrate de que la base de datos PostgreSQL esté activa


