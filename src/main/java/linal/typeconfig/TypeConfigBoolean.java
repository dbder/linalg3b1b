package linal.typeconfig;

public record TypeConfigBoolean(
        String key,
        Type type,
        Boolean initial
) implements ConfigType {

    public TypeConfigBoolean {
        type = Type.BOOLEAN;
        if (initial == null) initial = false;
    }

    public TypeConfigBoolean(String key, Boolean initial) {
        this(key, null, initial);
    }

    public TypeConfigBoolean(String key) {
        this(key, null, null);
    }

    public static void main(String[] args) {
        var t = new TypeConfigBoolean("mykey");
        System.out.println(t);
    }
}
