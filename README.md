# TickTack  MultiUser
 
este aplicacion es una adaptcacion del juego tic tac toe el cual se consiste en llenar en linea recta con su forma
correspondiente el tablero formando una linea o una diagonal en cualquier sentido siempre y cuando la longitud de la linea
sea igual a la dimension del tablero que por defecto es 3

## requsites
    -java version 1.7+
    -maven installed

## Installation
 ```sh
$ git clone https://github.com/danielGomez1703/ARSW-WebSockets
$ cd ARSW-WebSockets
$ mvn package
$ mvn spring-boot:run
```


# MANUAL
  una vez instalado y ejecutado el programa debera acceder a su navegador e introdocir localhost y el puerto tomcat determinado 8080 de la siegueinte forma:
  navegador  
	http://localhost:8080/index.html
  

  puede acceder a la aplicacion por el siguiente link:
[link Despliegue Heroku Del Tablero Dinamico](https://floating-citadel-07870.herokuapp.com/TableroOnline.html)

# PRUEBAS

Las siguientes imagenes son las evidencias de la aplicacion del chat de la aplicacion
- reinicio
- MultiUsuario
- cadenas invalidas

Al iniciar podemos ver las listas de la conversacion vacias.

![Modelo](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/evidence1.PNG)	

En la siguiente imagen s epuede ver como el punto que se crea en un lado del tablero, cambia asi mismo en el otro cliente.

![Modelo](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/evidence2.PNG)

se puede ver que el proyecto se cre de forma correcta.


![Modelo](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/Build.PNG)



## Modelo
![Modelo](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/Model.PNG)

El modelo de la aplicacion se compone por tres elementos **Punto** La cual va a hacer referencia al objeto que se va a mostrar , es decir un circulo que se concatena con otros para simular el seguimiento del raton

por otra parte tenemos la memoria local, lo caul nos permite trabajar en el mismo tablero para quien lo desee, esto mediante una clase **SharedTable** esta case solo genera una isntancia de si mismo como se ve en el diagrama y retorna esa instancia a quine le necesite.

por ultimo la clase **control** donde se manejan las solicitudes del cliente web y conecta con el REACT.
    
    


## Descripion

![ev1](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/esquema.PNG)

La aplciacion consiste en un canvas que se crea para que los usuarios puedan escribir dentro del tablero con el fin de hacer clases mas amenas

la trasa son ciruculos concatenados por donde pasa el mouse, es decir sigue la trasa del mouse con circulos y los pinta y guarda en una entidad.


![ev2](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/result.PNG)

una vez en la aplicacion el tablero esta disponible para que pueda escribir en el. en caso dado que quiera reiniciar dara click en el boton **clear**

![ev3](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/clear.PNG)
 
 este mensaje nos confirma que el tablero fue limpiado exitosamente.
 
 ![ev3](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/resources/final.PNG)

## DOCUMENTACION

la documentacion se encuentra adjunta en el repositorio 
[aqui encontrara la documentacion](https://github.com/danielGomez1703/ARSW-Lab7/tree/master/site/apidocs)


## AUHTOR

Daniel Felipe Gomez Suarez - [danielgomez1703](https://github.com/danielGomez1703)
    
## BUILT IN
Proyecto construido en [Maven](https://maven.apache.org/)
## License
----
para consultar su licencia vaya al link 
[leer aqui](https://github.com/danielGomez1703/ARSW-Lab7/blob/master/LICENSE.txt)
