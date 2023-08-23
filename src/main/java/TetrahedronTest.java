import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;

public class TetrahedronTest {
    // Функция для вычисления матрицы преобразования для тетраэдра
    public static RealMatrix tetraCoord(double[] A, double[] B, double[] C, double[] D) {
        // Создаем матрицу из векторов v1, v2, v3, где каждый вектор - это строка
        double[][] matData = { { B[0] - A[0], C[0] - A[0], D[0] - A[0] },
                { B[1] - A[1], C[1] - A[1], D[1] - A[1] },
                { B[2] - A[2], C[2] - A[2], D[2] - A[2] } };
        RealMatrix mat = new Array2DRowRealMatrix(matData);

        RealMatrix M1;
        try {
            // Используем LU-разложение для нахождения обратной матрицы
            M1 = new LUDecomposition(mat).getSolver().getInverse();
        } catch (SingularMatrixException e) {
            // Если матрица вырождена (не имеет обратной), обработаем исключение
            M1 = null;
        }

        return M1;
    }

    // Функция для проверки, находится ли точка внутри тетраэдра
    public static boolean pointInside(double[] v1, double[] v2, double[] v3, double[] v4, double[] p) {
        // Вычисляем матрицу преобразования
        RealMatrix M1 = tetraCoord(v1, v2, v3, v4);

        if (M1 == null) {
            // Если матрица не существует, преобразование недопустимо
            return false;
        }

        // Вычисляем новую точку в системе координат тетраэдра
        double[] newp = M1.operate(new double[] { p[0] - v1[0], p[1] - v1[1], p[2] - v1[2] });

        // Проверяем условия, чтобы определить, находится ли точка внутри тетраэдра
        boolean condition1 = newp[0] >= 0 && newp[1] >= 0 && newp[2] >= 0;
        boolean condition2 = newp[0] <= 1 && newp[1] <= 1 && newp[2] <= 1;
        boolean condition3 = newp[0] + newp[1] + newp[2] <= 1;

        // Если все условия выполняются, точка внутри тетраэдра
        return condition1 && condition2 && condition3;
    }

    public static void main(String[] args) {
        // Задаем вершины тетраэдра и точку для проверки
        double[] v1 = { 0.0, 0.0, 0.0 };
        double[] v2 = { 0.0, 1.0, 0.0 };
        double[] v3 = { 1.0, 1.0, 0.0 };
        double[] v4 = { 1.0, 1.0, 1.0 };
        double[] p = { 0.0, 1.0, 0.6 };

        // Проверяем, находится ли точка внутри тетраэдра
        boolean inside = pointInside(v1, v2, v3, v4, p);

        // Выводим результат на экран
        System.out.println("Is the point inside the tetrahedron? " + inside);
    }
}
