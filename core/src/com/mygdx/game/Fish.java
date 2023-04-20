package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Fish extends BaseActor{

    public Fish(float x, float y, Stage s)
    {
        super(x, y, s);

        loadTexture("fish.png");

        Action move = Actions.moveBy(10,0);
        this.addAction(Actions.forever(move));

    }
}
