

class CheckEllipse {

    public static void main(String arr[]) {
        float xE = 5, yE = 10, w = 4,h = 6;
        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 20; y++) {
                if (checkpoint(xE, yE, x, y, w, h) <= 1)
                    System.out.print(" 0 ");
                else
                    System.out.print(" - ");
            }
            System.out.println();
        }
//      if (checkpoint(h, k, x, y, a, b) == 1)
//           System.out.println("На эллипсе");
    }
    static float checkpoint(float xE, float yE, float x, float y, float w, float h) {
        return (float) ((Math.pow((x - xE), 2) / Math.pow(w, 2)) + (Math.pow((y - yE), 2) / Math.pow(h, 2)));
    }

}
