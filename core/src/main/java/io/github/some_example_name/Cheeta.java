package io.github.some_example_name;

public class Cheeta extends Animal{

    private int moveTime;
    private int move;

    public Cheeta(float x, float y) {
        super(x, y);

        super.name = "Cheeta";

        this.moveTime = 0;
        this.move = 3;

    }

    @Override
    public void act(){
        double random = Math.random();

        if(random < 0.020) {
            move -= moveTime;
            moveTime++;
        }

        x += move;

    }


}
