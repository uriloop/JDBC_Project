import java.util.Scanner;

public class Menu {


    DataBase db;
    //  Arranquem el menu i des d'aki llencem les accions


    /**
     * Constructor de la classe, li passem la base de dades
     */
    public Menu(DataBase db) {
        this.db = db;
    }

    public Menu() {

    }




    /**
     * Menu per a mostrar la informacio d'un pebrot. Imprimeix una llista de noms de pebrots i ids i demana escollir una id.
     * opcions:
     * 1- introduir una id
     * 2- llistar
     * 3- tornar enrere.
     */
    private void showOnePepperInfo() {
        System.out.println("Escull un numero de pebrot: ");
        int resposta = 0;

        do {
            System.out.println("---SELECT PEBROT---");
            System.out.println("Select a id of a pimiento: (integer from 0 to ...)");
            System.out.println("(-1)- back\n");
            resposta = readInt();

        } while (resposta<-1 && resposta>db.getPepperIdsNum());

        switch (resposta) {
            default -> {
                db.showPepperInfo(resposta);
                showOnePepperInfo();
            }
            case -1 -> inici();
        }


    }

    /**
     * Menu que gestiona la busqueda de pebrots mitjançant el rang d'escoville.
     * opcions:
     * (int)- llistar pebrots en el rang
     * (0)- enrere
     */
    private void searchByScoville() {
        System.out.println("Sense implementar   -1 per tornar enrere");



        if (new Scanner(System.in).nextLine().equals("-1"))inici();


        searchByScoville();
    }

    /**
     * Menu per gestionar la busqueda per nom.
     * opcions:
     * (yourWord)- enter a word to search
     * (back)- back
     */
    private void searchByName() {
        String resposta = "0";

        do {
            System.out.println("---SEARCH BY NAME---");
            System.out.println("Enter name:  (min 3 letters)");
            System.out.println("(0)- back\n");
            resposta = new Scanner(System.in).nextLine();

        } while (!resposta.equals("0"));

        switch (resposta) {
            case "0" -> askDatabase();
            default -> {
                db.getPeppersByStringSearch(resposta);
                searchByName();

            }
        }


    }


    /**
     * Pantalla inicial del menu
     * opcions:
     * - ask to database
     * - edit database
     * - close app
     */
    public void inici() {
        int resposta = 0;

        do {
            System.out.println("---MENU INICIAL---");
            System.out.println("Choose an action:");
            System.out.println("(1)- Ask to database\n" +
                    "(2)- Edit database\n" +
                    "(3)- close app\n");
            resposta = readInt();

        } while (resposta != 1 && resposta != 2 && resposta != 3 );

        switch (resposta) {
            case 1 -> askDatabase();
            case 2 -> editDatabase();
            case 3 -> closeApp();
        }
    }


    /**
     * Menu d'edició de la base de dades
     * opcions:
     * - drop all tables
     * - drop all data
     * - refill database from xml (file.xml)
     * - create tables
     * - back
     */
    public void editDatabase() {
        int resposta = 0;

        do {
            System.out.println("---EDIT DATABASE---");
            System.out.println("Choose an action:");
            System.out.println("(1)- Eliminar les taules\n" +
                    "(2)- Eliminar les dades de les taules\n" +
                    "(3)- Poblar taules desde un xml\n" +
                    "(4)- Crear les taules\n" +
                    "(5)- Enrere");
            resposta = readInt();

        } while (resposta != 1 && resposta != 2 && resposta != 3 && resposta != 4 && resposta != 5);

        switch (resposta) {
            case 1 -> {
                db.eliminarTaules();
                editDatabase();
            }
            case 2 -> {
                db.eliminarDades();
                editDatabase();
            }
            case 3 -> {
                db.insertFromXML();
                editDatabase();
            }
            case 4 -> {
                db.crearTaules();
                editDatabase();
            }
            case 5 -> inici();
        }
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
        int resposta = 0;

        do {
            System.out.println("---ASK DATABASE---");

            System.out.println("Choose an action:");
            System.out.println("(1)- Search by name\n" +
                    "(2)- Search by scoville\n" +
                    "(3)- show a pepper info\n" +
                    "(4)- show all peppers\n" +
                    "(5)- back");
            resposta = readInt();

        } while (resposta != 1 && resposta != 2 && resposta != 3 && resposta != 4&& resposta != 5);

        switch (resposta) {
            case 1 -> searchByName();
            case 2 -> searchByScoville();
            case 3 -> showOnePepperInfo();
            case 4 -> showAllPeppers();
            case 5 -> inici();
        }
    }

    private void showAllPeppers() {

        db.printAllPimientosAllInfo();

    }


    /**
     * Llegeix el System.in en busca de un integer i el tracta per limitar errors
     */
    public int readInt() {
        int retorn = 0;
        Scanner scan = new Scanner(System.in);
        while (true) {
            String resposta = scan.nextLine();
            try {
                retorn = Integer.parseInt(resposta);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Has d'introduir un enter");
            }

        }

        return retorn;
    }

    /**
     * Simplement acava el mètode inici() que hem executat al main i tanca la conexió amb la base de dades
     */
    public void closeApp() {
        try {
            db.close();
        } catch (NullPointerException e) {
            System.out.println("Avís:  No es pot tancar la db perque no està encesa");
        }

    }

    public String modificaPebrot() {
        int resposta = 0;

        do {
            System.out.println("---EDIT ROW---");

            System.out.println("Choose an action:");
            System.out.println("(1)- Change name\n" +
                    "(2)- Change description\n" +
                    "(3)- Eliminar\n" +
                    "(4)- back");
            resposta = readInt();

        } while (resposta != 1 && resposta != 2 && resposta != 3 && resposta != 4);

        switch (resposta) {
            case 1 -> {
                return "name";
            }
            case 2 -> {
                return "desc";
            }
            case 3 -> {
                return "drop";
            }
            case 4 -> {
                return "back";
            }

        }

        return "back";
    }
}
