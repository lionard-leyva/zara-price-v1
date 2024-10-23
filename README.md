# Zara Price v1

## Descripción

La API de zara-prices proporciona información sobre los precios de los productos. Los usuarios pueden buscar precios basándose en el ID del producto, el ID de la marca y la fecha y hora.

## Endpoints

- `GET /api/v1/prices/{productId}/{brandId}/{dateTime}`: Devuelve el precio de un producto para una marca y fecha específicos.

## Cómo ejecutar la aplicación

1. Clona el repositorio: `git clone https://github.com/lionard-leyva/zara-price-v1`
2. Navega al directorio del proyecto: `cd zara-price-v1`
3. Ejecuta la aplicación: `./gradlew bootRun --args='--spring.profiles.active=dev'`

## Pruebas

Para ejecutar las pruebas, usa el siguiente comando: `./gradlew test`

## Documentación

Para ver la documentación de la API, consulta la documentación en Swagger UI.
http://localhost:8080/webjars/swagger-ui/index.html
## Tecnologías utilizadas

- JDK 21
- Spring Boot
- H2
- Gradle

## Contribuir

Las contribuciones son bienvenidas. Por favor, abre un issue para discutir lo que te gustaría cambiar.

## Licencia
Lionard Leyva by
[MIT](https://choosealicense.com/licenses/mit/)
