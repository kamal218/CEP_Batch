public class classes {

    public static class ssflat {
        int story = 1;
        String color = "Red";
        int length = 3;// object variable
        // static int length=3;//class variable
        int width = 5;
        String[] person;
        static String parklocation = "flatLocation";

        public int areaOfFlat() {
            int length = 5;
            return this.length * width;
        }

        public ssflat(int s, String c) {
            this(5, 10);           
            story = s;
            color = c;
            System.out.println(this.color);// variable access
            System.out.println(this.areaOfFlat());// method access
        }

        public ssflat(int length, int width) {
            this.length = length;
            this.width = width;
        }

        public static void iamstatic() {
            System.out.println("I am in static Method");
        }

    }

    public static void main(String[] args) {
        // int a=5;
        // System.out.println(a);
        // ssflat f1 = new ssflat();
        // Accessing values
        // String str = f1.color;
        // System.out.println(f1);
        // Value Modification
        // f1.color = "green";
        // f1.story = 2;
        // f1.length = 6;
        // f1.width = 10;
        // System.out.println(f1.story);
        // printHllo();

        // ssflat f1 = new ssflat();
        // ssflat f1 = new ssflat(2, "green");
        // System.out.println(f1.color);

        // ssflat f1 = new ssflat();
        // System.out.println(f1.color);
        // f1.person = new String[5];
        // f1.person[0] = "Rahul";
        // System.out.println(f1.person.length);

        // ssflat f1 = new ssflat();
        // f1.parklocation = "centre";
        // ssflat f2 = new ssflat();
        // System.out.println(f2.parklocation);
        // System.out.println(ssflat.parklocation);
        // printHllo();
        // ssflat.iamstatic();
        // classes obj = new classes();
        // obj.printHllo();
        // printHello();

        ssflat f1 = new ssflat(4,"green");
        System.out.println(f1.areaOfFlat());
    }

    public void printHllo() {
        System.out.println("Hello");
    }

    public static void printHello() {
        System.out.println("I am in static Hello");
    }
}
