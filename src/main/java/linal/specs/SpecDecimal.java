package linal.specs;

public record SpecDecimal(
        String key,
        ISpec.Type type,
        Float sliderMin,
        Float sliderMax,
        float initial
) implements ISpec {

    public SpecDecimal {
        type = Type.DECIMAL;
        if (sliderMin == null) sliderMin = -1000f;
        if (sliderMax == null) sliderMax = 1000f;
    }

    public SpecDecimal(String key, float initial) {
        this(key, null, null, null, initial);
    }

    public SpecDecimal(String key) {
        this(key, null, null, null, 0);
    }
    public SpecDecimal(String key, float sliderMin, float sliderMax, float initial) {
        this(key, null, sliderMin, sliderMax, 0);
    }

    public static void main(String[] args) {
        var t = new SpecDecimal("mykey");
        System.out.println(t);
    }
}
