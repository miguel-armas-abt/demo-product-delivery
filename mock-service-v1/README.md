Reemplazar los siguientes valores donde corresponda:

| variable          | valor                                 |
|-------------------|---------------------------------------|
| `<APP_IMAGE>`     | `miguelarmasabt/mock-service:v1.0.1`  |
| `<APP_CONTAINER>` | `mock-service-v1`                     |
| `<APP_PORTS>`     | `8082:8082`                           |

## ▶️ Local

1. Configurar las [variables de entorno](./variables.env) en el IDE.
2. Ejecutar aplicación

---

## ▶️ Docker

1. Crear imagen
```shell
docker build -t <APP_IMAGE> -f ./Dockerfile .
```

2. Crear red
```shell
docker network create --driver bridge common-network
```

3. Ejecutar contenedor
```shell
docker run --rm -p <APP_PORTS> --env-file ./variables.env --name <APP_CONTAINER> --network common-network <APP_IMAGE>
```

---

## ▶️ Kubernetes

1. Encender Minikube
```shell
docker context use default
minikube start
```

2. Crear imagen dentro del clúster
```shell
eval $(minikube docker-env --shell bash)
docker build -t <APP_IMAGE> -f ./Dockerfile .
```

3. Crear namespace y aplicar manifiestos
```shell
kubectl create namespace demo
kubectl apply -f ./k8s.yaml -n demo
```

4. Eliminar orquestación
```shell
kubectl delete -f ./k8s.yaml -n demo
```

5. Port-forward
```shell
kubectl port-forward <POD_ID> <APP_PORTS> -n demo
```

---

[📋️ Documentación de commons](https://github.com/miguel-armas-abt/commons-spring-boot-parent/README.md)
