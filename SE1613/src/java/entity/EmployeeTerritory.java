package entity;

/**
 *
 * @author kazaf
 */
public class EmployeeTerritory {

    private int employeeId;
    private String territoryId;

    public EmployeeTerritory() {
    }

    public EmployeeTerritory(int employeeId, String territoryId) {
        this.employeeId = employeeId;
        this.territoryId = territoryId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

}
