package gui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {
    public static final EventType<GameEvent> ANY = new EventType<>(Event.ANY, "GAME_EVENT");
    public static final EventType<GameEvent> CHANGE_LEVEL = new EventType<>(ANY, "CHANGE_LEVEL");

    public GameEvent(EventType<? extends Event> eventType){
        super(eventType);
    }

}
