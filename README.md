
# CineApp Spring MVC 7 - Java Config

Este proyecto es una aplicación web de ejemplo para gestión de cine, desarrollada con Spring MVC 7, configuración Java, JSPs como vistas y conexión a base de datos MySQL. Incluye ejemplos CRUD, paginación, ordenamiento, consultas personalizadas y relaciones entre entidades.

## Estructura del Proyecto

- **src/main/java/net/itinajero/app/**: Lógica principal, configuración y controladores.
    - `config/`: Configuración Java de Spring MVC, JPA, seguridad y recursos.
        - `RootContextConfig.java`: Configuración de DataSource, JPA, escaneo de componentes y repositorios.
        - `DispatcherServletConfig.java`: Configuración de vistas JSP, recursos estáticos y mapeo.
        - `WebConfig.java`: Inicialización del servlet, filtros de seguridad, configuración de uploads y manejo de errores.
        - `DatabaseSecurityConfig.java`: Configuración de seguridad (Spring Security).
    - `controller/`: Controladores web.
    - `service/`: Servicios de negocio.
    - `repository/`: Repositorios JPA.
- **src/main/java/pruebascrudrepo/**, **pruebasjparepo/**, **pruebasquery/**, **pruebasrelaciones/**: Ejemplos de uso de repositorios, consultas, relaciones y operaciones CRUD.
- **src/main/webapp/WEB-INF/views/**: Vistas JSP organizadas por módulos (home, admin, detalle, login, banners, error, horarios, etc).
- **src/main/webapp/resources/**: Recursos estáticos (Bootstrap, CSS, JS, imágenes, TinyMCE).
- **database/cineapp-final.sql**: Script SQL para crear y poblar la base de datos.
- **pom.xml**: Configuración Maven y dependencias.

## Principales Tecnologías y Dependencias

- Spring Framework 7.0.5 (core, webmvc, context, beans, expression)
- Spring Data JPA
- Spring Security 7.0.3
- Jakarta Servlet API 6.1.0
- Jakarta JSP API 4.0.0
- Tomcat Embed Jasper 11.0.18
- MySQL Connector/J

## Configuración de Base de Datos

La configuración por defecto usa MySQL en localhost (puerto 3308):

```
jdbc:mysql://127.0.0.1:3308/cineapp?useSSL=false&serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true
Usuario: root
Contraseña: admin
```
Puedes modificar estos valores en `RootContextConfig.java` según tu entorno.

## Compilación y Ejecución

1. Compila el proyecto:
     ```
     mvn clean package
     ```
     El archivo WAR se generará en `target/cineapp-1.0-SNAPSHOT.war`.

2. Despliega el WAR en un servidor compatible con Servlet 6+ (por ejemplo, Tomcat 10+).

## Requisitos

- Java 17+ (recomendado Java 21+)
- Maven
- MySQL
- Tomcat 10+ (o similar)

## Autor
Iván Tinajero / Generado por GitHub Copilot
