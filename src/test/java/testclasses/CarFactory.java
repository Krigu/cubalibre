package testclasses;

import java.util.ArrayList;
import java.util.List;


public class CarFactory {

    public static Car buildCarWithFourWheels(){
        Car car = new Car();
        car.setName("MySuperDuperCar");
        car.setDoors(5);
        car.setManufacturer(buildManufacturer());
        car.setWheels(buildWheels());

        return car;
    }

    private static List<Wheel> buildWheels() {
        Wheel frontLeft = new Wheel(4);
        Wheel frontRight = new Wheel(4);
        Wheel rearLeft = new Wheel(4);
        Wheel rearRight = new Wheel(4);

        List<Wheel> wheels = new ArrayList<Wheel>();
        wheels.add(frontLeft);
        wheels.add(frontRight);
        wheels.add(rearLeft);
        wheels.add(rearRight);

        return wheels;
    }

    public static Manufacturer buildManufacturer(){
        Manufacturer m = new Manufacturer();
        m.setName("SuperCars Inc");

        return m;
    }
}
