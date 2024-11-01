package raf.draft.dsw.controller.messagegenerator;

import lombok.Getter;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MessageGenerator implements IPublisher {

    private List<ISubscriber> subscribers;
    public MessageGenerator() {
        subscribers = new ArrayList<>();
    }

    public void createMessage(MessageType messageType, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));

        stringBuilder.append("[").append(messageType).append("]");
        stringBuilder.append("[").append(formattedDate).append("]");
        stringBuilder.append(" ").append(message);


        notifySubscribers(stringBuilder.toString());
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
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(t);
        }
    }

}
