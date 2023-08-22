import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class MatrixTurn {

    private static double[][] rz180;
    private static double[][] rx315;
    private static double[][] ry35;
    private static double[][] ry325;
    private static double[][] rx45;


    public static void main(String[] args) {
        rz180 = new double[3][];
        rx315 = new double[3][];
        ry35 = new double[3][];
        ry325 = new double[3][];
        rx45 = new double[3][];

        rz180[0] = new double[]{-1, 0, 0};
        rz180[1] = new double[]{0, -1, 0};
        rz180[2] = new double[]{0, 0, 1};

        rx315[0] = new double[]{1, 0, 0};
        rx315[1] = new double[]{0, 0.707107, 0.707107};
        rx315[2] = new double[]{0, -0.707107, 0.707107};

        ry35[0] = new double[]{0.819152, 0, 0.573576};
        ry35[1] = new double[]{0, 1, 0};
        ry35[2] = new double[]{-0.573576, 0, 0.819152};

        ry325[0] = new double[]{0.819152, 0, -0.573576};
        ry325[1] = new double[]{0, 1, 0};
        ry325[2] = new double[]{0.573576, 0, 0.819152};

        rx45[0] = new double[]{1, 0, 0};
        rx45[1] = new double[]{0, 0.707107, -0.707107};
        rx45[2] = new double[]{0, 0.707107, 0.707107};

        //Получаем цвет
        double[] vector = getXYZ();
        soutVector(vector, "\nСтартовые координаты");
        //Сдвигаем куб
        vector[0] -= 1;
        vector[1] -= 1;
        //Поворачиваем куб на 180
        vector = calculateNewCoodinates(rz180, vector);
        soutVector(vector, "\nПоворот y 180");
        // x 315
        vector = calculateNewCoodinates(rx315, vector);
        soutVector(vector, "\nПоворот x 315 (-45)");
        //y 35
        vector = calculateNewCoodinates(ry35, vector);
        soutVector(vector, "\nПоворот y 35");

        vector[2] = 0;
        soutVector(vector, "\nНовая координата Z");
        //y325
        vector = calculateNewCoodinates(ry325, vector);
        soutVector(vector, "\nПоворот y 325 (-35)");
        //x 45
        vector = calculateNewCoodinates(rx45, vector);
        soutVector(vector, "\nПоворот x 45");
        //Поворачиваем куб на 180
        vector = calculateNewCoodinates(rz180, vector);
        soutVector(vector, "\nПоворот y 180");
        //Сдвигаем куб
        vector[0] += 1;
        vector[1] += 1;
        soutVector(vector, "\nРезультат");

    }
    private static double[] calculateNewCoodinates(double[][] matrixTurn, double[] vector) {
        double[][] matrixTmp = new double[3][3];
        double[] newCoordinates = new double[3];
        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 2; j++)
                matrixTmp[i][j] = matrixTurn[i][j] * vector[j];
        for (int i = 0; i <= 2; i++)
            newCoordinates[i] = matrixTmp[i][0] + matrixTmp[i][1] + matrixTmp[i][2];
        newCoordinates[0] = new BigDecimal(newCoordinates[0]).setScale(2, RoundingMode.HALF_UP).doubleValue();
        newCoordinates[1] = new BigDecimal(newCoordinates[1]).setScale(2, RoundingMode.HALF_UP).doubleValue();
        newCoordinates[2] = new BigDecimal(newCoordinates[2]).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return newCoordinates;
    }

    private static void soutVector(double[] vector, String message) {
        System.out.println(message);
        System.out.println(
                "{ " + vector[0] +
                        ", " + vector[1] +
                        ", " + vector[2] + " }");
    }

    private static double[] getXYZ() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи X:");
        String x = scanner.nextLine();
        System.out.println("Введи Y:");
        String y = scanner.nextLine();
        System.out.println("Введи Z:");
        String z = scanner.nextLine();

        return new double[]{Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z)};


    }
}
