package lu.itrust.monarc;

/**
 * Represents a Monarc delivery.
 */
public class MonarcDeliveries {
    private int id;
    private int typedoc;
    private String name;
    private String version;
    private int status;
    private String classification;
    private String respCustomer;
    private String respSmile;
    private String summaryEvalRisk;

    /**
     * Constructs a Monarc delivery with the specified attributes.
     *
     * @param id                the ID of the delivery
     * @param typedoc           the type of document
     * @param name              the name of the delivery
     * @param version           the version of the delivery
     * @param status            the status of the delivery
     * @param classification    the classification of the delivery
     * @param respCustomer      the responsible customer for the delivery
     * @param respSmile         the responsible SMILE for the delivery
     * @param summaryEvalRisk   the summary evaluation risk of the delivery
     */
    public MonarcDeliveries(int id, int typedoc, String name, String version, int status, String classification,
                            String respCustomer, String respSmile, String summaryEvalRisk) {
        this.id = id;
        this.typedoc = typedoc;
        this.name = name;
        this.version = version;
        this.status = status;
        this.classification = classification;
        this.respCustomer = respCustomer;
        this.respSmile = respSmile;
        this.summaryEvalRisk = summaryEvalRisk;
    }

    /**
     * Returns the ID of the delivery.
     *
     * @return the ID of the delivery
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the type of document.
     *
     * @return the type of document
     */
    public int getTypedoc() {
        return typedoc;
    }

    /**
     * Returns the name of the delivery.
     *
     * @return the name of the delivery
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the version of the delivery.
     *
     * @return the version of the delivery
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the status of the delivery.
     *
     * @return the status of the delivery
     */
    public int getStatus() {
        return status;
    }

    /**
     * Returns the classification of the delivery.
     *
     * @return the classification of the delivery
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Returns the responsible customer for the delivery.
     *
     * @return the responsible customer for the delivery
     */
    public String getRespCustomer() {
        return respCustomer;
    }

    /**
     * Returns the responsible SMILE for the delivery.
     *
     * @return the responsible SMILE for the delivery
     */
    public String getRespSmile() {
        return respSmile;
    }

    /**
     * Returns the summary evaluation risk of the delivery.
     *
     * @return the summary evaluation risk of the delivery
     */
    public String getSummaryEvalRisk() {
        return summaryEvalRisk;
    }
}
