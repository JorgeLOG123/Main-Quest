# 🗡️ Main Quest

Aplicación web en **Java + Spring Boot** que convierte la búsqueda de empleo en un juego: cada jugador cumple **misiones**, gana **experiencia (XP)** y sube de **nivel**. Incluye un panel de administración y una **API REST** para consumir los datos desde otras aplicaciones.

> Proyecto de aprendizaje desarrollado durante un programa intensivo de Java backend. Cada etapa del curso (POO, persistencia, Spring Web, testing, APIs REST) se fue aplicando sobre este mismo proyecto.

### 📌 Sobre este repositorio

Main Quest **no es un proyecto nuevo**: lo vengo desarrollando desde hace tiempo, semana a semana, con una rama por cada etapa del curso.

Este repositorio es una **versión limpia** de ese trabajo. Decidí migrarlo para aplicar buenas prácticas de control de versiones:

- **Las credenciales quedan fuera del repositorio.** La configuración local (`application.properties`) está en el `.gitignore`, y se incluye un `application.properties.example` como guía.
- **El historial empieza ordenado**, sin archivos de configuración del IDE ni de herramientas externas.
- **Una sola rama principal** (`master`), con ramas de trabajo que se integran al terminar cada etapa.

El historial de desarrollo anterior se conserva en un repositorio privado.

---

## ✨ Funcionalidades

### Para el jugador
- Registro e inicio de sesión, con validaciones (nombre y email únicos, contraseña mínima).
- Sesión HTTP para recordar al jugador entre páginas.
- Perfil con imagen.
- Misiones **generales** y **semanales**, con progreso (*en curso* / *completada*).
- Sistema de experiencia: cada **100 XP** se sube un nivel.

### Para el administrador
- Dashboard con la cantidad de jugadores registrados.
- Alta, edición y eliminación de jugadores (con modal de confirmación).
- Administración de misiones por tipo.

### API REST
- Listado público de jugadores, expuesto mediante **DTOs** (sin datos sensibles).
- Registro de jugadores enviando JSON.

---

## 🛠️ Tecnologías

| Área | Tecnología |
|---|---|
| Lenguaje | Java |
| Framework | Spring Boot 3 (Spring Web, Spring Data JPA) |
| Vistas | Thymeleaf + Layout Dialect, Bootstrap 5 |
| Base de datos | MySQL 8 |
| Persistencia | JPA / Hibernate |
| API | Jackson, ModelMapper (entidades → DTOs) |
| Testing | JUnit 5, Spring Boot Test, `java.net.http.HttpClient` |
| Build | Maven |

---

## 🧱 Arquitectura

La aplicación está organizada en capas, para que la lógica de negocio exista **una sola vez** y pueda usarse tanto desde la web como desde la API:

```
Navegador ──► Controllers web (Thymeleaf) ──┐
                                            ├──► Services ──► Repositories ──► MySQL
App / Postman ──► Controllers REST (JSON) ──┘
```

| Paquete | Responsabilidad |
|---|---|
| `controllers` | Reciben los requests (web y REST) |
| `services` | Reglas de negocio. Jerarquía genérica: `PersistableService` → `ExponibleService` |
| `repositories` | Acceso a datos con Spring Data JPA |
| `domain` | Entidades (`Jugador`, `Mision`, `ProgresoDeMision`, `Imagen`) |
| `forms` | Datos que **entran** (formularios y `@RequestBody`) |
| `dtos` | Datos que **salen** (lo que ve el cliente) |
| `Validators` | Validaciones con la interfaz `Validator` de Spring |

**Decisiones de diseño destacadas:**
- **Form = entrada, DTO = salida.** Las entidades nunca salen de la aplicación: la API devuelve DTOs, así no se exponen contraseñas ni datos internos.
- **Polimorfismo en lugar de `instanceof`.** Cada entidad indica qué DTO le corresponde (`dtoForAPI()` / `dtoForList()`) y un servicio genérico hace la conversión con ModelMapper.
- **Validación reutilizada.** El registro web y el registro por API usan el mismo `RegistracionFormValidator`.

---

## 📡 API REST

| Método | Endpoint | Descripción | Respuestas |
|---|---|---|---|
| `GET` | `/api/jugadores` | Lista los jugadores (perfil público) | `200` |
| `POST` | `/api/jugadores/registro` | Registra un jugador | `201` creado · `400` datos inválidos · `415` formato incorrecto |

### Ejemplo: listar jugadores

```http
GET /api/jugadores
Accept: application/json
```

```json
[
  { "nombre": "MaraD10S", "experiencia": 0, "nivel": 1 }
]
```

### Ejemplo: registrar un jugador

```http
POST /api/jugadores/registro
Content-Type: application/json

{
  "nombre": "nuevoJugador",
  "email": "jugador@ejemplo.com",
  "contrasena": "clave123"
}
```

- **`201 Created`** si se registró.
- **`400 Bad Request`** con el detalle de lo que falló, por ejemplo: `nombre: nombre.taken, email: email.taken`.

---

## 🚀 Cómo ejecutarlo

### Requisitos
- Java (JDK 21 o superior)
- Maven
- MySQL 8

### 1. Clonar el repositorio

```bash
git clone https://github.com/JorgeLOG123/Main-Quest.git
cd Main-Quest
```

### 2. Crear la base de datos

Crear las bases y un usuario dedicado (no usar `root` en la aplicación):

```sql
CREATE DATABASE main_quest;
CREATE DATABASE main_quest_test;

CREATE USER 'tu_usuario'@'localhost' IDENTIFIED BY 'tu_contraseña';
GRANT SELECT, INSERT, UPDATE, DELETE ON main_quest.* TO 'tu_usuario'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON main_quest_test.* TO 'tu_usuario'@'localhost';
```

Después ejecutar en **las dos bases** los scripts de `src/main/resources/database/`:
- `create_db.sql`: crea las tablas.
- `init_db.sql`: carga datos iniciales (opcional).

> La aplicación usa `spring.jpa.hibernate.ddl-auto=none`: Hibernate no crea tablas, por eso hay que correr los scripts.

### 3. Configurar las credenciales

Las credenciales **no están en el repositorio**. Copiar el archivo de ejemplo y completarlo:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Para los tests, crear `src/test/resources/application-test.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/main_quest_test
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=none
```

Los dos archivos están en el `.gitignore`.

### 4. Ejecutar

Desde el IDE, correr la clase `arg.jorge.mainquest.Principal`, o con Maven:

```bash
mvn spring-boot:run
```

La aplicación queda en **http://localhost:8080**.

---

## 🧪 Tests

```bash
mvn test
```

| Tipo | Qué prueba |
|---|---|
| **Unitarios** | Reglas de las entidades (`Jugador`, `Mision`, `ProgresoDeMision`): validaciones, XP y niveles |
| **Integración** | Servicios contra la base de test, con el perfil `test` |
| **API** | Levanta el servidor en un puerto aleatorio, llama a `/api/jugadores` con `HttpClient`, convierte el JSON a DTOs con `ObjectMapper` y valida la respuesta |

Los tests usan la base `main_quest_test`, nunca la principal.

---

## 🗺️ Próximos pasos

- [ ] Guardar las contraseñas **hasheadas** (BCrypt).
- [ ] Respuestas de error de la API en formato JSON.
- [ ] Más endpoints REST (misiones, progreso).
- [ ] Tests del endpoint `POST` de registro.
- [ ] Seguridad con Spring Security.

---

## 👤 Autor

**Jorge** · [@JorgeLOG123](https://github.com/JorgeLOG123)
