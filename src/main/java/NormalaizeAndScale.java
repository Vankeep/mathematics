public class NormalaizeAndScale {

    public static void main(String[] args) {
        //Первая шкала
        float min1 = 0.0f;
        float max1 = 2.0f;
        //Вторая шкала
        float min2 = 0.5f;
        float max2 = 1.5f;
        //Значение на первой шкале
        float value1 = 2.0f;
        NormalaizeAndScale ns = new NormalaizeAndScale();
        //Получили значение со второй шкалы
        for (float i = 0; i <= 2.1f; i = i + 0.1f) {
            float value2 = ns.scale(i, min1, max1, min2, max2);
            System.out.println("Шкалы " + i + " | " + ((1.0f - value2) + 1));
        }
    }

    /**
     * @param value число из диапазона min1 max2 которое нужно подогнать под диапазон min2 max2
     * @param min1  min диапазона из которого нужно подогнать в диапазон min2 - max2
     * @param max1  max диапазона из которого нужно подогнать в диапазон min2 - max2
     * @param min2  min исходного диапазона
     * @param max2  max исходного диапазона
     * @return подогнанное чило под диапазон min2 max2
     */
    private float scale(float value, float min1, float max1, float min2, float max2) {
        float normalizedValue = (value - min1) / (max1 - min1);
        float scaledValue = min2 + normalizedValue * (max2 - min2);
        return scaledValue;
    }
}