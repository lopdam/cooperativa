# Cooperativa
El proyecto trata de la creacion de una base de datos y una interfaz grafica, 
para un cooperativa de transporte.Se va a usar MySQL y Java.

El proyecto esta relizado con JavaFX

Se tiene que ejecuatar el archivo, Data.sql donde se encuentran los datos del la cooperativa.
Tambien se debe ejecutar el archivo SeguridadProyecto.sql donde se encuentra el usuario y sus privilegios.

Single Responsability Principle

Problema: El constructor de Bus solo se debería encargar de poner los elementos de la vista, este aparte los crea por lo que infringe este principio ya que cada método debe de realizar una única acción.
![image](https://user-images.githubusercontent.com/73263047/99894560-fb4d0000-2c52-11eb-84f4-67a2ac71dea4.png)


Solución
Separamos la acción de llenar la lista en un método aparte.
![image](https://user-images.githubusercontent.com/73263047/99894575-22a3cd00-2c53-11eb-98d0-ffb468d7bfa0.png)


Separamos la creación de los elementos en diferentes métodos.


En el constructor solo llamamos a un método.


Single Responsability Principle

Problema: El constructor de Cliente solo se debería encargar de poner los elementos de la vista, este aparte los crea por lo que infringe este principio ya que cada método debe de realizar una única acción.

![image](https://user-images.githubusercontent.com/73263047/99894598-5da60080-2c53-11eb-8ffb-c9235ab123e7.png)

Solución
Separamos la acción de llenar la lista en un método aparte.


Separamos la creación de los elementos en diferentes métodos.

En el constructor solo llamamos a un método.
![image](https://user-images.githubusercontent.com/73263047/99894603-6c8cb300-2c53-11eb-8dbe-56d8d28af908.png)

Interface Segregation Principle
![image](https://user-images.githubusercontent.com/73263047/99894609-7a423880-2c53-11eb-8bf3-79a97b759c51.png)

Problema: Los métodos de las secciones de las vistas en BusModificar deberían está en una interfaz para que cualquier clase que deba usarlos pueda implementarla.


Solución
Creamos una interfaz donde pongamos la firma de los métodos que solo serán usados por BusModificar.

![image](https://user-images.githubusercontent.com/73263047/99894612-83330a00-2c53-11eb-82a0-cdeadd155300.png)

Dependency Inversion Principle

![image](https://user-images.githubusercontent.com/73263047/99894617-8af2ae80-2c53-11eb-9bda-4d561ee89ab2.png)

 
Problema: La clase chofer depende de la clase Chofer registrar.
Solución
Creamos una interfaz que tenga el método que usa Chofer de ChofeRegistrar.

![image](https://user-images.githubusercontent.com/73263047/99894619-934ae980-2c53-11eb-931a-0edcb1162cc6.png)

 
Problema: La clase chofer depende de la clase Chofer registrar.
Solución
Creamos una interfaz que tenga el método que usa Chofer de ChofeRegistrar.





Open Closed Principle
Problema: La clase Cliente posee un método llamado Eventos con una cantidad sumamente extensa de código, el cual consiste principalmente en definir las acciones que realizaran cada uno de los botones correspondientes, realizar esta acción infringe el principio Open Closed, ya que este objeto debe estar abierto unicamente para extensión, no para modificación, y en caso de que ser quiera agregar una funcionalidad extra a este objeto será necesario realizar una modificación al código en el método eventos, cuando lo que se tiene que hacer es una extensión para cumplir el OCP.

![image](https://user-images.githubusercontent.com/73263047/99894623-9fcf4200-2c53-11eb-99be-8a425540904f.png)




Solución:
Se creo una interfaz con los métodos de las opciones que se deben implementar en la clase objetivo, por lo tanto, si se quieren crear nuevos botones con nuevas funcionalidades solo será necesario incluirlo en la interfaz y los objetos tendran que sobrescribir dichos métodos dependiendo de las acciones que realizaran los botones en cada objeto. Al realizar esta acción se cumple el OCP debido a que dicha clase estará abierta a extensión, pero no a modificaciones en su código.

![image](https://user-images.githubusercontent.com/73263047/99894628-afe72180-2c53-11eb-892a-0b19f36c2ff2.png)


Open Closed Principle II
Problema 2:
A su vez en la clase Bus se viola el OCP, cuando va a manejar los eventos en sus botones se puede evidenciar que realiza todos los manejos en un solo método dentro de la clase Bus, dicha clase se tendrá que modificar cada vez que la clase Bus quiera agregar un nuevo botón con una nueva funcionalidad.

![image](https://user-images.githubusercontent.com/73263047/99894635-b9708980-2c53-11eb-8a3c-0f4f44decec0.png)


Solución:
Lo más idóneo seria implementar la interfaz que creamos en el problema anterior y sobrescribir dichos métodos a nuestra conveniencia, eso nos ayudara a hacer que la clase se mantenga cerrada a modificaciones, pero abierta a extensiones cumpliendo el OCP, a continuación, se puede evidenciar como fue implementada la interfaz y sobrescritos los métodos de dicha interfaz.
![image](https://user-images.githubusercontent.com/73263047/99894639-c2615b00-2c53-11eb-9620-394e480b2a1e.png)

