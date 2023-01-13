package groupwork;

public class LoadDriver {

    private static boolean load = false;

    public static void loading(){
        try {
            if (!load){
            Class.forName("org.postgresql.Driver");
            load = true;}
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
