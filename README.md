# VeterinariaApp

Aplicación móvil desarrollada con **Jetpack Compose** para gestionar una veterinaria de manera simple y organizada. La app incluye **sistema de autenticación**, **CRUD completo**, registro de mascotas y consultas, navegación avanzada y el uso de componentes fundamentales de Android como Services, Content Providers, BroadcastReceivers e Intents.

Además, incorpora **persistencia local de datos** para el manejo de una **consulta activa**, accesible desde la pantalla principal incluso sin conexión a internet, junto con **programación asincrónica mediante Coroutines**, **animaciones modernas utilizando LottieFiles** y **análisis de uso de memoria** para asegurar un correcto rendimiento de la aplicación.

---

## 📌 Características principales

- **Sistema de autenticación de usuarios**
  - Registro de usuarios
  - Inicio de sesión seguro
  - Validaciones de credenciales

- **CRUD completo (Crear, Leer, Actualizar y Eliminar)**
  - Gestión completa de mascotas
  - Gestión completa de consultas

- Registrar mascotas con validaciones completas
- Registrar consultas solo si existe una mascota registrada
- Validación de fechas, campos obligatorios y formatos
- Lista completa y organizada de consultas registradas
- Cálculo automático del costo final de la consulta

- Pantalla de inicio con **ResumenUI** (mascotas, consultas, último dueño)

- **Animaciones UI**
  - Animaciones con `AnimatedVisibility`, `fadeIn`, `fadeOut`
  - Animaciones vectoriales usando **LottieFiles** para estados visuales y feedback al usuario

- Menú superior con navegación entre pantallas
- Menú lateral tipo hamburguesa con acciones adicionales

- **Visualización de consulta activa**
  - Accesible desde el menú del Home
  - Se muestra en un **diálogo modal**
  - Oscurece el resto de la aplicación
  - Indica cuando no existe una consulta activa
  - Persistencia local mediante **SharedPreferences**

- **Navegación Compose** con `NavHostController`

- Arquitectura basada en **Model – ViewModel – UI**

- **Uso de Coroutines**
  - Operaciones de registro y edición ejecutadas de forma asincrónica
  - Manejo de estados de carga (`isLoading`)
  - Uso de `Dispatchers.IO` para operaciones fuera del hilo principal

- **Botones de editar y eliminar** en listas

- **Intents implícitos para compartir información**

- **Content Provider personalizado** para exponer mascotas y consultas

- **BroadcastReceiver dinámico** para detectar cambios de Wi-Fi

- **Service en background** para recordatorios

- **Activities adicionales** para navegación explícita

---

## 🧠 Análisis y gestión de memoria

La aplicación fue evaluada utilizando herramientas de análisis de memoria con el objetivo de asegurar un uso eficiente de recursos y prevenir fugas de memoria.

### ✔ Android Profiler

- Se utilizó el **Memory Profiler** de Android Studio.
- Se analizó el consumo de memoria durante:
  - Navegación entre pantallas
  - Ejecución repetida de flujos
  - Apertura y cierre de actividades
- El uso de memoria se mantuvo **estable**, sin comportamientos anómalos.

### ✔ LeakCanary

- Se integró **LeakCanary** para la detección automática de fugas de memoria en modo debug.
- Se ejecutaron flujos críticos de la aplicación.
- No se detectaron **memory leaks**.
- No fue necesario realizar correcciones de código, ya que la app cumple con buenas prácticas de gestión de memoria y ciclo de vida.

Este proceso permitió **validar** que la aplicación no presenta fugas de memoria y mantiene un rendimiento adecuado.

---

## 🛠 Tecnologías utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **ViewModel + State Hoisting**
- **Kotlin Coroutines**
- **LottieFiles**
- **SharedPreferences**
- **LeakCanary**
- **Android Profiler**
- **Java Time API** (`LocalDate`, `LocalTime`)
- **Services**
- **Content Providers**
- **Broadcast Receivers**
- **Intents implícitos y explícitos**

---

## ⚙ Funcionamiento

### 1. Autenticación

La aplicación cuenta con un sistema de autenticación que permite:

- Registro de nuevos usuarios
- Inicio de sesión con validaciones
- Protección del acceso a las funcionalidades principales

---

### 2. Registrar Mascota

**Campos solicitados:**

- Nombre
- Especie
- Edad
- Nombre del dueño
- Teléfono
- Correo
- Fecha de última vacuna (AAAA-MM-DD)

**Validaciones:**

- Campos obligatorios
- Edad numérica
- Email válido
- Teléfono numérico
- Fecha válida

Las operaciones de registro y edición se realizan de forma asincrónica utilizando **Coroutines**, evitando bloqueos en la interfaz.

---

### 3. Registrar Consulta

Requiere al menos una mascota registrada.

Incluye:

- Validación completa
- Cálculo automático del costo final
- Persistencia de la **consulta activa**
- Ejecución asincrónica con **Coroutines**

---

### 4. Ver Consultas

Listado detallado con:

- Tarjetas Material 3
- Botones de **editar y eliminar**
- Opción de **compartir consulta** mediante Intent implícito

---

### 5. HomeScreen

Incluye:

- Resumen dinámico
- Menú superior y lateral
- Acceso a la **consulta activa**
- Animaciones visuales con **LottieFiles**

---

## ▶ Ejecución

1. Clonar el proyecto
2. Abrir en **Android Studio**
3. Ejecutar en emulador o dispositivo físico

---

## 📱 Requisitos

- Android Studio Iguana o superior
- Kotlin 1.9+
- Min SDK 24
