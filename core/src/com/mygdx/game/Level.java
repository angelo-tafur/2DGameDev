package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entities.Kunai;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Platform;
import com.mygdx.game.entities.Transparent;
import com.mygdx.game.entities.Zombie;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums;
import com.mygdx.game.util.Enums.Direction;

import static com.mygdx.game.util.Enums.ObjectType.CORNER_LEFT;
import static com.mygdx.game.util.Enums.ObjectType.MID_CENTER;
import static com.mygdx.game.util.Enums.ObjectType.MID_LEFT;
import static com.mygdx.game.util.Enums.ObjectType.MID_RIGHT;

public class Level {
    public static final String TAG = Level.class.getName();

    public Viewport viewport;
    private Player player;
    private Array<Platform> platforms;
    private Array<Transparent> transparents;
    private DelayedRemovalArray<Zombie> zombies;
    private DelayedRemovalArray<Kunai> kunais;
    private Texture background;

    private ShapeRenderer renderer;

    public Level() {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        player = new Player(new Vector2(200, 200), this);
        platforms = new Array<Platform>();
        transparents = new Array<Transparent>();
        zombies = new DelayedRemovalArray<Zombie>();
        kunais = new DelayedRemovalArray<Kunai>();
        background = new Texture("BG.png");
        renderer = new ShapeRenderer();
    }

    public static Level debugLevel() {
        Level level = new Level();
        level.initializeDebugLevel();
        return level;
    }

    private void initializeDebugLevel() {
        player = new Player(new Vector2(300, 200), this);
        platforms = new Array<Platform>();
        transparents = new Array<Transparent>();
        zombies = new DelayedRemovalArray<Zombie>();
        kunais = new DelayedRemovalArray<Kunai>();

        platforms.add(new Platform(35, 70, 100, 100, Enums.TileType.LEFT));
        platforms.add(new Platform(135, 70, 100, 100, Enums.TileType.CENTER));
        platforms.add(new Platform(235, 70, 100, 100, Enums.TileType.RIGHT));

        //ACID
        transparents.add(new Transparent(340, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.ObjectType.ACID_SURFACE));
        transparents.add(new Transparent(340, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.ObjectType.ACID));
        transparents.add(new Transparent(725, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.ObjectType.ACID_SURFACE));
        transparents.add(new Transparent(725, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.ObjectType.ACID));
        transparents.add(new Transparent(825, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.ObjectType.ACID_SURFACE));
        transparents.add(new Transparent(825, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.ObjectType.ACID));

        Platform zombiePlatform2 =new Platform(530, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.CENTER);
        Platform zombiePlatform3 = new Platform(50, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.CENTER);
        transparents.add(new Transparent(-250, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        transparents.add(new Transparent(-250, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        transparents.add(new Transparent(-250, 100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        transparents.add(new Transparent(-250, 200, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        platforms.add( new Platform(-250, 300, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.CENTER));
        platforms.add(new Platform(-150, 300, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.RIGHT));
        transparents.add(new Transparent(-150, 200, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_RIGHT));
        transparents.add(new Transparent(-150, 100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_RIGHT));
        transparents.add(new Transparent(-150, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, CORNER_LEFT));
        transparents.add(new Transparent(-150, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        platforms.add(new Platform(-50, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.CORNER_LEFT));
        transparents.add(new Transparent(-50, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        platforms.add(zombiePlatform3);
        transparents.add(new Transparent(50, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        platforms.add(new Platform(150, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.CENTER));
        transparents.add(new Transparent(150, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        platforms.add(new Platform(250, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.RIGHT));
        transparents.add(new Transparent(250, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_RIGHT));

        Platform zombiePlatform1 = new Platform(1015, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.CENTER);

        zombies.add(new Zombie(zombiePlatform1, this));
        zombies.add(new Zombie(zombiePlatform2, this));
        zombies.add(new Zombie(zombiePlatform3, this));

        platforms.add(new Platform(430, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.LEFT));
        transparents.add(new Transparent(430, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_LEFT));
        platforms.add(zombiePlatform2);
        transparents.add(new Transparent(530, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        platforms.add(new Platform(630, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.RIGHT));
        transparents.add(new Transparent(630, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_RIGHT));

        platforms.add(new Platform(915, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.LEFT));
        transparents.add(new Transparent(915, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_LEFT));
        platforms.add(zombiePlatform1);
        transparents.add(new Transparent(1015, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));
        platforms.add(new Platform(1115, 0, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, Enums.TileType.CENTER));
        transparents.add(new Transparent(1115, -100, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGTH, MID_CENTER));


    }

    public void update(float delta) {

        player.update(delta, platforms);

        //AGGIORNO I KUNAI
        kunais.begin();
        for (Kunai kunai : kunais) {
            kunai.update(delta);
            if(!kunai.active){
                kunais.removeValue(kunai, false);
            }
        }
        kunais.end();

        //AGGIORNO GLI ZOMBIES
        zombies.begin();
        for (Zombie zombie : zombies) {
            zombie.update(delta);
            if(zombie.health <= 0){
                zombie.startDead();
                if (zombie.isDeadFinish()){
//                    Gdx.app.log(TAG, "ZOMBIE MORTO");
//                    zombies.removeValue(zombie, false);
                }
            }
        }
        zombies.end();
    }

    public void render(SpriteBatch batch){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(background, -250, -200, 1500, 800);

        for (Transparent transparent : transparents) {
            transparent.render(batch);
        }
        for (Platform platform : platforms) {
            platform.render(batch);
        }
        for (Zombie zombie : zombies) {
            zombie.render(batch);
        }
        for (Kunai kunai : kunais) {
            kunai.render(batch);
        }

        player.render(batch);

        batch.end();

        // SOLO PER DEBUG --start
//        renderer.setProjectionMatrix(viewport.getCamera().combined);
//        renderer.begin(ShapeRenderer.ShapeType.Line);
//
//        renderer.setColor(Color.BLUE);
//
//        renderer.rect(player.getPosition().x,
//                player.getPosition().y,
//                player.getWidth(),
//                player.getHeight());
//
//        for (Zombie zombie : zombies) {
//            renderer.rect(zombie.getPosition().x - Constants.ZOMBIE_OFFSET.x,
//                    zombie.getPosition().y - Constants.ZOMBIE_OFFSET.y,
//                    Constants.ZOMBIE_WIDTH,
//                    Constants.ZOMBIE_HEIGHT);
//        }
//        renderer.setColor(Color.RED);
//        renderer.rect(player.playerBounds.x,
//                player.playerBounds.y,
//                player.playerBounds.width,
//                player.playerBounds.height);
//
//        for (Zombie zombie : zombies) {
//            renderer.rect(zombie.getZombieBounds().x,
//                    zombie.getZombieBounds().y,
//                    zombie.getZombieBounds().width,
//                    zombie.getZombieBounds().height);
//        }
//        renderer.end();
        //SOLO PER DEBUG --end
    }

    public void throwKunai(Vector2 position, Direction direction) {
        kunais.add(new Kunai(this, position, direction));
    }

    public Player getPlayer() {
        return player;
    }

    public DelayedRemovalArray<Zombie> getZombies() {
        return zombies;
    }

    public Viewport getViewport() {
        return viewport;
    }


}
