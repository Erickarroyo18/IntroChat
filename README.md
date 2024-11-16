# IntroChat

**IntroChat** es una aplicación de chat que permite la interconexión entre usuarios en una red local. Está diseñada para proporcionar comunicación en tiempo real con un sistema robusto y eficiente, basado en el uso de hilos, sockets, serialización y una interfaz gráfica de usuario (GUI).

---

## Descripción 
IntroChat es un sistema de chat desarrollado en Java diseñado para permitir la comunicación en tiempo real en una red local. La arquitectura del proyecto se fundamenta en varios patrones de diseño, como MVC (Modelo-Vista-Controlador), Observer, Singleton, Proxy y Fachada, con el objetivo de mantener un código modular, escalable y eficiente.

El controlador del sistema se compone de varias clases que gestionan la lógica del negocio y la interacción con la red. La clase GestorServidor se encarga de las operaciones administrativas del servidor, como conectar usuarios y validar información, mientras que IObservable define la interfaz necesaria para gestionar los eventos y notificaciones en el sistema de comunicación. La clase Servidor es una pieza clave que combina varios patrones: actúa como un sujeto observable, una fachada que simplifica las interacciones complejas y un controlador que gestiona los usuarios conectados. Servidor es también un Singleton, asegurando que solo haya una instancia de este objeto en funcionamiento. Por otro lado, la clase Usuario se encarga de representar a los participantes del chat y se comporta como un observador, suscribiéndose a eventos del servidor y manejando la lógica del usuario a través de métodos específicos.

El sistema incluye puntos de entrada mediante las clases ClienteServidor y ClienteUsuario, que contienen los métodos main y son responsables de ejecutar la aplicación sin la implementación de patrones adicionales. Además, la clase Constantes proporciona un conjunto de valores fijos usados en el sistema, como configuraciones y parámetros, facilitando la gestión de configuraciones sin recurrir a patrones formales.

El modelo de datos está compuesto por diversas clases que encapsulan la información transmitida y procesada en la aplicación. ListaUsuarios gestiona a los usuarios conectados mediante una estructura de datos que almacena paquetes de información, mientras que las clases Paquete, PaqueteAlta, PaqueteBaja, PaqueteDatos, y PaqueteMensaje siguen el patrón Proxy para manejar y representar diferentes tipos de datos enviados y recibidos en la red. Estas clases proxy encapsulan detalles de la información que fluye en el sistema y facilitan el acceso controlado a estos datos. ProxyPaquete es una clase que actúa como intermediaria para gestionar y transmitir eficientemente la información. Adicionalmente, PaqueteFachada proporciona una interfaz simplificada para enviar y recibir paquetes, ocultando la complejidad de la comunicación con los sockets y haciendo que el sistema sea más fácil de manejar.

El proyecto cuenta con una interfaz gráfica de usuario (GUI) desarrollada con Java Swing. Las clases Inicio, InterfazServidor, y InterfazUsuario representan las vistas en el patrón MVC. Inicio permite a los usuarios autenticarse, mientras que InterfazServidor proporciona una interfaz para el administrador del servidor, mostrando mensajes y acciones relevantes. InterfazUsuario es la ventana principal para los usuarios, mostrando mensajes de chat, notificaciones y opciones de interacción. Estas interfaces están diseñadas para ser intuitivas y amigables, facilitando la interacción del usuario con el sistema.

En general, IntroChat está diseñado para ser un sistema robusto y eficiente, capaz de manejar múltiples conexiones simultáneas mediante el uso de hilos. Los sockets se emplean para mantener una comunicación fluida y en tiempo real entre el cliente y el servidor. La serialización se usa para enviar objetos Java a través de la red de manera estructurada y segura, lo que permite transmitir mensajes, actualizaciones de usuarios y otros datos relevantes. Este enfoque asegura que IntroChat ofrezca una experiencia de usuario rápida y confiable en un entorno de red local.

### Estructura del Proyecto

La aplicación está organizada con una arquitectura basada en el patrón MVC (Modelo-Vista-Controlador) y se divide en las siguientes carpetas:

- **build.xml**: Archivo de configuración para la compilación y ejecución de la aplicación.
- **dist**: Contiene el archivo ejecutable `IntroChat.jar` y las librerías necesarias (`AbsoluteLayout.jar` y `json-simple-1.1.1.jar`).
- **manifest.mf**: Archivo de manifiesto para la creación del archivo JAR.
- **nbproject**: Archivos de configuración del entorno NetBeans, incluyendo `build-impl.xml`, `project.properties` y configuraciones privadas.
- **src**: Contiene el código fuente del proyecto, organizado en las siguientes carpetas:
  - **Controller**: Lógica del servidor y gestión de usuarios (`GestorServidor.java`, `Servidor.java`, `Usuario.java`, etc.).
  - **Model**: Representación de los datos, como `ListaUsuarios`, `Paquete`, `PaqueteMensaje`, entre otros.
  - **View**: Interfaz gráfica de usuario, con clases como `Inicio.java`, `InterfazServidor.java` y `InterfazUsuario.java`.
  - **main**: Puntos de entrada para ejecutar la aplicación (`ClienteServidor.java`, `ClienteUsuario.java`).
  - **meta**: Archivos con constantes y configuración general.
