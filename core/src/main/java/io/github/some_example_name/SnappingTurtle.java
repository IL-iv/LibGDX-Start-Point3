package io.github.some_example_name;

public class SnappingTurtle extends Turtle{
    public SnappingTurtle(float x, float y){
        super(x,y);
        super.name = "Snapping Turtle";

    }

    @Override
    public void act(){
        double random = Math.random();
        if(random < 0.008) {x += 7;}
        else {super.act();}
    }

}
