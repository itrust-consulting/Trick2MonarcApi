package lu.itrust.monarc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a MonarcObject.
 */
public class MonarcObject {

    private String monarc_version;
    private String type;
    private Map<String, Object> object;
    private Map<String, MonarcCategories> categories;
    private MonarcAsset asset;
    private List<String> rolfTags;
    private List<String> rolfRisks;
    private List<MonarcObject> children;
    private List<String> parentInstanceId;

    /**
     * Constructs a MonarcObject with the specified parameters.
     *
     * @param uuid           The UUID of the object.
     * @param mode           The mode of the object.
     * @param scope          The scope of the object.
     * @param name1          The first name of the object.
     * @param name2          The second name of the object.
     * @param name3          The third name of the object.
     * @param name4          The fourth name of the object.
     * @param label1         The first label of the object.
     * @param label2         The second label of the object.
     * @param label3         The third label of the object.
     * @param label4         The fourth label of the object.
     * @param disponibility  The disponibility of the object.
     * @param position       The position of the object.
     * @param category       The category of the object.
     * @param asset          The asset of the object.
     */
    public MonarcObject(String uuid,
                        int mode,
                        int scope,
                        String name1,
                        String name2,
                        String name3,
                        String name4,
                        String label1,
                        String label2,
                        String label3,
                        String label4,
                        int disponibility,
                        int position,
                        int category,
                        MonarcAsset asset)
    {
        this.type = "object";
        this.monarc_version = "2.12.2";
        this.object = new HashMap<>();
        object.put("uuid", uuid);
        object.put("mode", mode);
        object.put("scope", scope);
        object.put("name1", name1);
        object.put("name2", name2);
        object.put("name3", name3);
        object.put("name4", name4);
        object.put("label1", label1);
        object.put("label2", label2);
        object.put("label3", label3);
        object.put("label4", label4);
        object.put("disponibility", disponibility);
        object.put("position", position);
        object.put("category", category);
        object.put("asset", asset.getUuid());
        object.put("rolfTag", rolfTags);
        this.asset = asset;
        this.rolfTags = new LinkedList<>();
        this.rolfRisks = new LinkedList<>();
        this.children = new LinkedList<>();
        categories = new HashMap<>();
        this.parentInstanceId = new LinkedList<>();
    }

    /**
     * Adds a parent instance to the MonarcObject.
     *
     * @param parentInstance The parent instance to add.
     */
    public void addParentInstance(String parentInstance)
    {
        parentInstanceId.add(parentInstance);
    }
    
    /**
     * Returns the parent instance IDs of the MonarcObject.
     *
     * @return The parent instance IDs.
     */
    @JsonIgnore
    public List<String> getParentInstanceId() {
        return parentInstanceId;
    }

    /**
     * Adds a category to the MonarcObject.
     *
     * @param newCategory The category to add.
     */
    public void addCategory(MonarcCategories newCategory)
    {
        categories.put(newCategory.getId()+"", newCategory);
    }

    /**
     * Returns the UUID of the MonarcObject.
     *
     * @return The UUID.
     */
    @JsonIgnore
    public String getUuid()
    {
        return this.object.get("uuid").toString();
    }

    /**
     * Returns the Monarc version.
     *
     * @return The Monarc version.
     */
    public String getMonarc_version() {
        return monarc_version;
    }

    /**
     * Sets the Monarc version.
     *
     * @param monarc_version The Monarc version to set.
     */
    public void setMonarc_version(String monarc_version) {
        this.monarc_version = monarc_version;
    }

    /**
     * Returns the type of the MonarcObject.
     *
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the MonarcObject.
     *
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the object of the MonarcObject.
     *
     * @return The object.
     */
    public Map<String, Object> getObject() {
        return object;
    }

    /**
     * Sets the object of the MonarcObject.
     *
     * @param object The object to set.
     */
    public void setObject(Map<String, Object> object) {
        this.object = object;
    }

    /**
     * Returns the categories of the MonarcObject.
     *
     * @return The categories.
     */
    public Map<String, MonarcCategories> getCategories() {
        return categories;
    }

    /**
     * Sets the categories of the MonarcObject.
     *
     * @param categories The categories to set.
     */
    public void setCategories(Map<String, MonarcCategories> categories) {
        this.categories = categories;
    }

    /**
     * Returns the asset of the MonarcObject.
     *
     * @return The asset.
     */
    public MonarcAsset getAsset() {
        return asset;
    }

    /**
     * Sets the asset of the MonarcObject.
     *
     * @param asset The asset to set.
     */
    public void setAsset(MonarcAsset asset) {
        this.asset = asset;
    }

    /**
     * Returns the Rolf tags of the MonarcObject.
     *
     * @return The Rolf tags.
     */
    public List<String> getRolfTags() {
        return rolfTags;
    }

    /**
     * Sets the Rolf tags of the MonarcObject.
     *
     * @param rolfTags The Rolf tags to set.
     */
    public void setRolfTags(List<String> rolfTags) {
        this.rolfTags = rolfTags;
    }

    /**
     * Returns the Rolf risks of the MonarcObject.
     *
     * @return The Rolf risks.
     */
    public List<String> getRolfRisks() {
        return rolfRisks;
    }

    /**
     * Sets the Rolf risks of the MonarcObject.
     *
     * @param rolfRisks The Rolf risks to set.
     */
    public void setRolfRisks(List<String> rolfRisks) {
        this.rolfRisks = rolfRisks;
    }

    /**
     * Returns the children of the MonarcObject.
     *
     * @return The children.
     */
    public List<MonarcObject> getChildren() {
        return children;
    }

    /**
     * Sets the children of the MonarcObject.
     *
     * @param children The children to set.
     */
    public void setChildren(List<MonarcObject> children) {
        this.children = children;
    }

}
