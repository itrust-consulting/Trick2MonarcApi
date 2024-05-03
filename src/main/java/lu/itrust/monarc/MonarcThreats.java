package lu.itrust.monarc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Monarc threat.
 * This class extends the MonarcAMVThreats class and provides additional functionality.
 */
public class MonarcThreats extends MonarcAMVThreats {
    private int status;
    private int mode;
    private Set<String> parentInstanceId;
    private int localTheme;

    MonarcThreats(String uuid,
            String comment,
            String code,
            String label1,
            String label2,
            String label3,
            String label4,
            String description1,
            String description2,
            String description3,
            String description4,
            int status,
            int mode,
            int trend,
            int qualification,
            int c,
            int i,
            int a,
            int theme) {
        super(uuid, label1, label2, label3, label4, description1, description2, description3, description4, code, trend,
                qualification, c, i, a, comment, null);

        this.status = status;
        this.mode = mode;
        this.parentInstanceId = new HashSet<>();
        localTheme = theme;
    }

    public int getStatus() {
        return status;
    }

    public int getMode() {
        return mode;
    }

    @JsonIgnore
    public MonarcThreatTheme gTheme() {
        return super.getTheme();
    }

    @JsonProperty("theme")
    public int getThemeID() {
        return localTheme;
    }

    public void addParentInstance(String parentInstance) {
        parentInstanceId.add(parentInstance);
    }

    @JsonIgnore
    public Collection<String> getParentInstanceId() {
        return parentInstanceId;
    }

    public int getLocalTheme() {
        return localTheme;
    }
}
