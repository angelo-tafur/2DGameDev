package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Level;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums.Direction;
import com.mygdx.game.util.Utils;

public class Kunai {
    private final Direction direction;
    private final Level level;
    public boolean active;
    private Vector2 position;

    public Kunai(Level level, Vector2 position, Direction direction) {
        this.level = level;
        this.position = position;
        this.direction = direction;
        active = true;
    }

    public void update(float delta) {
        switch (direction) {
            case LEFT:
                position.x -= delta * Constants.KUNAI_MOVE_SPEED;
                break;
            case RIGHT:
                position.x += delta * Constants.KUNAI_MOVE_SPEED;
                break;
        }

        for (Zombie zombie : level.getZombies()) {
            if (position.dst(zombie.getPosition()) < 17) {
                active = false;
                zombie.health--;
            }
        }

        final float worldWidth = level.getViewport().getWorldWidth();
        final float cameraX = level.getViewport().getCamera().position.x;

        if (position.x < cameraX - worldWidth / 2 || position.x > cameraX + worldWidth / 2) {
            active = false;
        }
    }
    public void render(SpriteBatch batch) {
        TextureRegion region = Assets.getInstance().kunaiAssets.kunai;
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                .3f,
                .3f,
                -90,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                direction.value);
    }
}
