package raf.draft.dsw.controller.actions;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * The AbstractRoomAction class is an abstract class that extends AbstractAction.
 * It provides a method to load and scale an icon from a given path.
 */
public abstract class AbstractRoomAction extends AbstractAction {

    /**
     * Loads an icon from the specified path and scales it to 32x32 pixels.
     *
     * @param path the path to the icon image
     * @return the scaled icon, or null if the image file is not found
     */
    public Icon loadIcon(String path) {
        Icon icon = null;
        URL imageURL = getClass().getResource(path);
        if (imageURL != null) {
            Image img = new ImageIcon(imageURL).getImage();
            Image newImg = img.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else {
            System.err.println("File " + path + " not found");
        }
        return icon;
    }
}