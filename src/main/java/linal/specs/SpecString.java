package linal.specs;

public record SpecString(
        String key,
        Type type,
        String initial,
        Integer minLength,
        Integer maxLength
) implements ISpec {

    public SpecString {
        type = Type.STRING;
        if (minLength == null) minLength = 2;
        if (maxLength == null) maxLength = 2;
        if (initial == null) initial = "Dummy";
    }

    public SpecString(String key, String initial) {
        this(key, null,  initial, null, null);
    }

    public SpecString(String key) {
        this(key, null, null, null, null);
    }

    public SpecString(String key, int minLength, int maxLength) {
        this(key, null, null, minLength, maxLength);
    }
    public SpecString(String key, String initial, int minLength, int maxLength) {
        this(key, null, initial, minLength, maxLength);
    }

    public static void main(String[] args) {
        var t = new SpecString("mykey");
        System.out.println(t);
    }
}
