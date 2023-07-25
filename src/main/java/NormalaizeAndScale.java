public class NormalaizeAndScale {

    public static void main(String[] args) {
        //Первая шкала
        float min1 = -1;
        float max1 = 1;
        //Вторая шкала
        float min2 = -0.8f;
        float max2 = 0.8f;
        //Значение на первой шкале
        float value1 = -0.9f;
        NormalaizeAndScale ns = new NormalaizeAndScale();
        //Получили значение со второй шкалы
        float value2 = ns.scale(value1, min1, max1, min2, max2);
        System.out.println("Число с первой шкалы " + value1 + " равно числу "+ value2+" на второй шкале");
    }

    /**
     *
     * @param value число из диапазона min1 min2 которое нужно подогнать под дипазон min2 max2
     * @param min1 от
     * @param max1 до
     * @param min2 от
     * @param max2 до
     * @return подогнанное чило под диапазон min2 max2
     */
    private float scale(float value,float min1, float max1, float min2, float max2){
        /*
        Нормализация - это процесс приведения значений из одного диапазона к значениям в другом диапазоне.
        В данном коде normalizedValue является нормализованным значением value, которое будет находиться в диапазоне от 0.0 до 1.0.
        Это делается путем вычитания min1 из value для смещения его вниз по оси,
        а затем деления на разницу (max1 - min1) для приведения его к диапазону от 0.0 до 1.0.
        Таким образом, normalizedValue представляет, насколько value находится между min1 и max1.
         */
        float normalizedValue = (value - min1) / (max1 - min1);
//        System.out.println("Norm = " + normalizedValue);
        /*
        Масштабирование - это процесс преобразования нормализованных значений в новый диапазон.
        scaledValue представляет собой результат масштабирования normalizedValue из диапазона от 0.0 до 1.0 в новый диапазон от min2 до max2.
        Это достигается умножением normalizedValue на разницу (max2 - min2) и добавлением min2 для смещения результата в новый диапазон.
         */
        float scaledValue = min2 + normalizedValue * (max2 - min2);
        return scaledValue;
    }
}