package Singleton;

public enum EnumSingleton {
    INSTANCE(1, "instance");

    private final int number;

    private final String text;

    EnumSingleton(int value, String text) {
        this.number = value;
        this.text = text;
    }

    @Override
    public String toString() {
        return super.toString() + "hhh";
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }
}
