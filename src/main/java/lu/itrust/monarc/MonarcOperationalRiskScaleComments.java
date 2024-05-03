package lu.itrust.monarc;

/**
 * Represents a comment for an operational risk scale in the Monarc system.
 */
public class MonarcOperationalRiskScaleComments {
    private Integer id;
    private Integer scaleIndex;
    private Integer scaleValue;
    private boolean isHidden;
    private String commentTranslationKey;
    private MonarcTranslation translation;
    
    /**
     * Constructs a new MonarcOperationalRiskScaleComments object.
     * 
     * @param id                      the ID of the comment
     * @param scaleIndex              the index of the operational risk scale
     * @param scaleValue              the value of the operational risk scale
     * @param isHidden                indicates if the comment is hidden
     * @param commentTranslationKey   the translation key for the comment
     * @param translation             the translation object for the comment
     */
    public MonarcOperationalRiskScaleComments(Integer id, Integer scaleIndex, Integer scaleValue, boolean isHidden,
            String commentTranslationKey, MonarcTranslation translation) {
        this.id = id;
        this.scaleIndex = scaleIndex;
        this.scaleValue = scaleValue;
        this.isHidden = isHidden;
        this.commentTranslationKey = commentTranslationKey;
        this.translation = translation;
    }

    /**
     * Gets the ID of the comment.
     * 
     * @return the ID of the comment
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the comment.
     * 
     * @param id the ID of the comment
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the index of the operational risk scale.
     * 
     * @return the index of the operational risk scale
     */
    public Integer getScaleIndex() {
        return scaleIndex;
    }

    /**
     * Sets the index of the operational risk scale.
     * 
     * @param scaleIndex the index of the operational risk scale
     */
    public void setScaleIndex(Integer scaleIndex) {
        this.scaleIndex = scaleIndex;
    }

    /**
     * Gets the value of the operational risk scale.
     * 
     * @return the value of the operational risk scale
     */
    public Integer getScaleValue() {
        return scaleValue;
    }

    /**
     * Sets the value of the operational risk scale.
     * 
     * @param scaleValue the value of the operational risk scale
     */
    public void setScaleValue(Integer scaleValue) {
        this.scaleValue = scaleValue;
    }

    /**
     * Checks if the comment is hidden.
     * 
     * @return true if the comment is hidden, false otherwise
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Sets the hidden status of the comment.
     * 
     * @param isHidden true to hide the comment, false otherwise
     */
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * Gets the translation key for the comment.
     * 
     * @return the translation key for the comment
     */
    public String getCommentTranslationKey() {
        return commentTranslationKey;
    }

    /**
     * Sets the translation key for the comment.
     * 
     * @param commentTranslationKey the translation key for the comment
     */
    public void setCommentTranslationKey(String commentTranslationKey) {
        this.commentTranslationKey = commentTranslationKey;
    }

    /**
     * Gets the translation object for the comment.
     * 
     * @return the translation object for the comment
     */
    public MonarcTranslation getTranslation() {
        return translation;
    }

    /**
     * Sets the translation object for the comment.
     * 
     * @param translation the translation object for the comment
     */
    public void setTranslation(MonarcTranslation translation) {
        this.translation = translation;
    }

}
