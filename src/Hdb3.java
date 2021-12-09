public class Hdb3 {

    public static String encode(String bin) {
        String res = "";
        char aux = '-';
        int zeros = 0;
        for( int i = 0; i < bin.length(); i++ ) {
            //Contador de zeros consecutivos
            if(!Util.isValidBin(bin.charAt(i))) { return "Erro"; }
            if( i+4 <= bin.length() ) {
                zeros = Util.countZeros(bin.substring(i,i+4));
            } else { zeros = 0; }

            if( zeros == 4 ) {
                //Par ou Impar
                if( Util.countSignalDisparity(res)%2 == 0 ) {
                    //b00v
                    if(aux == '+') {
                        res += "-00-";
                        aux = '-';
                    } else {
                        res += "+00+";
                        aux = '+';
                    }
                } else {
                    //000v
                    if(aux == '+') {
                        res += "000+";
                        aux = '+';
                    } else {
                        res += "000-";
                        aux = '-';
                    }
                }
                i+=3;
            } else if(bin.charAt(i) == '0') {
                res+='0';
            } else {
                //inverte o sinal
                if( aux == '-' ) {
                    aux = '+';
                } else {
                    aux = '-';
                }
                res += aux;
            }
        }
        return res;
    }

    public static String decode(String signal) {
        String res = "";
        char aux = '-';
        for( int i = 0; i < signal.length(); i++ ) {
            if(!Util.isValidSignal2(signal.charAt(i))) { return "Erro"; }
            if(signal.charAt(i) == '0') {
                res+='0';
            } else {
                //invete o sinal auxiliar e concatena 1
                if( aux == '-' ) {
                    aux = '+';
                    res += '1';
                } else {
                    aux = '-';
                    res += '1';
                }
            }
        }
        return res;
    }
}
