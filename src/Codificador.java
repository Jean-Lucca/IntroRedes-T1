public class Codificador {
    public static void main(String[] args) {
        T8b6t t8b6t = new T8b6t();
        T6b8b t6b8b = new T6b8b();
        if(args[0].equals("nrzi")) {
            System.out.println(Nrzi.encode( Util.hexToBinary(args[1]) ));
        } else if ( args[0].equals("mdif") ) {
            System.out.println(Mdif.encode( Util.hexToBinary(args[1]) ));
        } else if ( args[0].equals("8b6t") ) {
            System.out.println(t8b6t.encode(args[1]));
        } else if ( args[0].equals("6b8b") ) {
            System.out.println(t6b8b.encode( Util.hexToBinary(args[1])));
        } else if ( args[0].equals("hdb3") ) {
            System.out.println(Hdb3.encode( Util.hexToBinary(args[1]) ));
        } else {
            System.out.println("Tecnica nao encontrada");
        }
    }
}