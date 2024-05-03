package lu.itrust.monarc;

/**
 * Represents a category of measures in the Monarc system.
 */
public class MonarcMeasureCategory {
    private int id;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private int status;
    
    /**
     * Constructs a new MonarcMeasureCategory object with the specified parameters.
     * 
     * @param id the ID of the measure category
     * @param label1 the first label of the measure category
     * @param label2 the second label of the measure category
     * @param label3 the third label of the measure category
     * @param label4 the fourth label of the measure category
     * @param status the status of the measure category
     */
    public MonarcMeasureCategory(int id, String label1, String label2, String label3, String label4, int status) {
        this.id = id;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.status = status;
    }

    /**
     * Returns the ID of the measure category.
     * 
     * @return the ID of the measure category
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the measure category.
     * 
     * @param id the ID of the measure category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first label of the measure category.
     * 
     * @return the first label of the measure category
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Sets the first label of the measure category.
     * 
     * @param label1 the first label of the measure category
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    /**
     * Returns the second label of the measure category.
     * 
     * @return the second label of the measure category
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Sets the second label of the measure category.
     * 
     * @param label2 the second label of the measure category
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    /**
     * Returns the third label of the measure category.
     * 
     * @return the third label of the measure category
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Sets the third label of the measure category.
     * 
     * @param label3 the third label of the measure category
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    /**
     * Returns the fourth label of the measure category.
     * 
     * @return the fourth label of the measure category
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Sets the fourth label of the measure category.
     * 
     * @param label4 the fourth label of the measure category
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    /**
     * Returns the status of the measure category.
     * 
     * @return the status of the measure category
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the measure category.
     * 
     * @param status the status of the measure category
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
