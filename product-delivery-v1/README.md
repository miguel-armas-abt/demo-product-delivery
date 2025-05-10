
[← Regresar](../README.md) <br>

---

## 📋 Core library
[🌐 Documentación](https://github.com/miguel-armas-abt/backend-core-library) <br>
[🏷️ Versión](./src/main/java/com/demo/poc/commons/core/package-info.java) <br>
[⚙️ GraalVM - Guía de instalación](https://github.com/miguel-armas-abt/roadmap-graalvm/blob/main/path/00-setup/README.md) <br>

---

## ▶️ Despliegue local en modo dev

```shell
clean compile quarkus:dev
```

---

## ▶️ Empaquetar y ejecutar jar

⚙️ Empaquetar en un tipo de **jar** específico (`fast-jar`, `uber-jar`, `mutable-jar`)
```shell
mvn clean package #jvm
mvn clean package -Dquarkus.package.type=uber-jar
```

⚙️ Ejecutar empaquetado
```shell
java -jar target/product-delivery-v1-1.0-SNAPSHOT-runner.jar
```

---

## ▶️ Despliegue local con imagen nativa

⚙️ Generar imagen nativa
```sh
mvn clean package -Pnative
```

⚙️ Ejecutar imagen nativa
```sh
./target/product-delivery-v1-1.0-SNAPSHOT-runner.exe
```

---

## ▶️ Despliegue con Docker (JVM)

⚙️ Empaquetar aplicación
```shell
mvn clean package
```

⚙️ Crear imagen/red
```shell
docker build -t miguelarmasabt/product-delivery:v1.0.1 -f ./docker/Dockerfile.jvm .
docker network create --driver bridge common-network
```

⚙️ Ejecutar contenedor
```shell
docker run --rm -p 8080:8080 --env-file ./variables.env --name product-delivery-v1 --network common-network miguelarmasabt/product-delivery:v1.0.1
```

---

## ▶️ Despliegue con Docker (imagen nativa)

⚙️ Generar imagen nativa
```sh
mvn clean package -Pnative
```

⚙️ Crear imagen/red
```shell
docker build -t miguelarmasabt/product-delivery:v1.0.1 -f ./docker/Dockerfile.native .
docker network create --driver bridge common-network
```

⚙️ Ejecutar contenedor
```shell
docker run --rm -p 8080:8080 --env-file ./variables.env --name product-delivery-v1 --network common-network miguelarmasabt/product-delivery:v1.0.1
```