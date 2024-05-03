package lu.itrust.monarc;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The MonarcReferentials class represents a set of referential labels associated with a UUID.
 */
public class MonarcReferentials {

    private String uuid;
    private String label1;
    private String label2;
    private String label3;
    private String label4;

    /**
     * Constructs a new MonarcReferentials object with the specified UUID and labels.
     *
     * @param uuid   The UUID associated with the referentials.
     * @param label1 The first label.
     * @param label2 The second label.
     * @param label3 The third label.
     * @param label4 The fourth label.
     */
    public MonarcReferentials(String uuid, String label1, String label2, String label3, String label4) {
        this.uuid = uuid;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
    }

    /**
     * Gets the UUID associated with the referentials.
     *
     * @return The UUID.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID associated with the referentials.
     *
     * @param uuid The UUID to set.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets the first label.
     *
     * @return The first label.
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Sets the first label.
     *
     * @param label1 The first label to set.
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    /**
     * Gets the second label.
     *
     * @return The second label.
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Sets the second label.
     *
     * @param label2 The second label to set.
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    /**
     * Gets the third label.
     *
     * @return The third label.
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Sets the third label.
     *
     * @param label3 The third label to set.
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    /**
     * Gets the fourth label.
     *
     * @return The fourth label.
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Sets the fourth label.
     *
     * @param label4 The fourth label to set.
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    /**
     * Checks if the given name matches any of the labels.
     *
     * @param name The name to check.
     * @return true if the name matches any of the labels, false otherwise.
     */
    @JsonIgnore
    public boolean isNameMatch(String name) {
        final String clsName = name == null ? "" : name.trim();
        if (clsName.isEmpty())
            return false;
        if (clsName.equalsIgnoreCase(label1))
            return true;
        else if (clsName.equalsIgnoreCase(label2))
            return true;
        else if (clsName.equalsIgnoreCase(label3))
            return true;
        else
            return clsName.equalsIgnoreCase(label4);
    }

}
