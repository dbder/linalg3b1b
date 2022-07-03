package linal.network;

import java.util.function.Consumer;

import static java.util.Optional.ofNullable;

@FunctionalInterface
interface CommandFactory<T> {
    /* This returns empty if arg is not of the correct type for the factory,
       and a valid command otherwise
    */
    Command<T> of(T arg);

    static <T> CommandFactory<T> factoryGiven(Consumer<? super T> consumer, Class<T> clz) {
        return arg -> ofNullable(arg)
                .filter(clz::isInstance)
                .map(clz::cast)
                .map(t -> new Command(consumer, t))
                .orElseThrow(() -> new IllegalArgumentException("Wrong type. this is real bad, try again"));
    }
}