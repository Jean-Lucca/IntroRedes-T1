public class Mdif {

    public static String encode(String bin) {
        String res = "";
        String aux = "--";
        for(int i=0;i<bin.length();i++) {
            if(!Util.isValidBin(bin.charAt(i))){ return "ERRO"; }
            if( bin.charAt(i) == '0' ) {
                if( aux.charAt(1) == '-' ) { 
                    aux = "+-"; 
                } else {
                    aux = "-+";
                }
            } else {
                if( aux.charAt(1) == '-' ) { 
                    aux = "-+"; 
                } else {
                    aux = "+-";
                }
            }
            res+=aux;
        }
        return res;
    }

    public static String decode(String signal) {
        String aux = '-' + signal;
        String res = "";
        for( int i = 0; i < aux.length()-1; i+=2 ) {
            //caso tenha mudanca de sinal concatena 0
            if(!Util.isValidSignal(signal.charAt(i))){ return "ERRO"; }
            if(aux.charAt(i) != aux.charAt(i+1)) {
                res += '0';
            } else {
                res += '1';
            }
        }
        return res;
    }
    
}
