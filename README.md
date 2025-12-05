# VeterinariaApp  
Aplicación móvil desarrollada con **Jetpack Compose** para gestionar una veterinaria de manera simple: registro de mascotas, registro de consultas, estadísticas rápidas y visualización de información registrada.

---

## Características principales

- Registrar mascotas con validación completa  
- Registrar consultas solo si existe una mascota registrada  
- Validación de fechas, campos obligatorios y formatos  
- Lista completa de consultas registradas  
- Cálculo automático del costo final de la consulta  
- Pantalla de inicio con **ResumenUI** (mascotas, consultas, último dueño)  
- Animaciones con `AnimatedVisibility`, `fadeIn`, `fadeOut`  
- Menú superior con navegación entre pantallas  
- Navegación con `NavHostController`  
- Arquitectura modular con modelos, ViewModel y UI organizada  

---

## Tecnologías utilizadas

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
- Nombre del dueño  
- Teléfono  
- Correo  
- Fecha de última vacuna (AAAA-MM-DD)

Validaciones:
- Campos obligatorios  
- Edad numérica  
- Email válido  
- Teléfono numérico  
- Fecha válida  

Las mascotas se guardan en memoria usando `mutableStateListOf` dentro del ViewModel.

---

### 2. Registrar Consulta

Requiere que exista al menos una mascota registrada.

Campos:
- Mascota seleccionada  
- Nombre del veterinario  
- Motivo  
- Costo base  
- Fecha (AAAA-MM-DD) — no se permiten fechas pasadas  
- Hora (HH:mm)

Incluye:
- Validación de fecha y hora  
- Conversión a `LocalDate` y `LocalTime`  
- Cálculo del costo final (incluye modificación según edad de la mascota, si corresponde)  
- Creación y almacenamiento del objeto `Consulta`  

---

### 3. Ver Consultas

Pantalla con listado de todas las consultas registradas, mostrando:
- Mascota  
- Dueño  
- Veterinario  
- Motivo  
- Fecha y hora  
- Costo final  
- Estado  

Las consultas se muestran en tarjetas con estilo Material 3.

---

### 4. HomeScreen

Incluye:
- Animación de entrada con `AnimatedVisibility`  
- Resumen dinámico mediante `ResumenUI`  
- Menú superior con opciones:
  - Registrar Mascota  
  - Registrar Consulta  
  - Ver Consultas  
- Botones rápidos para navegación

---

## Ejecución

1. Clonar el proyecto  
2. Abrir en **Android Studio**  
3. Ejecutar en emulador o dispositivo físico

---

## Requisitos

- Android Studio Iguana o superior  
- Kotlin 1.9+  
- Min SDK 24  
