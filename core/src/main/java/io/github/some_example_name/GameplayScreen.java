package io.github.some_example_name;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameplayScreen implements Screen {

    //Object that draws all our sprite graphics: jpgs, pngs, etc.
    private SpriteBatch spriteBatch;

    //Object that draws shapes: rectangles, ovals, lines, etc.
    private ShapeRenderer shapeRenderer;

    //Camera to view the virtual world
    private Camera camera;

    //control how the camera views the world
    //zoom in/out? Keep everything scaled?
    private Viewport viewport;

    ArrayList<Animal> animals;

    private boolean racing = true;

    private BitmapFont defaultFont = new BitmapFont();

    private String winner = "";

    private double endTime;

    private double startTime = System.currentTimeMillis();
    /*
     * runs one time, at the very beginning
     * all setup should happen here
     */
    @Override
    public void show() {
        //OrthographicCamera is a 2D camera
        camera = new OrthographicCamera();
        //set the camera position to the middle of the window
        camera.position.set(1280/2, 720/2, 0);
        //required to save and update the camera to the changes above
        camera.update();

        //freeze my view to 1280x720, no
        //matter the resolution fo the window the camera will
        //always show the same amount of the world
        viewport = new FitViewport(1280, 720, camera);

        //Objects that will draw graphics for us
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        //????, I just know that this was the solution to an annoying problem I had
        shapeRenderer.setAutoShapeType(true);

        animals = new ArrayList<>();
        animals.add(new Animal(0,0));
        animals.add(new Turtle(0,80));
        animals.add(new SnappingTurtle(0,160));
        animals.add(new Cheeta(0,240));

    }

    /*
     * this method runs as fast as it can (or to a set FPS)
     * repeatedly, constantly looped
     * Thing to include in this method:
     * (1) Process User Input
     * (2) A.I.
     * (3) Draw all graphics
     */
    @Override
    public void render(float v) {
        clearScreen();

        //User Input

        //A.I.
        int count = 0;

        if (racing) {
            for (Animal animal : animals) {
                animal.act();
                if (animal.getX() > 550) { // + animal.getSize()
                    count++;
                    winner = animal.getName();
                }
            }
        }

        if(count > 0){
            racing = false;
            endTime = System.currentTimeMillis();
        }


        //all drawing of shapes MUST go between begin/end
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(550,0,25,450);
        shapeRenderer.end();

        //all drawing of graphic MUST go between being/end
        spriteBatch.begin();
        if(!racing){
            defaultFont.draw(spriteBatch, "Winner: " + winner, 300, 300);
            defaultFont.draw(spriteBatch, "Time: " + ((endTime-startTime)/1000.0) + " seconds", 200, 200);
        }

        defaultFont.draw(spriteBatch, "Time: " + ((System.currentTimeMillis() - startTime) / 1000), 400, 400);


        for(Animal animal : animals){
            animal.draw(spriteBatch);
        }


        spriteBatch.end();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //prevent memory leaks
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }
}
