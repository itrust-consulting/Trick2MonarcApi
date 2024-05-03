package lu.itrust.monarc;

/**
 * Represents a Monarc scale with its properties.
 */
public class MonarcScales {
    private int id;
    private int min;
    private int max;
    private int type;
    
    /**
     * Constructs a new Monarc scale with the specified properties.
     * 
     * @param id   the ID of the scale
     * @param min  the minimum value of the scale
     * @param max  the maximum value of the scale
     * @param type the type of the scale
     */
    public MonarcScales(int id, int min, int max, int type) {
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
}
