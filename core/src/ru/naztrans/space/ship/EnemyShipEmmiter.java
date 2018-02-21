package ru.naztrans.space.ship;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.math.Rnd;
import ru.naztrans.space.engine.utils.Regions;

/**
 * Created by alexk on 17.02.2018.
 */

public class EnemyShipEmmiter {
    private enum ShipTypes {SMALL_SHIP("enemy0", 0.1f, 0.01f, 1, 5, 0.3f, 3f, 0.1f, 0.2f, 0f, 1), MIDDLE_SHIP("enemy1",0.11f, 0.02f, 5, 15, 0.25f, 4f, 0.2f, 0.03f, 0f, 5 ),
        BIG_SHIP("enemy2", 0.2f, 0.04f, 10, 45, 0.3f, 4f, 0.4f, 0.005f, 0f, 20) ;
        public String tr;
        public float height, bulletHeight;
        public int bulletDamage, collisionDamage;
        public float bulletYVel;
        public float initialReloadInterval, smallReloadInterval;
        public float initialYVel, initialXVel;
        public int initialHP;

        ShipTypes(String textureName, float height, float bulletHeight, int bulletDamage, int collisionDamage, float bulletYVel,
                  float initialReloadInterval, float smallReloadInterval, float initialYVel, float initialXVel, int initialHP) {
            this.tr=textureName;
            this.height=height;
            this.bulletHeight=bulletHeight;
            this.bulletDamage=bulletDamage;
            this.collisionDamage=collisionDamage;
            this.bulletYVel=bulletYVel;
            this.initialReloadInterval = initialReloadInterval;
            this.smallReloadInterval=smallReloadInterval;
            this.initialYVel=initialYVel;
            this.initialXVel=initialXVel;
            this.initialHP=initialHP;

        }
    }





    private float generateTimer;
    private float generateInterval = 4f;

    private final EnemyShipPool enemyShipPool;
    private Rect worldBounds;
    private Vector2 tmpV2;
    private  TextureRegion[] region;


    private TextureRegion bulletRegion;
    private int stage;
    private TextureAtlas textureAtlas;
    private ShipTypes currentship;
    public EnemyShipEmmiter(EnemyShipPool enemyShipPool, Rect worldBounds, TextureAtlas atlas) {
        this.enemyShipPool = enemyShipPool;
        this.worldBounds = worldBounds;
        this.textureAtlas=atlas;
        this.bulletRegion = textureAtlas.findRegion("bulletEnemy");
    }
    public void setToNewGame(){
        stage=1;
    }
    public void generateEnemy(float delta, int frags) {
        stage=frags/20+1;
        generateTimer += delta;
        if (generateInterval <= generateTimer) {
            generateTimer = 0f;
            EnemyShip enemy = enemyShipPool.obtain();

            float type = (float) Math.random();
            if (type<0.7f) {
                currentship=ShipTypes.SMALL_SHIP;
            } else if (type < 0.9f) {
                currentship=ShipTypes.MIDDLE_SHIP;
            } else {
                currentship=ShipTypes.BIG_SHIP;
            }
            region=Regions.split(textureAtlas.findRegion(currentship.tr), 1,2,2);

            enemy.set(region, currentship.initialXVel, -currentship.initialYVel,
                    bulletRegion, currentship.bulletHeight, -currentship.bulletYVel,
                    currentship.bulletDamage, currentship.collisionDamage, currentship.initialReloadInterval, currentship.smallReloadInterval, 1, currentship.height, currentship.initialHP );
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }

    public int getStage() {
        return stage;
    }
}
