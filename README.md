# Foro Hub 🚀

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-green)](https://spring.io/projects/spring-boot) [![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)

## 📖 Descripción
Foro Hub es una API REST desarrollada en Spring Boot que permite gestionar un foro con funcionalidades CRUD para tópicos. La aplicación simula un espacio donde usuarios pueden:

- 📝 Crear nuevos tópicos.
- 🔍 Consultar todos los tópicos o uno específico.
- ✏️ Actualizar un tópico existente.
- 🗑️ Eliminar un tópico.

Incluye autenticación y autorización mediante JWT para garantizar un acceso seguro.

---

## 📋 Tabla de Contenidos

1. [Características](#-características)
2. [Tecnologías Utilizadas](#-tecnologías-utilizadas)
3. [Instalación y Configuración](#-instalación-y-configuración)
4. [Uso](#-uso)
5. [Ejemplos de Solicitudes](#-ejemplos-de-solicitudes-📑)
6. [Licencia](#-licencia)

---

## ✨ Características

- API REST siguiendo las mejores prácticas.
- Validaciones de reglas de negocio.
- Persistencia de datos con base de datos relacional (MySQL).
- Seguridad mediante autenticación JWT.
- Documentación automática con SpringDoc y Swagger UI.

---

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Data JPA**
- **MySQL**
- **Spring Security**
- **JWT**
- **SpringDoc OpenAPI**

---

## ⚙️ Instalación y Configuración

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/challenge-foro-hub.git
   ```
2. Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```
3. Ejecutar las migraciones de la base de datos con Flyway:
   ```bash
   ./mvnw flyway:migrate
   ```
4. Iniciar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Acceder a la documentación Swagger en: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🚀 Uso

Para utilizar la API, asegúrate de autenticarte con un token JWT válido. Las rutas principales son:

- `POST /topicos`: Crear un nuevo tópico.
- `GET /topicos`: Obtener todos los tópicos.
- `GET /topicos/{id}`: Obtener un tópico específico.
- `PUT /topicos/{id}`: Actualizar un tópico.
- `DELETE /topicos/{id}`: Eliminar un tópico.

---

## 📑 Ejemplos de Solicitudes

### Crear un nuevo tópico
**POST** `/topicos`
```json
{
  "titulo": "Qué es la programación reactiva",
  "mensaje": "¿Cuál es la mejor manera de implementar logs en un proyecto de Spring Boot?",
  "autor": 1,
  "curso": 2
}
```
**Respuesta:**
```json
{
  "id": 5,
  "titulo": "Qué es la programación reactiva",
  "mensaje": "¿Cuál es la mejor manera de implementar logs en un proyecto de Spring Boot?",
  "fechaCreacion": "2025-01-18T10:00:00",
  "status": "NO_RESPONDIDO",
  "autor": "John Doe",
  "curso": "Spring Boot Avanzado"
}
```

### Actualizar un tópico existente
**PUT** `/topicos/5`
```json
{
  "titulo": "Programación Reactiva con Spring",
  "mensaje": "¿Cuáles son las mejores prácticas para usar WebFlux?"
}
```
**Respuesta:**
```json
{
  "id": 5,
  "titulo": "Programación Reactiva con Spring",
  "mensaje": "¿Cuáles son las mejores prácticas para usar WebFlux?",
  "fechaCreacion": "2025-01-18T10:00:00",
  "status": "NO_RESPONDIDO",
  "autor": "John Doe",
  "curso": "Spring Boot Avanzado"
}
```

### Eliminar un tópico
**DELETE** `/topicos/5`
**Respuesta:**
```http
204 No Content
```

---

## Contribuir 🤝

¡Gracias por tu interés en contribuir! 🛠️

1. Haz un fork de este repositorio.
2. Crea una nueva rama para tu funcionalidad o corrección de errores:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y confirma los commits:
   ```bash
   git commit -m "Añadida nueva funcionalidad X"
   ```
4. Haz un push de tu rama:
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. Crea un Pull Request explicando tus cambios.
