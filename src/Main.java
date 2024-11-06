import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final String FILENAME = "plantsFiles/kvetiny.txt";
    private static final String DELIMITER = "\t";

    public static void main(String[] args) throws PlantException {
    demoPlantFromFile();
    demoSort();
    testKvetiny();
    demoException();
    }

    private static void testKvetiny() {
    }

    private static void demoPlantFromFile() throws PlantException {
        PlantsManager manager = new PlantsManager();
        manager.plantFromFile(FILENAME,DELIMITER);
        manager.getPlantsList().forEach(System.out::println);
        manager.plantToFile(FILENAME,DELIMITER);
    }

    private static void demoSort() throws PlantException {
        List<Plant> plantList = new ArrayList<>();
        plantList.add(new Plant("Tulipan", "Nova rostlina", LocalDate.of(2024,1,2),
                LocalDate.of(2024,11,1), 7L));
        plantList.add(new Plant("Karafiat", "Neni na prodej", LocalDate.of(2023,9,2),
                LocalDate.of(2024,10,30), 10L));
        plantList.add(new Plant("Ruze", "Specialni druh", LocalDate.of(2023,12,2),
                LocalDate.of(2024,10,31), 3L));
        plantList.add(new Plant("Ruze", "Specialni druh", LocalDate.of(2023,12,2),
                LocalDate.of(2024,10,28), 3L));

        System.out.println("Serazeni podle nazvu: ");
        Collections.sort(plantList);
        plantList.forEach(System.out::println);
        System.out.println();
        System.out.println("Serazeni dle posledniho dne zalivky: ");
        plantList.sort(Comparator.comparing(Plant::getWatering));
        plantList.forEach(System.out::println);

    }

    private static void demoException() {
        try {
            Plant plant = new Plant("Ruze", "nova", LocalDate.of(2024, 6, 5),
                    LocalDate.of(2024, 7, 8), 6L);
            System.out.println(plant.getFrequencyOfWatering());
            System.out.println(plant.getWatering());
        } catch (PlantException e) {
            System.err.println(e.getMessage());
        }
    }
}