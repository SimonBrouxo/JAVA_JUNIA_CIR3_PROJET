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
        System.out.println(" - Army Name : " + army.getNameArmy() +
                " - Faction : " + army.getFactionArmy() +
                " - Max Army Points : " + army.getMaxArmyPoints() +
                "\n - Unit Groups : " + army.getUnitGroups()
        );
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

        return army.getNameArmy();
    }

    static String modifyFaction(Army army, Scanner scanner){
        System.out.println(" - Choose the new faction of the Army: ");
        army.setFactionArmy(scanner.next());

        return army.getFactionArmy();
    }

    static int modifyMaxArmyPoints(Army army, Scanner scanner){
        System.out.println(" - Choose the max number of Army Points: ");
        army.setMaxArmyPoints(scanner.nextInt());

        return army.getMaxArmyPoints();
    }

    static void addUnitGroups(Army army, Scanner scanner, Map<String, UnitGroup> mapOfUnitGroups) {
        System.out.println(" - Choose a unit group between:\n");
        mapOfUnitGroups.forEach((name, unitGroup) -> {
            unitGroup.printUnitGroup(unitGroup);
        });
        String unitGroupToAdd = scanner.next();
        mapOfUnitGroups.forEach((name, unitGroup) -> {
            if(unitGroupToAdd.equals(unitGroup.getName())) {
                army.unitGroups.put(unitGroup.getName(), unitGroup);
            }
        });
        army.printArmy(army);
    }

    static void removeUnitGroups(Army army, Scanner scanner) {
        System.out.println(" - Choose a unit group to remove between:\n");
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
