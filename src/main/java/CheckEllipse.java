

class CheckEllipse {

    public static void main(String arr[]) {
        float h = 5, k = 10, a = 4, b = 6;
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 20; y++) {
                if (checkpoint(h, k, x, y, a, b) <= 1)
                    System.out.print(" 0 ");
                else
                    System.out.print(" - ");
            }
            System.out.println();
        }
//        else if (checkpoint(h, k, x, y, a, b) == 1)
//            System.out.println("На эллипсе");
    }
    static float checkpoint(float h, float k, float x, float y, float a, float b) {
        float p = (float) ((Math.pow((x - h), 2) / Math.pow(a, 2))
                + (Math.pow((y - k), 2) / Math.pow(b, 2)));

        return p;
    }

}
