package ClientStuff.Checkers;

import CityStructure.City;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class WrongFieldChecker implements Checker {
    Scanner scanner = new Scanner(System.in);
    String newLine = new String();

    @Override
    public void checkEverything(City city, CheckParameter parameter) {
        if (parameter == CheckParameter.WHITHOUT_ASKING){
            this.checkWithoutAsking(city);
        }else{
            this.checkWithAsking(city);
        }
    }

    @Override
    public void checkEverything(City city){
        checkEverything(city, CheckParameter.WHITHOUT_ASKING);
    }

    private void checkWithAsking(City city) {

        if (city.getName() == "") {
            boolean passedValue = false;
            System.out.println("Looks like city name is empty. \n Enter a new name:");

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

        if (city.getArea() <= 0) {
            boolean passedValue = false;
            System.out.println("Looks like city area is less than 0. \n Enter new area:");

            while (!passedValue) {
                try {

                    float tempArea = Float.parseFloat(newLine = scanner.nextLine());

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

        if (city.getPopulation() <= 0) {
            boolean passedValue = false;
            System.out.println("Looks like city population is less than 0. \n Enter new population:");

            while (!passedValue) {
                try {

                    int tempPopulation = Integer.parseInt(newLine = scanner.nextLine());

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

        if ((city.getCarCode() <= 0) || (city.getCarCode() > 1000)) {
            boolean passedValue = false;
            System.out.println("Looks like city car-code is less than 0 or more then 1000. \n Enter a new car-code:");

            while (!passedValue) {
                try {

                    long tempCarCode = Long.parseLong(newLine = scanner.nextLine());

                    if (tempCarCode > 0 && tempCarCode <=1000) {
                        city.setCarCode(tempCarCode);
                        passedValue = true;
                    } else {
                        System.out.println("Entered value is inappropriate. Try another one:");
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

        if (city.getCoordinates().getX() > 349) {
            boolean passedValue = false;
            System.out.println("Looks like the city X-coordinate is more than 349. \n Enter a new one:");

            while (!passedValue) {
                try {

                    float tempX = Float.parseFloat(newLine = scanner.nextLine());

                    if (tempX > 0 && tempX<=349) {
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
    }

    private void checkWithoutAsking(City city){
        boolean changed = false;

        if (city.getName() == ""){
            city.setName("City" + city.getId());
            if (!changed){
                System.out.println("Some fields have wrong values, they will be replaced with defaults");
                changed = true;
            }
        }

        if (city.getArea() <= 0){
            city.setArea((float) (Math.random()*333) + 1f);
            if (!changed){
                System.out.println("Some fields have wrong values, they will be replaced with defaults");
                changed = true;
            }
        }

        if (city.getPopulation() <= 0){
            city.setPopulation((int) (Math.random()*2000000) + 1);
            if (!changed){
                System.out.println("Some fields have wrong values, they will be replaced with defaults");
                changed = true;
            }
        }

        if ((city.getCarCode() <= 0)||(city.getCarCode() > 1000)){
            city.setCarCode((long) (Math.random()*999) + 1L);
            if (!changed){
                System.out.println("Some fields have wrong values, they will be replaced with defaults");
                changed = true;
            }
        }

        if (city.getCoordinates().getX() > 349){
            city.getCoordinates().setX(349f);
            if (!changed){
                System.out.println("Some fields have wrong values, they will be replaced with defaults");
                changed = true;
            }
        }
    }
}
