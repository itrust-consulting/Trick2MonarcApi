/**
 * Represents a MonarcInstance, which is an instance inside a Monarc JSON file.
 * 
 * This class contains various properties and methods to manipulate and access the data
 * related to a MonarcInstance.
 * 
 * The MonarcInstance class has the following properties:
 * - type: The type of the instance.
 * - monarc_version: The version of the Monarc database.
 * - with_eval: A boolean indicating whether evaluation is enabled for the instance.
 * - measures: A map of MonarcMeasures associated with the instance.
 * - risksop: A list of MonarcRisksop associated with the instance.
 * - recSets: A map of MonarcRecSets associated with the instance.
 * - recos: A map of maps representing the recommendations implemented for each risk.
 * - recs: A map of MonarcRecs representing the recommendations stored in the database.
 * - object: A MonarcObject representing the object associated with the instance.
 * - instance: A map representing the instance data.
 * - consequences: A map of MonarcConsequences associated with the instance.
 * - children: A map of MonarcInstance representing the child instances.
 * - threats: A map of MonarcThreats associated with the instance.
 * - AMVs: A map of MonarcAMV associated with the instance.
 * - vulnerabilities: A map of MonarcVulnerabilities associated with the instance.
 * - risks: A map of MonarcRisks associated with the instance.
 * - anrMetadatasOnInstances: A list of MonarcAnrMetadatasOnInstances associated with the instance.
 * - instancesMetadatas: A list of MonarcAnrMetadatasOnInstances representing the metadata of the instances.
 * 
 * The MonarcInstance class also provides getters and setters for accessing and modifying the instance data.
 */
