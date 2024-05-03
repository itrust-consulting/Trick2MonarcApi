package lu.itrust.monarc;

/**
 * Represents a choice for a MonarcQuestion.
 */
public class MonarcQuestionChoice {
    private int question;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private int position;

    /**
     * Constructs a MonarcQuestionChoice object with the specified parameters.
     *
     * @param question the question ID
     * @param label1   the first label
     * @param label2   the second label
     * @param label3   the third label
     * @param label4   the fourth label
     * @param position the position of the choice
     */
    public MonarcQuestionChoice(int question, String label1, String label2, String label3, String label4,
                                int position) {
        this.question = question;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.position = position;
    }

    /**
     * Returns the question ID.
     *
     * @return the question ID
     */
    public int getQuestion() {
        return question;
    }

    /**
     * Returns the first label.
     *
     * @return the first label
     */
    public String getLabel1() {
        return label1;
    }

    /**
     * Returns the second label.
     *
     * @return the second label
     */
    public String getLabel2() {
        return label2;
    }

    /**
     * Returns the third label.
     *
     * @return the third label
     */
    public String getLabel3() {
        return label3;
    }

    /**
     * Returns the fourth label.
     *
     * @return the fourth label
     */
    public String getLabel4() {
        return label4;
    }

    /**
     * Returns the position of the choice.
     *
     * @return the position of the choice
     */
    public int getPosition() {
        return position;
    }
}
