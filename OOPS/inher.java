public class inher {
    public static class ssflat {
        int story = 3;
        String color = "Yellow";

        public void print() {
            System.out.println("I am in ssflat");
        }
    }

    public static class msflat extends ssflat {
        boolean lift = true;
    }

    public static void main(String[] args) {
        msflat f1 = new msflat();
        System.out.println(f1.color);

    }
}