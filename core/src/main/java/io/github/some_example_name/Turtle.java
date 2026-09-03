package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

public class Turtle extends Animal{

    public Turtle(float x, float y){
        super(x,y);
        setImage(new Texture("turtleIMG.png"));

        super.name = "Turtle";

    }

    @Override
    public void act(){
        x += 0.1f;

    }



}
