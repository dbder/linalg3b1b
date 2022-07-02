package linal.network;

import java.util.function.Consumer;

public record Command<T>(Consumer<? super T> consumer, T object) implements Runnable {
    @Override public void run() { consumer.accept(object); }
}