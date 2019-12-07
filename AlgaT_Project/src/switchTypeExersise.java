public class switchTypeExersise extends Main {
    private static Integer current_type_exercise = 1;
    private static Boolean goToExType_1 = false;

    public static Integer getCurrent_type_exercise() {
        return current_type_exercise;
    }

    public static void setCurrent_type_exercise(Integer current_type_exercise_tmp) {
        current_type_exercise = current_type_exercise_tmp;
    }

    public static Boolean getGoToExType_1() {
        return goToExType_1;
    }

    public static void setGoToExType_1(Boolean goToExType_1_tmp) {
        goToExType_1 = goToExType_1_tmp;
    }

}
