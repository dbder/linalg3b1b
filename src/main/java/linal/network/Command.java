package linal.network;

import java.util.function.Consumer;

public record Command(Consumer<Object> consumer, Object object) {

}
