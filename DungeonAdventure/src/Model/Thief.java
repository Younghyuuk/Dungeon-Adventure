package Model;

import Control.Keyboard;
import View.GamePanel;

import java.awt.*;

public class Thief extends Heroes{

    private static final double SURPRISE_ATTACK_CHANCE = 0.4;
    private static final double SURPRISE_FAIL_CHANCE = 0.2;

    protected Thief ( GamePanel theGamePanel, Keyboard theKeyboard) {
        super(75, "Thief", 6, 20, 40, 0.8, 0.4, theGamePanel,theKeyboard);
    }

    @Override
    public void regularAttack(DungeonCharacter theOpp) {

    }

    @Override
    public void specialSkill(DungeonCharacter theOpp) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D theGraphics) {

    }

}
