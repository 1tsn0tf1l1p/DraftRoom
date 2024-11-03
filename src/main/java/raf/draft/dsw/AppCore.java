package raf.draft.dsw;

import com.formdev.flatlaf.FlatLightLaf;
import raf.draft.dsw.core.ApplicationFramework;

import javax.swing.*;

public class AppCore {
    public static void main(String[] args) {
        guiLook();
        ApplicationFramework.getInstance().postInitialize();
    }

    private static void guiLook() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf: " + e.getMessage());
        }
    }
}