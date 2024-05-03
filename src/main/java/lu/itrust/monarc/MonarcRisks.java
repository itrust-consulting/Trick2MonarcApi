package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a Monarc Risk.
 */
/**
 * Represents a Monarc Risk.
 */
public class MonarcRisks {
    private int id;
    private int specific;
    private int mh;
    private int threatRate;
    private int vulnerabilityRate;
    private int kindOfMeasure;
    private int reductionAmount;
    private String comment;
    private String commentAfter;
    private int riskC;
    private int riskI;
    private int riskD;
    private int cacheMaxRisk;
    private int cacheTargetedRisk;
    private String amv;
    private String threat;
    private String vulnerability;
    private String context;
    private String riskOwner;
    private Set<String> parentInstanceId;

    /**
     * Represents a Monarc Risk.
     */
    public MonarcRisks(int id,
            int specific,
            int mh,
            int threatRate,
            int vulnerabilityRate,
            int kindOfMeasure,
            int reductionAmount,
            String comment,
            String commentAfter,
            int riskC,
            int riskI,
            int riskD,
            int cacheMaxRisk,
            int cacheTargetedRisk,
            String amv,
            String threat,
            String vulnerability,
            String context,
            String riskOwner) {
        this.id = id;
        this.specific = specific;
        this.mh = mh;
        this.threatRate = threatRate;
        this.vulnerabilityRate = vulnerabilityRate;
        this.kindOfMeasure = kindOfMeasure;
        this.reductionAmount = reductionAmount;
        this.comment = comment;
        this.commentAfter = commentAfter;
        this.riskC = riskC;
        this.riskI = riskI;
        this.riskD = riskD;
        this.cacheMaxRisk = cacheMaxRisk;
        this.cacheTargetedRisk = cacheTargetedRisk;
        this.amv = amv;
        this.threat = threat;
        this.vulnerability = vulnerability;
        this.context = context;
        this.riskOwner = riskOwner;
        parentInstanceId = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public int getSpecific() {
        return specific;
    }

    public int getMh() {
        return mh;
    }

    public int getThreatRate() {
        return threatRate;
    }

    public int getVulnerabilityRate() {
        return vulnerabilityRate;
    }

    public int getKindOfMeasure() {
        return kindOfMeasure;
    }

    public int getReductionAmount() {
        return reductionAmount;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentAfter() {
        return commentAfter;
    }

    public int getRiskC() {
        return riskC;
    }

    public int getRiskI() {
        return riskI;
    }

    public int getRiskD() {
        return riskD;
    }

    public int getCacheMaxRisk() {
        return cacheMaxRisk;
    }

    public int getCacheTargetedRisk() {
        return cacheTargetedRisk;
    }

    public String getAmv() {
        return amv;
    }

    public String getThreat() {
        return threat;
    }

    public String getVulnerability() {
        return vulnerability;
    }

    public String getContext() {
        return context;
    }

    public String getRiskOwner() {
        return riskOwner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpecific(int specific) {
        this.specific = specific;
    }

    public void setMh(int mh) {
        this.mh = mh;
    }

    public void setThreatRate(int threatRate) {
        this.threatRate = threatRate;
        this.updateMh();
    }

    public void setVulnerabilityRate(int vulnerabilityRate) {
        this.vulnerabilityRate = vulnerabilityRate;
        this.updateMh();
    }

    public void setKindOfMeasure(int kindOfMeasure) {
        this.kindOfMeasure = kindOfMeasure;
    }

    public void setReductionAmount(int reductionAmount) {
        this.reductionAmount = reductionAmount;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentAfter(String commentAfter) {
        this.commentAfter = commentAfter;
    }

    public void setRiskC(int riskC) {
        this.riskC = riskC;
    }

    public void setRiskI(int riskI) {
        this.riskI = riskI;
    }

    public void setRiskD(int riskD) {
        this.riskD = riskD;
    }

    public void setCacheMaxRisk(int cacheMaxRisk) {
        this.cacheMaxRisk = cacheMaxRisk;
    }

    public void setCacheTargetedRisk(int cacheTargetedRisk) {
        this.cacheTargetedRisk = cacheTargetedRisk;
    }

    public void setAmv(String amv) {
        this.amv = amv;
    }

    public void setThreat(String threat) {
        this.threat = threat;
    }

    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setRiskOwner(String riskOwner) {
        this.riskOwner = riskOwner;
    }

    public void setParentInstanceId(Set<String> parentInstanceId) {
        this.parentInstanceId = parentInstanceId;
    }

    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }

    /**
        * Returns the collection of parent instance IDs.
        *
        * @return the collection of parent instance IDs
        */
    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }

    /**
     * Updates the value of 'mh' based on the vulnerability rate and threat rate.
     * If either the vulnerability rate or the threat rate is greater than -1, 'mh' is set to 0.
     * Otherwise, 'mh' is set to -1.
     */
    @JsonIgnore
    protected void updateMh() {
        setMh(getVulnerabilityRate() > -1 || getThreatRate() > -1 ? 0 : -1);
    }
}
