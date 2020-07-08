package CityStructure;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Класс, опысывающий объекты City для коллекции
 */
public class City implements Comparable<City>, Serializable {
    final static long serialVersionUID = 15L;

    private static long previousID = 0;
    private long id; //А Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //А Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float area; //Значение поля должно быть больше 0
    private int population; //Значение поля должно быть больше 0
    private long metersAboveSeaLevel;
    private LocalDate establishmentDate;
    private long carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private StandardOfLiving standardOfLiving; //Поле может быть null
    private Human governor; //Поле может быть nulla

    /**
     * Метод для задания всех полей объекта
     * @param name имя
     * @param coordinates координаты
     * @param area зона
     * @param population население
     * @param metersAboveSeaLevel метров над уровнем моря
     * @param establishmentDate дата основания
     * @param carCode код региона
     * @param standardOfLiving уровень жизни
     * @param governor градоначальник
     */
    public void setCity(String name, Coordinates coordinates, float area, int population, long metersAboveSeaLevel, LocalDate establishmentDate, long carCode, StandardOfLiving standardOfLiving, Human governor) {
        this.name = name;
        this.coordinates = coordinates;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.establishmentDate = establishmentDate;
        this.carCode = carCode;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }

    public City(){
        this.setId();
        this.setCreationDate();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public float getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public long getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public long getCarCode() {
        return carCode;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setMetersAboveSeaLevel(long metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public void setCarCode(long carCode) {
        this.carCode = carCode;
    }

    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    public void setCreationDate(){
        this.creationDate = LocalDateTime.now();
    }

    public void setId() {
        long id = System.currentTimeMillis() % 10000000000L;
        if ( id <= previousID ) {
            id = (previousID + 1) % 10000000000L;
        }
        previousID=id;
        this.id = id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @param city объект класса City
     * @return результат сравнения
     */
    @Override
    public int compareTo(City city) {
        int diff = 0;
        diff += String.valueOf(this.getName()).compareTo(String.valueOf(city.getName()));
        return diff;
    }

    /**
     * @return поток вывода об элементе класса City
     */
    public String toString(){
        return "City ID: " + this.getId() + "\n"
                + "City name: " + this.getName() + "\n"
                + "Standard of living: " + this.getStandardOfLiving() + "\n"
                + "Coordinates: \n" + "\t x: " + this.getCoordinates().getX()
                + "\n \t y: " + this.getCoordinates().getY() + "\n"
                + "Governor: " + Human.valueOf(this.getGovernor());
    }


}

