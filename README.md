# cc3002-breakout


Tarea 2 la cual consistia en implementar la logica del juego Breakout y de los elementos de este (Ladrillos , Niveles)


## Como fue implementado

Usando las clases e interfase entregadas en el codigo base se implementaron los metodos presentes en ellos y se agregaron los siguientes metodos e interfase:

⋅⋅* **Brick**. 
1. void ChangeStatus()
2. void Break()
3. void accept(Visitor o)
4. void addedToLevel(Level level)

⋅⋅* **Level**. 
1. void addedToAGame(Game game)

⋅⋅* **Visitor**. 
 1. void VisitMetalBrick(MetalBrick brick)
 2. void VisitBrickWithPoints(Brick brick)
 
 *__Para mas detalles sobre la funcionalidad de los metodos se recomienda leer el JavaDoc dentro de cada clase__*
 
Los objetos de tipo *Brick* se implementaron como *Observables*, estos pues al romperse notifican a sus *Observers* (Level y Game) lo cual genera una serie de eventos en el juego, los objetos de tipo Level tambien son *Observables* pues deben notificar al *Game* cuando este sufre ciertos cambios .

La interfase *Visitor* fue implementada para facilitar las acciones de los objetos de tipo *Level* y *Game* ya que estan tambien son *Observers* y al ser notificadas visitaran a los *Bricks* y realizaran una serie de eventos dependiendo del *Brick* visitado.


### Como correr el juego

Para poder correr el juego se debe de hacer desde una IDE de Java , en esta se debe crear una clase Main la cual debera importar los archivos del juego y se debera de hacer de forma *manual*
**
A futuro(*_Tarea 3_*) se creara una interfaz grafica para facilitar la accion de jugar **Breakout**

## Armado con

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Juan-Pablo Silva** - *Trabajo Inicial* - [juanpablos](https://github.com/juanpablos)
* **Joaquin Moraga** - *Alumno* - [jmoragav](https://github.com/jmoragav)




## Otros

* Playlist con la cual se programa la tarea - [Viaje](https://open.spotify.com/playlist/3Adj8Nww8lEYE0hiP8WJQZ)

