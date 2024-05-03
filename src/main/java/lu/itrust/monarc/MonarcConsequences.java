package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents the consequences of a Monarc assessment.
 */
public class MonarcConsequences {
    
    private int id;
    private int isHidden;
    private int locallyTouched;
    private int c;
    private int i;
    private int d;
    private MonarcScaleImpactType scaleImpactType;
    private Set<String> parentInstanceId;

    /**
     * Constructs a new MonarcConsequences object with the specified parameters.
     * 
     * @param id The ID of the consequences.
     * @param isHidden The flag indicating if the consequences are hidden.
     * @param locallyTouched The flag indicating if the consequences are locally touched.
     * @param c The value of the C parameter.
     * @param i The value of the I parameter.
     * @param d The value of the D parameter.
     * @param scaleImpactType The scale impact type of the consequences.
     */
    public MonarcConsequences(int id, int isHidden, int locallyTouched, int c, int i, int d, MonarcScaleImpactType scaleImpactType) {
        this.id = id;
        this.isHidden = isHidden;
        this.locallyTouched = locallyTouched;
        this.c = c;
        this.i = i;
        this.d = d;
        this.scaleImpactType = scaleImpactType;
        this.parentInstanceId = new HashSet<>();
    }

    /**
     * Adds a parent instance ID to the set of parent instance IDs.
     * 
     * @param parentInstance The parent instance ID to add.
     */
    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }
    
    /**
     * Returns the collection of parent instance IDs.
     * 
     * @return The collection of parent instance IDs.
     */
    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }

    /**
     * Returns the ID of the consequences.
     * 
     * @return The ID of the consequences.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the flag indicating if the consequences are hidden.
     * 
     * @return The flag indicating if the consequences are hidden.
     */
    public int getIsHidden() {
        return isHidden;
    }

    /**
     * Returns the flag indicating if the consequences are locally touched.
     * 
     * @return The flag indicating if the consequences are locally touched.
     */
    public int getLocallyTouched() {
        return locallyTouched;
    }

    /**
     * Returns the value of the C parameter.
     * 
     * @return The value of the C parameter.
     */
    public int getC() {
        return c;
    }

    /**
     * Returns the value of the I parameter.
     * 
     * @return The value of the I parameter.
     */
    public int getI() {
        return i;
    }

    /**
     * Returns the value of the D parameter.
     * 
     * @return The value of the D parameter.
     */
    public int getD() {
        return d;
    }

    /**
     * Sets the flag indicating if the consequences are hidden.
     * 
     * @param isHidden The flag indicating if the consequences are hidden.
     */
    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * Sets the flag indicating if the consequences are locally touched.
     * 
     * @param locallyTouched The flag indicating if the consequences are locally touched.
     */
    public void setLocallyTouched(int locallyTouched) {
        this.locallyTouched = locallyTouched;
    }

    /**
     * Sets the value of the C parameter.
     * 
     * @param c The value of the C parameter.
     */
    public void setC(int c) {
        this.c = c;
    }

    /**
     * Sets the value of the I parameter.
     * 
     * @param i The value of the I parameter.
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * Sets the value of the D parameter.
     * 
     * @param d The value of the D parameter.
     */
    public void setD(int d) {
        this.d = d;
    }

    /**
     * Returns the scale impact type of the consequences.
     * 
     * @return The scale impact type of the consequences.
     */
    public MonarcScaleImpactType getScaleImpactType() {
        return scaleImpactType;
    }

    /**
     * Generates a hash code for the consequences.
     * 
     * @return The hash code for the consequences.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * Checks if the consequences are equal to another object.
     * 
     * @param obj The object to compare with.
     * @return true if the consequences are equal to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MonarcConsequences other = (MonarcConsequences) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
