package linal.network;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class CommandHandlerManager {


//    public Map<String, CommandFactory<?>> factoryMapping = new HashMap<>();
//
//    <T> void push(String key, Consumer<? super T> consumer, Class<T> clz) {
//        factoryMapping.put(key, CommandFactory.factoryGiven(consumer, clz));
//    }
//
//    <T> Command<T> get(String key, T arg) {
//        return  factoryMapping.get(key);
//    }


    /**
     * Here commands are stored to be picked up with String keys. ( e.g. camera.x )
     */
    public Map<String, Consumer> consumerMap = new HashMap<>();
    private List<String> keys = new ArrayList<>();

    /**
     * Buffer for commands to be consumed by main applciaiton.
     * Filled in the Restcontroller
     */
    private ConcurrentLinkedQueue<Command<?>> queue = new ConcurrentLinkedQueue<>();

    public <T> void put(String string, Consumer<T> consumer) {
        consumerMap.put(string, consumer);
    }

    public void queueCommand(Object o, Consumer<?> consumer) {
        queue.add(new Command(consumer, o));
        System.out.println(queue.size());
    }

    /**
     * triggered before draw and other updates in main thread
     */
    public void update() {
        while (!queue.isEmpty()) {
            Command poll = queue.poll();
            poll.run();
        }
    }

    public List<String> getKeys() {
        return keys;
    }

}
