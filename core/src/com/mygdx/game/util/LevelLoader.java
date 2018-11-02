package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Level;
import com.mygdx.game.entities.Platform;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class LevelLoader {

    public static final String TAG = LevelLoader.class.toString();

    public static Level load(String levelName) {
        Level level = new Level();

        String path = Constants.LEVEL_DIR + File.separator + levelName + "." + Constants.LEVEL_FILE_EXTENSION;

        FileHandle file  = Gdx.files.internal(path);
        JSONParser parser = new JSONParser();

        try {

            JSONObject root = (JSONObject) parser.parse(file.reader());

            Gdx.app.log(TAG, root.keySet().toString());

            JSONObject composite = (JSONObject) root.get(Constants.LEVEL_COMPOSITE);

            Gdx.app.log(TAG, composite.keySet().toString());

            JSONArray platforms = (JSONArray) composite.get(Constants.LEVEL_IMAGES);

            loadPlatforms(platforms, level);

        } catch (ParseException e) {
            Gdx.app.error(TAG, e.getMessage());
            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);
        } catch (IOException e) {
            Gdx.app.error(TAG, e.getMessage());
            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);
        }

        return level;
    }

    private static Vector2 extractXY(JSONObject object) {

        Number x = (Number) object.get(Constants.LEVEL_X_KEY);
        Number y = (Number) object.get(Constants.LEVEL_Y_KEY);

        return new Vector2(
                (x == null) ? 0 : x.floatValue(),
                (y == null) ? 0 : y.floatValue()
        );
    }

    private static void loadPlatforms(JSONArray array, Level level){
        Array<Platform> platforms = new Array<Platform>();

        for (Object object : array) {
            final JSONObject platformObject = (JSONObject) object;

            Vector2 bottomLeft = extractXY(platformObject);
            final float width = ((Number) platformObject.get(Constants.LEVEL_WIDTH_KEY)).floatValue();
            final float height = ((Number) platformObject.get(Constants.LEVEL_HEIGHT_KEY)).floatValue();

//            final Platform platform = new Platform(bottomLeft.x, bottomLeft.y + height, width, height);

        }
    }
}