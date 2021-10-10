public class Nrzi {
    
    public static String encode(String bin) {
        String res = "";
        char aux = '-';
        for(int i=0;i<bin.length();i++) {
            //inverte o sinal quando encontrar 1
            if(!Util.isValidBin(bin.charAt(i))) { return "Erro"; }
            if( bin.charAt(i) == '1' ) {
                if( aux == '-' ) { 
                    aux = '+'; 
                } else {
                    aux = '-';
                }
            }
            res+=aux;
        }
        return res;
    }

    public static String decode(String signal) {
        String aux = '-' + signal;
        String res = "";
        for(int i=1;i<aux.length();i++) {
            //caso tenha mudanca de sinal condatena 1
            if(!Util.isValidSignal(aux.charAt(i))) { return "Erro"; }
            if(aux.charAt(i) == aux.charAt(i-1)) {
                res += '0';
            } else {
                res += '1';
            }
        }
        return res;
    }
    
}
