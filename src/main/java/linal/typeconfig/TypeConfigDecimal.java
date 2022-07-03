package linal.typeconfig;

public record TypeConfigDecimal(
        String key,
        ConfigType.Type type,
        Float sliderMin,
        Float sliderMax,
        float initial
) implements ConfigType {

    public TypeConfigDecimal {
        type = Type.DECIMAL;
        if (sliderMin == null) sliderMin = -1000f;
        if (sliderMax == null) sliderMax = 1000f;
    }

    public TypeConfigDecimal(String key, float initial) {
        this(key, null, null, null, initial);
    }

    public TypeConfigDecimal(String key) {
        this(key, null, null, null, 0);
    }
    public TypeConfigDecimal(String key, float sliderMin, float sliderMax, float initial) {
        this(key, null, sliderMin, sliderMax, 0);
    }

    public static void main(String[] args) {
        var t = new TypeConfigDecimal("mykey");
        System.out.println(t);
    }
}
