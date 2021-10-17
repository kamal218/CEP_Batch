public class abst {

    public static abstract class ssflat {
        int l = 3;
        int w = 5;

        public abstract boolean LiftWorking(int floor);

        public ssflat(int l, int w) {
            this.l = l;
            this.w = w;
        }

        public ssflat() {

        }

        public int area() {
            return this.l * this.w;
        }
    }

    public static class msflat extends ssflat {
        // either abstract
        public boolean LiftWorking(int floor) {
            System.out.println("Lift is working");
            return true;
        }

        public msflat() {
            super(5, 6);
        }

    }

    public static void main(String[] args) {
        msflat f = new msflat();
        System.out.println(f.area());
    }
}