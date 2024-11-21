import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Plant implements Comparable<Plant> {
    private String name ;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering,
                 int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.setWatering(watering);
        this.setFrequencyOfWatering((long) frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) throws PlantException {
        this (name, "", LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) throws PlantException {
        this (name, "", LocalDate.now(), LocalDate.now(), 7);
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
        if (watering.isBefore(planted)) {
            throw new PlantException("Vypis seznamu rostlin není úplný! \nZkontroluj správnost zadaného parametru 'Datum zasazení rostliny' = nesmí být starší, než datum zálivky! ");
        }
        this.watering = watering;
    }

    public void setFrequencyOfWatering(Long frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Vypis seznamu rostlin není úplný! \nZkontroluj správnost zadaného" +
                    " parametru 'Frekvence zálivky' = nesmí se rovnat 0 anebo být záporný! ");
        }
        this.frequencyOfWatering = Math.toIntExact(frequencyOfWatering);
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


//    @Override
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
            return new Plant(name, notes, planted, watering, (int) frequencyOfWatering);
        } catch (DateTimeParseException e) {
            throw new PlantException("Chybny format data poslední zálivky (" + parts[3].trim()
                    + ") na radku: " + lineNumber);
        } catch (NumberFormatException ex) {
            throw new PlantException("Chybny format frekvence zálivky (" + parts[4].trim()
                    + ") na radku: " + lineNumber);
        }
    }
}

