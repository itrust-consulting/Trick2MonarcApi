/**
 * Class that represents the anr element from the Monarc JSON file
 * This is the root element of the JSON database, and should never
 * have more than one instance of it.
 */
package lu.itrust.monarc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the anr element from the Monarc JSON file
 * This is the root element of the JSON database, and should never
 * have more than one instance of it.
 */
public final class MonarcANR {
    private String type;
    private String monarc_version;
    private String export_datetime;
    private Map<Integer, MonarcInstance> instances;
    private boolean with_eval;
    private List<String> anrMetadatasOnInstances;
    private Map<String, MonarcSoaScaleComment> soaScaleComment;

    private Map<String, MonarcReferentials> referentials;
    private Map<String, MonarcMeasures> measures;
    private List<String> measuresMeasures;
    private List<MonarcSoaCategories> soacategories;
    private List<MonarcSoa> soas;
    private Map<String, MonarcOperationalRiskScales> operationalRiskScales;
    private Map<String, MonarcScales> scales;
    private Map<String, MonarcScalesComments> scalesComments;
    private MonarcMethod method;

    private MonarcANR(String type, String monarc_version, String export_datetime, boolean with_eval) {
        this.type = type;
        this.monarc_version = monarc_version;
        this.export_datetime = export_datetime;
        this.instances = new HashMap<>();
        this.with_eval = with_eval;
        this.anrMetadatasOnInstances = new LinkedList<>();
        this.soaScaleComment = new HashMap<>();
        this.referentials = new HashMap<>();
        this.measures = new HashMap<>();
        this.measuresMeasures = new LinkedList<>();
        this.soacategories = new LinkedList<>();
        this.soas = new LinkedList<>();
        this.operationalRiskScales = new HashMap<>();
        this.scales = new HashMap<>();
        this.scalesComments = new HashMap<>();
        this.method = new MonarcMethod();
    }

    public static MonarcANR build() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return new MonarcANR("anr", MonarcInstance.MONARC_DATABASE_VERSION, dtf.format(now).toString(), true);
    }

    public void setInstances(Map<Integer, MonarcInstance> instancesMap) {
        this.instances.clear();
        this.instances.putAll(instancesMap);
    }

    public String getType() {
        return type;
    }

    public String getMonarc_version() {
        return monarc_version;
    }

    public String getExport_datetime() {
        return export_datetime;
    }

    public Map<Integer, MonarcInstance> getInstances() {
        return instances;
    }

    public boolean isWith_eval() {
        return with_eval;
    }

    public List<String> getAnrMetadatasOnInstances() {
        return anrMetadatasOnInstances;
    }

    public Map<String, MonarcSoaScaleComment> getSoaScaleComment() {
        return soaScaleComment;
    }

    public Map<String, MonarcReferentials> getReferentials() {
        return referentials;
    }

    public Map<String, MonarcMeasures> getMeasures() {
        return measures;
    }

    public List<String> getMeasuresMeasures() {
        return measuresMeasures;
    }

    public List<MonarcSoaCategories> getSoacategories() {
        return soacategories;
    }

    public List<MonarcSoa> getSoas() {
        return soas;
    }

    public Map<String, MonarcOperationalRiskScales> getOperationalRiskScales() {
        return operationalRiskScales;
    }

    public Map<String, MonarcScales> getScales() {
        return scales;
    }

    public Map<String, MonarcScalesComments> getScalesComments() {
        return scalesComments;
    }

    public MonarcMethod getMethod() {
        return method;
    }

    public void setMethod(MonarcMethod newMethod) {
        this.method = newMethod;
    }

    public void setScales(Map<String, MonarcScales> scales) {
        this.scales = scales;
    }

    public void setSoaScaleComment(Map<String, MonarcSoaScaleComment> soaScaleComment) {
        this.soaScaleComment = soaScaleComment;
    }

    public void setSoa(List<MonarcSoa> soa) {
        this.soas = soa;
    }

    public void setReferentials(Map<String, MonarcReferentials> monarcReferentials) {
        this.referentials = monarcReferentials;
    }

    public void setMeasures(Map<String, MonarcMeasures> monarcMeasures) {
        this.measures = monarcMeasures;
    }

    public void setSoaCategories(List<MonarcSoaCategories> monarcSoaCategories) {
        this.soacategories = monarcSoaCategories;
    }

    public void setScalesComments(Map<String, MonarcScalesComments> monarcScalesComments) {
        this.scalesComments = monarcScalesComments;
    }

    public void setOperationalRiskScales(Map<String, MonarcOperationalRiskScales> operationalRiskScales) {
        this.operationalRiskScales = operationalRiskScales;
    }
}
