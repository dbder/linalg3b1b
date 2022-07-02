package linal.network;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class CommandHandlerManager {


    /**
     * Here commands are stored to be picked up with String keys. ( e.g. camera.x )
     */
    public Map<String, Consumer<Object>> consumerMap = new HashMap<>();

    /**
     * Buffer for commands to be consumed by main applciaiton.
     * Filled in the Restcontroller
     */
    private ConcurrentLinkedQueue<Command> queue = new ConcurrentLinkedQueue();

    public void put(String string, Consumer<Object> consumer) {
        consumerMap.put(string, consumer);
    }

    public void queueCommand(Object o, Consumer<Object> consumer) {
        queue.add(new Command(consumer, o));
        System.out.println(queue.size());
    }

    /**
     * triggered before draw and other updates in main thread
     */
    public void update() {
        while (!queue.isEmpty()) {
            Command poll = queue.poll();
            poll.consumer().accept(poll.object());
        }
    }

}
