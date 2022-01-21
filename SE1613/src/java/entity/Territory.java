package entity;

/**
 *
 * @author kazaf
 */
public class Territory {

    private String territoryId;
    private String territoryDescription;

    public Territory() {
    }

    public Territory(String territoryId, String territoryDescription) {
        this.territoryId = territoryId;
        this.territoryDescription = territoryDescription;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

}
