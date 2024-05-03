package lu.itrust.monarc;

/**
 * Placeholder for MonarcData regarding the 'measures' field in the JSON file.
 * 
 * This class represents the measures associated with a MonarcData object. It contains information such as the category,
 * referential, UUID, code, labels, and status of the measures.
 */
public class MonarcMeasures {

    Object category;
    Object referential;
    String uuid;
    String code;
    String label1;
    String label2;
    String label3;
    String label4;
    Integer status;

    /**
     * Constructs a MonarcMeasures object with the specified parameters.
     * 
     * @param category    the category of the measures
     * @param referential the referential of the measures
     * @param uuid        the UUID of the measures
     * @param code        the code of the measures
     * @param label1      the first label of the measures
     * @param label2      the second label of the measures
     * @param label3      the third label of the measures
     * @param label4      the fourth label of the measures
     * @param status      the status of the measures
     */
    public MonarcMeasures(String category, String referential, String uuid, String code, String label1, String label2,
            String label3, String label4, Integer status) {
        this.category = category;
        this.referential = referential;
        this.uuid = uuid;
        this.code = code;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.status = status;
    }

    /**
     * Constructs a MonarcMeasures object with the specified parameters.
     * 
     * @param category    the category of the measures
     * @param referential the referential of the measures
     * @param uuid        the UUID of the measures
     * @param code        the code of the measures
     * @param label1      the first label of the measures
     * @param label2      the second label of the measures
     * @param label3      the third label of the measures
     * @param label4      the fourth label of the measures
     * @param status      the status of the measures
     */
    public MonarcMeasures(MonarcMeasureCategory category, MonarcReferentials referential, String uuid, String code, String label1, String label2,
            String label3, String label4, Integer status) {
        this.category = category;
        this.referential = referential;
        this.uuid = uuid;
        this.code = code;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.status = status;
    }

    /**
     * Returns the category of the measures.
     * 
     * @return the category of the measures
     */
    public Object getCategory() {
        return category;
    }

    /**
     * Sets the category of the measures.
     * 
     * @param category the category of the measures
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the referential of the measures.
     * 
     * @return the referential of the measures
     */
    public Object getReferential() {
        return referential;
    }

    /**
     * Sets the referential of the measures.
     * 
     * @param referential the referential of the measures
     */
    public void setReferential(String referential) {
        this.referential = referential;
    }

    /**
     * Returns the UUID of the measures.
     * 
     * @return the UUID of the measures
     */
    public String getUuid() {
        return uuid;
    }
    
    /**
     * Sets the UUID of the measures.
     * 
     * @param uuid the UUID of the measures
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    /**
     * Returns the code of the measures.
     * 
     * @return the code of the measures
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Sets the code of the measures.
     * 
     * @param code the code of the measures
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Returns the first label of the measures.
     * 
     * @return the first label of the measures
     */
    public String getLabel1() {
        return label1;
    }
    
    /**
     * Sets the first label of the measures.
     * 
     * @param label1 the first label of the measures
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }
    
    /**
     * Returns the second label of the measures.
     * 
     * @return the second label of the measures
     */
    public String getLabel2() {
        return label2;
    }
    
    /**
     * Sets the second label of the measures.
     * 
     * @param label2 the second label of the measures
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }
    
    /**
     * Returns the third label of the measures.
     * 
     * @return the third label of the measures
     */
    public String getLabel3() {
        return label3;
    }
    
    /**
     * Sets the third label of the measures.
     * 
     * @param label3 the third label of the measures
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    /**
     * Returns the fourth label of the measures.
     * 
     * @return the fourth label of the measures
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Sets the fourth label of the measures.
     * 
     * @param label4 the fourth label of the measures
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    /**
     * Returns the status of the measures.
     * 
     * @return the status of the measures
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status of the measures.
     * 
     * @param status the status of the measures
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
