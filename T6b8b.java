import java.util.HashMap;

public class T6b8b {
    HashMap<String, String> map;
    public T6b8b() {
        map = new HashMap<String, String>();
        map.put("01011001", "000000");
        map.put("01110001", "000001");
        map.put("01110010", "000010");
        map.put("01100101", "000100");
        map.put("01101001", "001000");
        map.put("01010011", "010000");
        map.put("01100011", "100000");
        map.put("01110100", "110000");
        map.put("01000111", "000111");
        map.put("01010101", "010101");
        map.put("01100110", "111111");
        map.put("01001110", "111110");
        map.put("01001101", "111101");
        map.put("01011010", "111011");
        map.put("01010110", "110111");
        map.put("01101100", "101111");
        map.put("01011100", "011111");
        map.put("01001011", "001111");
        map.put("01111000", "111000");
        map.put("01101010", "101010");
    }

    public String encode(String bin) {
        String res = "";
        for ( int i = 0; i < bin.length(); i+=6 ) {
            res += applyEncode(bin.substring(i, i+6));
        }
        //codifica o resultado utilizando a tecnica nrzi
        return Nrzi.encode(res);
    }
    
    public String decode(String signal) {
        String res = "";
        signal = Nrzi.decode(signal);

        for ( int i = 0; i < signal.length(); i+=8 ) {
            res += applyDecode(signal.substring(i, i+8));
        }
        return res;
    }

    //aplica as regras de decodificacao
    public String applyDecode( String bin ) {
        String sufix = bin.substring(0, 2);
        if( sufix.equals("10") ) { return bin.substring(2, bin.length()); }
        else if ( sufix.equals("00") ) { return bin.substring(2, bin.length()); }
        else if ( sufix.equals("11") ) {return bin.substring(2, bin.length()); }
        else {
        return map.get(bin); }
    }

    //aplica as regras de codificacao
    public String applyEncode(String bin) {
        String res = "";
        Integer disparity = Util.countBinaryDisparity(bin);
        if( disparity == 0 ) {
            res = "10" + bin;
        } else if ( (disparity == 2) && !bin.equals("001111") ) {
            res = "00" + bin;
        } else if ( (disparity == -2) && !bin.equals("110000") ) { 
            res = "11" + bin;
        } else {
            res = searchEncode(disparity, bin);
        }
        return res;
    }

    public String searchEncode(int disparity, String bin) {
        String res = "";
        if( disparity == -6 || disparity == +6 ) {
            res = "01" + invertXBit(bin, "_xx__x");
        } else if ( disparity == -2 || disparity == +2 ) {
            res = "01" + invertXBit(bin, "___x__");
        } else if ( disparity == 0 ) {
            res = "01" + bin;
        } else if ( disparity == -4 || disparity == +4 ) {
            res = applyComplementEncode(bin);
        }
        return res;
    }

    public String applyComplementEncode(String bin) {
        if( bin.equals("000001") || bin.equals("000010") || bin.equals("111110") || bin.equals("111101") ) {
            return "01" + invertXBit(bin, "xx____");
        }
        else if ( bin.equals("000100") || bin.equals("001000") || bin.equals("111011") || bin.equals("110111") ) {
            return "01" + invertXBit(bin, "x____x");
        }
        else {
            return "01" + invertXBit(bin, "____xx");
        }
    }

    //inverte os bit nas posicoes x
    public String invertXBit(String bin, String rule) {
        String res = "";
        for(int i = 0; i < bin.length(); i++ ) {
            if( rule.charAt(i) == 'x' ) {
                if(bin.charAt(i) == '1') { res += 0; }
                else { res += '1'; }
            } else {
                res += bin.charAt(i);
            }
        }
        return res;
    }
}
