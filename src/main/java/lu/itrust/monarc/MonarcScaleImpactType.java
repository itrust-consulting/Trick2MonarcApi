package lu.itrust.monarc;

/**
 * Represents a Monarc Scale Impact Type.
 */
public class MonarcScaleImpactType {
    private int id;
    private int type;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private int isSys;
    private int isHidden;
    private int position;
    private int scale;

    /**
     * Constructs a MonarcScaleImpactType object with the specified parameters.
     *
     * @param id       The ID of the scale impact type.
     * @param type     The type of the scale impact type.
     * @param label1   The first label of the scale impact type.
     * @param label2   The second label of the scale impact type.
     * @param label3   The third label of the scale impact type.
     * @param label4   The fourth label of the scale impact type.
     * @param isSys    The flag indicating if the scale impact type is system-defined.
     * @param isHidden The flag indicating if the scale impact type is hidden.
     * @param position The position of the scale impact type.
     * @param scale    The scale of the scale impact type.
     */
    public MonarcScaleImpactType(int id, int type,
                                String label1, String label2,
                                String label3, String label4,
                                int isSys, int isHidden,
                                int position, int scale) {
        this.id = id;
        this.type = type;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.isSys = isSys;
        this.isHidden = isHidden;
        this.position = position;
        this.scale = scale;
    }

    /**
     * Returns the ID of the scale impact type.
     *
     * @return The ID of the scale impact type.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the type of the scale impact type.
     *
     * @return The type of the scale impact type.
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the first label of the scale impact type.
     *
     * @return The first label of the scale impact type.
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Returns the second label of the scale impact type.
     *
     * @return The second label of the scale impact type.
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Returns the third label of the scale impact type.
     *
     * @return The third label of the scale impact type.
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Returns the fourth label of the scale impact type.
     *
     * @return The fourth label of the scale impact type.
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Returns the flag indicating if the scale impact type is system-defined.
     *
     * @return The flag indicating if the scale impact type is system-defined.
     */
    public int getIsSys() {
        return isSys;
    }

    /**
     * Returns the flag indicating if the scale impact type is hidden.
     *
     * @return The flag indicating if the scale impact type is hidden.
     */
    public int getIsHidden() {
        return isHidden;
    }

    /**
     * Returns the position of the scale impact type.
     *
     * @return The position of the scale impact type.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the scale of the scale impact type.
     *
     * @return The scale of the scale impact type.
     */
    public int getScale() {
        return scale;
    }
}
