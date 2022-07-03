package linal.typeconfig;

public record TypeConfigString(
        String key,
        Type type,
        String initial,
        Integer minLength,
        Integer maxLength
) implements ConfigType {

    public TypeConfigString {
        type = Type.STRING;
        if (minLength == null) minLength = 2;
        if (maxLength == null) maxLength = 2;
        if (initial == null) initial = "Dummy";
    }

    public TypeConfigString(String key, String initial) {
        this(key, null,  initial, null, null);
    }

    public TypeConfigString(String key) {
        this(key, null, null, null, null);
    }

    public TypeConfigString(String key, int minLength, int maxLength) {
        this(key, null, null, minLength, maxLength);
    }
    public TypeConfigString(String key, String initial, int minLength, int maxLength) {
        this(key, null, initial, minLength, maxLength);
    }

    public static void main(String[] args) {
        var t = new TypeConfigString("mykey");
        System.out.println(t);
    }
}
