package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a Monarc Vulnerabilities object.
 */
public class MonarcVulnerabilities {
    private String uuid;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private int status;
    private int mode;
    private String code;
    private Set<String> parentInstanceId;

    /**
     * Constructs a MonarcVulnerabilities object with the specified parameters.
     *
     * @param uuid          the UUID of the vulnerability
     * @param label1        the first label of the vulnerability
     * @param label2        the second label of the vulnerability
     * @param label3        the third label of the vulnerability
     * @param label4        the fourth label of the vulnerability
     * @param description1  the first description of the vulnerability
     * @param description2  the second description of the vulnerability
     * @param description3  the third description of the vulnerability
     * @param description4  the fourth description of the vulnerability
     * @param status        the status of the vulnerability
     * @param mode          the mode of the vulnerability
     * @param code          the code of the vulnerability
     */
    public MonarcVulnerabilities(String uuid,
                                 String label1, String label2, String label3, String label4,
                                 String description1, String description2, String description3, String description4,
                                 int status, int mode, String code) {
        this.uuid = uuid;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.description4 = description4;
        this.status = status;
        this.mode = mode;
        this.code = code;
        parentInstanceId = new HashSet<>();
    }

    /**
     * Returns the UUID of the vulnerability.
     *
     * @return the UUID of the vulnerability
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Returns the first label of the vulnerability.
     *
     * @return the first label of the vulnerability
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Returns the second label of the vulnerability.
     *
     * @return the second label of the vulnerability
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Returns the third label of the vulnerability.
     *
     * @return the third label of the vulnerability
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Returns the fourth label of the vulnerability.
     *
     * @return the fourth label of the vulnerability
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Returns the first description of the vulnerability.
     *
     * @return the first description of the vulnerability
     */
    public String getDescription1() {
        return description1;
    }

    /**
     * Returns the second description of the vulnerability.
     *
     * @return the second description of the vulnerability
     */
    public String getDescription2() {
        return description2;
    }

    /**
     * Returns the third description of the vulnerability.
     *
     * @return the third description of the vulnerability
     */
    public String getDescription3() {
        return description3;
    }

    /**
     * Returns the fourth description of the vulnerability.
     *
     * @return the fourth description of the vulnerability
     */
    public String getDescription4() {
        return description4;
    }

    /**
     * Returns the status of the vulnerability.
     *
     * @return the status of the vulnerability
     */
    public int getStatus() {
        return status;
    }

    /**
     * Returns the mode of the vulnerability.
     *
     * @return the mode of the vulnerability
     */
    public int getMode() {
        return mode;
    }

    /**
     * Returns the code of the vulnerability.
     *
     * @return the code of the vulnerability
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the UUID of the vulnerability.
     *
     * @param uuid the UUID of the vulnerability
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Sets the first label of the vulnerability.
     *
     * @param label1 the first label of the vulnerability
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    /**
     * Sets the second label of the vulnerability.
     *
     * @param label2 the second label of the vulnerability
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    /**
     * Sets the third label of the vulnerability.
     *
     * @param label3 the third label of the vulnerability
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    /**
     * Sets the fourth label of the vulnerability.
     *
     * @param label4 the fourth label of the vulnerability
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    /**
     * Sets the first description of the vulnerability.
     *
     * @param description1 the first description of the vulnerability
     */
    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    /**
     * Sets the second description of the vulnerability.
     *
     * @param description2 the second description of the vulnerability
     */
    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    /**
     * Sets the third description of the vulnerability.
     *
     * @param description3 the third description of the vulnerability
     */
    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    /**
     * Sets the fourth description of the vulnerability.
     *
     * @param description4 the fourth description of the vulnerability
     */
    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    /**
     * Sets the status of the vulnerability.
     *
     * @param status the status of the vulnerability
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Sets the mode of the vulnerability.
     *
     * @param mode the mode of the vulnerability
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * Sets the code of the vulnerability.
     *
     * @param code the code of the vulnerability
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets the parent instance IDs of the vulnerability.
     *
     * @param parentInstanceId the parent instance IDs of the vulnerability
     */
    public void setParentInstanceId(Set<String> parentInstanceId) {
        this.parentInstanceId = parentInstanceId;
    }

    /**
     * Adds a parent instance ID to the vulnerability.
     *
     * @param parentInstance the parent instance ID to add
     */
    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }

    /**
     * Returns the parent instance IDs of the vulnerability.
     *
     * @return the parent instance IDs of the vulnerability
     */
    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }

    /**
     * Returns the label at the specified index.
     *
     * @param i the index of the label (1-4)
     * @return the label at the specified index, or null if the index is invalid
     */
    public String getLabel(int i) {
        switch (i) {
            case 1:
                return label1;
            case 2:
                return label2;
            case 3:
                return label3;
            case 4:
                return label4;
            default:
                return null;
        }
    }

    /**
     * Returns the description at the specified index.
     *
     * @param i the index of the description (1-4)
     * @return the description at the specified index, or null if the index is invalid
     */
    public String getDescription(int i) {
        switch (i) {
            case 1:
                return description1;
            case 2:
                return description2;
            case 3:
                return description3;
            case 4:
                return description4;
            default:
                return null;
        }
    }
}
