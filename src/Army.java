import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Army {

    // Attributes
    private String nameArmy;
    private String factionArmy;
    private int maxArmyPoints;
    private Map<String, UnitGroup> unitGroups;

    // Constructors
    public Army() {}

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
        int sumUnitGroupPoints = army.getUnitGroups().values().stream().mapToInt(UnitGroup::getPointsTotal).sum();
        System.out.println("========================================" +
                "\n - Name: " + army.getNameArmy() +
                "\n - Faction: " + army.getFactionArmy() +
                "\n - Points: " + sumUnitGroupPoints + "/" + army.getMaxArmyPoints() + " pts" +
                "\n - Unit Groups:");
        for (Map.Entry<String, UnitGroup> entry : army.getUnitGroups().entrySet()) {
            UnitGroup unitGroup = entry.getValue();
            System.out.println("\t - " + unitGroup.getName() + " (" + unitGroup.getPointsTotal() + " pts)" +
                    "\n\t\t - Units:");

            for (Unit unit : unitGroup.getUnits()) {
                if (unit instanceof Infantry infantry) {
                    System.out.println("\t\t\t - Infantry: " + infantry.getNameUnit() +
                            " (" + infantry.getPoints() + " pts, " +
                            "Type: " + infantry.getTypeInfantry() + ")");
                } else if (unit instanceof Vehicle vehicle) {
                    System.out.println("\t\t\t - Vehicle: " + vehicle.getNameUnit() +
                            " (" + vehicle.getPoints() + " pts, " +
                            "Type: " + vehicle.getTypeVehicle() + ", " +
                            "Capacity: " + vehicle.getTransportCapacity() + ")");
                }
            }
        }
        System.out.println("========================================");
    }

    static Army createNewArmy(Scanner scanner) {
        Army newArmy = new Army();
        System.out.println(" - Choose the name of the Army: ");
        String nameArmy = scanner.nextLine().replace(" ","_");
        newArmy.setNameArmy(nameArmy);
        System.out.println(" - Choose the faction of the Army: ");
        String factionArmy = scanner.nextLine().replace(" ","_");
        newArmy.setFactionArmy(factionArmy);
        System.out.println(" - Choose the max number of Army Points: ");
        int maxArmyPoints = scanner.nextInt();
        newArmy.setMaxArmyPoints(maxArmyPoints);
        newArmy.setUnitGroups(new HashMap<>());
        scanner.nextLine();
        newArmy.printArmy(newArmy);
        System.out.println("\nPress ENTER to continue");

        return newArmy;
    }

    static String modifyArmyName(Army army, Scanner scanner){
        System.out.println(" - Choose the new name of the Army: ");
        army.setNameArmy(scanner.nextLine().replace(" ","_"));
        army.printArmy(army);
        System.out.println("\nPress ENTER to continue");

        return army.getNameArmy();
    }

    static String modifyFaction(Army army, Scanner scanner){
        System.out.println(" - Choose the new faction of the Army: ");
        army.setFactionArmy(scanner.nextLine().replace(" ","_"));
        army.printArmy(army);
        System.out.println("\nPress ENTER to continue");

        return army.getFactionArmy();
    }

    static int modifyMaxArmyPoints(Army army, Scanner scanner){
        System.out.println(" - Choose the max number of Army Points: ");
        army.setMaxArmyPoints(scanner.nextInt());
        scanner.nextLine();
        army.printArmy(army);
        System.out.println("\nPress ENTER to continue");

        return army.getMaxArmyPoints();
    }

    static void addUnitGroups(Army army, Scanner scanner, Map<String, UnitGroup> mapOfUnitGroups) {
        System.out.println(" - Choose a unit group to add by its name:\n");
        mapOfUnitGroups.forEach((name, unitGroup) -> unitGroup.printUnitGroup(unitGroup));
        String unitGroupToAdd = scanner.nextLine();
        if(army.getUnitGroups().containsKey(unitGroupToAdd)){
            System.out.println("Warning!\nYou cannot add the same group in an army\n Press ENTER to continue.");
            scanner.nextLine();
        }
        else {
            mapOfUnitGroups.forEach((name, unitGroup) -> {
                if (unitGroupToAdd.equals(unitGroup.getName())) {
                    int totalPointsInArmy = army.getUnitGroups().values().stream().mapToInt(UnitGroup::getPointsTotal).sum();
                    if(totalPointsInArmy + unitGroup.getPointsTotal() > army.getMaxArmyPoints()){
                        System.out.println("Warning!\nThe max points limit has been exceeded\nPress ENTER to continue.");
                        scanner.nextLine();
                    }
                    else {
                        army.unitGroups.put(unitGroup.getName(), unitGroup);
                    }
                }
            });
            army.printArmy(army);
            System.out.println("\nPress ENTER to continue");
        }
    }

    static void removeUnitGroups(Army army, Scanner scanner) {
        System.out.println(" - Choose a unit group to remove by its name:\n");
        army.getUnitGroups().forEach((name, unitGroup) -> unitGroup.printUnitGroup(unitGroup));
        String unitGroupToRemove = scanner.nextLine();
        army.getUnitGroups().entrySet().removeIf(entry -> entry.getKey().equals(unitGroupToRemove));
        army.printArmy(army);
        System.out.println("\nPress ENTER to continue");
    }
}
