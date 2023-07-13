package Model;

import Control.Keyboard;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Thief extends Heroes{
    private static final double SURPRISE_ATTACK_CHANCE = 0.4;
    private static final double SURPRISE_FAIL_CHANCE = 0.2;

    public Thief(GamePanel theGamePanel, Keyboard theKeyboard) {
        super(75, "Thief", 6, 20, 40, 0.8, 0.4, theGamePanel,theKeyboard);
        getHeroesImage();
    }

    @Override
    public void regularAttack(DungeonCharacter theOpp) {
        attackBehavior(theOpp);
    }

    // needs fixing
    @Override
    public void specialSkill(DungeonCharacter theOpp) {
        if (Math.random() <= SURPRISE_ATTACK_CHANCE) {
           if (Math.random() <= SURPRISE_FAIL_CHANCE) {

           } else {
               attackBehavior(theOpp);
               attackBehavior(theOpp);
           }
        }
    }
    public void getHeroesImage(){
        try {
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_up1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_up2.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_down2.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_left1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_left2.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_right1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("/thief/thief_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
