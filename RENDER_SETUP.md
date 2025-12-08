# Guía de Configuración para Render

## 📋 Configuración en Render Dashboard

### 1. Root Directory
**Deja vacío** (o pon `.` o `/`)
- Tu `Dockerfile` está en la raíz del repositorio, así que no necesitas especificar un directorio.

### 2. Environment Variables (Variables de Entorno)

Ve a la sección **"Environment"** de tu servicio en Render y agrega estas variables:

#### 🔐 JWT_SECRET (OBLIGATORIO)
**Cómo generarlo:**
- **Windows (PowerShell):** Ejecuta `generate-jwt-secret.ps1`
- **Linux/Mac:** Ejecuta `bash generate-jwt-secret.sh`
- **O manualmente:** Usa cualquier generador de Base64 (32 bytes mínimo)

**Ejemplo de valor generado:**
```
K8j3mN9pQ2vR5tY7wX0zA1bC4dE6fG8hI=
```

**En Render:**
- **Key:** `JWT_SECRET`
- **Value:** (pega el valor generado)

---

#### 🗄️ Variables de Base de Datos PostgreSQL

Si ya creaste una base de datos PostgreSQL en Render, Render te proporciona estas variables automáticamente. Solo necesitas copiarlas:

**Opción 1: Usar las variables que Render proporciona automáticamente**
Render crea estas variables cuando conectas una base de datos:
- `DATABASE_URL` (contiene toda la información)

**Opción 2: Configurar manualmente (si prefieres separar los valores)**

Si tu base de datos PostgreSQL en Render tiene:
- **Host:** `dpg-xxxxx-a.oregon-postgres.render.com`
- **Puerto:** `5432`
- **Database:** `edutrackdb`
- **Username:** `edutrack_user`
- **Password:** `tu_password_aqui`

Entonces configura:

**DB_URL:**
```
jdbc:postgresql://dpg-xxxxx-a.oregon-postgres.render.com:5432/edutrackdb
```

**DB_USERNAME:**
```
edutrack_user
```

**DB_PASSWORD:**
```
tu_password_aqui
```

---

### 📝 Resumen de Variables de Entorno en Render

| Variable | Ejemplo | Descripción |
|----------|---------|-------------|
| `JWT_SECRET` | `K8j3mN9pQ2vR5tY7wX0zA1bC4dE6fG8hI=` | Clave secreta en Base64 (32+ bytes) |
| `DB_URL` | `jdbc:postgresql://host:5432/dbname` | URL completa de PostgreSQL |
| `DB_USERNAME` | `edutrack_user` | Usuario de la base de datos |
| `DB_PASSWORD` | `tu_password` | Contraseña de la base de datos |
| `PORT` | (automático) | Render lo asigna automáticamente |

---

## 🚀 Pasos en Render

1. **Crea un nuevo Web Service** en Render
2. **Conecta tu repositorio de GitHub**
3. **Configuración:**
   - **Name:** `edutrack-backend` (o el que prefieras)
   - **Environment:** `Docker`
   - **Root Directory:** (deja vacío)
   - **Dockerfile Path:** `Dockerfile` (o déjalo vacío si está en la raíz)
4. **Crea una base de datos PostgreSQL** en Render (si no la tienes)
5. **Agrega las variables de entorno** en la sección "Environment"
6. **Deploy!** 🎉

---

## ✅ Verificación

Después del deploy, verifica que:
- El servicio esté "Live" (verde)
- Los logs no muestren errores de conexión a la base de datos
- Los logs no muestren errores de JWT_SECRET

---

## 🔍 Notas Importantes

- **PORT:** Render asigna automáticamente el puerto, no necesitas configurarlo manualmente
- **JWT_SECRET:** Debe ser exactamente el mismo en todos los ambientes (desarrollo, producción)
- **Base de datos:** Asegúrate de que la base de datos PostgreSQL esté en la misma región que tu servicio web

