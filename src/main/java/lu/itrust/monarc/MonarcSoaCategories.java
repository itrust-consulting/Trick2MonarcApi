package lu.itrust.monarc;

/**
 * The MonarcSoaCategories class represents a category in the Monarc SOA (Service-Oriented Architecture) system.
 * It contains information about the referential, labels, and status of the category.
 */
public class MonarcSoaCategories {
    private String referential;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private int status;
    
    /**
     * Constructs a new MonarcSoaCategories object with the specified referential, labels, and status.
     * 
     * @param referential the referential of the category
     * @param label1 the first label of the category
     * @param label2 the second label of the category
     * @param label3 the third label of the category
     * @param label4 the fourth label of the category
     * @param status the status of the category
     */
    public MonarcSoaCategories(String referential, String label1, String label2, String label3, String label4,
            int status) {
        this.referential = referential;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.status = status;
    }

    /**
     * Returns the referential of the category.
     * 
     * @return the referential of the category
     */
    public String getReferential() {
        return referential;
    }

    /**
     * Sets the referential of the category.
     * 
     * @param referential the referential to set
     */
    public void setReferential(String referential) {
        this.referential = referential;
    }

    /**
     * Returns the first label of the category.
     * 
     * @return the first label of the category
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Sets the first label of the category.
     * 
     * @param label1 the first label to set
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    /**
     * Returns the second label of the category.
     * 
     * @return the second label of the category
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Sets the second label of the category.
     * 
     * @param label2 the second label to set
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    /**
     * Returns the third label of the category.
     * 
     * @return the third label of the category
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Sets the third label of the category.
     * 
     * @param label3 the third label to set
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    /**
     * Returns the fourth label of the category.
     * 
     * @return the fourth label of the category
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Sets the fourth label of the category.
     * 
     * @param label4 the fourth label to set
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    /**
     * Returns the status of the category.
     * 
     * @return the status of the category
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the category.
     * 
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
