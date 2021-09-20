#Gateway-API
Api que provee un solo punto de acceso a las apis

###Pasos para ejecutarlo
1. Generación del Jar:
```
./gradlew clean bootJar
```
2. Creación de la imagen:
```
docker build --tag=gateway-api:latest .
```
3. Ejecución
```
docker run -p8080:8080 -e EUREKA_URI=http://<MI_IP>:8761/eureka -e SESIONES_API=http://<MI_IP>:8877 -e NUMEROS_API=http://<MI_IP>:8879 -e CALCULOS_API=http://<MI_IP>:8880 gateway-api:latest
```
`<MI_IP>`= Se refiere a la ip, por ejemplo:
```
docker run -p8080:8080 -e EUREKA_URI=http://192.168.0.7:8761/eureka -e SESIONES_API=http://192.168.0.7:8877 -e NUMEROS_API=http://192.168.0.7:8879 -e CALCULOS_API=http://192.168.0.7:8880 gateway-api:latest
```

###Acceso
[Path base](http://localhost:8080)

###Información adicional
Herramienta  | Versión
------------- | -------------
Spring boot  | 2.3.7.RELEASE
Netflix Zuul   | 2.2.9.RELEASE