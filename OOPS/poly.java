public class poly {

    static {
        System.out.println("Hello i am static 2");
    }
    static {
        System.out.println("Hello i am static 1");
    }

    public static class ssflat {
        int story = 3;
        String color = "Yellow";
        int l = 3;
        int w = 4;
        // int a = 6;
        final int b = 3;

        final int nstat = 3;// instantiate in consrtuctor

        final static int stat = 5;// instantiate in static block

        public ssflat(int l, int w) {
            this.l = l;
            this.w = w;
        }

        public ssflat() {

        }

        public int area() {
            System.out.println("I am in void of ssflat");
            return this.l * this.w;
        }

        public int area(int a, int b) {
            System.out.println("I am in integer");
            return a + b;
        }

        public double area(double a, double b) {
            System.out.println("I am in double of ssflat");
            return a + b;
        }

        public void fun1() {
            System.out.println("I am in ssflat");
        }

        // public int area(char a, char b) {
        // return a + b;
        // }
    }

    public static class msflat extends ssflat {
        boolean lift = true;
        int l = 5;
        int w = 6;
        int a = 10;

        // override
        // @Override
        public double area(double a, double b) {
            System.out.println("I am in double of msflat");
            return a + b;
        }

        public void fun2() {
            System.out.println("I am in msflat");
        }
    }

    public static void main(String[] args) {

        System.out.println("I am in main");
        // ssflat f1 = new ssflat();
        // f1.area(2, 3);
        // System.out.println(f1.area('a', 'b'));

        // int a=3;
        // long b=a;
        // System.out.println(b);
        // long a = 3;
        // int b = (int) a;
        // System.out.println(b);
        // msflat f1 = new msflat();
        // f1.area(4, 5);

        // 4 combinations
        // msflat obj1 = new msflat();
        // System.out.println(obj1.area(3.0, 4.0));
        // System.out.println(obj1.a);
        // ssflat obj2 = new msflat();
        // System.out.println(obj2.area(3.0, 4.0));
        // System.out.println(obj2.a);
        // ssflat obj3=new ssflat();
        // System.out.println(obj3.area(3.0,5.0));
        // msflat obj4 = new ssflat();
        msflat f1 = new msflat();
    }
}
