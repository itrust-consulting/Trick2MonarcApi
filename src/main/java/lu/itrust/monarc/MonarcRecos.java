package lu.itrust.monarc;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Placeholder class for the recos field in the JSON
 * What is it?
 */
public class MonarcRecos extends MonarcRecs{

    private Set<String> relatedRisk = new HashSet<>();
    private String commentAfter = "";

    public MonarcRecos(String uuid, String recommandationSet, String code, String description, int importance,
            String comment, int status, String responsable, MonarcDuedate duedate, int counterTreated, String commentAfter, String ownerRisk) {
        super(uuid, recommandationSet, code, description, importance, comment, status, responsable, duedate, counterTreated);
        this.commentAfter = commentAfter;
        relatedRisk.add(ownerRisk);
    }

    public MonarcRecos(MonarcRecs baseRec)
    {
        super(baseRec.getUuid(), baseRec.getRecommandationSet(), baseRec.getCode(), baseRec.getDescription(),
                 baseRec.getImportance(), baseRec.getComment(), baseRec.getStatus(), baseRec.getResponsable(), 
                 baseRec.getDuedate(), baseRec.getCounterTreated());
    }
    
    public void addRelatedRisk(String relatedRiskId)
    {
        relatedRisk.add(relatedRiskId);
    }

    @JsonIgnore
    public Set<String> getRelatedRisks()
    {
        return relatedRisk;
    }

    public String getCommentAfter() {
        return commentAfter;
    }

    public void setCommentAfter(String commentAfter) {
        this.commentAfter = commentAfter;
    }
}
