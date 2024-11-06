import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PlantsManager {

    private List<Plant> plantsList = new ArrayList<>();

    public List<Plant> getPlantsList() {
        return plantsList;
    }

   // public void setPlantsList(List<Plant> plantsList) {
       // this.plantsList = plantsList;
  //  }

    public void addPlant (Plant plant) {
        plantsList.add(plant);
    }

    public Plant getPlant (int index) {
        return plantsList.get(index);
    }

    //public void removePlant (Plant plant) {
       // plantsList.remove(plant);
   // }

    public void removePlant (int index) {
        plantsList.remove(index);
    }

    public List<Plant> listClone (List<Plant> plantsList) {
        List<Plant> clonedList = new ArrayList<>(plantsList);
        //for (Plant plant: plantsList) {
            //clonedList.add(new Plant(""));
        //}
        return clonedList;
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
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter(filename)))) {

            for (Plant plant : plantsList) {
                writer.println(plant.toFileString("\t"));
            }
        } catch (IOException e) {
            throw new PlantException(" Soubor " + filename + " nelze načíst. ");
        }
    }
}
