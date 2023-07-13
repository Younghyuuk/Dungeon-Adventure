package Model;

import View.GamePanel;
import Control.Keyboard;

import java.awt.*;

public class Priestess extends Heroes{

    private static final int MIN_HEAL = 25;
    private static final int MAX_HEAL = 55;

    private int myHp;
    private String myChName;
    private int myAttackSpeed;
    private int myMinDamage;
    private int myMaxDamage;
    private double myHitChance;

    public Priestess(GamePanel theGamePanel, Keyboard theKeyboard) {
        super(75, "Priestess", 5, 25, 45, 0.7, 0.3, theGamePanel,theKeyboard);
    }
    @Override
    public void getHeroesImage() {

    }
    @Override
    public double getBlockChance() {
        return super.getBlockChance();
    }
    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }
    @Override
    public void specialSkill(DungeonCharacter theAlly) {
        theAlly.setHp(getHp() + genHeal());
    }

    private int genHeal() {
        return (int) (Math.random() * ((MIN_HEAL - MAX_HEAL + 1))) + MIN_HEAL;
    }

}
