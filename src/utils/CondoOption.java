package utils;

public class CondoOption {
    private int id;
    private String name;

    public CondoOption(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CondoOption(String name) {
        this.id = 0;           // Default id
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;  // This will be used in JComboBox or similar components
    }
}