- **test**: Carpeta destinada para las pruebas de la aplicación.

---

## Descripción de Clases

- **Controller**: Contiene la lógica del servidor y la gestión de usuarios. `GestorServidor` y `Servidor` se encargan de manejar las conexiones y la interacción entre los usuarios.
- **Model**: Gestiona la representación de los datos. `ListaUsuarios` mantiene un seguimiento de los usuarios activos y los paquetes de mensajes se gestionan mediante clases como `Paquete`, `PaqueteAlta` y `PaqueteMensaje`.
- **View**: Proporciona la interfaz gráfica para la interacción con la aplicación.
- **main**: Contiene los puntos de entrada para ejecutar el servidor y el cliente.

---

## Ejecución del Proyecto

Para compilar y ejecutar la aplicación, se utiliza el archivo `build.xml` con los siguientes comandos:

1. **Compilar el proyecto**: Compila el código fuente.
   ```bash
   ant compile
   ```

2. **Ejecutar el servidor**: Inicia el servidor de chat.
    ```bash
    ant run-server
    ```

3. **Ejecutar un cliente**: Inicia la interfaz del usuario.
    ```bash
    ant run-client
    ```

4. **Ejecutar ambos (cliente y servidor)**: Para pruebas locales.
    ```
    ant run-client-server
    ```

5. **Ejecutar solo el cliente de usuario**:
    ```bash
    ant run-client-user
    ```

Archivos de Configuración

	•	manifest.mf: Configuración del manifiesto del archivo JAR.
	•	nbproject: Carpeta de configuración del entorno NetBeans, utilizada para administrar el proyecto.

## Justificación de Implementación

### Sockets

Los sockets permiten establecer una comunicación en tiempo real entre el servidor y los clientes. Este enfoque es ideal para aplicaciones de chat en red porque los sockets mantienen una conexión abierta y persistente que facilita el intercambio rápido y eficiente de mensajes. Al usar sockets, el sistema puede manejar la transmisión bidireccional de datos, lo que permite a los clientes enviar y recibir mensajes sin tener que establecer repetidamente nuevas conexiones. Esto no solo mejora la latencia, sino que también optimiza el rendimiento de la aplicación al minimizar el tiempo de espera en el envío y recepción de datos.

### Serialización

La serialización se implementó para convertir objetos Java en un formato adecuado para ser enviado a través de la red y posteriormente reconstruirlos en su forma original. Esta técnica es crucial para enviar datos complejos como mensajes de texto, archivos, o actualizaciones de la lista de usuarios de manera estructurada y segura. Sin la serialización, el envío de objetos de Java no sería posible, ya que las conexiones de red suelen transmitir datos en formato binario. Serializar los objetos garantiza que la información se mantenga consistente y pueda ser fácilmente interpretada en el lado del receptor.

### Hilos

Para gestionar múltiples clientes que se conectan simultáneamente al servidor, el proyecto utiliza hilos (threads) en Java. Cada cliente tiene asignado un hilo independiente en el servidor, lo que permite procesar mensajes y solicitudes sin bloquear las operaciones de otros clientes. Esto es esencial para aplicaciones de chat en tiempo real, ya que asegura que la comunicación no se interrumpa y que el servidor pueda responder a nuevos mensajes y eventos sin demoras. El uso de hilos mejora el rendimiento y la capacidad de respuesta del sistema, brindando una experiencia de usuario más fluida.

### Interfaces Gráficas (GUIs)

La interfaz gráfica de usuario fue diseñada con Java Swing para ofrecer un entorno intuitivo y visualmente amigable. Las GUIs proporcionan a los usuarios una experiencia atractiva, donde pueden ver y enviar mensajes, recibir notificaciones y gestionar su conexión de manera sencilla. Además, el uso de Swing permitió personalizar la interfaz para que sea clara y funcional, tanto para los usuarios que chatean como para los administradores del servidor que monitorean las conexiones y actividades.


