package lu.itrust.monarc;

import java.util.List;

/**
 * Represents the types of operational risk scales in the Monarc system.
 */
public class MonarcOperationalRiskScaleTypes {
    private Integer id;
    private boolean isHidden;
    private String labelTranslationKey;
    private MonarcTranslation translation;
    private List<MonarcOperationalRiskScaleComments> operationalRiskScaleComments;
    
    /**
     * Constructs a new MonarcOperationalRiskScaleTypes object with the specified parameters.
     * 
     * @param id The ID of the operational risk scale type.
     * @param isHidden Indicates whether the operational risk scale type is hidden.
     * @param labelTranslationKey The translation key for the label of the operational risk scale type.
     * @param translation The translation object for the operational risk scale type.
     * @param operationalRiskScaleComments The list of operational risk scale comments associated with the type.
     */
    public MonarcOperationalRiskScaleTypes(Integer id, boolean isHidden, String labelTranslationKey,
            MonarcTranslation translation, List<MonarcOperationalRiskScaleComments> operationalRiskScaleComments) {
        this.id = id;
        this.isHidden = isHidden;
        this.labelTranslationKey = labelTranslationKey;
        this.translation = translation;
        this.operationalRiskScaleComments = operationalRiskScaleComments;
    }

    /**
     * Returns the ID of the operational risk scale type.
     * 
     * @return The ID of the operational risk scale type.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the operational risk scale type.
     * 
     * @param id The ID of the operational risk scale type.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns whether the operational risk scale type is hidden.
     * 
     * @return true if the operational risk scale type is hidden, false otherwise.
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Sets whether the operational risk scale type is hidden.
     * 
     * @param isHidden true if the operational risk scale type is hidden, false otherwise.
     */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * Returns the translation key for the label of the operational risk scale type.
     * 
     * @return The translation key for the label of the operational risk scale type.
     */
    public String getLabelTranslationKey() {
        return labelTranslationKey;
    }

    /**
     * Sets the translation key for the label of the operational risk scale type.
     * 
     * @param labelTranslationKey The translation key for the label of the operational risk scale type.
     */
    public void setLabelTranslationKey(String labelTranslationKey) {
        this.labelTranslationKey = labelTranslationKey;
    }

    /**
     * Returns the translation object for the operational risk scale type.
     * 
     * @return The translation object for the operational risk scale type.
     */
    public MonarcTranslation getTranslation() {
        return translation;
    }

    /**
     * Sets the translation object for the operational risk scale type.
     * 
     * @param translation The translation object for the operational risk scale type.
     */
    public void setTranslation(MonarcTranslation translation) {
        this.translation = translation;
    }

    /**
     * Returns the list of operational risk scale comments associated with the type.
     * 
     * @return The list of operational risk scale comments associated with the type.
     */
    public List<MonarcOperationalRiskScaleComments> getOperationalRiskScaleComments() {
        return operationalRiskScaleComments;
    }

    /**
     * Sets the list of operational risk scale comments associated with the type.
     * 
     * @param operationalRiskScaleComments The list of operational risk scale comments associated with the type.
     */
    public void setOperationalRiskScaleComments(List<MonarcOperationalRiskScaleComments> operationalRiskScaleComments) {
        this.operationalRiskScaleComments = operationalRiskScaleComments;
    }
    
}
