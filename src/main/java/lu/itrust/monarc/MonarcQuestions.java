package lu.itrust.monarc;

/**
 * Represents a Monarc question.
 */
public class MonarcQuestions {
    private String response;
    private int mode;
    private int id;
    private int type;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private int multichoice;
    private int position;

    /**
     * Constructs a Monarc question with the specified parameters.
     *
     * @param response    the response to the question
     * @param mode        the mode of the question
     * @param id          the ID of the question
     * @param type        the type of the question
     * @param label1      the first label of the question
     * @param label2      the second label of the question
     * @param label3      the third label of the question
     * @param label4      the fourth label of the question
     * @param multichoice the multichoice value of the question
     * @param position    the position of the question
     */
    public MonarcQuestions(String response, int mode, int id, int type, String label1, String label2, String label3,
            String label4, int multichoice, int position) {
        this.response = response;
        this.mode = mode;
        this.id = id;
        this.type = type;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.multichoice = multichoice;
        this.position = position;
    }

    /**
     * Returns the response to the question.
     *
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Returns the mode of the question.
     *
     * @return the mode
     */
    public int getMode() {
        return mode;
    }

    /**
     * Returns the ID of the question.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the type of the question.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the first label of the question.
     *
     * @return the first label
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Returns the second label of the question.
     *
     * @return the second label
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Returns the third label of the question.
     *
     * @return the third label
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Returns the fourth label of the question.
     *
     * @return the fourth label
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Returns the multichoice value of the question.
     *
     * @return the multichoice value
     */
    public int getMultichoice() {
        return multichoice;
    }

    /**
     * Returns the position of the question.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }
}
