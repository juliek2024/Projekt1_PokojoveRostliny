import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final String FILENAME = "plantsFiles/kvetiny-spatne-frekvence.txt";
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
        /*
        manager.addPlant( new Plant("Růže","Červená a krásná", LocalDate.of(2022,07,06),
                LocalDate.of(2024,10, 23), 21l));
        for (int i = 1; i < 11; i++) {
            manager.addPlant(new Plant("Tulipan " + i, "na prodej", LocalDate.now(),
                    LocalDate.now(), 14L));
        }
        manager.removePlant(2);
        */
        manager.plantToFile(FILENAME, DELIMITER);

        System.out.println("\nSerazeni dle nazvu: ");
        manager.getPlantsList().sort(Comparator.comparing(Plant::getName));
        manager.getPlantsList().forEach(System.out::println);

        System.out.println("\nSerazeni dle datumu zpětně: ");
        manager.getPlantsList().sort(Comparator.comparing(Plant::getPlanted).reversed());
        manager.getPlantsList().forEach(System.out::println);

        //System.out.println("\nKopie seznamu:" + manager.getCopyPlant());

        }
}