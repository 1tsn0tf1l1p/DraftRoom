package raf.draft.dsw.model.structures;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.observer.IPublisher;
import raf.draft.dsw.model.observer.ISubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Setter
@Getter
public class Room extends DraftNodeComposite implements IPublisher {

    private List<ISubscriber> subscribers;
    private int width;
    private int height;
    private int x,y;
    public Room(String ime, DraftNodeComposite parent) {
        super(ime, parent);
        subscribers = new CopyOnWriteArrayList<>();
        this.width = 0;
        this.height = 0;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if (subscribers.contains(subscriber)) {
            return;
        }
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

    }

    @Override
    public <T> void notifySubscribers(T t) {
        subscribers.forEach(e -> e.update(t));
    }

    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        notifySubscribers(this);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
