package lu.itrust.monarc;

/**
 * The MonarcDuedate class represents a due date in the Monarc system.
 */
public class MonarcDuedate {
    private String date;
    private int timezone_type;
    private String timezone;

    /**
     * Constructs a MonarcDuedate object with the specified date, timezone type, and timezone.
     *
     * @param date The date of the due date.
     * @param timezone_type The type of timezone.
     * @param timezone The timezone of the due date.
     */
    public MonarcDuedate(String date, int timezone_type, String timezone) {
        this.date = date;
        this.timezone_type = timezone_type;
        this.timezone = timezone;
    }

    /**
     * Returns the date of the due date.
     *
     * @return The date of the due date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the due date.
     *
     * @param date The date of the due date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the timezone type of the due date.
     *
     * @return The timezone type of the due date.
     */
    public int getTimezone_type() {
        return timezone_type;
    }

    /**
     * Sets the timezone type of the due date.
     *
     * @param timezone_type The timezone type of the due date.
     */
    public void setTimezone_type(int timezone_type) {
        this.timezone_type = timezone_type;
    }

    /**
     * Returns the timezone of the due date.
     *
     * @return The timezone of the due date.
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the timezone of the due date.
     *
     * @param timezone The timezone of the due date.
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
