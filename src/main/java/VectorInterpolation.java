public class VectorInterpolation {
    public static void main(String[] args) {
        // Начальный и конечный векторы
        double[] startVector = {0.5, 0.5, 0.5};
        double[] endVector = {1, 0.8, 0};

        // Параметр интерполяции (может быть от 0 до 1)
        double t = 0.5;

        // Вычисляем интерполированную точку
        double[] interpolatedVector = new double[startVector.length];
        for (int i = 0; i < startVector.length; i++) {
            interpolatedVector[i] = startVector[i] + t * (endVector[i] - startVector[i]);
        }

        // Выводим результат
        System.out.print("Interpolated Vector: [");
        for (int i = 0; i < interpolatedVector.length; i++) {
            System.out.print(interpolatedVector[i]);
            if (i < interpolatedVector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
