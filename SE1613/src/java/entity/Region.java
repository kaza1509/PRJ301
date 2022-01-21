package entity;

/**
 *
 * @author kazaf
 */
public class Region {

    private int regionId;
    private String regionDescription;

    public Region() {
    }

    public Region(int regionId, String regionDescription) {
        this.regionId = regionId;
        this.regionDescription = regionDescription;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

}
