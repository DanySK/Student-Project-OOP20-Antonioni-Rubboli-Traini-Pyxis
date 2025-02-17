package it.unibo.pyxis.model.level.iterator;

public enum Config {

    /**
     * Represents the extension (.yaml, .yml) of a level configuration file.
     */
    LEVEL_FILE_EXTENSION(".yaml"),
    /**
     * Represents the prefix of a generic level configuration file.
     */
    LEVEL_FILE_PREFIX("level"),
    /**
     * Represents the name of the source folder of level configurations files.
     */
    LEVEL_RESOURCE_FOLDER("config");

    private final String value;

    Config(final String inputValue) {
        this.value = inputValue;
    }

    /**
     * Returns the value of a configuration variable.
     *
     * @return A String representing the value of a configuration variable.
     */
    public String getValue() {
        return this.value;
    }
}
