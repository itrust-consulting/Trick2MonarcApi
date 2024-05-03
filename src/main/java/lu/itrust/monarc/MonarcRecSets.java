package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Placeholder class for the recSets field on the JSON file.
 * This class represents a set of recommendations in the MONARC system.
 */
public class MonarcRecSets {
    private String uuid;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private Set<String> parentInstanceId;
    
    /**
     * Constructs a new MonarcRecSets object with the specified UUID and labels.
     * @param uuid the UUID of the recSets object
     * @param label1 the first label
     * @param label2 the second label
     * @param label3 the third label
     * @param label4 the fourth label
     */
    public MonarcRecSets(String uuid, String label1, String label2, String label3, String label4) {
        this.uuid = uuid;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.parentInstanceId = new HashSet<>();
    }

    /**
     * Adds a parent instance ID to the set of parent instance IDs.
     * @param parentInstance the parent instance ID to add
     */
    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }

    /**
     * Returns the collection of parent instance IDs.
     * @return the collection of parent instance IDs
     */
    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }
    
    /**
     * Returns the UUID of the recSets object.
     * @return the UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID of the recSets object.
     * @param uuid the UUID to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the first label.
     * @return the first label
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Sets the first label.
     * @param label1 the first label to set
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    /**
     * Returns the second label.
     * @return the second label
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Sets the second label.
     * @param label2 the second label to set
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    /**
     * Returns the third label.
     * @return the third label
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Sets the third label.
     * @param label3 the third label to set
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    /**
     * Returns the fourth label.
     * @return the fourth label
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Sets the fourth label.
     * @param label4 the fourth label to set
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    /**
     * Checks if the given name matches any of the labels.
     * @param name the name to check
     * @return true if the name matches any of the labels, false otherwise
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
