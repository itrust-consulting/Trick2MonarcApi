package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Placeholder class for the recs field in the JSON file.
 * Represents a recommendation in the MONARC system.
 */
public class MonarcRecs {
    private String uuid;
    private String recommandationSet;
    private String code;
    private String description;
    private int importance;
    private String comment;
    private int status;
    private String responsable;
    private MonarcDuedate duedate;
    private int counterTreated;
    private Set<String> parentInstanceId;

    /**
     * Constructs a new MonarcRecs object with the specified parameters.
     * 
     * @param uuid              The UUID of the recommendation.
     * @param recommandationSet The recommendation set.
     * @param code              The code of the recommendation.
     * @param description       The description of the recommendation.
     * @param importance        The importance of the recommendation.
     * @param comment           The comment for the recommendation.
     * @param status            The status of the recommendation.
     * @param responsable       The responsible person for the recommendation.
     * @param duedate           The due date of the recommendation.
     * @param counterTreated    The counter for treated instances of the recommendation.
     */
    public MonarcRecs(String uuid, String recommandationSet, String code, String description, int importance,
            String comment, int status, String responsable, MonarcDuedate duedate, int counterTreated) {
        this.uuid = uuid;
        this.recommandationSet = recommandationSet;
        this.code = code;
        this.description = description;
        this.importance = importance;
        this.comment = comment;
        this.status = status;
        this.responsable = responsable;
        this.duedate = duedate;
        this.counterTreated = counterTreated;
        this.parentInstanceId = new HashSet<>();
    }

    /**
     * Constructs a new MonarcRecs object based on an existing MonarcRecs object.
     * 
     * @param baseRecs The base MonarcRecs object to copy from.
     */
    public MonarcRecs(MonarcRecs baseRecs) {
        this(baseRecs.uuid, baseRecs.recommandationSet, baseRecs.code, baseRecs.description, baseRecs.importance,
                baseRecs.comment, baseRecs.status, baseRecs.responsable, baseRecs.duedate, baseRecs.counterTreated);
    }

    /**
     * Adds a parent instance to the recommendation.
     * 
     * @param parentInstance The parent instance to add.
     */
    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }

    /**
     * Returns the collection of parent instance IDs associated with the recommendation.
     * 
     * @return The collection of parent instance IDs.
     */
    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }

    /**
     * Returns the UUID of the recommendation.
     * 
     * @return The UUID of the recommendation.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID of the recommendation.
     * 
     * @param uuid The UUID of the recommendation.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the recommendation set.
     * 
     * @return The recommendation set.
     */
    public String getRecommandationSet() {
        return recommandationSet;
    }

    /**
     * Sets the recommendation set.
     * 
     * @param recommandationSet The recommendation set.
     */
    public void setRecommandationSet(String recommandationSet) {
        this.recommandationSet = recommandationSet;
    }

    /**
     * Returns the code of the recommendation.
     * 
     * @return The code of the recommendation.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the recommendation.
     * 
     * @param code The code of the recommendation.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the description of the recommendation.
     * 
     * @return The description of the recommendation.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the recommendation.
     * 
     * @param description The description of the recommendation.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the importance of the recommendation.
     * 
     * @return The importance of the recommendation.
     */
    public int getImportance() {
        return importance;
    }

    /**
     * Sets the importance of the recommendation.
     * 
     * @param importance The importance of the recommendation.
     */
    public void setImportance(int importance) {
        this.importance = importance;
    }

    /**
     * Returns the comment for the recommendation.
     * 
     * @return The comment for the recommendation.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment for the recommendation.
     * 
     * @param comment The comment for the recommendation.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the status of the recommendation.
     * 
     * @return The status of the recommendation.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the recommendation.
     * 
     * @param status The status of the recommendation.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns the responsible person for the recommendation.
     * 
     * @return The responsible person for the recommendation.
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * Sets the responsible person for the recommendation.
     * 
     * @param responsable The responsible person for the recommendation.
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * Returns the due date of the recommendation.
     * 
     * @return The due date of the recommendation.
     */
    public MonarcDuedate getDuedate() {
        return duedate;
    }

    /**
     * Sets the due date of the recommendation.
     * 
     * @param duedate The due date of the recommendation.
     */
    public void setDuedate(MonarcDuedate duedate) {
        this.duedate = duedate;
    }

    /**
     * Returns the counter for treated instances of the recommendation.
     * 
     * @return The counter for treated instances of the recommendation.
     */
    public int getCounterTreated() {
        return counterTreated;
    }

    /**
     * Sets the counter for treated instances of the recommendation.
     * 
     * @param counterTreated The counter for treated instances of the recommendation.
     */
    public void setCounterTreated(int counterTreated) {
        this.counterTreated = counterTreated;
    }

}
