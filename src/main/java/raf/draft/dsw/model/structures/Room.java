package raf.draft.dsw.model.structures;

import lombok.Getter;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.nodes.DraftNode;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class Room extends DraftNode implements IPublisher {

    private List<ISubscriber> subscribers;

    public Room(String ime, DraftNode parent) {
        super(ime, parent);
        subscribers = new CopyOnWriteArrayList<>();
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscribers.contains(subscriber)) {
            return;
        }
        else {
            subscribers.add(subscriber);
        }
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

    }

    @Override
    public <T> void notifySubscribers(T t) {
        System.out.println(subscribers);
        subscribers.forEach(e -> e.update(t));
    }

    @Override
    public void setIme(String ime) {
        super.setIme(ime);
        notifySubscribers(this);
    }
}
