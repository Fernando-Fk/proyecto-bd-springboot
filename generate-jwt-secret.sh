#!/bin/bash
# Script para generar JWT_SECRET en Base64 (mínimo 32 bytes)
# Ejecuta este script en Linux/Mac para obtener tu clave secreta

# Genera 32 bytes aleatorios y los convierte a Base64
JWT_SECRET=$(openssl rand -base64 32)

echo "========================================="
echo "Tu JWT_SECRET (copia este valor):"
echo "$JWT_SECRET"
echo "========================================="
echo ""
echo "Este valor tiene exactamente 32 bytes (256 bits)"
echo "Úsalo en Render como variable de entorno JWT_SECRET"

