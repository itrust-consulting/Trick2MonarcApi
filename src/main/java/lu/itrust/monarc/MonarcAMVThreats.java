package lu.itrust.monarc;

/**
 * Class that represents the Monarc AMV Threats.
 */
public class MonarcAMVThreats {
    private String uuid;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String code;
    private int trend;
    private int qualification;
    private int c;
    private int i;
    private int a;
    private String comment;
    private MonarcThreatTheme theme;

    /**
     * Constructor for MonarcAMVThreats.
     * 
     * @param uuid         The UUID of the threat.
     * @param label1       The first label of the threat.
     * @param label2       The second label of the threat.
     * @param label3       The third label of the threat.
     * @param label4       The fourth label of the threat.
     * @param description1 The first description of the threat.
     * @param description2 The second description of the threat.
     * @param description3 The third description of the threat.
     * @param description4 The fourth description of the threat.
     * @param code         The code of the threat.
     * @param trend        The trend of the threat.
     * @param qualification The qualification of the threat.
     * @param c            The C value of the threat.
     * @param i            The I value of the threat.
     * @param a            The A value of the threat.
     * @param comment      The comment for the threat.
     * @param theme        The theme of the threat.
     */
    public MonarcAMVThreats(String uuid, String label1, String label2, String label3, String label4,
            String description1, String description2, String description3, String description4, String code, int trend,
            int qualification, int c, int i, int a, String comment, MonarcThreatTheme theme) {
        this.uuid = uuid;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.description4 = description4;
        this.code = code;
        this.trend = trend;
        this.qualification = qualification;
        this.c = c;
        this.i = i;
        this.a = a;
        this.comment = comment;
        this.theme = theme;
    }

    /**
     * Get the UUID of the threat.
     * 
     * @return The UUID of the threat.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the UUID of the threat.
     * 
     * @param uuid The UUID of the threat.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get the first label of the threat.
     * 
     * @return The first label of the threat.
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Set the first label of the threat.
     * 
     * @param label1 The first label of the threat.
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    /**
     * Get the second label of the threat.
     * 
     * @return The second label of the threat.
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Set the second label of the threat.
     * 
     * @param label2 The second label of the threat.
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    /**
     * Get the third label of the threat.
     * 
     * @return The third label of the threat.
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Set the third label of the threat.
     * 
     * @param label3 The third label of the threat.
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    /**
     * Get the fourth label of the threat.
     * 
     * @return The fourth label of the threat.
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Set the fourth label of the threat.
     * 
     * @param label4 The fourth label of the threat.
     */
    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    /**
     * Get the first description of the threat.
     * 
     * @return The first description of the threat.
     */
    public String getDescription1() {
        return description1;
    }

    /**
     * Set the first description of the threat.
     * 
     * @param description1 The first description of the threat.
     */
    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    /**
     * Get the second description of the threat.
     * 
     * @return The second description of the threat.
     */
    public String getDescription2() {
        return description2;
    }

    /**
     * Set the second description of the threat.
     * 
     * @param description2 The second description of the threat.
     */
    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    /**
     * Get the third description of the threat.
     * 
     * @return The third description of the threat.
     */
    public String getDescription3() {
        return description3;
    }

    /**
     * Set the third description of the threat.
     * 
     * @param description3 The third description of the threat.
     */
    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    /**
     * Get the fourth description of the threat.
     * 
     * @return The fourth description of the threat.
     */
    public String getDescription4() {
        return description4;
    }

    /**
     * Set the fourth description of the threat.
     * 
     * @param description4 The fourth description of the threat.
     */
    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    /**
     * Get the code of the threat.
     * 
     * @return The code of the threat.
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code of the threat.
     * 
     * @param code The code of the threat.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the trend of the threat.
     * 
     * @return The trend of the threat.
     */
    public int getTrend() {
        return trend;
    }

    /**
     * Set the trend of the threat.
     * 
     * @param trend The trend of the threat.
     */
    public void setTrend(int trend) {
        this.trend = trend;
    }

    /**
     * Get the qualification of the threat.
     * 
     * @return The qualification of the threat.
     */
    public int getQualification() {
        return qualification;
    }

    /**
     * Set the qualification of the threat.
     * 
     * @param qualification The qualification of the threat.
     */
    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    /**
     * Get the C value of the threat.
     * 
     * @return The C value of the threat.
     */
    public int getC() {
        return c;
    }

    /**
     * Set the C value of the threat.
     * 
     * @param c The C value of the threat.
     */
    public void setC(int c) {
        this.c = c;
    }

    /**
     * Get the I value of the threat.
     * 
     * @return The I value of the threat.
     */
    public int getI() {
        return i;
    }

    /**
     * Set the I value of the threat.
     * 
     * @param i The I value of the threat.
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * Get the A value of the threat.
     * 
     * @return The A value of the threat.
     */
    public int getA() {
        return a;
    }

    /**
     * Set the A value of the threat.
     * 
     * @param a The A value of the threat.
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * Get the comment for the threat.
     * 
     * @return The comment for the threat.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the comment for the threat.
     * 
     * @param comment The comment for the threat.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the theme of the threat.
     * 
     * @return The theme of the threat.
     */
    public MonarcThreatTheme getTheme() {
        return theme;
    }

    /**
     * Set the theme of the threat.
     * 
     * @param theme The theme of the threat.
     */
    public void setTheme(MonarcThreatTheme theme) {
        this.theme = theme;
    }

    /**
     * Get the label at the specified index.
     * 
     * @param i The index of the label.
     * @return The label at the specified index.
     */
    public String getLabel(int i) {
        switch (i) {
            case 1:
                return label1;
            case 2:
                return label2;
            case 3:
                return label3;
            case 4:
                return label4;
            default:
                return null;
        }
    }

    /**
     * Get the description at the specified index.
     * 
     * @param i The index of the description.
     * @return The description at the specified index.
     */
    public String getDescription(int i) {
        switch (i) {
            case 1:
                return description1;
            case 2:
                return description2;
            case 3:
                return description3;
            case 4:
                return description4;
            default:
                return null;
        }
    }

}
