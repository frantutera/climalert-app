# Climalert

Proyecto Spring Boot separado del TP principal para resolver el ejercicio de monitoreo climatico.

## Que hace

- Consulta `WeatherAPI` cada 5 minutos usando `/current.json`.
- Usa ubicacion fija `CABA`.
- Guarda cada medicion en una base H2 local para mantener historial.
- Analiza la ultima medicion cada 1 minuto.
- Si `temperatura > 35` y `humedad > 60`, envia un mail a:
  - `admin@clima.com`
  - `emergencias@clima.com`
  - `meteorologia@clima.com`
- Evita reenviar la misma alerta para el mismo registro.

## Variables necesarias

```powershell
$env:WEATHER_API_KEY="tu_api_key"
$env:MAIL_USERNAME="tu_mail@gmail.com"
$env:MAIL_PASSWORD="tu_app_password"
```

## Ejecutar

```powershell
mvn spring-boot:run
```

## Probar mas rapido

Se pueden bajar los intervalos en `src/main/resources/application.properties`:

```properties
climalert.scheduler.fetch-delay-ms=10000
climalert.scheduler.analysis-delay-ms=5000
```

## Base local

La base queda en `data/climalert-db.mv.db`.
