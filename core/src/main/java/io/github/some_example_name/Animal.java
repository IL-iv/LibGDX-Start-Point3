package io.github.some_example_name;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.time.temporal.Temporal;

public class Animal {
    protected float x;
    protected float y;
    protected Texture image;
    protected float speed;
    protected String name;

    public Animal(float x, float y) {
        this.x = x;
        this.y = y;
        this.name = "Animal";

        image = new Texture("monke.png");
        speed = 0.2f;
    }

    public void draw(SpriteBatch spriteBatch){

        spriteBatch.draw(image, x, y, 75, 75);

      //  spriteBatch.draw();

      //  spriteBatch.draw(image, x, y, );


    }

    public void act(){

        x += 0.175;

    }


    public float getSpeed() {
        return speed;
    }

    public Texture getImage() {
        return image;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}


