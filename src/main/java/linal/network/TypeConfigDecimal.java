package linal.network;

public record TypeConfigDecimal(
        String key,
        Type type,
        Float sliderMin,
        Float sliderMax,
        Float initial
) implements ConfigType {

    public TypeConfigDecimal {
        type = Type.DECIMAL;
    }

    public TypeConfigDecimal(String key, Float initial) {
        this(key, null, -1000f, 1000f, initial);
    }

    public TypeConfigDecimal(String key) {
        this(key, null, -1000f, 1000f, 0f);
    }

    public static void main(String[] args) {
        var t = new TypeConfigDecimal("mykey");
        System.out.println(t);
    }
}
