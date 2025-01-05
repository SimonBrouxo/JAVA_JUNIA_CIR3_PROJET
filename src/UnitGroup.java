import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class UnitGroup {

    // Attributes
    private String nameUnitGroup;
    private int pointsTotal;
    private List< Unit> units;

    // Constructors
    public UnitGroup() {
        this.units = new ArrayList<>();
    }
    public UnitGroup( String nameUnitGroup, int pointsTotal, List<Unit> units) {
        this.nameUnitGroup = nameUnitGroup;
        units.forEach((unit)-> this.pointsTotal += unit.getPoints());
        this.units = new ArrayList<>(units);
    }

    // Setters and getters
    public String getName() {
        return nameUnitGroup;
    }
    public void setName(String name) {
        this.nameUnitGroup = name;
    }
    public int getPointsTotal() {
        return pointsTotal;
    }
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    public List<Unit> getUnits() {
        return units;
    }

    public void printUnitGroup(UnitGroup unitGroup) {
        System.out.println("========================================" +
                "\n - Name: " + unitGroup.getName() +
                "\n - Total Points: " + unitGroup.getPointsTotal() + " pts" +
                "\n - Units:");
        for (Unit unit : unitGroup.getUnits()) {
            if (unit instanceof Infantry) {
                Infantry infantry = (Infantry) unit;
                System.out.println("\t - Infantry: " + infantry.getNameUnit() +
                        " (" + infantry.getPoints() + " pts, " +
                        "Type: " + infantry.getTypeInfantry() + ")");
            } else if (unit instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) unit;
                System.out.println("\t - Vehicle: " + vehicle.getNameUnit() +
                        " (" + vehicle.getPoints() + " pts, " +
                        "Type: " + vehicle.getTypeVehicle() +
                        ",Capacity: " + vehicle.getTransportCapacity() + ")");
            }
        }
    }


    static UnitGroup createNewUnitGroup(Scanner scanner){
        UnitGroup newUnitGroup = new UnitGroup();
        System.out.println(" - Choose the name of the Unit group: ");
        newUnitGroup.setName(scanner.next());
        newUnitGroup.printUnitGroup(newUnitGroup);

        return newUnitGroup;
    }

    static String modifyUnitGroupName(UnitGroup unitGroup, Scanner scanner){
        System.out.println(" - Choose the new name of the Unit Group: ");
        unitGroup.setName(scanner.next());
        unitGroup.printUnitGroup(unitGroup);

        return unitGroup.getName();
    }

    static void addUnit(UnitGroup unitGroup, Scanner scanner, Map<String, Unit> mapOfUnits) {
        System.out.println(" - Choose the unit to add by its name:\n");
        mapOfUnits.forEach((name,unit)->{
            if(unit instanceof Infantry){
                ((Infantry)unit).printInfantry((Infantry)unit);
            }
            else if (unit instanceof Vehicle) {
                ((Vehicle)unit).printVehicle((Vehicle)unit);
            }
        });
        String unitNameToAdd = scanner.next();
        if(mapOfUnits.containsKey(unitNameToAdd)){
            Unit newUnitToAdd = mapOfUnits.get(unitNameToAdd);
            unitGroup.getUnits().add(newUnitToAdd);
            unitGroup.pointsTotal += newUnitToAdd.getPoints();
        }
        unitGroup.printUnitGroup(unitGroup);
    }

    static void removeUnit(UnitGroup unitGroup, Scanner scanner) {
        System.out.println(" - Choose the unit to remove by its index:\n");
        for (int i = 0; i < unitGroup.getUnits().size(); i++) {
            Unit unit = unitGroup.getUnits().get(i);
            System.out.println(i + " - " + unit.getNameUnit() + " (" + unit.getPoints() + " points)");
        }
        int indexToRemove = scanner.nextInt();
        if (indexToRemove >= 0 && indexToRemove < unitGroup.getUnits().size()) {
            Unit removedUnit = unitGroup.getUnits().remove(indexToRemove);
            unitGroup.pointsTotal -= removedUnit.getPoints();
        }
        unitGroup.printUnitGroup(unitGroup);
    }

}