## Justificación de Patrones
### Observer
- **IObservable**: Implementa el patrón Observer al definir una interfaz para la suscripción y notificación de cambios en los datos. Esto permite gestionar la comunicación y sincronización entre múltiples objetos, como usuarios en el chat, de manera estructurada.
- **Servidor**: Actúa como un sujeto (Observable) en el patrón Observer. Notifica a los usuarios cuando hay nuevos mensajes o cambios en la lista de usuarios conectados.
- **Usuario**: Implementa el papel de observador, ya que los objetos `Usuario` se suscriben al `Servidor` para recibir actualizaciones, como nuevos mensajes o notificaciones de conexión/desconexión de otros usuarios. Esto garantiza una actualización en tiempo real.

---

### Singleton
- **Servidor**: La clase `Servidor` sigue el patrón Singleton para asegurarse de que solo haya una instancia activa del servidor en ejecución. Esto es esencial para coordinar las conexiones de todos los usuarios y manejar las solicitudes de manera centralizada, evitando la creación de múltiples instancias que podrían causar conflictos en la comunicación.

---

### MVC (Modelo-Vista-Controlador)
- **Servidor**: Actúa como el controlador en el patrón MVC, gestionando la lógica del negocio, las conexiones, y las operaciones de los usuarios en el chat.
- **Usuario**: También funciona como parte del controlador, interactuando con la interfaz de usuario y manejando las actualizaciones necesarias desde el servidor.
- **Inicio**: Representa la vista, proporcionando una interfaz gráfica con la que los usuarios interactúan al iniciar sesión.
- **InterfazServidor**: Una vista que muestra las acciones y mensajes del servidor, ofreciendo controles para gestionar las conexiones.
- **InterfazUsuario**: La vista principal para los usuarios, donde pueden ver y enviar mensajes, recibir notificaciones, y gestionar su experiencia de chat.
#### Modelo
- **PaqueteAlta**, **PaqueteBaja**, **PaqueteDatos**, **PaqueteMensaje**: Estas clases representan el modelo en el patrón MVC porque encapsulan los datos que se transmiten y procesan en la aplicación. Actúan como estructuras de datos que el controlador manipula y que la vista puede utilizar para mostrar información. Al organizar la información en estas clases, se logra una separación clara entre la lógica del negocio y la presentación, lo que facilita el mantenimiento y escalabilidad del sistema.


---

### Proxy
- **Paquete**: Define una interfaz para los diferentes tipos de paquetes de datos que se transmiten a través de la red. La clase `ProxyPaquete` actúa como intermediaria, controlando el acceso a los objetos `Paquete` reales.
- **PaqueteAlta**, **PaqueteBaja**, **PaqueteDatos**, **PaqueteMensaje**: Estas clases son proxies que encapsulan y representan distintos tipos de datos transmitidos, permitiendo un manejo eficiente de la información y simplificando la lógica de envío y recepción.
- **ProxyPaquete**: Implementa el patrón Proxy para manejar de forma estructurada la transmisión de datos entre el cliente y el servidor, asegurando que solo se envíen los datos necesarios de manera eficiente.

---

### Fachada
- **Servidor**: Actúa como una fachada, simplificando las interacciones complejas del sistema para los usuarios. Gestiona las conexiones, notificaciones y transmisión de mensajes de forma unificada.
- **Usuario**: También proporciona una interfaz simplificada para los usuarios del chat, manejando la lógica de envío y recepción de mensajes sin exponer la complejidad del servidor.
- **PaqueteFachada**: Esta clase abstrae la complejidad del envío y recepción de paquetes a través de la red. Ofrece métodos claros y sencillos para enviar notificaciones y recibir mensajes, ocultando los detalles de implementación, como el manejo de sockets y la serialización.

---

## Clases que No Implementan un Patrón Específico
Algunas clases no se asociaron con un patrón de diseño formal, ya que su funcionalidad es más específica y no requiere la estructura de un patrón. Estas incluyen:

- **GestorServidor**: Realiza tareas específicas de gestión del servidor sin seguir un patrón definido.
- **ClienteServidor y ClienteUsuario**: Implementan los métodos `main` y son responsables de ejecutar la aplicación, sin patrones adicionales.
- **Constantes**: Una clase de utilidad que almacena valores constantes usados en todo el sistema.
- **ListaUsuarios**: Gestiona una colección de usuarios conectados, pero sin la necesidad de un patrón de diseño estructurado.

---

## Conclusión
Los patrones de diseño seleccionados ayudan a estructurar y optimizar el proyecto, haciendo que sea más escalable, mantenible y fácil de entender. Cada patrón se ha implementado con un propósito claro, ya sea para gestionar la comunicación en tiempo real (Observer), garantizar una única instancia del servidor (Singleton), proporcionar una arquitectura bien definida (MVC), manejar eficientemente los datos (Proxy), o simplificar la interacción con el sistema (Fachada).


