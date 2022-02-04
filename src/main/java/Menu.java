import java.util.Scanner;

public class Menu {



    //  Arranquem el menu i des d'aki llencem les accions


    /**
     * Constructor de la classe
     */
    public Menu() {

    }

    /**
     * Pantalla inicial del menu
     * opcions:
     * - ask to database
     * - edit database
     * - close app
     */
    public void inici() {
        int resposta;

        do {
            System.out.println("Choose an action:\n");
            System.out.println("(1)- Ask to database\n" +
                    "(2)- Edit database\n" +
                    "(3)- close app\n");
            resposta=readInt();

        } while (resposta!=1&&resposta!=2&&resposta!=3&&resposta!=4);

        switch (resposta){
            case 1 -> askDatabase();
            case 2 -> editDatabase();
            case 3 -> closeApp();
        }
    }

    public void closeApp(){
        /*No  fa res pero així queda més maco el */
    }

    /**
     * Menu d'edició de la base de dades
     * opcions:
     * - drop all tables
     * - drop all data
     * - refill database from xml
     * - create tables
     * - back
     */
    public void editDatabase() {

    }


    /**
     * Menu amb les preguntes que li podem fer a la base de dades
     * opcions:
     * 1- Search by name(String userWord) .. where name like "%userWord%";
     * 2- Search by scoville(int userScoville)  .. where scoville_max>=userScoville and scoville_min<=userScoville;
     * 3- Show all pepper info (mostrar llista i escollir per id)
     * .. Select * from pimiento, cultivo_pimiento, caracteristicas_pimiento where pimiento.id=caracteristicas.id and pimiento.id=cultivo.id;
     * 4- back
     */
    public void askDatabase() {
        int resposta;

        do {
            System.out.println("Choose an action:\n");
            System.out.println("(1)- Search by name\n" +
                    "(2)- Search by scoville\n" +
                    "(3)- show a pepper info\n" +
                    "(4)- back");
                    resposta=readInt();

        } while (resposta!=1&&resposta!=2&&resposta!=3&&resposta!=4);

        switch (resposta){
            case 1 -> searchByName();
            case 2 -> searchByScoville();
            case 3 -> showOnePepperInfo();
            case 4 -> inici();
        }


    }

    /**
     * Menu per a mostrar la informacio d'un pebrot. Imprimeix una llista de noms de pebrots i ids i demana escollir una id.
     * opcions:
     * 1- introduir una id
     * 2- llistar
     * 3- tornar enrere.
     *
     */
    private void showOnePepperInfo() {


    }

    private void searchByScoville() {
    }

    /**
     * Menu per gestionar la busqueda per nom.
     * opcions:
     * (yourWord)- enter a word to search
     * (back)- back
     */
    private void searchByName() {

        System.out.println("Sense implementar");
    }

    /**
     * Llegeix el System.in i el tracta per limitar errors
     */
    public int readInt() {
        int retorn=0;
        Scanner scan = new Scanner(System.in);
        while (true){
            String resposta = scan.nextLine();
            try{
                retorn=Integer.parseInt(resposta);
                break;
            }catch(Exception e){
                System.out.println("Has d'introduir un enter");
            }

        }

        return retorn;
    }


}
