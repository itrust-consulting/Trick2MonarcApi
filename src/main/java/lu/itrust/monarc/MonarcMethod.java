package lu.itrust.monarc;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Monarc Method.
 */
public class MonarcMethod {
    private Map<String, Integer> steps;
    private Map<String, String> data;
    private Map<String, MonarcDeliveries> deliveries;
    private Map<String, MonarcQuestions> questions;
    private Map<String, MonarcQuestionChoice> questionChoice;
    private Map<String, Integer> thresholds;
    private Map<String, MonarcAMVThreats> threats;

    /**
     * Constructs a new MonarcMethod object with default values.
     */
    public MonarcMethod() {
        this.steps = new HashMap<>();
        this.data = new HashMap<>();
        this.deliveries = new HashMap<>();
        this.questions = new HashMap<>();
        this.questionChoice = new HashMap<>();
        this.thresholds = new HashMap<>();
        this.threats = new HashMap<>();

        // Initialize steps with default values
        this.steps.put("initAnrContext", 0);
        this.steps.put("initEvalContext", 0);
        this.steps.put("initRiskContext", 0);
        this.steps.put("initDefContext", 0);
        this.steps.put("modelImpacts", 0);
        this.steps.put("modelSummary", 0);
        this.steps.put("evalRisks", 0);
        this.steps.put("evalPlanRisks", 0);
        this.steps.put("manageRisks", 0);

        // Initialize data with default values
        this.data.put("contextAnaRisk", null);
        this.data.put("contextGestRisk", null);
        this.data.put("synthThreat", null);
        this.data.put("synthAct", null);

        // Initialize thresholds with default values
        this.thresholds.put("seuil1", 4);
        this.thresholds.put("seuil2", 8);
        this.thresholds.put("seuilRolf1", 4);
        this.thresholds.put("seuilRolf2", 8);
    }

    /**
     * Gets the steps of the Monarc Method.
     *
     * @return The steps map.
     */
    public Map<String, Integer> getSteps() {
        return steps;
    }

    /**
     * Gets the data of the Monarc Method.
     *
     * @return The data map.
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * Gets the deliveries of the Monarc Method.
     *
     * @return The deliveries map.
     */
    public Map<String, MonarcDeliveries> getDeliveries() {
        return deliveries;
    }

    /**
     * Gets the questions of the Monarc Method.
     *
     * @return The questions map.
     */
    public Map<String, MonarcQuestions> getQuestions() {
        return questions;
    }

    /**
     * Gets the question choices of the Monarc Method.
     *
     * @return The question choices map.
     */
    public Map<String, MonarcQuestionChoice> getQuestionChoice() {
        return questionChoice;
    }

    /**
     * Gets the thresholds of the Monarc Method.
     *
     * @return The thresholds map.
     */
    public Map<String, Integer> getThresholds() {
        return thresholds;
    }

    /**
     * Gets the threats of the Monarc Method.
     *
     * @return The threats map.
     */
    public Map<String, MonarcAMVThreats> getThreats() {
        return threats;
    }

    /**
     * Updates the Monarc Method.
     */
    public void updateMethod() {
        MonarcANR.build().setMethod(this);
    }

    /**
     * Sets the method threats of the Monarc Method.
     *
     * @param methodThreats The method threats map.
     */
    public void setMethodThreat(Map<String, MonarcAMVThreats> methodThreats) {
        threats.clear();
        threats.putAll(methodThreats);
    }

    /**
     * Sets the initAnrContext step of the Monarc Method.
     *
     * @param initAnrContext The value of the initAnrContext step.
     */
    public void setInitAnrContext(boolean initAnrContext) {
        this.steps.put("initAnrContext", initAnrContext ? 1 : 0);
    }

    /**
     * Gets the value of the initAnrContext step of the Monarc Method.
     *
     * @return The value of the initAnrContext step.
     */
    @JsonIgnore
    public boolean getInitAnrContext() {
        return this.steps.getOrDefault("initAnrContext", 0) == 1;
    }

    public void setInitEvalContext(boolean initEvalContext) {
        this.steps.put("initEvalContext", initEvalContext ? 1 : 0);
    }

    @JsonIgnore
    public boolean getInitEvalContext() {
        return this.steps.getOrDefault("initEvalContext", 0) == 1;
    }

    public void setInitRiskContext(boolean initRiskContext) {
        this.steps.put("initRiskContext", initRiskContext ? 1 : 0);
    }

    @JsonIgnore
    public boolean getInitRiskContext() {
        return this.steps.getOrDefault("initRiskContext", 0) == 1;
    }

    public void setInitDefContext(boolean initDefContext) {
        this.steps.put("initDefContext", initDefContext ? 1 : 0);
    }

    @JsonIgnore
    public boolean getInitDefContext() {
        return this.steps.getOrDefault("initDefContext", 0) == 1;
    }

    @JsonIgnore
    public void setStepInit(boolean step) {
        setInitAnrContext(step);
        setInitEvalContext(step);
        setInitRiskContext(step);
        setInitDefContext(step);
    }

    public void setModelImpacts(boolean modelImpacts) {
        this.steps.put("modelImpacts", modelImpacts ? 1 : 0);
    }

    @JsonIgnore
    public boolean getModelImpacts() {
        return this.steps.getOrDefault("modelImpacts", 0) == 1;
    }

    public void setModelSummary(boolean modelSummary) {
        this.steps.put("modelSummary", modelSummary ? 1 : 0);
    }

    @JsonIgnore
    public boolean getModelSummary(boolean modelSummary) {
        return this.steps.getOrDefault("modelSummary", 0) == 1;
    }

    @JsonIgnore
    public void setStepModel(boolean stepModel) {
        setModelImpacts(stepModel);
        setModelSummary(stepModel);
    }

    public void setEvalRisks(boolean evalRisk) {
        this.steps.put("evalRisks", evalRisk ? 1 : 0);
    }

    @JsonIgnore
    public boolean getEvalRisks() {
        return this.steps.getOrDefault("evalRisks", 0) == 1;
    }

    public void setEvalPlanRisks(boolean evalPlan) {
        this.steps.put("evalPlanRisks", evalPlan ? 1 : 0);
    }

    @JsonIgnore
    public boolean getEvalPlanRisks() {
        return this.steps.getOrDefault("evalPlanRisks", 0) == 1;
    }

    @JsonIgnore
    public void setStepEval(boolean value) {
        setEvalRisks(value);
        setEvalPlanRisks(value);
    }

    public void setManageRisks(boolean manageRisks) {
        this.steps.put("manageRisks", manageRisks ? 1 : 0);
    }

    @JsonIgnore
    public boolean getManageRisks() {
        return this.steps.getOrDefault("manageRisks", 0) == 1;
    }

    @JsonIgnore
    public void setStepManage(boolean value) {
        setManageRisks(value);
    }
}