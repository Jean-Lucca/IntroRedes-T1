import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class T8b6t {

    private HashMap<String, String> table;

    public T8b6t() {
        try {
            table = Util.build8b6tTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encode(String hex) {
        String curr = "";
        String res = "";
        int positivity = 0;
        hex  = hex.toUpperCase();
        for(int i=0;i<hex.length()-1;i+=2) {

            //pega dois valores em hexadecimal e procura na tabela
            curr = table.get(""+hex.charAt(i) + hex.charAt(i+1));
            if( curr == null ) { return "ERRO"; }
            //calcula a disparidade e inverte o sinal caso necessario
            if( positivity > 0 && Util.countSignalDisparity( curr ) > 0) {

                res += Util.invertSignal(curr);
                positivity += Util.countSignalDisparity( Util.invertSignal( curr ) );

            } else if( positivity < 0 && Util.countSignalDisparity( curr ) < 0) {

                res += Util.invertSignal( curr );
                positivity += Util.countSignalDisparity( Util.invertSignal( curr ) );

            } else {

                res+= curr;
                positivity += Util.countSignalDisparity( curr );

            }
        }
        return res;
    }

    public String decode(String signal) {
        String res = "";
        //procura pelo sinal no mapa se nao encontrar inverte e procura novamente
        for(int i = 0; i < signal.length(); i+=6 ) {
            String key = searchSignal(signal.substring(i, i+6));
            
            if(!key.equals("not found")) {
                res += key;
            } else {
                String aux = Util.invertSignal(signal.substring(i, i+6));
                res += searchSignal(aux);
            }
        }
        return res;
    }

    public String searchSignal (String signal) {
        for( Entry<String, String> entry : table.entrySet() ) {
            if(signal.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return "not found";
    }
}
