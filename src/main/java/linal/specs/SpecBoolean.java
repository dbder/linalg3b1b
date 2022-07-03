package linal.specs;

public record SpecBoolean(
        String key,
        Type type,
        Boolean initial
) implements ISpec {

    public SpecBoolean {
        type = Type.BOOLEAN;
        if (initial == null) initial = false;
    }

    public SpecBoolean(String key, Boolean initial) {
        this(key, null, initial);
    }

    public SpecBoolean(String key) {
        this(key, null, null);
    }

    public static void main(String[] args) {
        var t = new SpecBoolean("mykey");
        System.out.println(t);
    }
}
