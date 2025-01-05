import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Army {

    // Attributs
    private String nameArmy;
    private String factionArmy;
    private int maxArmyPoints;
    private Map<String, UnitGroup> unitGroups;

    // Constructors
    public Army() {}
    public Army(String nameArmy, String faction, int maxArmyPoints, Map<String, UnitGroup> unitGroups) {
        this.nameArmy = nameArmy;
        this.factionArmy = faction;
        this.maxArmyPoints = maxArmyPoints;
        this.unitGroups = unitGroups;
    }

    // Setters and getters
    public String getNameArmy() {
        return nameArmy;
    }
    public void setNameArmy(String nameArmy) {
        this.nameArmy = nameArmy;
    }
    public String getFactionArmy() {
        return factionArmy;
    }
    public void setFactionArmy(String factionArmy) {
        this.factionArmy = factionArmy;
    }
    public int getMaxArmyPoints() {
        return maxArmyPoints;
    }
    public void setMaxArmyPoints(int maxArmyPoints) {
        this.maxArmyPoints = maxArmyPoints;
    }
    public Map<String, UnitGroup> getUnitGroups() {
        return unitGroups;
    }
    public void setUnitGroups(Map<String, UnitGroup> unitGroups) {
        this.unitGroups = unitGroups;
    }


    public void printArmy(Army army) {
        System.out.println("========================================");
        System.out.println(" - Name: " + army.getNameArmy());
        System.out.println(" - Faction: " + army.getFactionArmy());
        int sumUnitGroupPoints = army.getUnitGroups().values().stream().mapToInt(UnitGroup::getPointsTotal).sum();
        System.out.println(" - Points: " + sumUnitGroupPoints + "/" + army.getMaxArmyPoints() + " pts");

        System.out.println(" - Unit Groups:");
        for (Map.Entry<String, UnitGroup> entry : army.getUnitGroups().entrySet()) {
            UnitGroup unitGroup = entry.getValue();
            System.out.println(" \t- " + unitGroup.getName() + " (" + unitGroup.getPointsTotal() + " pts)");
            System.out.println(" \t- Units:");

            for (Unit unit : unitGroup.getUnits()) {
                if (unit instanceof Infantry) {
                    Infantry infantry = (Infantry) unit;
                    System.out.println(" \t\t- Infantry: " + infantry.getNameUnit() + " (" + infantry.getPoints() + " pts, Type: " + infantry.getTypeInfantry() + ")");
                } else if (unit instanceof Vehicle) {
                    Vehicle vehicle = (Vehicle) unit;
                    System.out.println(" \t\t- Vehicle: " + vehicle.getNameUnit() + " (" + vehicle.getPoints() + " pts, Type: " + vehicle.getTypeVehicle() + ", Transport Capacity: " + vehicle.getTransportCapacity() + ")");
                }
            }
        }
        System.out.println("========================================\n");
    }

    static Army createNewArmy(Scanner scanner) {
        Army newArmy = new Army();
        System.out.println(" - Choose the name of the Army: ");
        String nameArmy = scanner.nextLine();
        newArmy.setNameArmy(nameArmy);
        System.out.println(" - Choose the faction of the Army: ");
        String factionArmy = scanner.nextLine();
        newArmy.setFactionArmy(factionArmy);
        System.out.println(" - Choose the max number of Army Points: ");
        int maxArmyPoints = scanner.nextInt();
        newArmy.setMaxArmyPoints(maxArmyPoints);
        newArmy.setUnitGroups(new HashMap<>());
        newArmy.printArmy(newArmy);

        return newArmy;
    }

    static String modifyArmyName(Army army, Scanner scanner){
        System.out.println(" - Choose the new name of the Army: ");
        army.setNameArmy(scanner.next());
        army.printArmy(army);

        return army.getNameArmy();
    }

    static String modifyFaction(Army army, Scanner scanner){
        System.out.println(" - Choose the new faction of the Army: ");
        army.setFactionArmy(scanner.next());
        army.printArmy(army);

        return army.getFactionArmy();
    }

    static int modifyMaxArmyPoints(Army army, Scanner scanner){
        System.out.println(" - Choose the max number of Army Points: ");
        army.setMaxArmyPoints(scanner.nextInt());
        army.printArmy(army);

        return army.getMaxArmyPoints();
    }

    static void addUnitGroups(Army army, Scanner scanner, Map<String, UnitGroup> mapOfUnitGroups) {
        System.out.println(" - Choose a unit group to add by its name:\n");
        mapOfUnitGroups.forEach((name, unitGroup) -> {
            unitGroup.printUnitGroup(unitGroup);
        });
        String unitGroupToAdd = scanner.next();
        if(army.getUnitGroups().containsKey(unitGroupToAdd)){
            System.out.println("Warning!\nYou cannot add the same group in an army\n Press ENTER to continue.");
            scanner.nextLine();
        }
        else {
            mapOfUnitGroups.forEach((name, unitGroup) -> {
                if (unitGroupToAdd.equals(unitGroup.getName())) {
                    army.unitGroups.put(unitGroup.getName(), unitGroup);
                }
            });
            army.printArmy(army);
        }
    }

    static void removeUnitGroups(Army army, Scanner scanner) {
        System.out.println(" - Choose a unit group to remove by its name:\n");
        army.getUnitGroups().forEach((name, unitGroup) -> {
            unitGroup.printUnitGroup(unitGroup);
        });
        String unitGroupToRemove = scanner.next();
        army.getUnitGroups().forEach((name, unitGroup) -> {
            if(unitGroupToRemove.equals(unitGroup.getName())) {
                army.unitGroups.remove(unitGroupToRemove);
            }
        });
        army.printArmy(army);
    }
}
