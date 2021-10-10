import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class Util {

    //Fonte: https://stackoverflow.com/questions/8640803/convert-hex-string-to-binary-string
    public static String hexToBinary(String hex) {
        int len = hex.length() * 4;
        String bin = new BigInteger(hex, 16).toString(2);
        if(bin.length() < len){
            int diff = len - bin.length();
            String pad = "";
            for(int i = 0; i < diff; ++i){
                pad = pad.concat("0");
            }
            bin = pad.concat(bin);
        }
        return bin;
    }

    public static String binaryToHex(String bin) {
        Integer decimal = Integer.parseInt(bin, 2);
        return Integer.toString(decimal, 16);
    }

    public static HashMap<String, String> build8b6tTable() throws IOException {
        File file = new File("table.txt");
        HashMap<String, String> table = new HashMap<String, String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            table.put(st.substring(0, 2) , st.substring(3, st.length()));
        }
        return table;
    }

    public static int countSignalDisparity(String signal) {
        int positive = 0; int negative = 0;
        for(int i = 0; i < signal.length(); i++) {
            if( signal.charAt(i) == '-' ) { 
                negative++;
            } else if (signal.charAt(i) == '+') { 
                positive++;
            }
        }
        return positive - negative;
    }
    
    public static int countBinaryDisparity(String bin) {
        int positive = 0; int negative = 0;
        for(int i = 0; i < bin.length(); i ++) {
            if( bin.charAt(i) == '0' ) { 
                negative++;
            } else if (bin.charAt(i) == '1') { 
                positive ++;
            }
        }
        return positive - negative;
    }

    public static String invertSignal(String signal) {
        String res = "";
        for(int i = 0; i < signal.length(); i ++) {
            if( signal.charAt(i) == '-' ) { 
                res += "+";
            } else if (signal.charAt(i) == '+') { 
                res += "-";
            } else {
                res += signal.charAt(i);
            }
        }
        return res;
    }

    public static boolean isValidBin(char b) {
        if(b == '1' || b == '0') { return true; }
        else return false;
    }

    public static boolean isValidSignal(char s) {
        if(s == '+' || s == '-') { return true; }
        else return false;
    }

    public static boolean isValidSignal2(char s) {
        if(s == '+' || s == '-' || s == '0' || s == '1') { return true; }
        else return false;
    }

    public static int countZeros(String bin) {
        int count = 0;
        for( int i = 0; i < bin.length(); i++ ){
            if(bin.charAt(i)-'0' == 0){ count++; }
        }
        return count;
    }

}
