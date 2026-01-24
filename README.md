# VeterinariaApp

Aplicación móvil desarrollada con **Jetpack Compose** para gestionar una veterinaria de manera simple y organizada. La app incluye **sistema de autenticación**, **CRUD completo**, registro de mascotas y consultas, navegación avanzada y el uso de componentes fundamentales de Android como Services, Content Providers, BroadcastReceivers e Intents.

Además, incorpora **persistencia local de datos** para el manejo de una **consulta activa**, accesible desde la pantalla principal incluso sin conexión a internet, junto con **programación asincrónica mediante Coroutines** y **animaciones modernas utilizando LottieFiles** para mejorar la experiencia de usuario.

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

## 🛠 Tecnologías utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **ViewModel + State Hoisting**
- **Kotlin Coroutines**
- **LottieFiles**
- **SharedPreferences**
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

Las operaciones de registro y edición se realizan de forma asincrónica utilizando **Coroutines**, evitando bloqueos en la interfaz y mejorando la fluidez de la aplicación.

---

### 3. Registrar Consulta

Requiere al menos una mascota registrada.

**Campos:**

- Mascota seleccionada
- Veterinario
- Motivo
- Costo base
- Fecha (AAAA-MM-DD)
- Hora (HH:mm)

Incluye:

- Validación completa
- Uso de `LocalDate` y `LocalTime`
- Cálculo automático del costo final
- Persistencia de la **consulta activa**
- Ejecución asincrónica mediante **Coroutines** para mantener una interfaz responsiva

---

### 4. Ver Consultas

Pantalla con listado detallado:

- Mascota
- Dueño
- Veterinario
- Motivo
- Fecha y hora
- Costo final
- Estado

Incluye:

- Tarjetas Material 3
- **Botones de editar y eliminar** (CRUD completo)
- **Compartir consulta** mediante Intent implícito

---

### 5. HomeScreen

Incluye:

- Animaciones de entrada
- Resumen dinámico
- Menú superior y lateral
- Acciones rápidas
- Acceso a la **consulta activa** mediante diálogo modal
- Animaciones visuales utilizando **LottieFiles**

---

## 📡 Funcionalidades Android añadidas

### ✔ Activities adicionales

Navegación explícita y flujos separados según los requerimientos del sistema.

### ✔ Service en background

Servicio encargado de programar recordatorios y notificaciones automáticas.

### ✔ Content Provider

Exposición de datos de:

- Mascotas
- Consultas

Permitiendo el acceso desde aplicaciones externas.

### ✔ BroadcastReceiver dinámico

Receiver registrado programáticamente que detecta:

- Cambios en el estado del Wi-Fi
- Eventos relevantes del sistema

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
