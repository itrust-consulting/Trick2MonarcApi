package lu.itrust.monarc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Main class for converting the Monarc JSON database file in Java objects.
 * This class not only gathers the data from the JSON file, but provides
 * functions for searching the data.
 * 
 */
public class MonarcDatabase {

    private Map<String, MonarcInstance> monarcData;
    private Map<String, MonarcRisks> monarcRiskList;
    private Map<String, MonarcVulnerabilities> monarcVulnerabilityList;
    private Map<String, MonarcThreats> monarcThreatList;
    private Map<String, MonarcAMVThreats> monarcAMVThreatList;
    private Map<String, MonarcAMV> monarcAMVList;
    private Map<String, ?> jsonMap;
    private Map<String, MonarcScales> scales;
    private Map<String, MonarcScalesComments> scalesComments;
    private Map<String, MonarcSoaScaleComment> soaScaleComment;
    private Map<String, MonarcObject> monarcObjectsList;
    private Map<String, MonarcThemes> monarcThemesList;
    private Map<String, MonarcRecs> monarcRecsList;
    private Map<String, MonarcRecos> monarcRecosList;
    private Map<String, MonarcRecSets> monarcRecSetsList;
    private Map<String, MonarcReferentials> monarcReferentials;
    private Map<String, MonarcMeasures> monarcMeasures;
    private List<MonarcSoaCategories> monarcSoaCategories;
    private List<MonarcSoa> monarcSoa;
    private Map<String, MonarcOperationalRiskScales> monarcOperationalRiskScales;
    // Asset by code
    private Map<String, MonarcAsset> monarcAssetList;
    // private Map<String, MonarcConsequences> monarcConsequenceList;
    private MonarcANR monarcANR;

    /**
     * Constructor, processes the JSON file and populates the internal
     * representation of the
     * JSON as Java objects.
     * 
     * This function initiates the processing of the file by reading the
     * upper level of the JSON file and processing all elements that are
     * in the uppermost level, calling the relevant functions for processing
     * the subsequent elements.
     * 
     * @param jsonPath The path for the Monarc JSON file to be processed
     * @throws StreamReadException
     * @throws DatabindException
     * @throws IOException
     */
    public MonarcDatabase(String jsonPath) throws Exception {
        this.monarcData = new HashMap<>();
        this.monarcRiskList = new HashMap<>();
        this.monarcVulnerabilityList = new HashMap<>();
        this.monarcThreatList = new HashMap<>();
        this.monarcAMVThreatList = new HashMap<>();
        this.monarcAMVList = new HashMap<>();
        this.scales = new HashMap<>();
        this.soaScaleComment = new HashMap<>();
        this.monarcObjectsList = new HashMap<>();
        this.monarcAssetList = new HashMap<>();
        // this.monarcConsequenceList = new HashMap<>();
        this.monarcThemesList = new HashMap<>();
        this.monarcRecsList = new HashMap<>();
        this.monarcRecosList = new HashMap<>();
        this.monarcRecSetsList = new HashMap<>();
        this.monarcReferentials = new HashMap<>();
        this.monarcMeasures = new HashMap<>();
        this.monarcSoaCategories = new ArrayList<>();
        this.monarcSoa = new ArrayList<>();
        this.monarcANR = MonarcANR.build();
        this.scalesComments = new HashMap<>();
        this.monarcOperationalRiskScales = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        jsonMap = mapper.readValue(Paths.get(jsonPath).toFile(), Map.class);

        // Referentials must be mapped before "instances", as this information
        // will be used to complete "measures" in "instance"

        Object soaReferentials = jsonMap.get("referentials");
        extractReferentials(soaReferentials);

        Object instancesData = jsonMap.get("instances");
        if (instancesData instanceof Map) {
            Map<?, ?> instances = ((Map<?, ?>) instancesData);
            for (Map.Entry<?, ?> entry : instances.entrySet()) {
                extractInstanceData(entry);
            }
        }

        // If there is no data in soaScaleComment, it is represented as a list on the
        // json file instead of a map
        // so, we need to check if the element is a map before processing it.
        Object soaScaleComment = jsonMap.get("soaScaleComment");
        extractSoaScaleComment(soaScaleComment);

        Object soaMeasures = jsonMap.get("measures");
        extractMeasures(soaMeasures);

        Object soaCategories = jsonMap.get("soacategories");
        extractSoaCategories(soaCategories);

        Object soas = jsonMap.get("soas");
        extractSoas(soas);

        Object ANRScalesMap = jsonMap.get("scales");
        extractANRScales(ANRScalesMap);

        Object ANRScalesCommentsMap = jsonMap.get("scalesComments");
        extractANRScalesComment(ANRScalesCommentsMap);

        Object AMVThreats = jsonMap.get("method");
        extractAMVThreats(AMVThreats);

        Object operationalRiskScales = jsonMap.get("operationalRiskScales");
        extractOperationalRiskScales(operationalRiskScales);
    }

    private void extractOperationalRiskScales(Object data) {
        if (!(data instanceof Map))
            return;

        Map<?, ?> operationalRiskScales = ((Map<?, ?>) data);
        Integer index = 1;

        for (Map.Entry<?, ?> operationalRiskScale : ((Map<?, ?>) operationalRiskScales).entrySet()) {

            Map<?, ?> operationalRiskScaleEntry = (Map<?, ?>) operationalRiskScale.getValue();
            List<?> operationalRiskScaleTypes = (List<?>) operationalRiskScaleEntry.get("operationalRiskScaleTypes");

            ArrayList<MonarcOperationalRiskScaleTypes> newMonarcOperationalRiskScaleType = new ArrayList<>();

            for (Object operationalRiskScaleType : operationalRiskScaleTypes) {

                MonarcTranslation newTranslation = ExtractTranslation((Map<?, ?>) operationalRiskScaleType);

                List<?> operationalRiskScaleComments = (List<?>) ((Map<?, ?>) operationalRiskScaleType)
                        .get("operationalRiskScaleComments");

                ArrayList<MonarcOperationalRiskScaleComments> newListOperationalRiskScaleComments = new ArrayList<>();

                for (Object operationalRiskScaleComment : operationalRiskScaleComments) {
                    Map<?, ?> operationalRiskScaleCommentEntry = (Map<?, ?>) operationalRiskScaleComment;

                    MonarcOperationalRiskScaleComments newOperationalRiskScaleComment = ExtractOperationalRiskScaleComments(
                            operationalRiskScaleCommentEntry);

                    newListOperationalRiskScaleComments.add(newOperationalRiskScaleComment);
                }

                MonarcOperationalRiskScaleTypes newOperationalRiskScales = new MonarcOperationalRiskScaleTypes(
                        (Integer) ((Map<?, ?>) operationalRiskScaleType).get("id"),
                        (Boolean) ((Map<?, ?>) operationalRiskScaleType).get("isHidden"),
                        ((Map<?, ?>) operationalRiskScaleType).get("labelTranslationKey").toString(),
                        newTranslation,
                        newListOperationalRiskScaleComments);

                newMonarcOperationalRiskScaleType.add(newOperationalRiskScales);
            }

            List<?> operationalRiskScaleComments = (List<?>) operationalRiskScaleEntry
                    .get("operationalRiskScaleComments");
            ArrayList<MonarcOperationalRiskScaleComments> newMonarcOperationalRiskScaleComment = new ArrayList<>();

            for (Object operationalRiskScaleComment : operationalRiskScaleComments) {
                MonarcTranslation newTranslation = ExtractTranslation((Map<?, ?>) operationalRiskScaleComment);

                MonarcOperationalRiskScaleComments newOperationalRiskScaleComment = new MonarcOperationalRiskScaleComments(
                        (Integer) ((Map<?, ?>) operationalRiskScaleComment).get("id"),
                        (Integer) ((Map<?, ?>) operationalRiskScaleComment).get("scaleIndex"),
                        (Integer) ((Map<?, ?>) operationalRiskScaleComment).get("scaleValue"),
                        (Boolean) ((Map<?, ?>) operationalRiskScaleComment).get("isHidden"),
                        ((Map<?, ?>) operationalRiskScaleComment).get("commentTranslationKey").toString(),
                        newTranslation);

                newMonarcOperationalRiskScaleComment.add(newOperationalRiskScaleComment);
            }

            MonarcOperationalRiskScales newRiskScale = new MonarcOperationalRiskScales(
                    (Integer) ((Map<?, ?>) operationalRiskScaleEntry).get("id"),
                    (Integer) ((Map<?, ?>) operationalRiskScaleEntry).get("min"),
                    (Integer) ((Map<?, ?>) operationalRiskScaleEntry).get("max"),
                    (Integer) ((Map<?, ?>) operationalRiskScaleEntry).get("type"),
                    newMonarcOperationalRiskScaleType,
                    newMonarcOperationalRiskScaleComment);

            monarcOperationalRiskScales.put(index.toString(), newRiskScale);
            index++;
        }

        this.monarcANR.setOperationalRiskScales(monarcOperationalRiskScales);
    }

    private MonarcTranslation ExtractTranslation(Map<?, ?> translationContainer) {
        Map<?, ?> translation = (Map<?, ?>) ((Map<?, ?>) translationContainer).get("translation");

        return new MonarcTranslation(Objects.toString(translation.get("key"), ""),
                Objects.toString(translation.get("lang"), ""),
                Objects.toString(translation.get("value"), ""));
    }

