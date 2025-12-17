# VeterinariaApp

Aplicación móvil desarrollada con **Jetpack Compose** para gestionar una veterinaria de manera simple y organizada. La app incluye **sistema de autenticación**, **CRUD completo**, registro de mascotas y consultas, estadísticas rápidas, navegación avanzada y el uso de componentes fundamentales de Android como Services, Content Providers, BroadcastReceivers e Intents.

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

- Animaciones con `AnimatedVisibility`, `fadeIn`, `fadeOut`

- Menú superior con navegación entre pantallas

- **Navegación Compose** con `NavHostController`

- Arquitectura con modelos, ViewModel y UI desacoplada

- **Botones de editar y eliminar** en listas

- **Pantalla principal más completa y validada**

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
- Protección del acceso a las funcionalidades principales de la app

---

### 2. Registrar Mascota

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

Las mascotas se almacenan en el **ViewModel** mediante `mutableStateListOf`, permitiendo su creación, edición y eliminación.

---

### 3. Registrar Consulta

Requiere al menos una mascota registrada.

Campos:

- Mascota seleccionada
- Veterinario
- Motivo
- Costo base
- Fecha (AAAA-MM-DD) — sin fechas pasadas
- Hora (HH:mm)

Incluye:

- Validación completa
- Conversión con `LocalDate` y `LocalTime`
- Cálculo automático del costo final según la edad de la mascota
- Almacenamiento y edición del objeto **Consulta**

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
- **Opciones de compartir consulta** mediante Intent implícito

---

### 5. HomeScreen

Incluye:

- Animación de entrada
- Resumen dinámico
- Menú superior con navegación
- Acciones rápidas
- Estadísticas básicas

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

Permitendo el acceso desde aplicaciones externas.

### ✔ BroadcastReceiver dinámico

Receiver registrado programáticamente que detecta:

- Cambios en el estado del Wi-Fi
- Eventos relevantes del sistema

Muestra mensajes o ejecuta lógica según corresponda.

### ✔ Intents implícitos

Incluye:

- `ShareReceiverActivity` para recibir texto de otras apps
- Intent Filter configurado para `ACTION_SEND`

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
