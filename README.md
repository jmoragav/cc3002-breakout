# cc3002-breakout


Tarea 3 la cual consistía en implementar la interfaz gráfica del juego Breakout y de los elementos de este (Ladrillos,Player,Bolita)


## Como fue implementado

Se uso el codigo propio de la tarea2 para implementar la lógica del juego y el framework [FXGL](https://github.com/AlmasB/FXGL) para la implementación de la interfaz gráfica.

En la parte de la lógica se añadieron métodos las siguientes interfaces:

+ [**Brick**](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/logic/brick/Brick.java). 
1. boolean isMetalBrick()
2. boolean isWoodenBrick()
3. boolean isGlassBrick()
4. boolean isAlmostBroke()
5. boolean isNormal()
6. boolean isHitted()
7. String getTexture()
8. void setState(State state)

+ [**Facade**](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/facade/HomeworkTwoFacade.java). 
1. void addedToaGUI(Observer o)
2. void resetGame()

 
 *__Para más detalles sobre la funcionalidad de los métodos se recomienda leer el JavaDoc dentro de cada clase__*
 
Los objetos de tipo *Brick* se implementaron como *Observables*, estos pues al romperse notifican a sus *Observers* (PlayableLevel y Game) lo cual genera una serie de eventos en el juego, los objetos PlayableLevel también son *Observables* pues deben notificar al *Game* cuando este sufre ciertos cambios.

La interfase *Visitor* fue implementada para facilitar las acciones de los objetos de tipo *Level* y *Game* ya que estos también son *Observers* y al ser notificados visitaran a los *Bricks* y realizaran una serie de eventos dependiendo del *Brick* visitado.

Se creo la clase [Placeholder](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/logic/level/PlaceHolder.java) siguiendo el Null Pattern para poder representar un nivel injugable.

Para facilitar el cambio de texturas de los bricks dentro de la interfaz gráfica , a estos se les implemento el patrón de diseño [*State*](https://github.com/jmoragav/cc3002-breakout/tree/master/src/main/java/logic/state),la funcion principal de los estados es retornar el nombre de la textura correspondiente al ladrillo y responder en qué estado se encuentra.

Se creo el objeto [BehaviourSelector](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/logic/visitor/BehaviourSelector.java) implementado el patrón de diseño visitor, la función del BehaviorSelector es entregar el sonido correspondiente a cada brick cuando este es golpeado y facilitar el componente del brick.

La clase [Game](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/controller/Game.java) fue transformada en un observable para que el [GUI](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/gui/BreakoutApp.java) pueda obtener informaciógan correspondiente al puntaje y a las vidas disponibles.

El GUI fue implementado con la ayuda del framework FXGL, dentro del package [gui](https://github.com/jmoragav/cc3002-breakout/tree/master/src/main/java/gui) se encuentran los [componentes](https://github.com/jmoragav/cc3002-breakout/tree/master/src/main/java/gui/components) de cada entidad dentro de la interfaz gráfica, la [fabrica](https://github.com/jmoragav/cc3002-breakout/tree/master/src/main/java/gui/factory) de estas entidades, los [tipos](https://github.com/jmoragav/cc3002-breakout/tree/master/src/main/java/gui/types) de estas entidades y la [interfaz gráfica](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/gui/BreakoutApp.java) en sí.

### Features implementados

De los features pedidos en el enunciado de la tarea se implementaron 5 de ellos, siendo 3 mayores y 2 menores

+ **Features Mayores**
1. Estados distintos en los bricks al ser golpeados
2. Nuevos niveles configurables, estos se pueden configurar presionando la tecla m
3. Mecanismo de testing, llamado CHEATMODE en el juego se activa con la tecla y, cuando el CHEATMODE se activa se pueden golpear los brick con un simple click en ellos y con el click derecho se puede hacer desaparecer la bola para que se reubique en la posición del jugador

+ **Features Menores**
1. Sonido al golpear los bricks, cada brick tiene su propio sonido asociado y un sonido compartido cuando estos son destruidos.
2. Partículas al destruir bricks, estas aparecerán por unos segundos en la pantalla al destruirse un brick, solo aparecen cuando se destruye un brick pues si se implementaba cada vez que se golpeaba un brick la cantidad de partículas afectaba el rendimiento (FPS) del juego breakout , es por eso la limitación.

### Como correr el juego

Para poder correr el juego se debe de hacer desde una IDE de Java , en esta se debe correr el método main dentro de [BreakoutApp](https://github.com/jmoragav/cc3002-breakout/blob/master/src/main/java/gui/BreakoutApp.java),se intento añadir un jar file al repositorio para que fuera de manera más directa, pero no se pudo a futuro y con más conocimientos en el tema se añadirá al repositorio

## Armado con

* [Maven](https://maven.apache.org/) - Dependency Management
* [FXGL](https://github.com/AlmasB/FXGL)- Graphic Interface


## Authors

* **Juan-Pablo Silva** - *Trabajo Inicial* - [juanpablos](https://github.com/juanpablos)
* **Joaquin Moraga** - *Alumno* - [jmoragav](https://github.com/jmoragav)




## Otros

* Playlist con la cual se programa la tarea - [Viaje](https://open.spotify.com/playlist/3Adj8Nww8lEYE0hiP8WJQZ)

