import java.util.Scanner;

public class Main {

    /**
     * Inicia l'aplicació i envia el missatge final de fi de sessió
     *
     * @param args En principì no aporten res. potser més endavant podriem especificar rutes d'arxius, etc.
     */
    public static void main(String[] args) {
        DataBase db= new DataBase();
        Menu menu= new Menu(db);
        System.out.println("Benvingut a l'enciclopèdia de pebrots picants!\n");
        boolean exit=false;
        menu.inici();
        int resposta;
        while (!exit){
            System.out.println("Segur que vols sortir?  sortir=1  quedar-se=0");
            try{
                exit=(resposta=(Integer.parseInt(new Scanner(System.in).nextLine())))==1;
            }catch (Exception e){
                resposta=-1;
            }
            if (resposta==0)menu.inici();
        }
        System.out.println("Yoghurt !!");

    }
}
