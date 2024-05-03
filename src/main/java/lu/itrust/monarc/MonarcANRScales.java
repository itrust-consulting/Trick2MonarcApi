package lu.itrust.monarc;

/**
 * The MonarcANRScales class represents a scale used in the Monarc ANR system.
 * It stores the scale's ID, minimum value, maximum value, and type.
 */
public class MonarcANRScales {
    private int id;
    private int min;
    private int max;
    private int type;

    /**
     * Constructs a MonarcANRScales object with the specified ID, minimum value, maximum value, and type.
     * 
     * @param id   the ID of the scale
     * @param min  the minimum value of the scale
     * @param max  the maximum value of the scale
     * @param type the type of the scale
     */
    public MonarcANRScales(int id, int min, int max, int type) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.type = type;
    }

    /**
     * Returns the ID of the scale.
     * 
     * @return the ID of the scale
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the minimum value of the scale.
     * 
     * @return the minimum value of the scale
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximum value of the scale.
     * 
     * @return the maximum value of the scale
     */
    public int getMax() {
        return max;
    }

    /**
     * Returns the type of the scale.
     * 
     * @return the type of the scale
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the ID of the scale.
     * 
     * @param id the ID of the scale
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the minimum value of the scale.
     * 
     * @param min the minimum value of the scale
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the maximum value of the scale.
     * 
     * @param max the maximum value of the scale
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Sets the type of the scale.
     * 
     * @param type the type of the scale
     */
    public void setType(int type) {
        this.type = type;
    }
}
