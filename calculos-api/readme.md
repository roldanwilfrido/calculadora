#Calculos-API
Encargado de la administración de los numeros por sesion del ambiente

###Pasos para ejecutarlo
1. Ejecución de pruebas unitarias y generación del Jar:
```
./gradlew clean test bootJar
```
2. Creación de la imagen:
```
docker build --tag=calculos-api:latest .
```
3. Ejecución
```
docker run -p8880:8880 -e EUREKA_URI=http://<MI_IP>:8761/eureka -e NUMEROS_API=http://<MI_IP>:8879 calculos-api:latest
```
`<MI_IP>`= Se refiere a la ip, por ejemplo:
```
docker run -p8880:8880 -e EUREKA_URI=http://192.168.0.7:8761/eureka -e NUMEROS_API=http://192.168.0.7:8879 calculos-api:latest
```

###Detalle de Numeros-API
Path base: `http://localhost:8880/calculos`

Endpoint  | Detalle 
------------- | ------------- 
[GET] /{{sesionId}}/{{operacion}}  | Realiza la operacion aritmetica con todos los numeros que pertenecen a una sesion 

Nota: `{{operacion}}` puede ser sustituido por:
suma, resta, multiplicacion, division, potenciacion... es decir:
- `http://localhost:8880/calculos/{{sesionId}}/suma`
- `http://localhost:8880/calculos/{{sesionId}}/resta`
- `http://localhost:8880/calculos/{{sesionId}}/multiplicacion`
- `http://localhost:8880/calculos/{{sesionId}}/division`
- `http://localhost:8880/calculos/{{sesionId}}/potenciacion`


Para más información de clic [aquí](http://localhost:8880/docs)

###Información adicional
Herramienta  | Versión
------------- | -------------
Spring boot  | 2.5.2
Unirest  | 1.4.9
Lombok  | 1.18.20
OpenApi   | 1.5.9
