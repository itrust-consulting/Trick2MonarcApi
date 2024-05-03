package lu.itrust.monarc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MonarcScalesComments {
    private Integer id;
    private Integer scaleIndex;
    private Integer scaleValue;
    private String comment1;
    private String comment2;
    private String comment3;
    private String comment4;
    private MonarcScales scale;

    @JsonInclude(value = Include.NON_NULL)
    private Object scaleImpactType;

    public MonarcScalesComments(Integer id, Integer scaleIndex, Integer scaleValue, String comment1, String comment2,
            String comment3, String comment4, MonarcScales scale) {
        this.id = id;
        this.scaleIndex = scaleIndex;
        this.scaleValue = scaleValue;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.comment3 = comment3;
        this.comment4 = comment4;
        this.scale = scale;
        this.scaleImpactType = null;
    }

    public MonarcScalesComments(Integer id, Integer scaleIndex, Integer scaleValue, String comment1, String comment2,
            String comment3, String comment4, MonarcScales scale, Object scaleImpactType) {
        this.id = id;
        this.scaleIndex = scaleIndex;
        this.scaleValue = scaleValue;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.comment3 = comment3;
        this.comment4 = comment4;
        this.scale = scale;
        this.scaleImpactType = scaleImpactType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScaleIndex() {
        return scaleIndex;
    }

    public void setScaleIndex(Integer scaleIndex) {
        this.scaleIndex = scaleIndex;
    }

    public Integer getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(Integer scaleValue) {
        this.scaleValue = scaleValue;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getComment4() {
        return comment4;
    }

    public void setComment4(String comment4) {
        this.comment4 = comment4;
    }

    public MonarcScales getScale() {
        return scale;
    }

    public void setScale(int id, int type) {
        this.scale = new MonarcScales(id, -1, -1, type);
    }

    public Object getScaleImpactType() {
        return scaleImpactType;
    }

    public void setScaleImpactType(Object scaleImpactType) {
        this.scaleImpactType = scaleImpactType;
    }

    // TODO: Pass the scaleImpactType object
    // public void setScaleImpactType(Object )
}
