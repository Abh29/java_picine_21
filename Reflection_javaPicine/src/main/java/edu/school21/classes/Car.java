package edu.school21.classes;

public class Car {
    private final String MODEL;
    private final Integer MAXSPEED;
    private Boolean isOn;
    private Integer speed;


    public Car(){
        MODEL = "generic car";
        MAXSPEED = 200;
        isOn = false;
        speed = 0;
    }

    public Car(String model ,int maxSpeed) {
        MODEL = model;
        MAXSPEED = maxSpeed;
        speed = 0;
        isOn = false;
    }

    public void turnOn(){
        if (isOn)
            System.out.println("the car's engine is already on");
        else
        {
            isOn = true;
            System.out.println("car engine is now on");
        }
    }

    public void turnOff(){
        if (speed > 0){
            System.out.println("you can't turn engine off while driving\nyou should stop first");
            return;
        }
        if (!isOn)
            System.out.println("the car's engine is already off");
        else {
            isOn = true;
            System.out.println("car engine is now off");
        }
    }

    public int Accelerate(int acceleration){

        if (!isOn){
            System.out.println("car engine is off");
            return 0;
        }

        this.speed += acceleration;
        if (speed > MAXSPEED)
        {
            System.out.println("max speed attended");
            speed = MAXSPEED;
        }
        else if (speed < 0){
            System.out.println("the car has stopped");
            speed = 0;
        }
        return this.speed;
    }

    public boolean honk(String honk, int repeat){
        if (!isOn)
            return false;
        for (int i = 0; i < repeat; i++) {
            System.out.println(honk);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" +
                "MODEL='" + MODEL + '\'' +
                ", MAXSPEED=" + MAXSPEED +
                ", isOn=" + isOn +
                ", speed=" + speed +
                '}';
    }
}
