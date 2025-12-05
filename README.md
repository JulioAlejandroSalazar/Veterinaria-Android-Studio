# VeterinariaApp  
Aplicaci車n m車vil desarrollada con **Jetpack Compose** para gestionar una veterinaria de manera simple: registro de mascotas, registro de consultas, estad赤sticas r芍pidas y visualizaci車n de informaci車n registrada.

---

## Caracter赤sticas principales

- Registrar mascotas con validaci車n completa  
- Registrar consultas solo si existe una mascota registrada  
- Validaci車n de fechas, campos obligatorios y formatos  
- Lista completa de consultas registradas  
- C芍lculo autom芍tico del costo final de la consulta  
- Pantalla de inicio con **ResumenUI** (mascotas, consultas, 迆ltimo due?o)  
- Animaciones con `AnimatedVisibility`, `fadeIn`, `fadeOut`  
- Men迆 superior con navegaci車n entre pantallas  
- Navegaci車n con `NavHostController`  
- Arquitectura modular con modelos, ViewModel y UI organizada  

---

## Tecnolog赤as utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **State Hoisting**
- **ViewModel**
- **Java Time API (LocalDate, LocalTime)**

---

## Funcionamiento

### 1. Registrar Mascota

Campos solicitados:
- Nombre  
- Especie  
- Edad  
- Nombre del due?o  
- Tel谷fono  
- Correo  
- Fecha de 迆ltima vacuna (AAAA-MM-DD)

Validaciones:
- Campos obligatorios  
- Edad num谷rica  
- Email v芍lido  
- Tel谷fono num谷rico  
- Fecha v芍lida  

Las mascotas se guardan en memoria usando `mutableStateListOf` dentro del ViewModel.

---

### 2. Registrar Consulta

Requiere que exista al menos una mascota registrada.

Campos:
- Mascota seleccionada  
- Nombre del veterinario  
- Motivo  
- Costo base  
- Fecha (AAAA-MM-DD) 〞 no se permiten fechas pasadas  
- Hora (HH:mm)

Incluye:
- Validaci車n de fecha y hora  
- Conversi車n a `LocalDate` y `LocalTime`  
- C芍lculo del costo final (incluye modificaci車n seg迆n edad de la mascota, si corresponde)  
- Creaci車n y almacenamiento del objeto `Consulta`  

---

### 3. Ver Consultas

Pantalla con listado de todas las consultas registradas, mostrando:
- Mascota  
- Due?o  
- Veterinario  
- Motivo  
- Fecha y hora  
- Costo final  
- Estado  

Las consultas se muestran en tarjetas con estilo Material 3.

---

### 4. HomeScreen

Incluye:
- Animaci車n de entrada con `AnimatedVisibility`  
- Resumen din芍mico mediante `ResumenUI`  
- Men迆 superior con opciones:
  - Registrar Mascota  
  - Registrar Consulta  
  - Ver Consultas  
- Botones r芍pidos para navegaci車n

---

## Ejecuci車n

1. Clonar el proyecto  
2. Abrir en **Android Studio**  
3. Ejecutar en emulador o dispositivo f赤sico

---

## Requisitos

- Android Studio Iguana o superior  
- Kotlin 1.9+  
- Min SDK 24  

---
