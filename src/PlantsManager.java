import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class PlantsManager {

    private List<Plant> plantsList = new ArrayList<>();

    public List<Plant> getPlantsList() {
        return plantsList;
    }

    public void setPlantsList(List<Plant> plantsList) {
        this.plantsList = plantsList;
    }

    public void addPlant (Plant plant) {
        this.plantsList.add(plant);
    }

    public Plant getPlant (int index){
        return plantsList.get(index);
    }

    public void removePlant (int index) {
        plantsList.remove(index);
    }

    public void sortPlantName () {
         plantsList.sort(Comparator.comparing(Plant::getName));
    }

    public void sortPlantWateringName () {
        plantsList.sort(Comparator.comparing(Plant::getWatering).thenComparing(Plant::getName));
    }

    public void sortPlantWateringReversed() {
        plantsList.sort(Comparator.comparing(Plant::getPlanted).reversed());
    }


    public List<Plant> getCopyPlant () {
        return List.copyOf(plantsList);
    }

    public List<Plant> getWateringInfo() {
        int index = 1; index++;
        List<Plant> watering = new ArrayList<>();
        for(Plant plant : plantsList) {
            System.out.println(plant.getWateringDescription());
        }
        return watering;
    }

    public void plantFromFile(String filename, String delimiter) throws PlantException {
        try (Scanner scanner = new Scanner (
                new BufferedReader (new FileReader(filename)))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;
                plantsList.add(Plant.parse(line,lineNumber, delimiter));
            }
        } catch (FileNotFoundException e) {
            throw new PlantException(" Soubor " + filename + " nebyl nalezen. ");
        }
    }

    public void plantToFile(String filename, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter((new FileWriter(filename)))) {

            for (Plant plant : plantsList) {
                writer.println(plant.toFileString("\t"));
            }
        } catch (IOException e) {
            throw new PlantException(" Soubor " + filename + " nelze načíst. ");
        }
    }
}
