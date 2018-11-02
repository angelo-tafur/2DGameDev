package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Enums;
import com.mygdx.game.util.Enums.ObjectType;

public class Transparent {
    public final float top;
    public final float bottom;
    public final float left;
    public final float right;

    public final ObjectType objectType;

    String identifier;

    public Transparent(float left, float top, float width, float height, ObjectType objectType) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
        this.objectType = objectType;
    }

    public void render(SpriteBatch batch){
        final float width = right - left;
        final float height = top - bottom;
        switch (objectType) {
            case ACID:
                Assets.getInstance().transparentAssets.acid.draw(batch, left - 1, bottom - 1, width + 1, height + 1);
                break;
            case ACID_SURFACE:
                Assets.getInstance().transparentAssets.acidSurface.draw(batch, left - 1, bottom - 1, width + 1, height + 1);
                break;
            case MID_LEFT:
                Assets.getInstance().platformAssets.platformMidLeft.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
                break;
            case MID_CENTER:
                Assets.getInstance().platformAssets.platformMidCenter.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
                break;
            case MID_RIGHT:
                Assets.getInstance().platformAssets.platformMidRight.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
                break;
            case CORNER_LEFT:
                Assets.getInstance().platformAssets.platformCornerTopLeft.draw(batch, left - 1, bottom - 1, width + 2, height + 2);
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
