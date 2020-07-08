package ClientStuff.Checkers;


import CityStructure.City;
import CityStructure.Coordinates;
import CityStructure.Human;
import CityStructure.StandardOfLiving;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class NullPointerChecker implements Checker {
    Scanner scanner = new Scanner(System.in);
    String newLine = new String();

    @Override
    public void checkEverything(City city, CheckParameter parameter){
        if (parameter == CheckParameter.WITH_ASKING) {
            if (city.getName() == null) {
                boolean passedValue = false;
                System.out.println("Looks like the city name is null. \n Enter the new name:");

                while (!passedValue) {
                    try {
                        if ((newLine = scanner.nextLine()) != "") {
                            city.setName(newLine);
                            passedValue = true;
                        } else {
                            System.out.println("Entered value is inappropriate. Try another one:");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with default value");
                        city.setName("City" + city.getId());
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("I dont feel so good");
                        System.exit(0);
                    }
                }
            }

            if (Float.valueOf(city.getArea()) == null) {
                boolean passedValue = false;
                System.out.println("Looks like the city area is null. \n Enter the new area:");

                while (!passedValue) {
                    try {

                        float tempArea = Float.parseFloat(scanner.nextLine());

                        if (tempArea > 0) {
                            city.setArea(tempArea);
                            passedValue = true;
                        } else {
                            System.out.println("Entered value is inappropriate. Try another one:");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("This is not a number");
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with random value");
                        city.setArea((float) (Math.random() * 333) + 1f);
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("I dont feel so good");
                        System.exit(0);
                    }
                }
            }

            if (Integer.valueOf(city.getPopulation()) == null) {
                boolean passedValue = false;
                System.out.println("Looks like the city population is null. \n Enter the new population:");

                while (!passedValue) {
                    try {

                        int tempPopulation = Integer.parseInt(scanner.nextLine());

                        if (tempPopulation > 0) {
                            city.setPopulation(tempPopulation);
                            passedValue = true;
                        } else {
                            System.out.println("Entered value is inappropriate. Try another one:");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("This is not a number");
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with random value");
                        city.setPopulation((int) (Math.random() * 2000000) + 1);
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("I dont feel so good");
                        System.exit(0);
                    }
                }
            }

            if (Long.valueOf(city.getCarCode()) == null) {
                boolean passedValue = false;
                System.out.println("Looks like the city car-code is null. \n Enter the new car-code:");

                while (!passedValue) {
                    try {

                        long tempCarCode = Long.parseLong(scanner.nextLine());

                        if (tempCarCode > 0 && tempCarCode <= 1000) {
                            city.setCarCode(tempCarCode);
                            passedValue = true;
                        } else {
                            System.out.println("Entered value is inappropriate. Try another one in range from 1 to 1000:");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("This is not a number");
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with random value");
                        city.setCarCode((long) (Math.random() * 999) + 1L);
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("I dont feel so good");
                        System.exit(0);
                    }
                }
            }

            if (city.getCoordinates() == null) {
                boolean passedValue = false;
                System.out.println("Looks like the city coordinates are null. \n Enter the new coordinates:");

                city.setCoordinates(new Coordinates(0, 0));
                changeX(city);
                changeY(city);
            }

            if (Float.valueOf(city.getCoordinates().getX()) == null) {
                System.out.println("Looks like the city X-coordinate is null.");
                changeX(city);
            }

            if (Double.valueOf(city.getCoordinates().getY()) == null) {
                System.out.println("Looks like the city Y-coordinate is null.");
                changeY(city);
            }

            if (city.getEstablishmentDate() == null) {
                boolean passedValue = false;
                System.out.println("Looks like the city establishment date is null. \n Enter the new date:");

                while (!passedValue) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        city.setEstablishmentDate(LocalDate.parse(scanner.nextLine(), formatter));
                        passedValue = true;
                    } catch (NumberFormatException e) {
                        System.out.println("This is not a number.");
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with random value.");
                        city.setEstablishmentDate(LocalDate.of((int) Math.random() * 2020, (int) Math.random() * 11 + 1 , (int) Math.random() * 20 + 1));
                        passedValue = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format. Try yyyy-MM-dd");
                    } catch (Exception e) {
                        System.out.println("I dont feel so good");
                        System.exit(0);
                    }
                }
            }

            if (city.getStandardOfLiving() == null){
                boolean passedValue = false;

                System.out.println("Looks like standard of living is null. \n Possible values are: ULTRA_LOW, MEDIUM, HIGH. \n Enter new standard of living:");
                while (!passedValue) {
                    try {
                        switch (scanner.nextLine()) {
                            case "ULTRA_LOW":
                                city.setStandardOfLiving(StandardOfLiving.ULTRA_LOW);
                                passedValue = true;
                                break;
                            case "HIGH":
                                city.setStandardOfLiving(StandardOfLiving.HIGH);
                                passedValue = true;
                                break;
                            case "MEDIUM":
                                city.setStandardOfLiving(StandardOfLiving.MEDIUM);
                                passedValue = true;
                                break;
                            default:
                                System.out.println("Unknown value, try another one");
                                break;
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with default value.");
                        city.setStandardOfLiving(StandardOfLiving.MEDIUM);
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("I don't feel so good");
                        System.exit(0);
                    }
                }
            }

            if (city.getGovernor() == null){
                boolean passedValue = false;
                System.out.println("Looks like governor is null, enter date of birth:");

                while (!passedValue){
                    try {
                        try {
                            city.setGovernor(new Human(Date.valueOf(scanner.nextLine())));
                            passedValue = true;
                        }catch (IllegalArgumentException e){
                            System.out.println("Illegal argument. Try another one:\n");
                        }
                    }catch (NoSuchElementException e){
                        System.out.println("End of input. Field will be replaced with default value.");
                        city.setGovernor(new Human(Date.valueOf(Integer.toString((int) Math.random() * 2020)+"-"+Integer.toString((int) Math.random() * 12)+"-"+Integer.toString((int) Math.random() * 20))));
                        passedValue = true;
                    }catch (Exception e){
                        System.out.println("I don't feel so good");
                        System.exit(0);
                    }
                }
            }
        }else {
            this.checkEverything(city);
        }
    }

    @Override
    public void checkEverything(City city) {
        if (city.getName() == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.setName("id" + city.getId());
        }

        if (city.getCoordinates() == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.setCoordinates(new Coordinates(100f,100d));
        }

        if (city.getCoordinates().getX() == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.getCoordinates().setX(100f);
        }

        if (Double.valueOf(city.getCoordinates().getY()) == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.getCoordinates().setY(100d);
        }

        if (Double.valueOf(city.getArea()) == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.setArea(51f);
        }

        if (Integer.valueOf(city.getPopulation()) == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.setPopulation(100);
        }

        if (Long.valueOf(city.getMetersAboveSeaLevel()) == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.setMetersAboveSeaLevel(1337L);
        }

        if (city.getEstablishmentDate() == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.setEstablishmentDate(LocalDate.now());
        }

        if (Long.valueOf(city.getCarCode()) == null){
            System.out.println("Looks like some of the fields contain null-values. They will be replaced automatically");

            city.setCarCode(1488L);
        }


        if (city.getStandardOfLiving() == null){
            city.setStandardOfLiving(StandardOfLiving.MEDIUM);
        }

        if (city.getGovernor() == null){
            city.setGovernor(new Human(Date.valueOf("2020-10-10")));
        }
    }

    private void changeX(City city){
        boolean passedValue = false;
        System.out.println("Enter new X-coordinate:");

        while (!passedValue) {
            try {

                float tempX = Float.parseFloat(newLine = scanner.nextLine());

                if (tempX > 0) {
                    city.getCoordinates().setX(tempX);
                    passedValue = true;
                } else {
                    System.out.println("Entered value is inappropriate. Try another one:");
                }

            } catch (NumberFormatException e) {
                System.out.println("This is not a number");
            } catch (NoSuchElementException e) {
                System.out.println("End of input. Field will be replaced with random value");
                city.getCoordinates().setX((float) (Math.random() * 348) + 1f);
                passedValue = true;
            } catch (Exception e) {
                System.out.println("I dont feel so good");
                System.exit(0);
            }
        }
    }

    private void changeY(City city){
        boolean passedValue = false;
        System.out.println("Enter new Y-coordinate:");

        while (!passedValue) {
            try {

                float tempX = Float.parseFloat(newLine = scanner.nextLine());

                if (tempX > 0) {
                    city.getCoordinates().setY(tempX);
                    passedValue = true;
                } else {
                    System.out.println("Entered value is inappropriate. Try another one:");
                }

            } catch (NumberFormatException e) {
                System.out.println("This is not a number.");
            } catch (NoSuchElementException e) {
                System.out.println("End of input. Field will be replaced with random value.");
                city.getCoordinates().setY((float) (Math.random() * 348) + 1f);
                passedValue = true;
            } catch (Exception e) {
                System.out.println("I dont feel so good");
                System.exit(0);
            }
        }
    }
}
