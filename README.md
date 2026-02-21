# 🎓 Administrador plataforma educativa API - Securizado & Dockerizado

Este proyecto es una solución integral para la gestión de una plataforma educativa, enfocada en la seguridad basada en roles (**RBAC**) y la portabilidad mediante **Docker**. 
Originalmente concebido en MySQL, el sistema fue migrado a **PostgreSQL** para aprovechar sus capacidades en entornos productivos.

---

## 🚀 Desafíos Técnicos Superados (Lo que aprendí)

*   **Migración de Stack:** Transición de MySQL a PostgreSQL, reconfigurando el dialecto de Hibernate y resolviendo conflictos de tipos de datos.
*   **Contenerización con Docker:** Orquestación de servicios (App + DB) asegurando la persistencia de datos mediante volúmenes.
*   **Handshake de Base de Datos:** Resolución de conflictos de TimeZone (`America/Buenos_Aires` vs `UTC`) entre el driver JDBC y el contenedor de Postgres.
*   **Seguridad Granular:** Implementación de Authenticacion con JWT, OAuth2 y filtros personalizados para la validación de tokens.

---

## 🛠️ Stack Tecnológico
*   **Backend:** Java 17, Spring Boot 3.5.10
*   **Seguridad:** Spring Security, JWT (Auth0)
*   **Persistencia:** Spring Data JPA, Hibernate
*   **Base de Datos:** PostgreSQL (en contenedor Docker)
*   **Infraestructura:** Docker, Docker Compose
*   **Documentación:** Swagger UI (OpenAPI 3.0)

---

## 📋 Reglas de Negocio e Infraestructura
*   **Roles:** 
    *   `ADMIN`: Control total (CRUD) sobre todas las entidades.
    *   `PROFESOR`: Acceso a lectura de cursos y alumnos. (Lógica de edición de cursos propios en desarrollo).
    *   `ESTUDIANTE`: Solo lectura de cursos disponibles.
*   **Modelado:** Relaciones Many-to-Many entre Alumnos y Cursos, y Many-to-One para Profesores.

---

## 🛠️ Guía de Inicio Rápido (Docker)

Para levantar el entorno completo sin configurar nada localmente:

1. **Clonar el repositorio:** `git clone https://github.com`
2. **Lanzar contenedores:**
* cd docker
* docker compose up -d
3. **Acceder a la API:** La documentación interactiva (Swagger) estará disponible en:
   http://localhost:8080/swagger-ui/index.html

*Nota: Asegúrate de tener Docker Desktop iniciado antes de ejecutar los comandos.*

🔑 Credenciales de Prueba (Acceso Admin)
Para facilitar la evaluación del proyecto, el sistema cuenta con un usuario administrador precargado automáticamente al iniciar los contenedores:
Usuario: admin
Contraseña: 1234

💡 Cómo probar en Swagger:
1.Dirigirse al endpoint POST /auth/login.
2.Ingresar las credenciales arriba mencionadas.
3.Copiar el jwt del cuerpo de la respuesta.
4.Hacer clic en el botón "Authorize" (el candado) arriba a la derecha.
5.Pegar el token que copiamos en el paso 3 
6.Presionar el botón que dice "Authorize"
7.¡Listo! Ya podés probar todos los endpoints protegidos.

📌 Roadmap:
Implementación de Social Login (OAuth2) con Google y GitHub.



