package lu.itrust.monarc;


/**
 * Class that represents the themes of the threats. 
 */
/**
 * Represents a threat theme in the Monarc system.
 */
public class MonarcThreatTheme {
    
    private int id;
    private String label2;
    private String label1;
    private String label3;
    private String label4;
    
    /**
     * Constructs a new MonarcThreatTheme object with the specified properties.
     * 
     * @param id the ID of the threat theme
     * @param label2 the second label of the threat theme
     * @param label1 the first label of the threat theme
     * @param label3 the third label of the threat theme
     * @param label4 the fourth label of the threat theme
     */
    public MonarcThreatTheme(int id, String label2, String label1, 
                                String label3, String label4) {
        this.id = id;
        this.label2 = label2;
        this.label1 = label1;
        this.label3 = label3;
        this.label4 = label4;
    }
    
    /**
     * Returns the ID of the threat theme.
     * 
     * @return the ID of the threat theme
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the ID of the threat theme.
     * 
     * @param id the ID of the threat theme
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Returns the second label of the threat theme.
     * 
     * @return the second label of the threat theme
     */
    public String getLabel2() {
        return label2;
    }
    
    /**
     * Sets the second label of the threat theme.
     * 
     * @param label2 the second label of the threat theme
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }
    
    /**
     * Returns the first label of the threat theme.
     * 
     * @return the first label of the threat theme
     */
    public String getLabel1() {
        return label1;
    }
    
    /**
     * Sets the first label of the threat theme.
     * 
     * @param label1 the first label of the threat theme
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }
    
    /**
     * Returns the third label of the threat theme.
     * 
     * @return the third label of the threat theme
     */
    public String getLabel3() {
        return label3;
    }
    
    /**
     * Sets the third label of the threat theme.
     * 
     * @param label3 the third label of the threat theme
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }
    
    /**
     * Returns the fourth label of the threat theme.
     * 
     * @return the fourth label of the threat theme
     */
    public String getLabel4() {
        return label4;
    }
    
    /**
     * Sets the fourth label of the threat theme.
     * 
     * @param label4 the fourth label of the threat theme
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

}
