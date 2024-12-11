package raf.draft.dsw.view.bars;

import raf.draft.dsw.controller.actions.global.AddNodeStateAction;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class AddStateToolBar extends JToolBar {
    public AddStateToolBar() {
        super(VERTICAL);
        setFloatable(false);
        setBackground(Color.WHITE);
        setBorder(new MatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));
        addButtons();
    }

    private void addButtons() {

        add(Box.createVerticalGlue());
        add(new AddNodeStateAction("bed","/images/bed.png"));
        add(new AddNodeStateAction("doors","/images/door.png"));
        add(new AddNodeStateAction("table","/images/table.png"));
        add(new AddNodeStateAction("wardrobe","/images/wardrobe.png"));
        add(new AddNodeStateAction("sink","/images/sink.png"));
        add(new AddNodeStateAction("bathtub","/images/bathtub.png"));
        add(new AddNodeStateAction("boiler","/images/boiler.png"));
        add(new AddNodeStateAction("washingMachine","/images/washingMachine.png"));
        add(new AddNodeStateAction("toiletBowl","/images/toiletBowl.png"));
        add(Box.createVerticalGlue());
    }
}
