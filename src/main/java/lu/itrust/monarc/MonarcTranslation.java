package lu.itrust.monarc;

/**
 * The MonarcTranslation class represents a translation entry in the Monarc system.
 * It stores the key, language, and value of the translation.
 */
public class MonarcTranslation {

    private String key;
    private String lang;
    private String value;
    
    /**
     * Constructs a new MonarcTranslation object with the specified key, language, and value.
     * 
     * @param key   the translation key
     * @param lang  the language of the translation
     * @param value the translated value
     */
    public MonarcTranslation(String key, String lang, String value) {
        this.key = key;
        this.lang = lang;
        this.value = value;
    }

    /**
     * Returns the translation key.
     * 
     * @return the translation key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the translation key.
     * 
     * @param key the translation key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the language of the translation.
     * 
     * @return the language of the translation
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the language of the translation.
     * 
     * @param lang the language of the translation to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Returns the translated value.
     * 
     * @return the translated value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the translated value.
     * 
     * @param value the translated value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
