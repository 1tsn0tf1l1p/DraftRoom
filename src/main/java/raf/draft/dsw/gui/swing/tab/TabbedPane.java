package raf.draft.dsw.gui.swing.tab;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.controller.tab.TabCloseButton;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JTabbedPane implements ISubscriber {

    private Project project;

    public TabbedPane() {
        this.setBackground(Color.WHITE);
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void setProject(Project project) {
        if(project == null) {
            for (int i = 0; i < this.getTabCount(); i++) {
                System.out.println("Removing tab: " + this.getTitleAt(i));
            }
            this.removeAll();
            System.out.println();
            System.out.println();
            for (int i = 0; i < this.getTabCount(); i++) {
                System.out.println("Removing tab: " + this.getTitleAt(i));
            }
            this.project = null;
            return;
        }
        System.out.println("Pozvao se setproject");
        this.project = project;
        System.out.println(project.toString());
        this.removeAll();
        ApplicationFramework.getInstance().getTree().addSubscriber(this);
        project.getChildren().forEach(e -> {
            if(e instanceof Room) {
                ((Room) e).addSubscriber(this);
            }
            else if (e instanceof Building) {
                ((Building) e).getChildren().forEach(r -> {
                    ((Room) r).addSubscriber(this);
                });
            }
        });
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new TabCloseButton(component, title));
        if(component instanceof TabView) {
            Color roomColor = ((TabView)component).getColor();
            this.setBackgroundAt(count, roomColor);
        }
        else {
            this.setBackgroundAt(count, Color.WHITE);
        }
        component.setBackground(Color.WHITE);
    }

    @Override
    public <T> void update(T t) {
        System.out.println("Usao sam u update");
        refreshTabs();
    }

    private void refreshTabs() {
        if(project == null) {
            return;
        }

        this.removeAll();
        project.getChildren().forEach(child -> {
            if(child instanceof Room) {
                ((Room) child).addSubscriber(this);
                TabView tab = new TabView((Room) child);
                this.addTab(child.getIme(), tab);
            }
            else if (child instanceof Building) {
                ((Building) child).getChildren().forEach(buildingChild -> {
                    ((Room)buildingChild).addSubscriber(this);
                    TabView tabView = new TabView((Room) buildingChild);
                    this.addTab(buildingChild.getIme(), tabView);
                });
            }
        });
    }
}