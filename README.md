# 🐾 VeterinariaApp

Aplicación móvil desarrollada con **Jetpack Compose** para gestionar una veterinaria de manera moderna, organizada y escalable.

La app incluye **sistema de autenticación**, **CRUD completo**, registro de mascotas y consultas, consumo de API REST, navegación avanzada y el uso de componentes fundamentales de Android como **Services, Content Providers, BroadcastReceivers e Intents**.

Además, incorpora **persistencia local**, **programación asincrónica con Coroutines**, **carga dinámica de imágenes**, **animaciones modernas con LottieFiles**, **testing unitario e instrumentado** y **análisis de memoria** para asegurar un correcto rendimiento.

---

# 🚀 Características principales

## 🔐 Sistema de autenticación

- Registro de usuarios
- Inicio de sesión seguro
- Validaciones de credenciales
- Protección de rutas internas

---

## 🐶 Gestión completa (CRUD)

### 📌 Mascotas

- Crear
- Editar
- Eliminar
- Validaciones completas
- Formato de fecha correcto
- Validación de email y teléfono

### 📌 Consultas

- Registro solo si existe mascota
- Cálculo automático del costo final
- Persistencia de **consulta activa**
- Edición y eliminación
- Compartir consulta vía **Intent implícito**

---

## 👨‍⚕️ Sección: Veterinarios (Consumo de API)

- Pantalla independiente con navegación desde menú hamburguesa
- Consumo de API REST usando arquitectura ViewModel
- Manejo de estado de carga (`isLoading`)
- Indicador visual con `CircularProgressIndicator`
- Lista con `LazyColumn`
- Tarjetas Material 3
- Carga dinámica de imágenes usando:
  - **RandomUser API**
  - **Glide Compose**
- Alternancia automática hombre / mujer en imágenes
- Fondo personalizado desde `theme/Color.kt`

---

## 🏠 HomeScreen

Incluye:

- Resumen dinámico (ResumenUI)
- Menú superior (`TopAppBar`)
- Menú lateral tipo hamburguesa
- Acceso a la consulta activa
- Diálogo modal
- Animaciones con `AnimatedVisibility`
- Animaciones vectoriales con **LottieFiles**

---

## 💾 Persistencia local

- Uso de **SharedPreferences**
- Guardado de consulta activa
- Recuperación automática incluso sin conexión

---

## 🔄 Programación asincrónica

- Uso de **Kotlin Coroutines**
- Operaciones en `Dispatchers.IO`
- Manejo de estados de carga
- UI reactiva con `StateFlow`

---

## 🧪 Testing

La aplicación incluye pruebas unitarias e instrumentadas.

### ✔ Pruebas Unitarias (`src/test`)

- Test del `MascotaViewModel`
- Simulación de repositorio usando **MockK**
- Verificación de:
  - Actualización correcta del `uiState`
  - Manejo de éxito
  - Manejo de errores
- Uso de:
  - `kotlinx-coroutines-test`
  - `StandardTestDispatcher`
  - `InstantTaskExecutorRule`

Total: **3 pruebas unitarias**

---

### ✔ Pruebas Instrumentadas (`androidTest`)

- Test de pantalla `RegistrarMascotaScreen`
- Uso de **Compose UI Test**
- Verificación de:
  - Renderizado correcto
  - Interacción con campos
  - Acciones de botón

Total: **1 prueba funcional**

---

## 📡 Componentes avanzados de Android

- **Service en background** (recordatorios)
- **BroadcastReceiver dinámico** (detección de cambios de Wi-Fi)
- **Content Provider personalizado**
- **Intents implícitos y explícitos**
- Activities adicionales

---

# 🧠 Arquitectura

La aplicación sigue una estructura basada en:

- Model – ViewModel – UI
- State Hoisting
- Navigation Compose
- Separación clara de responsabilidades
- Flujo reactivo con `StateFlow`

---

# 🛠 Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- ViewModel
- StateFlow
- Kotlin Coroutines
- Glide Compose
- LottieFiles
- SharedPreferences
- MockK
- JUnit
- kotlinx-coroutines-test
- Compose UI Test
- LeakCanary
- Android Profiler
- Java Time API (`LocalDate`, `LocalTime`)
- Services
- Broadcast Receivers
- Content Providers
- Intents

---

# 🧪 Análisis y gestión de memoria

## ✔ Android Profiler

- Monitoreo de consumo de memoria
- Navegación repetida entre pantallas
- Verificación de estabilidad

## ✔ LeakCanary

- Detección automática de memory leaks
- Pruebas en flujos críticos
- No se detectaron fugas de memoria

La aplicación mantiene un uso de memoria estable y correcto manejo del ciclo de vida.

---

# ⚙ Funcionamiento

## 1️⃣ Autenticación

- Registro
- Login
- Validaciones
- Protección de rutas

---

## 2️⃣ Registrar Mascota

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

Las operaciones se ejecutan de forma asincrónica utilizando **Coroutines**, evitando bloqueos en la interfaz.

---

## 3️⃣ Registrar Consulta

- Requiere al menos una mascota registrada
- Validación completa
- Cálculo automático del costo final
- Persistencia de la consulta activa
- Ejecución asincrónica

---

## 4️⃣ Ver Consultas

- Tarjetas Material 3
- Botones de editar y eliminar
- Opción de compartir consulta mediante Intent implícito

---

## 5️⃣ Ver Veterinarios

- Consumo de API
- Lista dinámica
- Imágenes remotas
- Indicador de carga
- Fondo personalizado

---

# ▶ Ejecución

1. Clonar el proyecto
2. Abrir en **Android Studio**
3. Ejecutar en emulador o dispositivo físico

---

# 📱 Requisitos

- Android Studio Iguana o superior
- Kotlin 1.9+
- Min SDK 24
