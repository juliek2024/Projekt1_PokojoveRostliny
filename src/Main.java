import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final String FILENAME = "plantsFiles/kvetiny.txt";
    private static final String DELIMITER = "\t";

    public static void main(String[] args) throws PlantException {
        PlantsManager manager = new PlantsManager();
        try {
            testCode(manager);
        } catch (PlantException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void testCode(PlantsManager manager) throws PlantException {
        manager.plantFromFile(FILENAME, DELIMITER);
        manager.getPlantsList().forEach(System.out::println);

        try {
            System.out.println("\nInformace o zálivce: ");
            manager.getWateringInfo().forEach(System.out::println);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        }

        manager.addPlant( new Plant("Růže","Červená a krásná", LocalDate.of(2022,07,06),
                LocalDate.of(2024,10, 23), 21l));
        for (int i = 1; i < 11; i++) {
            manager.addPlant(new Plant("Tulipan " + i, "na prodej", LocalDate.now(),
                    LocalDate.now(), 14L));
        }
        manager.removePlant(2);

        manager.plantToFile(FILENAME, DELIMITER);

        System.out.print("\nKopie seznamu:\n" + manager.getCopyPlant());

        System.out.println("\n\nSerazeni dle datumu zálivky : ");
        manager.getPlantsList().sort(Comparator.comparing(Plant::getWatering).thenComparing(Plant::getName).reversed());
        manager.getPlantsList().forEach(System.out::println);

        System.out.println("\nSerazeni dle datumu zasazení zpětně: ");
        manager.getPlantsList().sort(Comparator.comparing(Plant::getPlanted).reversed());
        manager.getPlantsList().forEach(System.out::println);

        }
}

