package lu.itrust.monarc;

import java.util.List;

/**
 * Represents the operational risk scales in the Monarc system.
 */
public class MonarcOperationalRiskScales {
    private Integer id;
    private Integer min;
    private Integer max;
    private Integer type;
    private List<MonarcOperationalRiskScaleTypes> operationalRiskScaleTypes;
    private List<MonarcOperationalRiskScaleComments> operationalRiskScaleComments;
    
    /**
     * Constructs a new MonarcOperationalRiskScales object with the specified parameters.
     * 
     * @param id the ID of the operational risk scale
     * @param min the minimum value of the operational risk scale
     * @param max the maximum value of the operational risk scale
     * @param type the type of the operational risk scale
     * @param operationalRiskScaleTypes the list of operational risk scale types associated with this scale
     * @param operationalRiskScaleComments the list of operational risk scale comments associated with this scale
     */
    public MonarcOperationalRiskScales(Integer id, Integer min, Integer max, Integer type,
            List<MonarcOperationalRiskScaleTypes> operationalRiskScaleTypes,
            List<MonarcOperationalRiskScaleComments> operationalRiskScaleComments) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.type = type;
        this.operationalRiskScaleTypes = operationalRiskScaleTypes;
        this.operationalRiskScaleComments = operationalRiskScaleComments;
    }

    /**
     * Returns the ID of the operational risk scale.
     * 
     * @return the ID of the operational risk scale
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the operational risk scale.
     * 
     * @param id the ID of the operational risk scale
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the minimum value of the operational risk scale.
     * 
     * @return the minimum value of the operational risk scale
     */
    public Integer getMin() {
        return min;
    }

    /**
     * Sets the minimum value of the operational risk scale.
     * 
     * @param min the minimum value of the operational risk scale
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     * Returns the maximum value of the operational risk scale.
     * 
     * @return the maximum value of the operational risk scale
     */
    public Integer getMax() {
        return max;
    }

    /**
     * Sets the maximum value of the operational risk scale.
     * 
     * @param max the maximum value of the operational risk scale
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * Returns the type of the operational risk scale.
     * 
     * @return the type of the operational risk scale
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets the type of the operational risk scale.
     * 
     * @param type the type of the operational risk scale
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Returns the list of operational risk scale types associated with this scale.
     * 
     * @return the list of operational risk scale types associated with this scale
     */
    public List<MonarcOperationalRiskScaleTypes> getOperationalRiskScaleTypes() {
        return operationalRiskScaleTypes;
    }

    /**
     * Sets the list of operational risk scale types associated with this scale.
     * 
     * @param operationalRiskScaleTypes the list of operational risk scale types associated with this scale
     */
    public void setOperationalRiskScaleTypes(List<MonarcOperationalRiskScaleTypes> operationalRiskScaleTypes) {
        this.operationalRiskScaleTypes = operationalRiskScaleTypes;
    }

    /**
     * Returns the list of operational risk scale comments associated with this scale.
     * 
     * @return the list of operational risk scale comments associated with this scale
     */
    public List<MonarcOperationalRiskScaleComments> getOperationalRiskScaleComments() {
        return operationalRiskScaleComments;
    }

    /**
     * Sets the list of operational risk scale comments associated with this scale.
     * 
     * @param operationalRiskScaleComments the list of operational risk scale comments associated with this scale
     */
    public void setOperationalRiskScaleComments(List<MonarcOperationalRiskScaleComments> operationalRiskScaleComments) {
        this.operationalRiskScaleComments = operationalRiskScaleComments;
    }
    
}
