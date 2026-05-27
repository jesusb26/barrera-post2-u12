# Sistema de Gestión de Pedidos – Integración de Patrones (Unidad 12)

## 📌 Arquitectura y patrones aplicados

El sistema implementa cuatro patrones de diseño para desacoplar la lógica de negocio, la selección de algoritmos y las notificaciones:

- **Strategy** – Define una familia de algoritmos para procesar pedidos según su tipo (ESTANDAR, EXPRESS, INTERNACIONAL).
- **Factory** – Encapsula la selección dinámica de la estrategia adecuada mediante un mapa inyectado por Spring.
- **Observer** – Desacopla la notificación (email y log) mediante eventos de Spring (`ApplicationEventPublisher` y `@EventListener`).
- **Facade** – Provee una interfaz simplificada para el controlador REST, ocultando la complejidad interna (Factory, Repository, Event Publisher).

## 📊 Comparativa de métricas (SonarQube)

| Métrica | Antes (legacy) | Después (refactorizado) | Mejora |
|---------|----------------|-------------------------|--------|
| **Complejidad Ciclomática** del método principal (`procesarPedido` / `crearPedido`) | 5 | 1 | ↓ 80% |
| **Complejidad Cognitiva** | 3 | 0 | ↓ 100% |
| **Cobertura de pruebas** | 17.8% | 88.3% | ↑ 396% |
| **Security Issues** | 0 | 0 (Rating A) | ✅ |
| **Maintainability Issues** | 2 | 0 (Rating A) | ✅ |
| **Reliability Issues** | 0 | 0 (Rating A) | ✅ |

> **Nota**: Los valores "Antes" corresponden al análisis del código monolítico (`ServicioPedidosLegacy`). Los valores "Después" corresponden al código refactorizado con los cuatro patrones. El Quality Gate final es **Passed** (todas las métricas en A).

## 📸 Evidencia de los análisis

### Análisis inicial (legacy)
- **Cyclomatic Complexity = 5** (clase `ServicioPedidosLegacy`)  
![alt text](<docs/Captura de pantalla 2026-05-27 152539.png>)
- **Cognitive Complexity = 3**  
![alt text](<docs/Captura de pantalla 2026-05-27 152708.png>)
- **Dashboard inicial** (cobertura 17.8%, varios issues)  
![alt text](<docs/Captura de pantalla 2026-05-27 151841.png>)

### Análisis final (refactorizado)
- **Método `crearPedido` de `FachadaPedidos`** – Sin condicionales (CC = 1)  
![alt text](<docs/Captura de pantalla 2026-05-27 162446.png>)
- **Cognitive Complexity = 0** en la clase `FachadaPedidos`  
![alt text](<docs/Captura de pantalla 2026-05-27 161811.png>)
- **Dashboard final** (cobertura 88.3%, todo en A)  
![alt text](<docs/Captura de pantalla 2026-05-27 164815.png>)

## 🧪 Ejecución y verificación

```bash
# Levantar SonarQube local (requiere Docker)
docker start sonarqube

# Ejecutar pruebas y análisis (usar token generado en SonarQube)
mvn clean verify sonar:sonar -Dsonar.token=TU_TOKEN_LOCAL

# Ver el dashboard
http://localhost:9000/dashboard?id=sistema-pedidos-refactored

```
## 🧠 Conclusión y reflexión sobre Open/Closed

La refactorización aplicando Strategy, Factory, Observer y Facade redujo la complejidad ciclomática del método principal de 5 a 1, eliminó la complejidad cognitiva y elevó la cobertura al 88.3%. Los issues de seguridad, fiabilidad y mantenibilidad se redujeron a rating A, cumpliendo con el Quality Gate.

Principio Open/Closed: El patrón Strategy permite añadir nuevos tipos de pedido (por ejemplo, "EXPRESS_NOCTURNO") sin modificar la clase FachadaPedidos ni el factory. Solo se crea una nueva implementación de ProcesadorPedido y se anota con @Component. El factory la detecta automáticamente gracias a la inyección de lista de Spring. Esto demuestra que el sistema está abierto para extensión, pero cerrado para modificación.