    private MonarcOperationalRiskScaleComments ExtractOperationalRiskScaleComments(
            Map<?, ?> operationalRiskScaleCommentsContainer) {
        // Map<?, ?> operationalRiskScaleComments = (Map<?, ?>)((Map<?, ?>)
        // operationalRiskScaleCommentsContainer).get("operationalRiskScaleComments");

        return new MonarcOperationalRiskScaleComments((Integer) operationalRiskScaleCommentsContainer.get("id"),
                (Integer) operationalRiskScaleCommentsContainer.get("scaleIndex"),
                (Integer) operationalRiskScaleCommentsContainer.get("scaleValue"),
                (Boolean) operationalRiskScaleCommentsContainer.get("isHidden"),
                Objects.toString(operationalRiskScaleCommentsContainer.get("commentTranslationKey"), ""),
                ExtractTranslation(operationalRiskScaleCommentsContainer));
    }

    private void extractSoas(Object data) {
        if (!(data instanceof List))
            return;

        List<?> soas = ((List<?>) data);
        for (Object soa : soas) {
            Map<?, ?> soaEntry = (Map<?, ?>) soa;

            MonarcSoa newSoa;

            if (Objects.toString(soaEntry.get("soaScaleComment"), "").isEmpty())

                // In this case, soaScaleComment is set to null by default.
                newSoa = new MonarcSoa(Objects.toString(soaEntry.get("remarks"), null),
                        Objects.toString(soaEntry.get("evidences"), null),
                        Objects.toString(soaEntry.get("actions"), null),
                        Objects.toString(soaEntry.get("measure_id"), null),
                        Integer.parseInt(Objects.toString(soaEntry.get("EX"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("LR"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("CO"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("BR"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("BP"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("RRA"))));

            else

                newSoa = new MonarcSoa(Objects.toString(soaEntry.get("remarks"), null),
                        Objects.toString(soaEntry.get("evidences"), null),
                        Objects.toString(soaEntry.get("actions"), null),
                        Objects.toString(soaEntry.get("measure_id"), null),
                        Integer.parseInt(Objects.toString(soaEntry.get("EX"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("LR"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("CO"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("BR"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("BP"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("RRA"))),
                        Integer.parseInt(Objects.toString(soaEntry.get("soaScaleComment"))));

            monarcSoa.add(newSoa);
        }

        this.monarcANR.setSoa(this.monarcSoa);
    }

    private void extractSoaCategories(Object data) {
        if (!(data instanceof List))
            return;

        List<?> soaCategories = ((List<?>) data);

        for (Object soaCategory : soaCategories) {
            Map<?, ?> categoryEntry = (Map<?, ?>) soaCategory;

            MonarcSoaCategories newSoaCategory = new MonarcSoaCategories(
                    Objects.toString(categoryEntry.get("referential"), ""),
                    Objects.toString(categoryEntry.get("label1"), ""),
                    Objects.toString(categoryEntry.get("label2"), ""),
                    Objects.toString(categoryEntry.get("label3"), ""),
                    Objects.toString(categoryEntry.get("label4"), ""),
                    Integer.parseInt(Objects.toString(categoryEntry.get("status"))));

            monarcSoaCategories.add(newSoaCategory);
        }

        this.monarcANR.setSoaCategories(monarcSoaCategories);
    }

    private void extractMeasures(Object data) {

        if (!(data instanceof Map))
            return;

        Map<?, ?> measures = ((Map<?, ?>) data);
        for (Map.Entry<?, ?> measure : ((Map<?, ?>) measures).entrySet()) {
            Map<?, ?> measureEntry = (Map<?, ?>) measure.getValue();

            MonarcMeasures newMeasure = new MonarcMeasures(Objects.toString(measureEntry.get("category"), ""),
                    Objects.toString(measureEntry.get("referential"), ""),
                    Objects.toString(measureEntry.get("uuid"), ""),
                    Objects.toString(measureEntry.get("code"), ""),
                    Objects.toString(measureEntry.get("label1"), ""),
                    Objects.toString(measureEntry.get("label2"), ""),
                    Objects.toString(measureEntry.get("label3"), ""),
                    Objects.toString(measureEntry.get("label4"), ""),
                    Integer.parseInt(Objects.toString(measureEntry.get("status"))));

            monarcMeasures.put(newMeasure.getUuid(), newMeasure);
        }

        this.monarcANR.setMeasures(this.monarcMeasures);
    }

    private void extractReferentials(Object data) {

        if (!(data instanceof Map))
            return;

        Map<?, ?> referentials = ((Map<?, ?>) data);
        // For each recommendation set
        for (Map.Entry<?, ?> referential : ((Map<?, ?>) referentials).entrySet()) {
            Map<?, ?> referentialEntry = (Map<?, ?>) referential.getValue();

            MonarcReferentials newReferential = new MonarcReferentials(
                    Objects.toString(referentialEntry.get("uuid"), ""),
                    Objects.toString(referentialEntry.get("label1"), ""),
                    Objects.toString(referentialEntry.get("label2"), ""),
                    Objects.toString(referentialEntry.get("label3"), ""),
                    Objects.toString(referentialEntry.get("label4"), ""));

            this.monarcReferentials.put(newReferential.getUuid(), newReferential);
        }

        this.monarcANR.setReferentials(this.monarcReferentials);
    }

    /**
     * This function is used to process the information inside the instance element
     * of the
     * JSON file and initializes the processing of child sections of instance.
     * 
     * @param entry The monarc instance in the ANR Monarc JSON element
     * @return The MonarcInstance corresponding one of the branches of the tree
     */
    private MonarcInstance extractInstanceData(Map.Entry<?, ?> entry) {
        Map<?, ?> instance = (Map<?, ?>) ((Map<?, ?>) entry.getValue()).get("instance");
        Map<?, ?> consequences = (Map<?, ?>) ((Map<?, ?>) entry.getValue()).get("consequences");

        // Converting the JSON into Java classes
        MonarcInstance newElement = new MonarcInstance((int) instance.get("id"),
                Objects.toString(instance.get("name1"), ""),
                Objects.toString(instance.get("name2"), ""),
                Objects.toString(instance.get("name3"), ""),
                Objects.toString(instance.get("name4"), ""),
                Objects.toString(instance.get("label1"), ""),
                Objects.toString(instance.get("label2"), ""),
                Objects.toString(instance.get("label3"), ""),
                Objects.toString(instance.get("label4"), ""),
                (Integer) instance.get("disponibility"),
                (Integer) instance.get("level"),
                (Integer) instance.get("assetType"),
                (Integer) instance.get("exportable"),
                (Integer) instance.get("position"),
                (Integer) instance.get("c"),
                (Integer) instance.get("i"),
                (Integer) instance.get("d"),
                (Integer) instance.get("ch"),
                (Integer) instance.get("ih"),
                (Integer) instance.get("dh"),
                Objects.toString(instance.get("asset"), ""),
                Objects.toString(instance.get("object"), ""),
                (Integer) instance.get("root"),
                (Integer) instance.get("parent"));

        for (Map.Entry<?, ?> consequence : consequences.entrySet()) {
            Map<?, ?> newScaleImpactType = (Map<?, ?>) ((Map<?, ?>) consequence.getValue()).get("scaleImpactType");

            MonarcScaleImpactType consequenceScaleImpactType = new MonarcScaleImpactType(
                    (int) newScaleImpactType.get("id"),
                    (int) newScaleImpactType.get("type"),
                    newScaleImpactType.get("label1").toString(),
                    newScaleImpactType.get("label2").toString(),
                    newScaleImpactType.get("label3").toString(),
                    newScaleImpactType.get("label4").toString(),
                    (int) newScaleImpactType.get("isSys"),
                    (int) newScaleImpactType.get("isHidden"),
                    (int) newScaleImpactType.get("position"),
                    (int) newScaleImpactType.get("scale"));

            MonarcConsequences newConsequence = new MonarcConsequences(
                    (int) ((Map<?, ?>) consequence.getValue()).get("id"),
                    (int) ((Map<?, ?>) consequence.getValue()).get("isHidden"),
                    (int) ((Map<?, ?>) consequence.getValue()).get("locallyTouched"),
                    (int) ((Map<?, ?>) consequence.getValue()).get("c"),
                    (int) ((Map<?, ?>) consequence.getValue()).get("i"),
                    (int) ((Map<?, ?>) consequence.getValue()).get("d"),
                    consequenceScaleImpactType);
            newElement.addConsequence(newConsequence);
        }

        extractRisks(entry, newElement);
        extractVulnerabilities(entry, newElement);
        extractThreats(entry, newElement);
        extractAMVs(entry, newElement);
        extractObjectMeasures(entry, newElement);
        extractObject(entry);
        extractRecs(entry);
        extractRecos(entry);
        extractRecSets(entry);

        monarcData.put("" + newElement.getId(), newElement);

        Object children = ((Map<?, ?>) entry.getValue()).get("children");

        if (children instanceof Map) {
            for (Map.Entry<?, ?> child : ((Map<?, ?>) children).entrySet())
                newElement.addChild(extractInstanceData(child));
        }
        return newElement;
    }

    /**
     * Helper function to eliminate duplicates from the records.
     * 
     * @param <T>
     * @param keyExtractor
     * @return
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    // region Data extraction functions

    private void extractObjectMeasures(Map.Entry<?, ?> entry, MonarcInstance parent) {
        Object measures = ((Map<?, ?>) entry.getValue()).get("measures");
        if (measures == null || measures instanceof Collection)
            return;

        for (Map.Entry<?, ?> measure : ((Map<?, ?>) measures).entrySet()) {
            Map<?, ?> category = (Map<?, ?>) ((Map<?, ?>) measure.getValue()).get("category");
            Map<?, ?> referential = (Map<?, ?>) ((Map<?, ?>) measure.getValue()).get("referential");
            Map<?, ?> measureEntry = (Map<?, ?>) measure.getValue();

            MonarcMeasureCategory newMeasureCategory = new MonarcMeasureCategory((int) (category.get("id")),
                    category.get("label1").toString(),
                    category.get("label2").toString(),
                    category.get("label3").toString(),
                    category.get("label4").toString(),
                    (int) (category.get("status")));

            MonarcReferentials newReferential = new MonarcReferentials(referential.get("uuid").toString(),
                    referential.get("label1").toString(),
                    referential.get("label2").toString(),
                    referential.get("label3").toString(),
                    referential.get("label4").toString());

            MonarcMeasures newMeasure = new MonarcMeasures(newMeasureCategory,
                    newReferential,
                    Objects.toString(measureEntry.get("uuid"), ""),
                    Objects.toString(measureEntry.get("code"), ""),
                    Objects.toString(measureEntry.get("label1"), ""),
                    Objects.toString(measureEntry.get("label2"), ""),
                    Objects.toString(measureEntry.get("label3"), ""),
                    Objects.toString(measureEntry.get("label4"), ""),
                    (int) (measureEntry.get("status")));

            parent.addMeasure(newMeasure);
        }
    }

    /**
     * Function to extract data from the "recos" field in some given instance.
     * For a given instance, the "recos" field is where the suggested security
     * recommendations were implemented. Every element has a list of the whole
     * list of security recommendations stored in the database, but they are
     * under the rec tag. When the recommendation is implemented for one of the
     * elements, it is moved to the "recos" element.
     * The "recos" element has three levels:
     * 
     * "recos":
     * "<ID of the threat addressed by the recommendation>":
     * "<ID of the recommendation moved to recos tag>":
     * 
     * @param entry The "instance" from which the "recos" parameter should be
     *              extracted from.
     */
    private void extractRecos(Map.Entry<?, ?> entry) {
        Object recos = ((Map<?, ?>) entry.getValue()).get("recos");
        if (recos == null || recos instanceof Collection)
            return;

        for (Map.Entry<?, ?> risk : ((Map<?, ?>) recos).entrySet()) {
            // For each recommendation
            for (Map.Entry<?, ?> reco : ((Map<?, ?>) risk.getValue()).entrySet()) {
                Object duedate = ((Map<?, ?>) reco.getValue()).get("duedate");
                Map<?, ?> recoEntry = (Map<?, ?>) reco.getValue();

                if (monarcRecosList.containsKey(recoEntry.get("uuid").toString())) {
                    monarcRecosList.get(recoEntry.get("uuid").toString()).addParentInstance(entry.getKey().toString());
                    monarcRecosList.get(recoEntry.get("uuid").toString()).addRelatedRisk(risk.getKey().toString());
                    continue;
                }
                MonarcDuedate newDuedate;

                if (duedate == null || duedate instanceof Collection) {
                    newDuedate = null;
                } else {
                    newDuedate = new MonarcDuedate(Objects.toString(((Map<?, ?>) duedate).get("date"), ""),
                            (int) ((Map<?, ?>) duedate).get("timezone_type"),
                            Objects.toString(((Map<?, ?>) duedate).get("timezone"), ""));
                }

                MonarcRecos newReco = new MonarcRecos(Objects.toString(recoEntry.get("uuid"), ""),
                        Objects.toString(recoEntry.get("recommandationSet"), ""),
                        Objects.toString(recoEntry.get("code"), ""),
                        Objects.toString(recoEntry.get("description"), ""),
                        (int) (recoEntry.get("importance")),
                        Objects.toString(recoEntry.get("comment"), ""),
                        (int) (recoEntry.get("status")),
                        Objects.toString(recoEntry.get("responsable"), ""),
                        newDuedate,
                        (int) recoEntry.get("counterTreated"),
                        Objects.toString(recoEntry.get("commentAfter"), ""),
                        risk.getKey().toString());

                newReco.addParentInstance(entry.getKey().toString());

                this.monarcRecosList.put(newReco.getUuid() + "", newReco);
                MonarcRecs rec1 = this.monarcRecsList.computeIfAbsent(newReco.getUuid(), k -> new MonarcRecs(newReco));
                rec1.addParentInstance(entry.getKey().toString());
            }
        }
    }

    /**
     * Function to extract data from the "recs" field in some given instance.
     * The "recs" field holds all the recommendations that were not implemented
     * in any of the threats inside the instance.
     * 
     * @param entry The "instance" from which the "recs" parameter should be
     *              extracted from.
     */
    private void extractRecs(Map.Entry<?, ?> entry) {
        Object recs = ((Map<?, ?>) entry.getValue()).get("recs");
        if (recs == null || recs instanceof Collection)
            return;

        // For each recommendation
        for (Map.Entry<?, ?> rec : ((Map<?, ?>) recs).entrySet()) {
            Object duedate = ((Map<?, ?>) rec.getValue()).get("duedate");
            Map<?, ?> recEntry = (Map<?, ?>) rec.getValue();

            if (monarcRecsList.containsKey(recEntry.get("uuid").toString())) {
                monarcRecsList.get(recEntry.get("uuid").toString()).addParentInstance(entry.getKey().toString());
                continue;
            }
            MonarcDuedate newDuedate;

            if (duedate == null || duedate instanceof Collection) {
                newDuedate = null;
            } else {
                newDuedate = new MonarcDuedate(Objects.toString(((Map<?, ?>) duedate).get("date"), ""),
                        (int) ((Map<?, ?>) duedate).get("timezone_type"),
                        Objects.toString(((Map<?, ?>) duedate).get("timezone"), ""));
            }

            MonarcRecs newRec = new MonarcRecs(Objects.toString(recEntry.get("uuid"), ""),
                    Objects.toString(recEntry.get("recommandationSet"), ""),
                    Objects.toString(recEntry.get("code"), ""),
                    Objects.toString(recEntry.get("description"), ""),
                    (int) (recEntry.get("importance")),
                    Objects.toString(recEntry.get("comment"), ""),
                    (int) (recEntry.get("status")),
                    Objects.toString(recEntry.get("responsable"), ""),
                    newDuedate,
                    (int) recEntry.get("counterTreated"));

            newRec.addParentInstance(entry.getKey().toString());

            this.monarcRecsList.put(newRec.getUuid() + "", newRec);
        }
    }

    /**
     * Function to extract data from the "recsSet" field in the instance at hand.
     * This element holds only the uuid of the recommendation sets and the label
     * for them.
     * 
     * This function populates a Hash Map with all the RecSets in the JSON file,
     * in which the UUID acts as key and the MonarcRecSets java object corresponding
     * to the recSets element in the instance object.
     * 
     * @param entry The "instance" from which the "recs" parameter should be
     *              extracted from.
     */
    private void extractRecSets(Map.Entry<?, ?> entry) {
        Object recSets = ((Map<?, ?>) entry.getValue()).get("recSets");
        if (recSets == null || recSets instanceof Collection)
            return;

        // For each recommendation set
        for (Map.Entry<?, ?> recSet : ((Map<?, ?>) recSets).entrySet()) {
            Map<?, ?> recSetEntry = (Map<?, ?>) recSet.getValue();

            if (this.monarcRecSetsList.keySet().contains(((Map<?, ?>) recSet.getValue()).get("uuid").toString())) {
                this.monarcRecSetsList.get(recSetEntry.get("uuid").toString())
                        .addParentInstance(entry.getKey().toString());
            } else {
                MonarcRecSets newRecset = new MonarcRecSets(Objects.toString(recSetEntry.get("uuid"), ""),
                        Objects.toString(recSetEntry.get("label1"), ""),
                        Objects.toString(recSetEntry.get("label2"), ""),
                        Objects.toString(recSetEntry.get("label3"), ""),
                        Objects.toString(recSetEntry.get("label4"), ""));

                newRecset.addParentInstance(entry.getKey().toString());
                this.monarcRecSetsList.put(newRecset.getUuid(), newRecset);
            }
        }
    }

    /**
     * Helper function to extract the risks from the Monarc JSON file and create a
     * HashMap populated with the Java representation of such data.
     * 
     * In this case, the key is the risk ID and the value is the Java object
     * containing
     * the risk information.
     * 
     * @param entry The "instance" from which the "recs" parameter should be
     *              extracted from.
     */
    private void extractRisks(Map.Entry<?, ?> entry, MonarcInstance parent) {
        // If there are no risks, abort execution.

        Object risks = ((Map<?, ?>) entry.getValue()).get("risks");
        if (risks == null || risks instanceof Collection)
            return;

        // For each risk
        for (Map.Entry<?, ?> risk : ((Map<?, ?>) risks).entrySet()) {
            Map<?, ?> riskEntry = (Map<?, ?>) risk.getValue();

            if (this.monarcRiskList.keySet().contains(riskEntry.get("id").toString())) {
                this.monarcRiskList.get(riskEntry.get("id").toString()).addParentInstance(entry.getKey().toString());
                continue;
            }

            MonarcRisks monarcRisks = new MonarcRisks((int) ((Map<?, ?>) risk.getValue()).get("id"),
                    (int) ((Map<?, ?>) risk.getValue()).get("specific"),
                    (int) ((Map<?, ?>) risk.getValue()).get("mh"),
                    (int) ((Map<?, ?>) risk.getValue()).get("threatRate"),
                    (int) ((Map<?, ?>) risk.getValue()).get("vulnerabilityRate"),
                    (int) ((Map<?, ?>) risk.getValue()).get("kindOfMeasure"),
                    (int) ((Map<?, ?>) risk.getValue()).get("reductionAmount"),
                    Objects.toString(((Map<?, ?>) risk.getValue()).get("comment"), ""),
                    Objects.toString(((Map<?, ?>) risk.getValue()).get("commentAfter"), ""),
                    (int) ((Map<?, ?>) risk.getValue()).get("riskC"),
                    (int) ((Map<?, ?>) risk.getValue()).get("riskI"),
                    (int) ((Map<?, ?>) risk.getValue()).get("riskD"),
                    (int) ((Map<?, ?>) risk.getValue()).get("cacheMaxRisk"),
                    (int) ((Map<?, ?>) risk.getValue()).get("cacheTargetedRisk"),
                    ((Map<?, ?>) risk.getValue()).get("amv").toString(),
                    ((Map<?, ?>) risk.getValue()).get("threat").toString(),
                    ((Map<?, ?>) risk.getValue()).get("vulnerability").toString(),
                    ((Map<?, ?>) risk.getValue()).get("context").toString(),
                    ((Map<?, ?>) risk.getValue()).get("riskOwner").toString());

            monarcRisks.addParentInstance(entry.getKey().toString());

            parent.addRisks(monarcRisks);

            monarcRiskList.put(monarcRisks.getId() + "", monarcRisks);
        }
    }

    /**
     * Helper function to extract the vulnerabilities from the Monarc JSON file and
     * create a HashMap populated with the Java representation of such data.
     * 
     * In this case, the key is the risk ID and the value is the Java object
     * containing
     * the risk information.
     * 
     * @param entry The "instance" from which the "recs" parameter should be
     *              extracted from.
     */
    private void extractVulnerabilities(Map.Entry<?, ?> entry, MonarcInstance parent) {

        Map<?, ?> vulnerabilitiesData = (Map<?, ?>) entry.getValue();

        Object vuls = vulnerabilitiesData.get("vuls");
        if (vuls == null || vuls instanceof Collection)
            return;

        // For each vulnerability
        for (Map.Entry<?, ?> vul : ((Map<?, ?>) vuls).entrySet()) {
            Map<?, ?> vulnerabilityEntry = (Map<?, ?>) vul.getValue();

            if (this.monarcVulnerabilityList.keySet().contains(vulnerabilityEntry.get("uuid").toString())) {
                this.monarcVulnerabilityList.get(vulnerabilityEntry.get("uuid").toString())
                        .addParentInstance(entry.getKey().toString());
                continue;
            }

            MonarcVulnerabilities monarcVulnerabilities = new MonarcVulnerabilities(
                    vulnerabilityEntry.get("uuid").toString(),
                    Objects.toString(vulnerabilityEntry.get("label1"), ""),
                    Objects.toString(vulnerabilityEntry.get("label2"), ""),
                    Objects.toString(vulnerabilityEntry.get("label3"), ""),
                    Objects.toString(vulnerabilityEntry.get("label4"), ""),
                    Objects.toString(vulnerabilityEntry.get("description1"), ""),
                    Objects.toString(vulnerabilityEntry.get("description2"), ""),
                    Objects.toString(vulnerabilityEntry.get("description3"), ""),
                    Objects.toString(vulnerabilityEntry.get("description4"), ""),
                    (int) vulnerabilityEntry.get("status"),
                    (int) vulnerabilityEntry.get("mode"),
                    vulnerabilityEntry.get("code").toString());

            monarcVulnerabilities.addParentInstance(entry.getKey().toString());

            parent.addVulnerabilities(monarcVulnerabilities);

            this.monarcVulnerabilityList.put(monarcVulnerabilities.getUuid(), monarcVulnerabilities);
        }
    }

    /**
     * Helper function to extract the threats from the Monarc JSON file and create a
     * HashMap populated with the Java representation of such data enconded in a
     * MonarcThreats object.
     * 
     * In this case, the key is the threat UUID and the value is the Java object
     * containing the threat information.
     * 
     * @param entry
     */
    private void extractThreats(Map.Entry<?, ?> entry, MonarcInstance parent) {

        Object threats = ((Map<?, ?>) entry.getValue()).get("threats");

        if (threats == null || threats instanceof Collection)
            return;

        // For each vulnerability
        for (Map.Entry<?, ?> threat : ((Map<?, ?>) threats).entrySet()) {
            Map<?, ?> threatEntry = (Map<?, ?>) threat.getValue();
            if (this.monarcThreatList.keySet().contains(threatEntry.get("uuid").toString())) {
                this.monarcThreatList.get(threatEntry.get("uuid").toString())
                        .addParentInstance(entry.getKey().toString());
                continue;
            }

            MonarcThreats monarcThreats = new MonarcThreats(threatEntry.get("uuid").toString(),
                    Objects.toString(threatEntry.get("comment"), ""),
                    Objects.toString(threatEntry.get("code"), ""),
                    Objects.toString(threatEntry.get("label1"), ""),
                    Objects.toString(threatEntry.get("label2"), ""),
                    Objects.toString(threatEntry.get("label3"), ""),
                    Objects.toString(threatEntry.get("label4"), ""),
                    Objects.toString(threatEntry.get("description1"), ""),
                    Objects.toString(threatEntry.get("description2"), ""),
                    Objects.toString(threatEntry.get("description3"), ""),
                    Objects.toString(threatEntry.get("description4"), ""),
                    (int) threatEntry.get("status"),
                    (int) threatEntry.get("mode"),
                    (int) threatEntry.get("trend"),
                    (int) threatEntry.get("qualification"),
                    (int) threatEntry.get("c"),
                    (int) threatEntry.get("i"),
                    (int) threatEntry.get("a"),
                    (int) threatEntry.get("theme"));

            monarcThreats.addParentInstance(entry.getKey().toString());

            parent.addThreats(monarcThreats);

            this.monarcThreatList.put(monarcThreats.getUuid(), monarcThreats);
        }
    }

    /**
     * Helper function to extract the threats inside the "method" element
     * from the Monarc JSON file and create a HashMap populated with the
     * Java representation of such data.
     * 
     * @param entry The monarc threat element in the ANR Monarc JSON element
     *              inside the method tag
     */
    private void extractAMVThreats(Object data) {

        if (!(data instanceof Map))
            return;

        Map<?, ?> entry = ((Map<?, ?>) data);
        Object threats = ((Map<?, ?>) entry).get("threats");

        if (!(threats instanceof Map))
            return;

        // For each threat
        for (Map.Entry<?, ?> threat : ((Map<?, ?>) threats).entrySet()) {
            Map<?, ?> threatEntry = (Map<?, ?>) threat.getValue();

            if (((Map<?, ?>) threats).containsValue(threatEntry.get("uuid")))
                continue;

            MonarcThreatTheme theme = new MonarcThreatTheme((int) ((Map<?, ?>) threatEntry.get("theme")).get("id"),
                    Objects.toString(((Map<?, ?>) threatEntry.get("theme")).get("label2"), ""),
                    Objects.toString(((Map<?, ?>) threatEntry.get("theme")).get("label1"), ""),
                    Objects.toString(((Map<?, ?>) threatEntry.get("theme")).get("label3"), ""),
                    Objects.toString(((Map<?, ?>) threatEntry.get("theme")).get("label4"), ""));

            MonarcAMVThreats monarcAMVThreats = new MonarcAMVThreats(Objects.toString(threatEntry.get("uuid"), ""),
                    Objects.toString(threatEntry.get("label1"), ""),
                    Objects.toString(threatEntry.get("label2"), ""),
                    Objects.toString(threatEntry.get("label3"), ""),
                    Objects.toString(threatEntry.get("label4"), ""),
                    Objects.toString(threatEntry.get("description1"), ""),
                    Objects.toString(threatEntry.get("description2"), ""),
                    Objects.toString(threatEntry.get("description3"), ""),
                    Objects.toString(threatEntry.get("description4"), ""),
                    Objects.toString(threatEntry.get("code"), ""),
                    (int) threatEntry.get("trend"),
                    (int) threatEntry.get("qualification"),
                    (int) threatEntry.get("c"),
                    (int) threatEntry.get("i"),
                    (int) threatEntry.get("a"),
                    Objects.toString(threatEntry.get("comment"), null),
                    theme);

            this.monarcAMVThreatList.put(monarcAMVThreats.getUuid(), monarcAMVThreats);
        }

    }

    /**
     * Helper function to extract the threats inside the "method" element
     * from the Monarc JSON file and create a HashMap populated with the
     * Java representation of such data.
     * 
     * @param entry The monarc threat element in the ANR Monarc JSON element
     *              inside the method tag
     */
    private void extractObject(Map.Entry<?, ?> entry) {
        Map<?, ?> object = (Map<?, ?>) ((Map<?, ?>) entry.getValue()).get("object");

        if (object == null)
            return;

        MonarcAsset newAsset = extractAssets(object, entry.getKey().toString());

        if (this.monarcObjectsList.keySet().contains(((Map<?, ?>) object.get("object")).get("uuid").toString())) {
            this.monarcObjectsList.get(((Map<?, ?>) object.get("object")).get("uuid").toString())
                    .addParentInstance(entry.getKey().toString());
        } else {

            MonarcObject newObject = new MonarcObject(((Map<?, ?>) object.get("object")).get("uuid").toString(),
                    (int) ((Map<?, ?>) object.get("object")).get("mode"),
                    (int) ((Map<?, ?>) object.get("object")).get("scope"),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("name1"), ""),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("name2"), ""),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("name3"), ""),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("name4"), ""),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("label1"), ""),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("label2"), ""),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("label3"), ""),
                    Objects.toString(((Map<?, ?>) object.get("object")).get("label4"), ""),
                    (int) ((Map<?, ?>) object.get("object")).get("disponibility"),
                    (int) ((Map<?, ?>) object.get("object")).get("position"),
                    (int) ((Map<?, ?>) object.get("object")).get("category"),
                    newAsset);

            Map<?, ?> categories = (Map<?, ?>) ((Map<?, ?>) object).get("categories");

            for (Map.Entry<?, ?> category : categories.entrySet()) {
                newObject.addCategory(new MonarcCategories((int) ((Map<?, ?>) category.getValue()).get("id"),
                        Objects.toString(((Map<?, ?>) category.getValue()).get("label1"), ""),
                        Objects.toString(((Map<?, ?>) category.getValue()).get("label2"), ""),
                        Objects.toString(((Map<?, ?>) category.getValue()).get("label3"), ""),
                        Objects.toString(((Map<?, ?>) category.getValue()).get("label4"), ""),
                        (Integer) ((Map<?, ?>) category.getValue()).get("parent")));
            }
            newObject.addParentInstance(entry.getKey().toString());
            this.monarcObjectsList.put(newObject.getUuid(), newObject);
        }
    }

    /**
     * Helper function to extract assets from inside the "object" element
     * from the Monarc JSON file and create a HashMap populated with the
     * Java representation of such data.
     * 
     * @param entry      The monarc object element in the ANR Monarc JSON element
     *                   inside the method tag.
     * @param parentCode The code of the parent of the asset to be extracted.
     */
    private MonarcAsset extractAssets(Map<?, ?> object, String parentCode) {
        Map<?, ?> asset = (Map<?, ?>) ((Map<?, ?>) object).get("asset");

        if (asset == null)
            return null;

        extractThemes(asset, parentCode);

        MonarcAsset newAsset;

        if (this.monarcAssetList.keySet().contains(((Map<?, ?>) asset.get("asset")).get("code").toString())) {
            this.monarcAssetList.get(((Map<?, ?>) asset.get("asset")).get("code").toString())
                    .addParentInstance(parentCode);
            newAsset = this.monarcAssetList.get(((Map<?, ?>) asset.get("asset")).get("code").toString());
        } else {
            newAsset = new MonarcAsset(((Map<?, ?>) asset.get("asset")).get("uuid").toString(),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("label1"), ""),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("label2"), ""),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("label3"), ""),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("label4"), ""),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("description1"), ""),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("description2"), ""),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("description3"), ""),
                    Objects.toString(((Map<?, ?>) asset.get("asset")).get("description4"), ""),
                    (int) ((Map<?, ?>) asset.get("asset")).get("status"),
                    (int) ((Map<?, ?>) asset.get("asset")).get("mode"),
                    (int) ((Map<?, ?>) asset.get("asset")).get("type"),
                    ((Map<?, ?>) asset.get("asset")).get("code").toString());

            newAsset.addParentInstance(parentCode);

            this.monarcAssetList.put(newAsset.getCode(), newAsset);
        }

        return newAsset;
    }

    /**
     * Helper function to extract themes from inside the "asset" element
     * from the Monarc JSON file and create a HashMap populated with the
     * Java representation of such data.
     * 
     * @param entry      The monarc object element in the ANR Monarc JSON element
     *                   inside the method tag
     * @param parentCode The code of the parent element to the theme.
     */
    private List<MonarcThemes> extractThemes(Map<?, ?> asset, String parentCode) {
        Object themes = ((Map<?, ?>) asset).get("themes");

        if (themes == null || themes instanceof Collection)
            return Collections.emptyList();

        final Map<String, Map<String, Object>> myThemes = (Map<String, Map<String, Object>>) themes;

        for (Map<String, Object> theme : myThemes.values()) {
            if (this.monarcThemesList.containsKey(theme.get("id") + "")) {
                this.monarcThemesList.get(theme.get("id").toString())
                        .addParentInstance(parentCode);
            } else {
                MonarcThemes newTheme = new MonarcThemes((int) theme.get("id"),
                        Objects.toString(theme.get("label1"), ""),
                        Objects.toString(theme.get("label2"), ""),
                        Objects.toString(theme.get("label3"), ""),
                        Objects.toString(theme.get("label4"), ""));

                newTheme.addParentInstance(parentCode);

                monarcThemesList.put(newTheme.getId() + "", newTheme);
            }

        }
        return null;
    }

    /**
     * Helper function to extract the AMVs from the Monarc JSON file and create a
     * List populated
     * with the Java representation of such data.
     * 
     * @param entry
     */
    private void extractAMVs(Map.Entry<?, ?> entry, MonarcInstance parent) {

        Object amvs = ((Map<?, ?>) entry.getValue()).get("amvs");

        if (amvs == null || amvs instanceof Collection)
            return;

        // For each vulnerability
        for (Map.Entry<?, ?> amv : ((Map<?, ?>) amvs).entrySet()) {

            Map<?, ?> amvEntry = (Map<?, ?>) amv.getValue();

            if (this.monarcAMVList.containsKey(amvEntry.get("uuid").toString())) {
                this.monarcAMVList.get(amvEntry.get("uuid").toString()).addParentInstance(entry.getKey().toString());
                continue;
            }

            MonarcAMV monarcAMVs = new MonarcAMV(Objects.toString(amvEntry.get("uuid"), ""),
                    Objects.toString(amvEntry.get("threat"), ""),
                    Objects.toString(amvEntry.get("asset"), ""),
                    Objects.toString(amvEntry.get("vulnerability"), ""),
                    (int) amvEntry.get("status"));

            monarcAMVs.addParentInstance(entry.getKey().toString());

            parent.addAMVs(monarcAMVs);

            Object measureList = amvEntry.get("measures");
            if (measureList == null)
                monarcAMVs.setMeasureList(new HashSet<>());
            else
                monarcAMVs.setMeasureList(
                        ((Collection<String>) measureList).stream().distinct().collect(Collectors.toSet()));

            this.monarcAMVList.put(monarcAMVs.getUuid(), monarcAMVs);
        }

    }

    private void extractANRScales(Object data) {

        if (!(data instanceof Map))
            return;

        Map<?, ?> entry = ((Map<?, ?>) data);
        for (Map.Entry monarcAMV : entry.entrySet()) {
            scales.put(monarcAMV.getKey().toString(),
                    new MonarcScales((int) ((Map<String, ?>) monarcAMV.getValue()).get("id"),
                            (int) ((Map<String, ?>) monarcAMV.getValue()).get("min"),
                            (int) ((Map<String, ?>) monarcAMV.getValue()).get("max"),
                            (int) ((Map<String, ?>) monarcAMV.getValue()).get("type")));
        }

        this.monarcANR.setScales(scales);
    }

    private void extractANRScalesComment(Object data) {
        if (!(data instanceof Map))
            return;

        Map<?, ?> entry = ((Map<?, ?>) data);
        for (Map.Entry monarcANRScalesComment : entry.entrySet()) {
            MonarcScales newMonarcScales = scales
                    .get(((Map<?, Integer>) ((Map) monarcANRScalesComment.getValue()).get("scale")).get("type") + "");

            var newMonarcANRScalesComment = new MonarcScalesComments(
                    (int) ((Map<String, ?>) monarcANRScalesComment.getValue()).get("id"),
                    (int) ((Map<String, ?>) monarcANRScalesComment.getValue()).get("scaleIndex"),
                    (int) ((Map<String, ?>) monarcANRScalesComment.getValue()).get("scaleValue"),
                    ((Map<String, ?>) monarcANRScalesComment.getValue()).get("comment1").toString(),
                    ((Map<String, ?>) monarcANRScalesComment.getValue()).get("comment2").toString(),
                    ((Map<String, ?>) monarcANRScalesComment.getValue()).get("comment3").toString(),
                    ((Map<String, ?>) monarcANRScalesComment.getValue()).get("comment4").toString(),
                    newMonarcScales, ((Map<String, ?>) monarcANRScalesComment.getValue()).get("scaleImpactType"));

            scalesComments.put(monarcANRScalesComment.getKey().toString(), newMonarcANRScalesComment);

        }

        this.monarcANR.setScalesComments(scalesComments);
    }

    private void extractSoaScaleComment(Object data) {
        if (!(data instanceof Map))
            return;

        Map<?, ?> entry = ((Map<?, ?>) data);
        for (Map.Entry monarcAMV : entry.entrySet()) {
            soaScaleComment.put(monarcAMV.getKey().toString(),
                    new MonarcSoaScaleComment(Integer.parseInt((String) monarcAMV.getKey()),
                            (int) ((Map<String, ?>) monarcAMV.getValue()).get("scaleIndex"),
                            (boolean) ((Map<String, ?>) monarcAMV.getValue()).get("isHidden"),
                            Objects.toString(((Map<String, ?>) monarcAMV.getValue()).get("colour"), ""),
                            Objects.toString(((Map<String, ?>) monarcAMV.getValue()).get("comment"), "")));
        }

        this.monarcANR.setSoaScaleComment(soaScaleComment);
    }

    // endregion
    // Region Instance search methods

    public List<MonarcInstance> getAllMonarcInstances() {
        return this.monarcData.values().stream().collect(Collectors.toList());
    }

    public List<MonarcInstance> searchInstanceByName(String name) {
        return this.monarcData.values().stream().filter(e -> e.isNameMatch(name)).collect(Collectors.toList());
    }

    public List<MonarcInstance> searchInstanceByName(String name, int languageCode) {
        return name == null ? null : this.monarcData.values().stream().filter(e -> {
            final String myName = e.getName(languageCode);
            return myName != null && myName.trim().equalsIgnoreCase(name);
        }).collect(Collectors.toList());
    }

    public List<MonarcInstance> searchInstanceByLabel(String label) {
        return this.monarcData.values().stream().filter(e -> e.isLabelMatch(label)).collect(Collectors.toList());
    }

    public List<MonarcInstance> searchInstanceByLabel(String label, int languageCode) {
        return label == null ? null : this.monarcData.values().stream().filter(e -> {
            final String name = e.getLabel(languageCode);
            return name != null && name.trim().equalsIgnoreCase(label);
        }).collect(Collectors.toList());
    }

    /**
     * Retrives all assets
     */
    public List<MonarcAsset> getAllAssets() {
        return this.monarcAssetList.values().stream().collect(Collectors.toList());
    }

    public List<MonarcAsset> searchAssetByLabel(String label) {
        return this.monarcAssetList.values().stream().filter(e -> e.isNameMatch(label)).collect(Collectors.toList());
    }

    public List<MonarcAsset> searchAssetByLabel(String label, int languageCode) {
        final String mlLabel = "label" + languageCode;
        return this.monarcAssetList.values().stream().filter(e -> {
            final String name = (String) e.getAsset().get(mlLabel);
            return name != null && name.trim().equalsIgnoreCase(label);
        }).collect(Collectors.toList());
    }

    public List<MonarcConsequences> getAllMonarcConsequences() {
        return this.monarcData.values().stream().flatMap(e -> e.getConsequences().values().stream())
                .collect(Collectors.toList());
    }

    public int getMaxConsequencesId() {
        return this.monarcData.values().stream().flatMap(e -> e.getConsequences().keySet().stream()).mapToInt(e -> e)
                .max().orElse(0);
    }

    // region Risk search methods

    public List<MonarcRisks> searchRiskByInstanceId(int id) {
        return monarcRiskList.values().stream()
                .filter(o -> o.getParentInstanceId().contains(id + ""))
                .collect(Collectors.toList());

    }

    /**
     * Search the risks extracted from the Monarc JSON file according to its ID.
     * This method returns only one object, as the
     * ID is a unique field to reference a risk.
     * 
     * @param riskID The ID of the risk in the database
     * @return The MonarcRisks object relative to the searched risk
     */
    public MonarcRisks searchRiskByID(int riskID) {
        return monarcRiskList.values().stream().filter(o -> o.getId() == riskID).findFirst().orElse(null);
    }

    /**
     * Search the risks extracted from the Monarc JSON file related to a certain
     * AMV. This method returns multiple objects, as
     * one AMV may relate to multiple risks.
     * 
     * @param amv The amv UUID
     * @return A List with MonarcRisks related to a single risk UUID
     */
    public List<MonarcRisks> searchRiskByAMV(String amv) {
        return monarcRiskList.values().stream().filter(o -> amv.equals(o.getAmv()))
                .filter(distinctByKey(MonarcRisks::getId)).collect(Collectors.toList());
    }

    /**
     * Search the risks extracted from the Monarc JSON file related to a certain
     * threat. This method returns multiple objects, as
     * one threat may relate to multiple risks.
     * 
     * @param threat The threat UUID
     * @return A List with MonarcRisks related to a single threat UUID
     */
    public List<MonarcRisks> searchRiskByThreat(String threat) {
        return monarcRiskList.values().stream().filter(o -> threat.equals(o.getThreat()))
                .filter(distinctByKey(MonarcRisks::getId)).collect(Collectors.toList());
    }

    /**
     * Search the risks extracted from the Monarc JSON file related to a certain
     * vulnerability. This method returns multiple objects, as
     * one vulnerability may relate to multiple risks.
     * 
     * @param vulnerability The vulnerability UUID
     * @return A List with MonarcRisks related to a single vulnerability UUID
     */
    public List<MonarcRisks> searchRiskByVulnerability(String vulnerability) {
        return monarcRiskList.values().stream()
                .filter(o -> vulnerability.equals(o.getVulnerability())).filter(distinctByKey(MonarcRisks::getId))
                .collect(Collectors.toList());
    }

    // endregion

    public List<MonarcThreats> searchThreatByInstanceId(int id) {
        return monarcThreatList.values().stream()
                .filter(o -> o.getParentInstanceId().contains(id + ""))
                .collect(Collectors.toList());
    }

    // region Threat search methods
    /**
     * Search the threats extracted from the Monarc JSON by the label field. This
     * method returns the MonarcThreat object relative to
     * the label, which is unique to the record.
     * 
     * @param label        The string of the label to be searched
     * @param languageCode A numerical code of the language in which to search: 1 -
     *                     label1 (usually French); 2 - Label2 (usually English); 3
     *                     - Label3 (usually German); 4 - Label4 (usually
     *                     Luxembourgish)
     * @return The object that represents the threat found in the search. If more
     *         than one is found, the first one will be returned.
     */
    public MonarcThreats searchThreatByLabel(String label, int languageCode) {
        List<MonarcThreats> returnValue;

        switch (languageCode) {
            case 1:
                returnValue = monarcThreatList.values().stream().filter(o -> label.equals(o.getLabel1()))
                        .collect(Collectors.toList());
                break;

            case 2:
                returnValue = monarcThreatList.values().stream().filter(o -> label.equals(o.getLabel2()))
                        .collect(Collectors.toList());
                break;

            case 3:
                returnValue = monarcThreatList.values().stream().filter(o -> label.equals(o.getLabel3()))
                        .collect(Collectors.toList());
                break;

            case 4:
                returnValue = monarcThreatList.values().stream().filter(o -> label.equals(o.getLabel4()))
                        .collect(Collectors.toList());
                break;

            default:
                returnValue = Collections.emptyList();
                break;
        }

        return returnValue.stream().filter(distinctByKey(MonarcThreats::getUuid)).findFirst().orElse(null);
    }

    /**
     * Search the threats extracted from the Monarc JSON by the description field.
     * This method returns the MonarcThreat object relative to
     * the description, which is unique to the record.
     * 
     * @param description  The string of the description to be searched
     * @param languageCode A numerical code of the language in which to search: 1 -
     *                     label1 (usually French); 2 - Label2 (usually English); 3
     *                     - Label3 (usually German); 4 - Label4 (usually
     *                     Luxembourgish)
     * @return The object that represents the threat found in the search. If more
     *         than one is found, the first one will be returned.
     */
    public MonarcThreats searchThreatByDescription(String description, int languageCode) {
        switch (languageCode) {
            case 1:
                return monarcThreatList.values().stream().filter(o -> description.equals(o.getDescription1()))
                        .findFirst().orElse(null);

            case 2:
                return monarcThreatList.values().stream().filter(o -> description.equals(o.getDescription2()))
                        .findFirst().orElse(null);

            case 3:
                return monarcThreatList.values().stream().filter(o -> description.equals(o.getDescription3()))
                        .findFirst().orElse(null);

            case 4:
                return monarcThreatList.values().stream().filter(o -> description.equals(o.getDescription4()))
                        .findFirst().orElse(null);

            default:
                return null;
        }
    }

    public MonarcScales searchScaleByType(final int type) {
        return this.scales.values().stream().filter(e -> e.getType() == type).findAny().orElse(null);
    }

    /**
     * Search the threats extracted from the Monarc JSON by the code field. This
     * method returns the MonarcThreat object relative to
     * the code, which is unique to the record.
     * 
     * @param code The code string to be searched
     * @return The object that represents the threat found in the search. If more
     *         than one is found, the first one will be returned.
     */
    public MonarcThreats searchThreatByCode(String code) {
        return monarcThreatList.values().stream().filter(o -> code.equals(o.getCode())).findFirst().orElse(null);
    }

    /**
     * Search the threats extracted from the Monarc JSON by the uuid field. This
     * method returns the MonarcThreat object relative to
     * the uuid, which is unique to the record.
     * 
     * @param uuid The uuid string to be searched
     * @return The object that represents the threat found in the search. If more
     *         than one is found, the first one will be returned.
     */
    public MonarcThreats searchThreatByUUID(String uuid) {
        return monarcThreatList.values().stream().filter(o -> uuid.equals(o.getUuid())).findFirst().orElse(null);
    }

    /**
     * Search the threats extracted from the Monarc JSON related to a single
     * vulnerability. This method returns a list populated with MonarcThreat
     * objects relative to a MonarcVulnerability
     * 
     * @param vulnerability The vulnerability to be searched
     * @return A list with all the MonarcThreats related to the MonarcVulnerability
     *         passed as argument
     */
    public List<MonarcThreats> getAllThreatsForVulnerability(MonarcVulnerabilities vulnerability) {
        List<MonarcThreats> queryResult = new ArrayList<>();

        // Get all the AMVs associated with a vulnerability
        List<MonarcAMV> queryAMVs = searchAMVByVulnerability(vulnerability.getUuid());

        // Get the threats from the AMVs and add to a list
        for (MonarcAMV monarcAMV : queryAMVs) {
            queryResult.add(searchThreatByUUID(monarcAMV.getThreat()));
        }

        // Return the list
        return queryResult.stream().filter(Objects::nonNull).filter(distinctByKey(MonarcThreats::getUuid))
                .collect(Collectors.toList());
    }
    // endregion

    // region AMV search methods

    public List<MonarcAMV> searchAMVByInstanceId(int id) {
        return monarcAMVList.values().stream()
                .filter(o -> o.getParentInstanceId().contains(id + ""))
                .collect(Collectors.toList());
    }

    /**
     * Search the AMV extracted from the Monarc JSON by the uuid field. This method
     * returns the MonarcAMV object relative to
     * the uuid, which is unique to the record.
     * 
     * @param uuid The uuid string to be searched
     * @return The object that represents the threat found in the search. If more
     *         than one is found, the first one will be returned.
     */
    public MonarcAMV searchAMVByUUID(String uuid) {
        return monarcAMVList.values().stream().filter(o -> uuid.equals(o.getUuid())).findFirst().orElse(null);
    }

    /**
     * Search the threats extracted from the Monarc JSON related to a single threat
     * code. This method returns a list populated with MonarcAMV
     * objects relative to a single threat
     * 
     * @param threat The threat uuid to be searched
     * @return
     */
    public List<MonarcAMV> searchAMVByThreat(String threat) {
        return monarcAMVList.values().stream().filter(o -> threat.equals(o.getThreat()))
                .filter(distinctByKey(MonarcAMV::getUuid)).collect(Collectors.toList());
    }

    /**
     * Search the threats extracted from the Monarc JSON related to a single
     * vulnerability code. This method returns a list populated with MonarcAMV
     * objects relative to a single vulnerability
     * 
     * @param vulnerability The vulnerability uuid to be searched
     * @return A list with all the MonarcAMV objects related to the
     *         MonarcVulnerability passed as argument
     */
    public List<MonarcAMV> searchAMVByVulnerability(String vulnerability) {
        return monarcAMVList.values().stream().filter(o -> vulnerability.equals(o.getVulnerability()))
                .filter(distinctByKey(MonarcAMV::getUuid)).collect(Collectors.toList());
    }

    /**
     * Search the threats extracted from the Monarc JSON related to a single asset
     * code. This method returns a list populated with MonarcAMV
     * objects relative to a single asset.
     * 
     * @param asset The asset uuid to be searched
     * @return A list with all the MonarcAMV objects related to the
     *         MonarcVulnerability passed as argument
     */
    public List<MonarcAMV> searchAMVByAsset(String asset) {
        return monarcAMVList.values().stream().filter(o -> asset.equals(o.getAsset()))
                .filter(distinctByKey(MonarcAMV::getUuid)).collect(Collectors.toList());
    }
    // endregion

    public List<MonarcVulnerabilities> searchVulnerabilityByInstanceId(int id) {
        return monarcVulnerabilityList.values().stream()
                .filter(o -> o.getParentInstanceId().contains(id + ""))
                .collect(Collectors.toList());
    }

    // region Vulnerabilities search methods
    /**
     * Search the vulnerabilities extracted from the Monarc JSON by the label field.
     * This method returns the MonarcVulnerability object relative to
     * the label, which is unique to the record.
     * 
     * @param label        The string of the label to be searched
     * @param languageCode A numerical code of the language in which to search: 1 -
     *                     label1 (usually French); 2 - Label2 (usually English); 3
     *                     - Label3 (usually German); 4 - Label4 (usually
     *                     Luxembourgish)
     * @return The object that represents the vulnerability found in the search. If
     *         more than one is found, the first one will be returned.
     */
    public MonarcVulnerabilities searchVulnerabilityByLabel(String label, int languageCode) {
        switch (languageCode) {
            case 1:
                return monarcVulnerabilityList.values().stream().filter(o -> label.equals(o.getLabel1())).findFirst()
                        .orElse(null);

            case 2:
                return monarcVulnerabilityList.values().stream().filter(o -> label.equals(o.getLabel2())).findFirst()
                        .orElse(null);

            case 3:
                return monarcVulnerabilityList.values().stream().filter(o -> label.equals(o.getLabel3())).findFirst()
                        .orElse(null);

            case 4:
                return monarcVulnerabilityList.values().stream().filter(o -> label.equals(o.getLabel4())).findFirst()
                        .orElse(null);

            default:
                return null;
        }
    }

    /**
     * Search the vulnerabilities extracted from the Monarc JSON by the description
     * field. This method returns the MonarcVulnerability object relative to
     * the description, which is unique to the record.
     * 
     * @param description  The string of the description to be searched
     * @param languageCode A numerical code of the language in which to search: 1 -
     *                     label1 (usually French); 2 - Label2 (usually English); 3
     *                     - Label3 (usually German); 4 - Label4 (usually
     *                     Luxembourgish)
     * @return The object that represents the vulnerability found in the search. If
     *         more than one is found, the first one will be returned.
     */
    public MonarcVulnerabilities searchVulnerabilityByDescription(String description, int languageCode) {
        switch (languageCode) {
            case 1:
                return monarcVulnerabilityList.values().stream().filter(o -> description.equals(o.getDescription1()))
                        .findFirst().orElse(null);

            case 2:
                return monarcVulnerabilityList.values().stream().filter(o -> description.equals(o.getDescription2()))
                        .findFirst().orElse(null);

            case 3:
                return monarcVulnerabilityList.values().stream().filter(o -> description.equals(o.getDescription3()))
                        .findFirst().orElse(null);

            case 4:
                return monarcVulnerabilityList.values().stream().filter(o -> description.equals(o.getDescription4()))
                        .findFirst().orElse(null);

            default:
                return null;
        }
    }

    /**
     * Search the vulnerabilities extracted from the Monarc JSON by the code field.
     * This method returns the MonarcVulnerability object relative to
     * the code, which is unique to the record.
     * 
     * @param code The code string to be searched
     * @return The object that represents the vulnerability found in the search. If
     *         more than one is found, the first one will be returned.
     */
    public MonarcVulnerabilities searchVulnerabilityByCode(String code) {
        return monarcVulnerabilityList.values().stream()
                .filter(o -> code.equals(o.getCode())).filter(distinctByKey(MonarcVulnerabilities::getCode)).findFirst()
                .orElse(null);
    }

    /**
     * Search the vulnerabilities extracted from the Monarc JSON by the uuid field.
     * This method returns the MonarcVulnerability object relative to
     * the uuid, which is unique to the record.
     * 
     * @param uuid The UUID string to be searched
     * @return The object that represents the vulnerability found in the search. If
     *         more than one is found, the first one will be returned.
     */
    public MonarcVulnerabilities searchVulnerabilityByUUID(String uuid) {
        return monarcVulnerabilityList.values().stream().filter(o -> uuid.equals(o.getUuid())).findFirst().orElse(null);
    }

    public MonarcReferentials searchReferentialsByUUID(String uuid) {
        return monarcReferentials.values().stream().filter(o -> uuid.equals(o.getUuid())).findFirst().orElse(null);
    }

    public MonarcMeasures searchMeasuresByUUID(String uuid) {
        return monarcMeasures.values().stream()
                .filter(o -> uuid.equals(o.getUuid())).findFirst().orElse(null);
    }

    public MonarcMeasures searchMeasuresByReferentialLabelAndCode(String label, String code) {
        final MonarcReferentials referentials = searchReferentialByLabel(label);
        return referentials == null ? null
                : monarcMeasures.values().stream().map(MonarcMeasures.class::cast)
                        .filter(e -> e.getReferential().equals(referentials.getUuid()) && e.getCode().equals(code))
                        .findAny().orElse(null);
    }

    public List<MonarcMeasures> searchMeasuresByCode(String code) {
        return monarcMeasures.values().stream().map(MonarcMeasures.class::cast).filter(o -> o.getCode().equals(code))
                .collect(Collectors.toList());
    }

    public List<MonarcSoaCategories> searchSoaCategoriesByReferential(String referential) {
        return monarcSoaCategories.stream().filter(o -> referential.equals(o.getReferential()))
                .collect(Collectors.toList());
    }

    public List<MonarcSoa> searchSoaByMeasureID(String measureId) {
        return monarcSoa.stream().filter(o -> measureId.equals(o.getMeasureId())).collect(Collectors.toList());
    }

    public MonarcReferentials searchReferentialByLabel(String label) {
        return monarcReferentials.values().stream().filter(o -> o.isNameMatch(label)).findAny().orElse(null);
    }

    public List<MonarcMeasures> searchMeasuresByReferentialLabel(String label) {
        final MonarcReferentials referentials = searchReferentialByLabel(label);
        return referentials == null ? Collections.emptyList()
                : monarcMeasures.values().stream().map(MonarcMeasures.class::cast)
                        .filter(e -> e.getReferential().equals(referentials.getUuid())).collect(Collectors.toList());
    }

    public List<MonarcSoa> getAllMonarcSoas() {
        return monarcSoa;
    }

    public List<MonarcSoaScaleComment> getMonarcSoaScalesComments() {
        return this.soaScaleComment.values().stream()
                .sorted((e1, e2) -> Integer.compare(e1.getScaleIndex(), e2.getScaleIndex()))
                .collect(Collectors.toList());
    }

    public void setSoaScaleComments(List<MonarcSoaScaleComment> soaScaleComments) {
        this.soaScaleComment = soaScaleComments.stream()
                .sorted((e1, e2) -> Integer.compare(e1.getScaleIndex(), e2.getScaleIndex()))
                .collect(Collectors.toMap(e -> e.getId() + "", Function.identity(), (e1, e2) -> e1));
        this.monarcANR.setSoaScaleComment(this.soaScaleComment);
    }

    public MonarcRecSets searchRecSetsByLabel(String label) {
        return this.monarcRecSetsList.values().stream().filter(e -> e.isNameMatch(label)).findAny().orElse(null);
    }

    public List<MonarcRecs> searchRecsByRecSetsLabel(String label) {
        final MonarcRecSets recSets = searchRecSetsByLabel(label);
        if (recSets == null)
            return Collections.emptyList();
        return this.monarcRecsList.values().stream().filter(e -> e.getRecommandationSet().equals(recSets.getUuid()))
                .collect(Collectors.toList());
    }

    public void removeIf(Predicate<? super MonarcRecs> filter) {
        this.monarcRecsList.values().removeIf(filter);
        this.monarcData.values().parallelStream().forEach(e -> e.getRecs().values().removeIf(filter));
    }

    public MonarcRecos createOrUpdate(int riskId, MonarcRecs recs) {
        final MonarcRecos recos = this.monarcRecosList.computeIfAbsent(recs.getUuid(), b -> new MonarcRecos(recs));
        recos.addRelatedRisk(riskId + "");
        return recos;
    }

    public MonarcMethod getMethod() {
        return this.monarcANR.getMethod();
    }

    public MonarcANR getANR() {
        return this.monarcANR;
    }

    // endregion

    // region JSON update functions

    public void updateThreatValue(String threatUUID, String newValue) {
        Map<String, ?> newMap = (Map<String, ?>) jsonMap.get("instances");
        while (!newMap.keySet().contains("threats")) {
            newMap = (Map<String, ?>) jsonMap.get("children");
        }
        System.out.println("ok");
    }

    public void saveMapToJson(String outputPath) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        String newJson = obj.writeValueAsString(jsonMap);
        Files.write(Paths.get(outputPath), newJson.getBytes());
    }

    public void saveInstancesToJSON(String outputPath) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        Map<Integer, MonarcInstance> newInstacesTree = new HashMap<>();

        this.uppdateDependencyValue();

        for (MonarcInstance instanceElement : monarcData.values()) {

            List<MonarcAMV> instanceAMVs = monarcAMVList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toList());

            List<MonarcVulnerabilities> instanceVulnerabilities = monarcVulnerabilityList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toList());

            List<MonarcThreats> instanceThreats = monarcThreatList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toList());

            List<MonarcRisks> instanceRisks = monarcRiskList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toList());

            List<MonarcThemes> instanceThemes = monarcThemesList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toList());

            List<MonarcRecSets> instanceRecSets = monarcRecSetsList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toList());

            Map<String, MonarcRecs> instanceRecs = monarcRecsList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toMap(MonarcRecs::getUuid, Function.identity()));

            List<MonarcRecos> instanceRecos = monarcRecosList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + ""))
                    .collect(Collectors.toList());

            Map<String, Map<String, MonarcRecos>> finalInstanceRecos = new HashMap<>();

            for (MonarcRisks eachInstanceRisk : instanceRisks) {
                Map<String, MonarcRecos> recosToAdd = instanceRecos.stream()
                        .filter(o -> o.getRelatedRisks().contains(eachInstanceRisk.getId() + ""))
                        .collect(Collectors.toMap(e -> e.getUuid(), Function.identity()));
                if (recosToAdd.size() > 0) {
                    finalInstanceRecos.put(eachInstanceRisk.getId() + "", recosToAdd);
                }
            }

            finalInstanceRecos.values().stream().flatMap(e -> e.values().stream())
                    .forEach(v -> instanceRecs.remove(v.getUuid()));

            instanceElement.addAllThreats(instanceThreats);
            instanceElement.addAllVulnerabilities(instanceVulnerabilities);
            instanceElement.addALLAMVs(instanceAMVs);
            instanceElement.addAllRisks(instanceRisks);
            instanceElement.addAllRecSets(instanceRecSets);
            instanceElement.addAllRecs(instanceRecs.values());
            instanceElement.addAllRecos(finalInstanceRecos);

            MonarcObject objectToAdd = monarcObjectsList.values().stream()
                    .filter(o -> o.getParentInstanceId().contains(instanceElement.getId() + "")).findFirst()
                    .orElse(null);
            if (objectToAdd != null) {
                objectToAdd.getAsset().addAllAMVs(instanceAMVs);
                objectToAdd.getAsset().addAllThreats(instanceThreats);
                objectToAdd.getAsset().addAllVulnerabilities(instanceVulnerabilities);
                objectToAdd.getAsset().addAllThemes(instanceThemes);
            }
            instanceElement.addObject(objectToAdd);

        }

        // Add all the threats to their parent instances
        for (MonarcThreats threat : monarcThreatList.values()) {
            for (String parentInstance : threat.getParentInstanceId()) {
                monarcData.get(parentInstance).addThreats(threat);
            }
        }

        // Initially, get all nodes that do not have a parent and assign these to the
        // upper levels of the new tree.
        for (MonarcInstance instance : monarcData.values().stream().filter(o -> o.getParent() == 0)
                .filter(distinctByKey(o -> o.getId())).collect(Collectors.toList())) {
            newInstacesTree.put(instance.getId(), instance);
        }

        // Now, for each of the upper nodes of the tree, populate the rest of the
        // branches.
        for (MonarcInstance parentInstance : newInstacesTree.values().stream()
                .filter(distinctByKey(MonarcInstance::getId))
                .collect(Collectors.toList())) {
            populateTree(newInstacesTree, parentInstance);
        }

        this.monarcANR.setInstances(newInstacesTree);
        this.monarcANR.getMethod().setMethodThreat(monarcAMVThreatList);

        String newJson = obj.writeValueAsString(this.monarcANR);

        Files.write(Paths.get(outputPath), newJson.getBytes());
    }

    public void populateTree(Map<Integer, MonarcInstance> treeBase, MonarcInstance newParent) {
        // Filter a list with all children of newParent
        List<MonarcInstance> childrenOfTheParent = monarcData.values().stream()
                .filter(o -> o.getParent() == newParent.getId()).filter(distinctByKey(MonarcInstance::getId))
                .collect(Collectors.toList());

        for (MonarcInstance potentialChild : childrenOfTheParent) {
            // For each element of the list, check if has children, if so, populate
            // newParent child tree
            if (monarcData.values().stream().filter(o -> o.getParent() == potentialChild.getId())
                    .filter(distinctByKey(o -> o.getId())).collect(Collectors.counting()) > 0)
                populateTree(treeBase, potentialChild);

            // If has children, proceed to adding this element as child of newParent
            newParent.addChild(potentialChild);

        }

    }

    public void createNewAssetFile(MonarcObject newObject, String outputPath) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        String newJson = obj.writeValueAsString(newObject);
        Files.write(Paths.get(outputPath), newJson.getBytes());
    }

    public void uppdateDependencyValue() {
        final List<MonarcInstance> instances = monarcData.values().stream().filter(e -> e.getParent() == 0)
                .collect(Collectors.toList());
        for (MonarcInstance instance : instances) {

            computeImpactAndRisk(instance, null);

            uppdateDependencyValue(instance);

        }

    }

    private void computeImpactAndRisk(MonarcInstance child, MonarcInstance parent) {
        child.setC(
                child.getConsequences().values().stream().mapToInt(MonarcConsequences::getC).max().orElse(-1));
        child.setI(
                child.getConsequences().values().stream().mapToInt(MonarcConsequences::getI).max().orElse(-1));
        child.setD(
                child.getConsequences().values().stream().mapToInt(MonarcConsequences::getD).max().orElse(-1));

        child.setCh(child.getC() == -1);
        child.setIh(child.getI() == -1);
        child.setDh(child.getD() == -1);

        if (parent != null) {

            if (child.getCh())
                child.setC(parent.getC());

            if (child.getIh())
                child.setI(parent.getI());

            if (child.getDh())
                child.setD(parent.getD());
        }

        final int maxImpact = Math.max(child.getC(), Math.max(child.getI(), child.getD()));

        searchRiskByInstanceId(child.getI()).forEach(e -> {
            e.setCacheMaxRisk(Math.max(maxImpact * e.getVulnerabilityRate() * e.getThreatRate(), -1));
            if (e.getKindOfMeasure() == 3)// accept
                e.setCacheTargetedRisk(e.getCacheMaxRisk());
            else {
                e.setCacheTargetedRisk(Math
                        .max(Math.min(
                                maxImpact * Math.max(e.getVulnerabilityRate() - e.getReductionAmount(), 0)
                                        * e.getThreatRate(),
                                e.getCacheMaxRisk()), -1));
            }
        });

    }

    private void uppdateDependencyValue(MonarcInstance parent) {
        final List<MonarcInstance> instances = monarcData.values().stream()
                .filter(e -> e.getParent() == parent.getId())
                .collect(Collectors.toList());

        instances.forEach(child -> {

            computeImpactAndRisk(child, parent);

            uppdateDependencyValue(child);

        });

    }

    // endregion
}
