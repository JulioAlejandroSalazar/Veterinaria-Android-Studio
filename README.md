# 🐾 VeterinariaApp  
Aplicación móvil desarrollada con **Jetpack Compose** para gestionar una veterinaria de manera simple: registro de mascotas, registro de consultas y visualización de consultas realizadas.

---

## 📱 Características principales

- ✔️ Registrar mascotas con validación de datos  
- ✔️ Registrar consultas solo si existe una mascota registrada  
- ✔️ Validación de fechas, campos obligatorios y formato de datos  
- ✔️ Lista de consultas registradas  
- ✔️ Navegación usando `NavHostController`  
- ✔️ Arquitectura simple y modular

---

## 🧱 Tecnologías utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **State Hoisting**
- **Navigation Compose**

---

## ▶️ Funcionamiento

### **1. Registrar Mascota**
El usuario completa:
- Nombre  
- Especie  
- Edad  
- Nombre del dueño  
- Teléfono  
- Correo  
- Fecha de última vacuna (AAAA-MM-DD)

Incluye validaciones:
- Campos obligatorios  
- Edad numérica  
- Fecha válida  
- Email válido  
- Teléfono numérico  

La mascota queda almacenada en memoria usando `mutableStateListOf`.

---

### **2. Registrar Consulta**
Requiere que exista al menos 1 mascota registrada.

Campos:
- Nombre mascota (verificación en lista)
- Nombre veterinario
- Motivo
- Costo base
- Fecha (AAAA-MM-DD) → No se permiten fechas pasadas
- Hora (HH:MM)

---

### **3. Ver Consultas**
Muestra un listado simple del resumen de cada consulta realizada.

---

## 🚀 Ejecución

1. Clonar proyecto  
2. Abrir en **Android Studio**  
3. Ejecutar con un emulador o dispositivo físico  

---

## 📌 Requisitos

- Android Studio Iguana o superior  
- Kotlin 1.9+  
- Min SDK 24  

---