package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class Wall extends BaseActor{


    public Wall(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("wall.png");

        setBoundaryPolygon(8);
    }
}
