package raf.draft.dsw.view.tab;

import raf.draft.dsw.controller.tab.TabCloseButton;
import raf.draft.dsw.model.core.ApplicationFramework;
import raf.draft.dsw.model.patterns.observer.IPublisher;
import raf.draft.dsw.model.patterns.observer.ISubscriber;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TabbedPane extends JTabbedPane implements ISubscriber, IPublisher {

    List<ISubscriber> subscribers;
    private Project project;

    public TabbedPane() {
        subscribers = new CopyOnWriteArrayList<>();
        this.setBackground(Color.WHITE);
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setUpChangeListener();
    }

    private void setUpChangeListener() {
        this.addChangeListener(e -> {
            Component selectedTab = getSelectedTab();
            if (selectedTab != null) {
                notifySubscribers(selectedTab);
            }
        });
    }

    public void setProject(Project project) {
        if (project == null) {
            this.removeAll();
            this.project = null;
            return;
        }
        this.project = project;
        this.removeAll();
        ApplicationFramework.getInstance().getTree().addSubscriber(this);
        project.getChildren().forEach(e -> {
            if (e instanceof Room) {
                ((IPublisher) e).addSubscriber(this);
            } else if (e instanceof Building) {
                ((Building) e).getChildren().forEach(r -> {
                    ((IPublisher) r).addSubscriber(this);
                });
            }
        });
    }

    public Component getSelectedTab() {
        int selectedIndex = this.getSelectedIndex();
        if (selectedIndex != -1) {
            return this.getComponentAt(selectedIndex);
        }
        return null;
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new TabCloseButton(component, title));
        if (component instanceof TabView) {
            Color roomColor = ((TabView) component).getColor();
            this.setBackgroundAt(count, roomColor);
        } else {
            this.setBackgroundAt(count, Color.WHITE);
        }
        component.setBackground(Color.WHITE);
    }

    @Override
    public <T> void update(T t) {
        refreshTabs();
    }

    private void refreshTabs() {
        if (project == null) {
            return;
        }

        this.removeAll();
        project.getChildren().forEach(child -> {
            if (child instanceof Room) {
                ((IPublisher) child).addSubscriber(this);
                TabView tab = new TabView((Room) child);
                this.addTab(child.getIme(), tab);
            } else if (child instanceof Building) {
                ((Building) child).getChildren().forEach(buildingChild -> {
                    ((IPublisher) buildingChild).addSubscriber(this);
                    TabView tabView = new TabView((Room) buildingChild);
                    this.addTab(buildingChild.getIme(), tabView);
                });
            }
        });
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public <T> void notifySubscribers(T t) {
        subscribers.forEach(e -> e.update(t));
    }
}