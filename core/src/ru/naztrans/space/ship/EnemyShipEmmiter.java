package ru.naztrans.space.ship;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

import ru.naztrans.space.engine.math.Rect;
import ru.naztrans.space.engine.math.Rnd;
import ru.naztrans.space.engine.utils.Regions;

/**
 * Created by alexk on 17.02.2018.
 */

public class EnemyShipEmmiter {
    private enum ShipTypes {SMALL_SHIP("enemy0", 0.1f, 0.01f, 1, 5, 0.3f, 3f, 0.3f, 0.2f, 0f, 1), MIDDLE_SHIP("enemy1",0.11f, 0.02f, 5, 15, 0.25f, 4f, 0.2f, 0.03f, 0f, 5 ),
        BIG_SHIP("enemy2", 0.2f, 0.04f, 10, 45, 0.3f, 4f, 0.4f, 0.005f, 0f, 20), SMALL_SHIP_D("enemy0", 0.1f, 0.01f, 1, 5, 0.3f, 3f, 0.3f, 0.2f, 0.3f, 1), MIDDLE_SHIP_D("enemy1",0.11f, 0.02f, 5, 15, 0.25f, 4f, 0.2f, 0.03f, 0.15f, 5 ), ;
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




    private TextureRegion bulletRegion;
    private int stage;

    private float velosityC;
    private int maxBullets=1;

    private HashMap<String, TextureRegion[]> map=new HashMap<String, TextureRegion[]>();
    private ShipTypes currentship;
    public EnemyShipEmmiter(EnemyShipPool enemyShipPool, Rect worldBounds, TextureAtlas atlas) {
        this.enemyShipPool = enemyShipPool;
        this.worldBounds = worldBounds;

        this.bulletRegion = atlas.findRegion("bulletEnemy");
        for (ShipTypes s: ShipTypes.values()){
            map.put(s.tr, Regions.split(atlas.findRegion(s.tr), 1,2,2));
        }
    }
    public void setToNewGame(){
        stage=0;
    }
    public void generateEnemy(float delta, int frags) {
        stage=frags/20+1;
        generateInterval=4f-(float)stage/10f;
        generateTimer += delta;
        if (generateInterval <= generateTimer) {
            generateTimer = 0f;
            EnemyShip enemy = enemyShipPool.obtain();

            float type = (float) Math.random();
            if (type<0.7f) {
                if (stage>5&&type<0.3){
                    currentship=ShipTypes.SMALL_SHIP_D;
                }
                else {
                    currentship = ShipTypes.SMALL_SHIP;
                }
            } else if (type < 0.9f) {
                if (stage>10&&type<0.8){
                    currentship=ShipTypes.MIDDLE_SHIP_D;}
                else {
                    currentship=ShipTypes.MIDDLE_SHIP;
                }
            } else {
                currentship=ShipTypes.BIG_SHIP;
            }
            if (currentship!=ShipTypes.SMALL_SHIP&&currentship!=ShipTypes.SMALL_SHIP_D){
                velosityC=stage/10+1;
            }
            else {velosityC=1;}
            if (stage>3){
                maxBullets = 1 + (stage / 3);
                if (maxBullets>5) maxBullets=5;
            }



            enemy.setBottom(worldBounds.getTop());
            enemy.set(map.get(currentship.tr), currentship.initialXVel, -currentship.initialYVel*velosityC,
                    bulletRegion, currentship.bulletHeight, -currentship.bulletYVel,
                    currentship.bulletDamage+(int)stage/10, currentship.collisionDamage, currentship.initialReloadInterval, currentship.smallReloadInterval, maxBullets, currentship.height, currentship.initialHP );
            //System.out.println(maxBullets);
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            if (enemy.pos.x>worldBounds.pos.x){
                enemy.getV0().set(-currentship.initialXVel, -currentship.initialYVel);
            }

        }
    }

    public int getStage() {
        return stage;
    }
}
