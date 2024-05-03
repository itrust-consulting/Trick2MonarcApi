package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a Monarc asset.
 * An asset is a component or element that is being assessed in the Monarc framework.
 */
public class MonarcAsset {

    private String type;
    private Map<String, Object> asset;
    private String version;
    private Map<String, MonarcAMV> amvs;
    private Map<String, MonarcThreats> threats;
    private Map<String, MonarcThemes> themes;
    private Map<String, MonarcVulnerabilities> vuls;
    private List<String> measures;
    private Set<String> parentInstanceId;

    public MonarcAsset(String uuid,
            String label1,
            String label2,
            String label3,
            String label4,
            String description1,
            String description2,
            String description3,
            String description4,
            int status,
            int mode,
            int type,
            String code,
            String version) {
        this.type = "asset";
        this.asset = new HashMap<>();
        asset.put("uuid", uuid);
        asset.put("label1", label1);
        asset.put("label2", label2);
        asset.put("label3", label3);
        asset.put("label4", label4);
        asset.put("description1", description1);
        asset.put("description2", description2);
        asset.put("description3", description3);
        asset.put("description4", description4);
        asset.put("status", status);
        asset.put("mode", mode);
        asset.put("type", type);
        asset.put("code", code);
        this.version = version;

        this.amvs = new HashMap<>();
        this.threats = new HashMap<>();
        this.themes = new HashMap<>();
        this.vuls = new HashMap<>();
        this.measures = new LinkedList<>();
        this.parentInstanceId = new HashSet<>();
    }

    public MonarcAsset(String uuid,
            String label1,
            String label2,
            String label3,
            String label4,
            String description1,
            String description2,
            String description3,
            String description4,
            int status,
            int mode,
            int type,
            String code) {
        this.type = "asset";
        this.asset = new HashMap<>();
        asset.put("uuid", uuid);
        asset.put("label1", label1);
        asset.put("label2", label2);
        asset.put("label3", label3);
        asset.put("label4", label4);
        asset.put("description1", description1);
        asset.put("description2", description2);
        asset.put("description3", description3);
        asset.put("description4", description4);
        asset.put("status", status);
        asset.put("mode", mode);
        asset.put("type", type);
        asset.put("code", code);
        this.version = "2.12.2";

        this.amvs = new HashMap<>();
        this.threats = new HashMap<>();
        this.themes = new HashMap<>();
        this.vuls = new HashMap<>();
        this.measures = new LinkedList<>();
        this.parentInstanceId = new HashSet<>();
    }

    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }

    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }

    @JsonIgnore
    public String getCode() {
        return asset.get("code").toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getAsset() {
        return asset;
    }

    public void setAsset(Map<String, Object> asset) {
        this.asset = asset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, MonarcAMV> getAmvs() {
        return amvs;
    }

    public void setAmvs(Map<String, MonarcAMV> amvs) {
        this.amvs = amvs;
    }

    public Map<String, MonarcThreats> getThreats() {
        return threats;
    }

    public void setThreats(Map<String, MonarcThreats> threats) {
        this.threats = threats;
    }

    public Map<String, MonarcThemes> getThemes() {
        return themes;
    }

    public void setThemes(Map<String, MonarcThemes> themes) {
        this.themes = themes;
    }

    public Map<String, MonarcVulnerabilities> getVuls() {
        return vuls;
    }

    public void setVuls(Map<String, MonarcVulnerabilities> vuls) {
        this.vuls = vuls;
    }

    public List<String> getMeasures() {
        return measures;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }

    public String getLabel(int i) {
        return (String) asset.get("label" + i);
    }

    public String getDescription(int i) {
        return (String) asset.get("description" + i);
    }

    @JsonIgnore
    public boolean isNameMatch(String name) {
        final String clsName = name == null ? "" : name.trim();
        if (clsName.isEmpty())
            return false;
        return asset.entrySet().stream().anyMatch(e -> e.getKey().startsWith("label") && e.getValue() != null
                && clsName.equalsIgnoreCase(e.getValue().toString()));
    }

    @JsonIgnore
    public String getUuid() {
        return asset.get("uuid").toString();
    }

    public void addAllAMVs(List<MonarcAMV> allAMVs)
    {
        for (MonarcAMV monarcAMV : allAMVs) {
            this.amvs.put(monarcAMV.getUuid(), monarcAMV);
        }
    }

    public void addAllVulnerabilities(List<MonarcVulnerabilities> instanceVulnerabilities)
    {
        for (MonarcVulnerabilities monarcVulnerability : instanceVulnerabilities) {
            this.vuls.put(monarcVulnerability.getUuid(), monarcVulnerability);
        }
    }

    public void addAllThreats(List<MonarcThreats> instanceThreats)
    {
        for (MonarcThreats monarcThreat : instanceThreats) {
            this.threats.put(monarcThreat.getUuid(), monarcThreat);
        }
    }

    public void addAllThemes(List<MonarcThemes> instanceThemes) {
        this.themes = instanceThemes.stream().collect(Collectors.toMap(e -> e.getId()+"", Function.identity(), (e1, e2) -> e1));
    }
}
