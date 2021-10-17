public class inher {
    public static class ssflat {
        int story = 3;
        String color = "Yellow";
        int l = 3;
        int w = 4;

        public void print() {
            System.out.println("I am in ssflat");
        }

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
        boolean lift = true;

        public msflat() {
            super();
        }

        public msflat(int l, int w) {
            super(l, w);
        }
    }

    public static void main(String[] args) {
        msflat f1 = new msflat();
        // System.out.println(f1.color);
        System.out.println(f1.area());
    }
}