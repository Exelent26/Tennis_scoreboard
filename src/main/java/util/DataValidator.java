package util;

import exception.ValidationException;

import java.util.UUID;

public class DataValidator {

    public static boolean isPlayerNameValid(String name){
        return name != null &&
               name.length() >= 2 &&
               name.length() <= 100 &&
               name.matches("[A-Za-zА-Яа-я\\s]+") &&
               !name.isBlank();
    }

    public static boolean isPlayerIdValid(Long id){
        return id != null && id >= 1;
    }

    public static UUID validateAndParseMatchId(String id){
        if(id == null)
            throw new ValidationException("id is null");
        try{
            return UUID.fromString(id);
        }
        catch(IllegalArgumentException e){
            throw new ValidationException("id is invalid");
        }
    }

    public static Winner parseAndValidateWinnerParameter(String parameter){
        if (parameter.equalsIgnoreCase("playerOneWin")) {

            return Winner.PLAYER_ONE;
        } else if (parameter.equalsIgnoreCase("playerTwoWin")) {
            return Winner.PLAYER_TWO;
        }else {
            throw new ValidationException("winner parameter is invalid");
        }
    }

}
