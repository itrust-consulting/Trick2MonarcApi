package lu.itrust.monarc;

/**
 * The MonarcCategories class represents a category in the Monarc system.
 */
public class MonarcCategories {
    private int id;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private Integer parent;

    /**
     * Constructs a new MonarcCategories object with the specified parameters.
     *
     * @param id      the ID of the category
     * @param label1  the first label of the category
     * @param label2  the second label of the category
     * @param label3  the third label of the category
     * @param label4  the fourth label of the category
     * @param parent  the ID of the parent category (null if there is no parent)
     */
    public MonarcCategories(int id,
                            String label1,
                            String label2,
                            String label3,
                            String label4,
                            Integer parent) {
        this.id = id;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.parent = parent;
    }

    /**
     * Returns the ID of the category.
     *
     * @return the ID of the category
     */
    public int getId() {
        return id;
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
     * Returns the second label of the category.
     *
     * @return the second label of the category
     */
    public String getLabel2() {
        return label2;
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
     * Returns the fourth label of the category.
     *
     * @return the fourth label of the category
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Returns the ID of the parent category.
     *
     * @return the ID of the parent category (null if there is no parent)
     */
    public Integer getParent() {
        return parent;
    }
}
