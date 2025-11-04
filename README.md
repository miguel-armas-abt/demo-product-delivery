# 📌 Resumen
`<autor>`: Miguel Rodrigo Armas Abt <br>

---

## 📦 [mock-service-v1](mock-service-v1/README.md)
Simula APIs RESTful para los siguientes casos de uso:

| endpoint                 | descripción                                                          |
|--------------------------|----------------------------------------------------------------------|
| `/customer-search`       | Consultar datos de contacto (dirección del cliente, teléfono, email) |
| `/delivery-requests`     | Consultar productos pendientes para delivery                         |
| `/delivery-availability` | Consultar horarios disponibles para delivery                         |
| `/delivery-coordination` | Reservar o registrar capacidad para el delivery                      |

## 📦 [product-delivery-v1](product-delivery-v1/README.md)
- Orquesta las APIs, asegurando un orden coherente en cada una de las peticiones.
- La solución aplica **state pattern**.

<img src="./diagrams.svg" width="380" height="180">

<br>

<img src="./sequencediagram.svg" width="750" height="800">