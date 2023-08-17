package Model;

import View.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import static javax.imageio.ImageIO.read;
/**
 * This class acts as all the four pillars in each of the corners of the game.
 */
public class FourPillars extends Item implements Serializable {
    /**
     * The images representing each pillar of OO.
     */
    private transient BufferedImage myAbstraction, myEncapsulation, myInheritance, myPolymorphism;
    /**
     * The name of the specific pillar of OO this 'FourPillars' object is.
     */
    private String myName;

    /**
     * Constructs a 'FourPillars' object.
     *
     * @param theWorldX The world-x coordinate to draw the item at
     * @param theWorldY The world-y coordinate to draw the item at
     * @param theName   The name of the specific pillar.
     * @param theGP     The GamePanel to draw the item onto.
     */
    public FourPillars(final int theWorldX, final int theWorldY, final String theName, final GamePanel theGP) {
        super(theGP, theWorldX, theWorldY);
        myName = theName;
        getItemImage();
        super.setImage(findImage());
    }

    /**
     * Sets the Buffered Images in this class to the correct images.
     */
    public void getItemImage() {
        try {
            myAbstraction = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/a.png")));
            myEncapsulation = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/e.png")));
            myInheritance = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/i.png")));
            myPolymorphism = read(Objects.requireNonNull(getClass().getResourceAsStream("/fourPillars/p.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the name of the specific four pillars object.
     *
     * @return The name of the four pillars object.
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Gets the image associated with the pillar of abstraction.
     *
     * @return The abstraction pillar image.
     */
    public BufferedImage getMyAbstraction() {
        return myAbstraction;
    }

    /**
     * Gets the image associated with the pillar of encapsulation.
     *
     * @return The encapsulation pillar image.
     */
    public BufferedImage getMyEncapsulation() {
        return myEncapsulation;
    }

    /**
     * Gets the image associated with the pillar of inheritance.
     *
     * @return The inheritance pillar image.
     */
    public BufferedImage getMyInheritance() {
        return myInheritance;
    }

    /**
     * Gets the image associated with the pillar of polymorphism.
     *
     * @return The polymorphism pillar image.
     */
    public BufferedImage getMyPolymorphism() {
        return myPolymorphism;
    }
    /**
     * Finds the specific image associated with the pillar's name to give to 'Item'.
     *
     * @return The specific image this pillar uses.
     */
    public BufferedImage findImage() {
        return switch (myName) {
            case "a" -> myAbstraction;
            case "e" -> myEncapsulation;
            case "i" -> myInheritance;
            case "p" -> myPolymorphism;
            default -> null;
        };
    }
}
