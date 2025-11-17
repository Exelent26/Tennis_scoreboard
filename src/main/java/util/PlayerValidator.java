package util;

public class PlayerValidator {

    public static boolean isPlayerNameValid(String name){
        return name != null &&
               name.length() >= 2 &&
               name.length() <= 100 &&
               name.matches("[A-Za-zА-Яа-я\\\\s]+") &&
               !name.isBlank();
    }

    public static boolean isIdValid(Long id){
        return id >= 1 && id <= 999;
    }


}
