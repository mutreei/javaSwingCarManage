package utils;

public class VarifyInput {
    public static boolean isEmpty(String value){
        if(value == ""|| value.trim().equals("")){
            return true;
        }
        return false;
    }
}
