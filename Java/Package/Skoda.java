package Package;

import Package.Cars;

class Skoda {

    public String model;
    public int torque;

    Skoda(String model, int torque) {
        this.model = model;
        this.torque = torque;
    }

    public static void main(String args[]) {

        Cars cars = new Cars("SKODA", " Laurin & Klement", 1948, 2.34);

        Skoda skoda = new Skoda("OCTAVIA VRS 230", 350);

        System.out.println("Make : " + cars.make + "\n" + "Founder : " + cars.founder + "\n" + "Established Year : "
                + cars.getYear() + "\n" + "Share Price : " + cars.getSharePrice());
        System.out.println("Model : " + skoda.model + "\n" + "Torque : " + skoda.torque + "Nm\n");

    }
}