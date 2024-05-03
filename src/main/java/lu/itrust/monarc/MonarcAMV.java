package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a Monarc Asset, Measure, Vulnerability (AMV) entity.
 */
public class MonarcAMV {
    private String uuid;
    private String threat;
    private String asset;
    private String vulnerability;
    private int status;
    private Set<String> parentInstanceId;
    private Set<String> measures;

    /**
     * Constructs a new MonarcAMV object with the specified properties.
     *
     * @param uuid          The UUID of the AMV.
     * @param threat        The threat associated with the AMV.
     * @param asset         The asset associated with the AMV.
     * @param vulnerability The vulnerability associated with the AMV.
     * @param status        The status of the AMV.
     */
    public MonarcAMV(String uuid,
                     String threat,
                     String asset,
                     String vulnerability,
                     int status) {
        this.uuid = uuid;
        this.threat = threat;
        this.asset = asset;
        this.vulnerability = vulnerability;
        this.status = status;
        this.measures = new HashSet<>();
        this.parentInstanceId = new HashSet<>();
    }

    /**
     * Returns the UUID of the AMV.
     *
     * @return The UUID of the AMV.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Returns the threat associated with the AMV.
     *
     * @return The threat associated with the AMV.
     */
    public String getThreat() {
        return threat;
    }

    /**
     * Returns the asset associated with the AMV.
     *
     * @return The asset associated with the AMV.
     */
    public String getAsset() {
        return asset;
    }

    /**
     * Returns the vulnerability associated with the AMV.
     *
     * @return The vulnerability associated with the AMV.
     */
    public String getVulnerability() {
        return vulnerability;
    }

    /**
     * Returns the status of the AMV.
     *
     * @return The status of the AMV.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Adds a parent instance to the AMV.
     *
     * @param parentInstance The parent instance to add.
     */
    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }

    /**
     * Returns the collection of parent instance IDs associated with the AMV.
     *
     * @return The collection of parent instance IDs associated with the AMV.
     */
    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }

    /**
     * Adds a measure to the AMV.
     *
     * @param measureUUID The UUID of the measure to add.
     */
    public synchronized void addMeasure(String measureUUID) {
        if (this.measures == null)
            this.measures = new HashSet<>();
        this.measures.add(measureUUID);
    }

    /**
     * Sets the list of measures associated with the AMV.
     *
     * @param newMeasureList The new list of measures.
     */
    public void setMeasureList(Set<String> newMeasureList) {
        this.measures = newMeasureList;
    }

    /**
     * Returns the set of measures associated with the AMV.
     *
     * @return The set of measures associated with the AMV.
     */
    public Set<String> getMeasures() {
        return measures;
    }
}
