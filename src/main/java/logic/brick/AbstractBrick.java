package main.java.logic.brick;

public abstract class AbstractBrick implements logic.brick.Brick {
    protected void hit(int hp,boolean status){
        int new_hp= hp-1;
        if(new_hp==0) status=false;

    }
}
