package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a Monarc theme.
 */
public class MonarcThemes {
    private int id;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private Set<String> parentInstanceId;
    
    /**
     * Constructs a MonarcThemes object with the specified parameters.
     * 
     * @param id the ID of the theme
     * @param label1 the first label of the theme
     * @param label2 the second label of the theme
     * @param label3 the third label of the theme
     * @param label4 the fourth label of the theme
     */
    public MonarcThemes(int id, String label1, String label2, String label3, String label4) {
        this.id = id;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        parentInstanceId = new HashSet<>();
    }
    
    /**
     * Returns the ID of the theme.
     * 
     * @return the ID of the theme
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the ID of the theme.
     * 
     * @param id the ID of the theme
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Returns the first label of the theme.
     * 
     * @return the first label of the theme
     */
    public String getLabel1() {
        return label1;
    }
    
    /**
     * Sets the first label of the theme.
     * 
     * @param label1 the first label of the theme
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }
    
    /**
     * Returns the second label of the theme.
     * 
     * @return the second label of the theme
     */
    public String getLabel2() {
        return label2;
    }
    
    /**
     * Sets the second label of the theme.
     * 
     * @param label2 the second label of the theme
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }
    
    /**
     * Returns the third label of the theme.
     * 
     * @return the third label of the theme
     */
    public String getLabel3() {
        return label3;
    }
    
    /**
     * Sets the third label of the theme.
     * 
     * @param label3 the third label of the theme
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }
    
    /**
     * Returns the fourth label of the theme.
     * 
     * @return the fourth label of the theme
     */
    public String getLabel4() {
        return label4;
    }
    
    /**
     * Sets the fourth label of the theme.
     * 
     * @param label4 the fourth label of the theme
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }
    
    /**
     * Adds a parent instance to the theme.
     * 
     * @param parentInstance the parent instance to add
     */
    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }
    
    /**
     * Returns the collection of parent instance IDs associated with the theme.
     * 
     * @return the collection of parent instance IDs
     */
    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }
}
