# Spring MVC 7 Demo

Este proyecto es una aplicación web básica usando Spring MVC 7, configuración Java y JSPs como vistas.

## Estructura
- `src/main/java/com/example/config/WebConfig.java`: Configuración Java de Spring MVC.
- `src/main/java/com/example/controller/HomeController.java`: Controlador principal.
- `src/main/webapp/WEB-INF/web.xml`: Configuración del servlet.
- `src/main/webapp/WEB-INF/jsp/home.jsp`: Vista JSP.
- `pom.xml`: Dependencias y configuración Maven.

## Cómo compilar

Ejecuta:

    mvn clean package

El archivo WAR se generará en `target/`.

## Cómo ejecutar

Despliega el WAR en un servidor compatible con Servlet 4.0+ (por ejemplo, Tomcat 9+).

## Requisitos
- Java 17+
- Maven
- Tomcat 9+ (o similar)

## Autor
Generado por GitHub Copilot
