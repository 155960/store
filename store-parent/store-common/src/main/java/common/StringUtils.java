package common;

public class StringUtils {

    public static boolean isEmpty(String s){
        if(s!=null&&s.length()>0){
            return false;
        }
        return true;
    }
}
