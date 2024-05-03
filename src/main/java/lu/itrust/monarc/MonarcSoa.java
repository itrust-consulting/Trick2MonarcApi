package lu.itrust.monarc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The `MonarcSoa` class represents a Monarc Statement of Applicability (SoA).
 * It contains information about remarks, evidences, actions, measure ID, and various scale values.
 * The scale values include EX, LR, CO, BR, BP, and RRA.
 * The class also includes a scale comment for the SoA.
 */
public class MonarcSoa {
    private String remarks;
    private String evidences;
    private String actions;
    @JsonProperty("measure_id")
    private String measureId;
    private Integer EX;
    private Integer LR;
    private Integer CO;
    private Integer BR;
    private Integer BP;
    private Integer RRA;
    @JsonInclude(value = Include.NON_NULL)
    private Integer soaScaleComment;

    public MonarcSoa(String remarks, String evidences, String actions, String measureId, Integer eX, Integer lR,
            Integer cO, Integer bR, Integer bP, Integer rRA, Integer soaScaleComment) {
        this.remarks = remarks;
        this.evidences = evidences;
        this.actions = actions;
        this.measureId = measureId;
        EX = eX;
        LR = lR;
        CO = cO;
        BR = bR;
        BP = bP;
        RRA = rRA;
        this.soaScaleComment = soaScaleComment;
    }

    public MonarcSoa(String remarks, String evidences, String actions, String measureId, Integer eX, Integer lR,
            Integer cO, Integer bR, Integer bP, Integer rRA) {
        this.remarks = remarks;
        this.evidences = evidences;
        this.actions = actions;
        this.measureId = measureId;
        this.soaScaleComment = null;
        EX = eX;
        LR = lR;
        CO = cO;
        BR = bR;
        BP = bP;
        RRA = rRA;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEvidences() {
        return evidences;
    }

    public void setEvidences(String evidences) {
        this.evidences = evidences;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    @JsonProperty("measure_id")
    public String getMeasureId() {
        return measureId;
    }

    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    @JsonProperty("EX")
    public Integer getEX() {
        return EX;
    }

    public void setEX(Integer eX) {
        EX = eX;
    }

    @JsonProperty("LR")
    public Integer getLR() {
        return LR;
    }

    public void setLR(Integer lR) {
        LR = lR;
    }

    @JsonProperty("CO")
    public Integer getCO() {
        return CO;
    }

    public void setCO(Integer cO) {
        CO = cO;
    }

    @JsonProperty("BR")
    public Integer getBR() {
        return BR;
    }

    public void setBR(Integer bR) {
        BR = bR;
    }

    @JsonProperty("BP")
    public Integer getBP() {
        return BP;
    }

    public void setBP(Integer bP) {
        BP = bP;
    }

    @JsonProperty("RRA")
    public Integer getRRA() {
        return RRA;
    }

    public void setRRA(Integer rRA) {
        RRA = rRA;
    }

    public Integer getSoaScaleComment() {
        return soaScaleComment;
    }

    public void setSoaScaleComment(Integer soaScaleComment) {
        this.soaScaleComment = soaScaleComment;
    }
}
