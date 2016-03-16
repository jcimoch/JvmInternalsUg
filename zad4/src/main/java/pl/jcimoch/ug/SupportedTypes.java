package pl.jcimoch.ug;

/**
 * Created by Jarek on 13.03.2016.
 */
public enum SupportedTypes {
    Enum("Enum"),
    prInteger("int"),
    Integer("Integer"),
    prDouble("double"),
    Double("Double"),
    Float("Float"),
    prFloat("float"),
    Byte("Byte"),
    String("String"),
    Character("Character"),
    Long("Long"),
    prLong("long"),
    Short("Short"),
    prShort("short"),
    Boolean("Boolean"),
    prBoolean("boolean");

    private final String supportedType;

    SupportedTypes(String type) {
        this.supportedType = type;
    }

    @Override
    public String toString() {
        return supportedType;
    }

    public static <T> Boolean isSupported(T o) {
        for (SupportedTypes type : SupportedTypes.values()) {
            if (type.toString().equals(o)) {
                return true;
            }
        }

        return false;
    }

}
