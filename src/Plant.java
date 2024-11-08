import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private long frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering,
                 Long frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.setWatering(watering);
        this.setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, String notes) {
        this.notes = "";
        this.planted = LocalDate.now(); // vychozí nastavení datumu zasazení květiny na dnešní datum
        this.watering = LocalDate.now(); // vychozí nastavení datumu poslední zálivky květiny na dnešní datum
    }

    public Plant(String name) {
        this.notes = "";
        this.planted = LocalDate.now(); // vychozí nastavení datumu zasazení květiny na dnešní datum
        this.watering = LocalDate.now(); // vychozí nastavení datumu poslední zálivky květiny na dnešní datum
        this.frequencyOfWatering = 7L; // vychozí nastavení frekvence zálivky na 7 dní
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        this.watering = watering;
        if (watering.isBefore(planted)) {
            throw new PlantException("Vypis seznamu rostlin není úplný! \nZkontroluj správnost zadání parametru 'Datum zasazení rostliny' = nesmí být starší, než datum zálivky! ");
        }
    }

    public void setFrequencyOfWatering(Long frequencyOfWatering) throws PlantException {
        this.frequencyOfWatering = frequencyOfWatering;
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Vypis seznamu rostlin není úplný! \nZkontroluj správnost zadání parametru 'Frekvence zálivky' = nesmí se rovnat 0 anebo být záporný! ");
        }
    }

    public List<Plant> needWateringNow(int plantsList, Plant plant) {
        List<Plant> needWateringNow = new ArrayList<>();
        for (int i = 0; i < plantsList; i++) {
            if (watering.plusDays(frequencyOfWatering).isBefore(LocalDate.now())) {
                needWateringNow.add(plant);
            }
            return new ArrayList<>();
        }
        return needWateringNow;
    }

    public String getWateringDescription() {
        String description = "Rostlina: " + name + ",datum poslední zálivky: "
              + watering + ", datum doporučené další zálivky: "
              + watering.plusDays(frequencyOfWatering);
        return description;
    }

    public void doWateringNow () {
        watering = LocalDate.now();
    }

    public String toFileString (String delimiter){
        return name + delimiter + notes + delimiter + planted + delimiter + watering + delimiter
                + frequencyOfWatering;
    }


    @Override
    public String toString () {
        return "Rostlina: " + name + " (" + notes + ")" + " Datum zasazeni = " + planted + ","
                + " datum posledni zalivky = " + watering + ", frekvence zalivky = "
                + frequencyOfWatering + ".";
    }

    @Override
        public int compareTo (Plant otherPlant){
              return name.compareTo(otherPlant.name);
    }

    public static Plant parse (String line,int lineNumber, String delimiter) throws PlantException {
        String[] parts = line.split(delimiter);
        if (parts.length != 5) { // 5= pocet polozek v radku.
            throw new PlantException("Nespravny pocet polozek na radku cislo " + lineNumber + " !");
        }
        String name = parts[0].trim();
        String notes = parts[1].trim();
        try {
            LocalDate planted = LocalDate.parse(parts[2].trim());
            LocalDate watering = LocalDate.parse(parts[3].trim());
            long frequencyOfWatering = Long.parseLong(parts[4].trim());
            return new Plant(name, notes, planted, watering, frequencyOfWatering);
        } catch (DateTimeParseException e) {
            throw new PlantException("Chybny format data poslední zálivky (" + parts[3].trim()
                    + ") na radku: " + lineNumber);
        } catch (NumberFormatException ex) {
            throw new PlantException("Chybny format frekvence zálivky (" + parts[4].trim()
                    + ") na radku: " + lineNumber);
        }
    }
}
