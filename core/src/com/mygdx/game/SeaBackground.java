package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class SeaBackground extends BaseActor {

    public SeaBackground(float x, float y, Stage s)
    {
        super(x,y,s);

        String[] filenames =
                {"water.jpg", "water-2.jpg", "water-3.jpg"};

        loadAnimationFromFiles(filenames, 0.1f, true);
    }
}
