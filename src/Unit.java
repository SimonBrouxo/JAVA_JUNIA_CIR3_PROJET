import java.util.Scanner;

public abstract class Unit {

    // Attributes
    private String nameUnit;
    private int points;

    // Constructors
    public Unit() {}
    public Unit(String name, int points) {
        this.nameUnit = name;
        this.points = points;
    }

    // Setters and getters
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public String getNameUnit() {
        return nameUnit;
    }
    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    public void printInfantry(Infantry infantry) {}
    public void printVehicle(Vehicle vehicle) {}

    static String modifyUnitName(Unit unit, Scanner scanner){
        System.out.println(" - Choose the new name of the Unit : ");
        unit.setNameUnit(scanner.next());

        return unit.getNameUnit();
    }
}
