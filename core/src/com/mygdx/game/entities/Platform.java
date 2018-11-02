package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Enums;
import com.mygdx.game.util.Enums.TileType;

public class Platform {
    public final float top;
    public final float bottom;
    public final float left;
    public final float right;

    public final TileType tileType;

    String identifier;

    public Platform(float left, float top, float width, float height, TileType tileType) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
        this.tileType = tileType;
    }
    public void render(SpriteBatch batch) {
        final float width = right - left;
        final float height = top - bottom;
        switch (tileType){
            case LEFT:
                Assets.getInstance().platformAssets.platformLeft.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
                break;
            case CENTER:
                Assets.getInstance().platformAssets.platformCenter.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
                break;
            case RIGHT:
                Assets.getInstance().platformAssets.platformRight.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
                break;
            case CORNER_LEFT:
                Assets.getInstance().platformAssets.platformCornerLeft.draw(batch, left - 1, bottom - 1, width + 2, height + 2 );
                break;
            case SOLO:
                Assets.getInstance().platformAssets.platformNinePatch.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
                break;
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
