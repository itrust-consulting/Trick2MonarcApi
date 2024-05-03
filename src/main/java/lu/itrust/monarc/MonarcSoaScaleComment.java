package lu.itrust.monarc;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a comment for a specific scale in the MonarcSoaScaleComment class.
 */
public class MonarcSoaScaleComment {

    @JsonIgnore
    private int id;

    private int scaleIndex;
    private boolean isHidden;
    private String colour;
    private String comment;

    /**
     * Constructs a MonarcSoaScaleComment object with the specified parameters.
     *
     * @param id          the ID of the comment
     * @param scaleIndex  the index of the scale
     * @param isHidden    indicates if the comment is hidden
     * @param colour      the color associated with the comment
     * @param comment     the comment text
     */
    public MonarcSoaScaleComment(int id, int scaleIndex, boolean isHidden, String colour, String comment) {
        this.id = id;
        this.scaleIndex = scaleIndex;
        this.isHidden = isHidden;
        this.colour = colour;
        this.comment = comment;
    }

    /**
     * Returns the index of the scale.
     *
     * @return the scale index
     */
    public int getScaleIndex() {
        return scaleIndex;
    }

    /**
     * Returns whether the comment is hidden.
     *
     * @return true if the comment is hidden, false otherwise
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Returns the color associated with the comment.
     *
     * @return the comment color
     */
    public String getColour() {
        return colour;
    }

    /**
     * Returns the comment text.
     *
     * @return the comment text
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns the ID of the comment.
     *
     * @return the comment ID
     */
    public int getId() {
        return id;
    }

}
