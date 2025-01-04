import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UnitGroup {

    // Attributes
    private String nameUnitGroup;
    private int pointsTotal;
    private Map<String, Unit> units;

    // Constructors
    public UnitGroup() {}
    public UnitGroup(String name, Map<String, Unit> units) {
        this.nameUnitGroup = name;
        units.forEach((unitName,unit)->{this.pointsTotal += unit.getPoints();});
        this.units = units;
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
    public Map<String, Unit> getUnits() {
        return units;
    }
    public void setUnits(Map<String, Unit> units) {
        this.units = units;
    }

    public void printUnitGroup(UnitGroup unitGroup) {
        System.out.println(" - Unit group Name : " + unitGroup.getName() +
                " - Points : " + unitGroup.getPointsTotal() +
                "\n\t - Units : " + unitGroup.units.toString()
        );
    }

    static UnitGroup createNewUnitGroup(Scanner scanner){
        UnitGroup newUnitGroup = new UnitGroup();
        newUnitGroup.setUnits(new HashMap<String, Unit>());
        System.out.println(" - Choose the name of the Unit group: ");
        newUnitGroup.setName(scanner.next());

        return newUnitGroup;
    }

    static String modifyUnitGroupName(UnitGroup unitGroup, Scanner scanner){
        System.out.println(" - Choose the new name of the Unit Group: ");
        unitGroup.setName(scanner.next());

        return unitGroup.getName();
    }

    static void addUnit(UnitGroup unitGroup, Scanner scanner, Map<String, Unit> mapOfUnits) {
        System.out.println(" - Choose the unit between:\n");
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
            unitGroup.getUnits().put(newUnitToAdd.getNameUnit(), newUnitToAdd);
            unitGroup.pointsTotal += newUnitToAdd.getPoints();
        }
        unitGroup.printUnitGroup(unitGroup);
    }

    static void removeUnit(UnitGroup unitGroup, Scanner scanner) {
        System.out.println(" - Choose the unit between:\n");
        unitGroup.units.forEach((unitName,unit)->{
            if(unit instanceof Infantry){
                ((Infantry)unit).printInfantry((Infantry)unit);
            }
            else if (unit instanceof Vehicle) {
                ((Vehicle)unit).printVehicle((Vehicle)unit);
            }
        });
        String unitNameToRemove = scanner.next();
        if(unitGroup.getUnits().containsKey(unitNameToRemove)){
            unitGroup.getUnits().remove(unitNameToRemove);
            unitGroup.pointsTotal -= unitGroup.getPointsTotal();
        }
        unitGroup.printUnitGroup(unitGroup);
    }

}