package lu.itrust.monarc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MonarcInstance {
    
    @JsonIgnore
    public static final String MONARC_DATABASE_VERSION = "2.12.3";

    private String type;
    private String monarc_version;
    private boolean with_eval;
    private Map<String, MonarcMeasures> measures;
    private List<MonarcRisksop> risksop;
    private Map<String, MonarcRecSets> recSets;
    
    /**
     * The structures recos and recs work together when defining recommendations implemented to one given threat
     * and, for once, the database does not replicate data on these variables.
     * 
     * The recs structure stores all the recommendations on the database and replicates them for every instance
     * in the JSON file. When a recommendation is implemented for a given risk, the data in the data structure is
     * transferred to the recos structure, avoiding data replication.
     * 
     * The difference in how they are stored in the JSON file, while the recs structure is a HashMap with recs UUID
     * as key and stored as:
     * 
     * "recs": {
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *                  ...
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     * } 
     * 
     * Meanwhile, recos is stored as
     * 
     * "recos": {
     *      <risk ID in String format>: {
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *                  ...
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *      }
     *      <risk ID in String format>: {
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *                  ...
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *      }
     *              ...
     *      <risk ID in String format>: {
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *                  ...
     *          <recsUUID in String format>: {
     *              <recs data>
     *          }
     *      }
     * }
     * 
     * The recs information should be transferred from recs to recos in order to represent that it was implemented 
     * for that instance.
     */
    private Map<String, Map<String, MonarcRecos>> recos;
    private Map<String, MonarcRecs> recs;


    private MonarcObject object;
    private Map<String, Object> instance;
    private Map<Integer, MonarcConsequences> consequences;
    private Map<Integer, MonarcInstance> children;
    private Map<String, MonarcThreats> threats;
    private Map<String, MonarcAMV> AMVs;
    private Map<String, MonarcVulnerabilities> vulnerabilities;
    private Map<Integer, MonarcRisks> risks;
    private List<MonarcAnrMetadatasOnInstances> anrMetadatasOnInstances;
    private List<MonarcAnrMetadatasOnInstances> instancesMetadatas;

    /**
     * Constructor that represents an instance inside a Monarc JSON file
     * 
     * @param id
     * @param name1
     * @param name2
     * @param name3
     * @param name4
     * @param label1
     * @param label2
     * @param label3
     * @param label4
     * @param disponibility
     * @param level
     * @param assetType
     * @param exportable
     * @param position
     * @param c
     * @param i
     * @param d
     * @param ch
     * @param ih
     * @param dh
     * @param asset
     * @param object
     * @param root
     * @param parent
     */
    public MonarcInstance(int id, String name1, String name2, String name3, String name4,
            String label1, String label2, String label3, String label4,
            int disponibility, int level, int assetType, int exportable,
            int position, int c, int i, int d,
            int ch, int ih, int dh,
            String asset, String object,
            int root, int parent) {
        this.type = "instance";
        this.monarc_version = MONARC_DATABASE_VERSION;
        this.with_eval = true;
        this.consequences = new HashMap<>();
        this.children = new HashMap<>();
        this.threats = new HashMap<>();
        this.AMVs = new HashMap<>();
        this.vulnerabilities = new HashMap<>();
        this.risks = new HashMap<>();
        this.instance = new HashMap<>();
        instance.put("id", id);
        instance.put("name1", name1);
        instance.put("name2", name2);
        instance.put("name3", name3);
        instance.put("name4", name4);
        instance.put("label1", label1);
        instance.put("label2", label2);
        instance.put("label3", label3);
        instance.put("label4", label4);
        instance.put("disponibility", disponibility);
        instance.put("level", level);
        instance.put("assetType", assetType);
        instance.put("exportable", exportable);
        instance.put("position", position);
        instance.put("c", c);
        instance.put("i", i);
        instance.put("d", d);
        instance.put("ch", ch);
        instance.put("ih", ih);
        instance.put("dh", dh);
        instance.put("asset", asset);
        instance.put("object", object);
        instance.put("root", root);
        instance.put("parent", parent);
        this.measures = new HashMap<>();
        this.risksop = new ArrayList<>();
        this.anrMetadatasOnInstances = new ArrayList<>();
        this.recSets = new HashMap<>();
        this.recos = new HashMap<>();
        this.recs = new HashMap<>();
    }

    // region Getters and Setters

    public String getType() {
        return type;
    }

    public String getMonarc_version() {
        return monarc_version;
    }

    public boolean isWith_eval() {
        return with_eval;
    }

    public Map<String, Object> getInstance() {
        return instance;
    }

    public MonarcObject getObject() {
        return object;
    }

    public List<MonarcAnrMetadatasOnInstances> getAnrMetadatasOnInstances() {
        return anrMetadatasOnInstances;
    }

    public Map<Integer, MonarcRisks> getRisks() {
        return risks;
    }

    public Map<String, MonarcAMV> getAMVs() {
        return AMVs;
    }

    public Map<String, MonarcThreats> getThreats() {
        return threats;
    }

    public Map<String, MonarcVulnerabilities> getVuls() {
        return vulnerabilities;
    }

    public Map<String, MonarcMeasures> getMeasures() {
        return measures;
    }

    public List<MonarcRisksop> getRisksop() {
        return risksop;
    }

    public Map<String, MonarcRecSets> getRecSets() {
        return recSets;
    }

    public Map<String, Map<String, MonarcRecos>> getRecos() {
        return recos;
    }

    public Map<String, MonarcRecs> getRecs() {
        return recs;
    }

    public Map<Integer, MonarcConsequences> getConsequences() {
        return consequences;
    }

    public Map<Integer, MonarcInstance> getChildren() {
        return children;
    }

    @JsonIgnore
    public int getId() {
        return (int) instance.get("id");
    }

    @JsonIgnore
    public String getName1() {
        return (String) instance.get("name1");
    }

    @JsonIgnore
    public String getName2() {
        return (String) instance.get("name2");
    }

    @JsonIgnore
    public String getName3() {
        return (String) instance.get("name3");
    }

    @JsonIgnore
    public String getName4() {
        return (String) instance.get("name4");
    }

    @JsonIgnore
    public String getLabel1() {
        return (String) instance.get("label1");
    }

    @JsonIgnore
    public String getLabel2() {
        return (String) instance.get("label2");
    }

    @JsonIgnore
    public String getLabel3() {
        return (String) instance.get("label3");
    }

    @JsonIgnore
    public String getLabel4() {
        return (String) instance.get("label4");
    }

    @JsonIgnore
    public String getAsset() {
        return (String) instance.get("asset");
    }

    @JsonIgnore
    public String getInstanceObject() {
        return (String) instance.get("object");
    }

    @JsonIgnore
    public int geDisponibility() {
        return (int) instance.get("disponibility");
    }

    @JsonIgnore
    public int getLevel() {
        return (int) instance.get("level");
    }

    @JsonIgnore
    public int getAssetType() {
        return (int) instance.get("assetType");
    }

    @JsonIgnore
    public int getExportable() {
        return (int) instance.get("exportable");
    }

    @JsonIgnore
    public int getPosition() {
        return (int) instance.get("position");
    }

    @JsonIgnore
    public int getC() {
        return (int) instance.get("c");
    }

    @JsonIgnore
    public int getI() {
        return (int) instance.get("i");
    }

    @JsonIgnore
    public int getD() {
        return (int) instance.get("d");
    }

    @JsonIgnore
    public boolean getCh() {
        return instance.getOrDefault("ch", 0).equals(1);
    }

    @JsonIgnore
    public boolean getIh() {
        return instance.getOrDefault("ih", 0).equals(1);
    }

    @JsonIgnore
    public boolean getDh() {
        return instance.getOrDefault("dh", 0).equals(1);
    }

    @JsonIgnore
    public void setC(int value) {
        instance.put("c", value);
    }

    @JsonIgnore
    public void setI(int value) {
        instance.put("i", value);
    }

    @JsonIgnore
    public void setD(int value) {
        instance.put("d", value);
    }

    @JsonIgnore
    public void setCh(boolean value) {
        instance.put("ch", value ? 1 : 0);
    }

    @JsonIgnore
    public void setIh(boolean value) {
        instance.put("ih", value ? 1 : 0);
    }

    @JsonIgnore
    public void setDh(boolean value) {
        instance.put("dh", value ? 1 : 0);
    }

    @JsonIgnore
    public int getRoot() {
        return (int) instance.get("root");
    }

    @JsonIgnore
    public int getParent() {
        return (int) instance.get("parent");
    }

    // endregion

    /**
     * Adds consequences to the Monarc Instance in the form of MonarcConsequences
     * objects.
     * 
     * @param newConsequence
     */
    public void addConsequence(MonarcConsequences newConsequence) {
        this.consequences.put(newConsequence.getId(), newConsequence);
        newConsequence.addParentInstance(getInstanceObject());
    }

    /**
     * Adds children to the Monarc Instance. The children are Monarc Instances as
     * well.
     * 
     * @param newChild
     */
    public void addChild(MonarcInstance newChild) {
        // Can a child have two or more parents?
        this.children.put(newChild.getId(), newChild);
    }

    public void addThreats(MonarcThreats newThreat) {
        this.threats.put(newThreat.getUuid(), newThreat);
    }

    public void addAMVs(MonarcAMV newAMV) {
        this.AMVs.put(newAMV.getUuid(), newAMV);
    }

    public void addVulnerabilities(MonarcVulnerabilities newVulnerabilities) {
        this.vulnerabilities.put(newVulnerabilities.getUuid(), newVulnerabilities);
    }

    public void addRisks(MonarcRisks newRisk) {
        this.risks.put(newRisk.getId(), newRisk);
    }

    public void addAllThreats(List<MonarcThreats> newThreat) {
        this.threats.clear();
        for (MonarcThreats monarcThreats : newThreat) {
            this.threats.put(monarcThreats.getUuid(), monarcThreats);
        }

    }

    public void addALLAMVs(List<MonarcAMV> newAMV) {
        MonarcANR.build();
        for (MonarcAMV monarcAMV : newAMV) {
            this.AMVs.put(monarcAMV.getUuid(), monarcAMV);
        }

    }

    public void addAllVulnerabilities(List<MonarcVulnerabilities> newVulnerabilities) {
        this.vulnerabilities.clear();
        for (MonarcVulnerabilities monarcVulnerabilities : newVulnerabilities) {
            this.vulnerabilities.put(monarcVulnerabilities.getUuid(), monarcVulnerabilities);
        }
    }

    public void addAllRisks(List<MonarcRisks> newRisk) {
        this.risks.clear();
        for (MonarcRisks monarcRisks : newRisk) {
            this.risks.put(monarcRisks.getId(), monarcRisks);
        }
    }

    public void addAllRecs(Collection<MonarcRecs> instanceRecs) {
        this.recs = instanceRecs.stream()
                .collect(Collectors.toMap(MonarcRecs::getUuid, Function.identity(), (e1, e2) -> e1));
    }

    /*public void addAllRecos(List<MonarcRecos> instanceRecos) {
        /*for (MonarcRecos monarcRecos : instanceRecos) {
            for (String riskID : monarcRecos.getRelatedRisks()) {
                
            }
        }

        this.recos = instanceRecos.stream()
                .collect(Collectors.toMap(MonarcRecs::getUuid, Function.identity(), (e1, e2) -> e1));
    }*/

    public void addAllRecos(Map<String, Map<String, MonarcRecos>> instanceRecos) {
        this.recos = instanceRecos;
    }

    public void addAllRecSets(List<MonarcRecSets> instanceRecSets) {
        this.recSets = instanceRecSets.stream()
                .collect(Collectors.toMap(MonarcRecSets::getUuid, Function.identity(), (e1, e2) -> e1));
    }

    public void remove(MonarcRecs rec) {
        this.recs.remove(rec.getUuid());
        rec.getParentInstanceId().remove(this.getId() + "");
    }

    public void addObject(MonarcObject list) {
        object = list;
    }

    public String getLabel(int i) {
        return (String) instance.get("label" + i);
    }

    public String getDescription(int i) {
        return (String) instance.get("description" + i);
    }

    public boolean isLabelMatch(String name) {
        final String clsName = name == null ? "" : name.trim();
        if (clsName.isEmpty())
            return false;
        return instance.entrySet().stream().anyMatch(e -> e.getKey().startsWith("label") && e.getValue() != null
                && clsName.equalsIgnoreCase(e.getValue().toString()));
    }

    public String getName(int i) {
        return (String) instance.get("name" + i);
    }

    public boolean isNameMatch(String name) {
        final String clsName = name == null ? "" : name.trim();
        if (clsName.isEmpty())
            return false;
        return instance.entrySet().stream().anyMatch(e -> e.getKey().startsWith("name") && e.getValue() != null
                && clsName.equalsIgnoreCase(e.getValue().toString()));
    }

    public void addMeasure(MonarcMeasures newMeasure) {
        measures.put(newMeasure.uuid, newMeasure);
    }
}
