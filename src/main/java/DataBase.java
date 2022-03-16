import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DataBase {
    Connection conn;

    private List<String> idsTrobades;

    /**
     * Constructor de la base de dades on linkem amb la base de dades postgres allotjada a la màquina virtual vagrant
     *
     * @param file
     * @hidden Les dades de conexió no haurien d'estar al codi font
     */
    public DataBase(File file) throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:postgresql://192.168.22.172/biblioteca";

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(dbURL, "usuario", "password");


    }

    /**
     * Elimina les taules de la base de dades
     */
    public void eliminarTaules() {

        int taulesEliminades = 0;
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.executeUpdate("drop table caracteristicas;");
            System.out.println("Taula esborrada amb exit!");
            taulesEliminades++;
        } catch (SQLException e) {
            System.out.println("Error: eliminant una taula");
        }
        try {
            st.executeUpdate("drop table cultivo;");
            System.out.println("Taula esborrada amb exit!");
            taulesEliminades++;
        } catch (SQLException e) {
            System.out.println("Error: eliminant una taula");
        }
        try {
            st.executeUpdate("drop table pimientos;");
            System.out.println("Taula esborrada amb exit!");
            taulesEliminades++;
        } catch (SQLException e) {
            System.out.println("Error: eliminant una taula");
        }

        System.out.println(" Eliminades " + taulesEliminades + " de 3 taules amb exit");

        try {
            ResultSet i = (st.executeQuery("SELECT count(*) FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema';"));
            i.next();
            System.out.println("Hi ha " + i.getLong(1) + " de 3 taules a la base de dades");
        } catch (SQLException u) {
            u.printStackTrace();
        }
    }

    /**
     * Esborra totes les dades de totes les taules
     */
    public void eliminarDades() {

        int taulesBuides = 0;
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.executeUpdate("delete from caracteristicas;");
            System.out.println("Taula buidada amb exit!");
            taulesBuides++;
        } catch (SQLException e) {
            System.out.println("Error: buidant una taula");
        }
        try {
            st.executeUpdate("delete from cultivo;");
            System.out.println("Taula buidada amb exit!");
            taulesBuides++;
        } catch (SQLException e) {
            System.out.println("Error: buidant una taula");
        }
        try {
            st.executeUpdate("delete from pimientos;");
            System.out.println("Taula buidada amb exit!");
            taulesBuides++;
        } catch (SQLException e) {
            System.out.println("Error: buidant una taula");
        }

        System.out.println("S'han buidat " + taulesBuides + " de 3 taules amb exit.");


        try {
            ResultSet i = st.executeQuery("select count(*) from pimientos as p,cultivo as u,caracteristicas as a where p.id=u.id p.id=a.id  ;");
            System.out.println("Queden " + i.getInt("count") + " rows per esborrar");
        } catch (SQLException e) {
            try {
                ResultSet i = (st.executeQuery("SELECT count(*) FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema';"));
                i.next();
                System.out.println("Hi ha " + i.getLong(1) + " de 3 taules a la base de dades");
            } catch (SQLException u) {
                u.printStackTrace();
            }
        }

        System.out.println();
    }

    /**
     * Omple de dades les taules a partir d'un File en format xml
     */
    public void insertFromXML() {

        XMLClass xml = new XMLClass();
        for (int i = 0; i < xml.getNumOfElements(); i++) {
            int id = i + 1;
            try {

                // inserts de la taula pimientos
                PreparedStatement pst = conn.prepareStatement("INSERT INTO pimientos (id,nombre,descripcion,familia,origen,img) VALUES (?,?,?,?,?,?)");

                pst.setInt(1, id);
                pst.setString(2, xml.getNombre(id));
                pst.setString(3, xml.getDescripcion(id));
                pst.setString(4, xml.getFamilia(id));
                pst.setString(5, xml.getOrigen(id));
                pst.setString(6, xml.getImg(id));
                pst.executeUpdate();
                System.out.println();
            } catch (SQLException e) {
                System.out.println("info");
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
            try {
                // inserts de la taula caracteristiques

                PreparedStatement pst = conn.prepareStatement("INSERT INTO caracteristicas (id,altura_max,altura_min,ancho_max,ancho_min,scoville_max,scoville_min,dies_cult_max,dies_cult_min,color,rendimiento) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1, id);
                pst.setInt(2, xml.getAlturaMax(id));
                pst.setInt(3, xml.getAlturaMin(id));
                pst.setFloat(4, (xml.getAnchoMax(id)));
                pst.setFloat(5, (xml.getAnchoMin(id)));
                pst.setInt(6, xml.getScovilleMax(id));
                pst.setInt(7, xml.getScovilleMin(id));
                pst.setInt(8, xml.getDiesCultMax(id));
                pst.setInt(9, xml.getDiesCultMin(id));
                pst.setString(10, xml.getColor(id));
                pst.setString(11, xml.getRendiment(id));
                pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println("carac");
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
            // inserts de la taula cultiu      id prof_semilla dist_semillas  dist_plantas  temp_cre_max temp_cre_min temp_germ_max temp_germ_min luz;
            try {
                PreparedStatement pst = conn.prepareStatement("INSERT INTO cultivo (id,prof_semilla,dist_semillas,dist_plantas,temp_cre_max,temp_cre_min,temp_germ_max,temp_germ_min,luz) VALUES (?,?,?,?,?,?,?,?,?)");

                pst.setInt(1, id);
                pst.setFloat(2, xml.getProfSemilla(id));
                pst.setFloat(3, xml.getDistSemilla(id));
                pst.setInt(4, xml.getDistPlantas(id));
                pst.setInt(5, xml.getTempCrecMax(id));
                pst.setInt(6, xml.getTempCrecMin(id));
                pst.setInt(7, xml.getTempGermMax(id));
                pst.setInt(8, xml.getTempGermMin(id));
                pst.setString(9, xml.getLuz(id));
                pst.executeUpdate();


            } catch (SQLException e) {
                System.out.println("cultiu");

                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }


    }

    /**
     * Executa les querys pertinents de les taules que necessitem
     */
    public void crearTaules() {

        // si no té entrada de dades és segur el statement? en comptes del prepared statement?
        int taulesCreades = 0;
        Statement st = null;
        try {
            st = conn.createStatement();
            try {
                st.executeUpdate("create table pimientos (id smallint primary key unique, nombre varchar(50),descripcion text, familia varchar(20),origen varchar(150), img varchar(150));");
                System.out.println("Taula creada amb exit!");
                taulesCreades++;
            } catch (SQLException e) {
                System.out.println("Error: creant una taula");
            }
            try {
                st.executeUpdate("create table caracteristicas (id integer primary key unique, altura_max integer, altura_min integer, ancho_max integer, ancho_min integer, scoville_max integer, scoville_min integer, dies_cult_max integer, dies_cult_min integer, color varchar(25),rendimiento varchar(30)); ");
                System.out.println("Taula creada amb exit!");
                taulesCreades++;
            } catch (SQLException e) {
                System.out.println("Error: creant una taula");
            }
            try {
                st.executeUpdate("create table cultivo (id integer primary key unique, prof_semilla decimal, dist_semillas decimal, dist_plantas integer, temp_cre_max integer, temp_cre_min integer , temp_germ_max integer,temp_germ_min integer , luz varchar(20));");
                System.out.println("Taula creada amb exit!");
                taulesCreades++;
            } catch (SQLException e) {
                System.out.println("Error: creant una taula");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println(" Creades " + taulesCreades + " de 3 taules amb exit");
        try {
            ResultSet i = (st.executeQuery("SELECT count(*) FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema';"));
            i.next();
            System.out.println("Hi ha " + i.getLong(1) + " de 3 taules creades");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    /**
     * Tanca la conexió amb el servidor
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error:  Al tancar la conexió.");
        }
    }

    /**
     * Imprimeix tots els pebrots amb un format de un pebrot per línia
     */
    public void printAllPimientosAllInfo() {

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from pimientos p, caracteristicas c, cultivo u where p.id=c.id and p.id=u.id;");
            ResultSetMetaData md = rs.getMetaData();
            int columnesNum = md.getColumnCount();
            rs.next();
            while (rs.next()) {

                System.out.println(rs.getInt("id") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Familia: " + rs.getString("familia") +
                        " | Descripción: " + rs.getString("descripcion") +
                        " | Origen: " + rs.getString("origen") +
                        " | Enlace imagen: " + rs.getString("img") +
                        " | Prof. semilla: " + rs.getInt("prof_semilla") +
                        " | Dist. semilla: " + rs.getInt("dist_semillas") +
                        " | Dist. plantas: " + rs.getInt("dist_plantas") +
                        " | Temp. crecimiento: " + rs.getInt("temp_cre_min") +
                        " / " + rs.getInt("temp_cre_max") +
                        " | Temp. germinación: " + rs.getInt("temp_germ_min") +
                        " / " + rs.getInt("temp_germ_max") +
                        " | Luz: " + rs.getString("luz") +
                        " | Altura planta: " + rs.getInt("altura_min") +
                        " / " + rs.getInt("altura_max") +
                        " | Ancho planta: " + rs.getInt("ancho_min") +
                        " / " + rs.getInt("ancho_max") +
                        " | Scoville: " + rs.getInt("scoville_min") +
                        " / " + rs.getInt("scoville_max") +
                        " | Dias cultivo: " + rs.getInt("dies_cult_min") +
                        " / " + rs.getInt("dies_cult_max") +
                        " | Rendimiento" + rs.getString("rendimiento")

                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    /**
     * @return retorna el numero de pebrots que tenim a la base de dades
     */
    public int getPepperIdsNum() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select count(*) from pimientos");
            return rs.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Mostra la info d'un pebrot en concret de la base de dades passant-li la id per a buscar-lo
     *
     * @param pepperId Requereix de la id del pebrot
     */
    public void showPepperInfo(int pepperId) {
        try {

            // inserts de la taula pimientos
            PreparedStatement pst = conn.prepareStatement("Select * from pimientos p, caracteristicas k, cultivo c where p.id=k.id and p.id=c.id and p.id=?");
            pst.setInt(1, pepperId);
            ResultSet rs = pst.executeQuery();
            rs.next();

            System.out.println(rs.getInt("id") +
                    " | Nombre: " + rs.getString("nombre") +
                    " | Familia: " + rs.getString("familia") +
                    " | Descripción: " + rs.getString("descripcion") +
                    " | Origen: " + rs.getString("origen") +
                    " | Enlace imagen: " + rs.getString("img") +
                    " | Prof. semilla: " + rs.getInt("prof_semilla") +
                    " | Dist. semilla: " + rs.getInt("dist_semillas") +
                    " | Dist. plantas: " + rs.getInt("dist_plantas") +
                    " | Temp. crecimiento: " + rs.getInt("temp_cre_min") +
                    " / " + rs.getInt("temp_cre_max") +
                    " | Temp. germinación: " + rs.getInt("temp_germ_min") +
                    " / " + rs.getInt("temp_germ_max") +
                    " | Luz: " + rs.getString("luz") +
                    " | Altura planta: " + rs.getInt("altura_min") +
                    " / " + rs.getInt("altura_max") +
                    " | Ancho planta: " + rs.getInt("ancho_min") +
                    " / " + rs.getInt("ancho_max") +
                    " | Scoville: " + rs.getInt("scoville_min") +
                    " / " + rs.getInt("scoville_max") +
                    " | Dias cultivo: " + rs.getInt("dies_cult_min") +
                    " / " + rs.getInt("dies_cult_max") +
                    " | Rendimiento" + rs.getString("rendimiento")

            );

            // opcions per eliminar-lo, cambiar alguna columna

            String resposta = "";
            switch (new Menu().modificaPebrot()) {
                case "name":
                    System.out.println("What is the new name:");
                    resposta = new Scanner(System.in).nextLine();
                    modificaPebrotName(rs.getInt("id"), resposta);
                    break;
                case "desc":
                    System.out.println("What is the new description:");
                    resposta = new Scanner(System.in).nextLine();
                    modificaPebrotDescripcio(rs.getInt("id"), resposta);
                    break;
                case "drop":
                    do {
                        System.out.println("Sure?   y/n");
                        resposta = new Scanner(System.in).nextLine();
                    } while (!resposta.equals("y") && !resposta.equals("n"));
                    if (resposta.equals("y")) dropRow(rs.getInt("id"));
                    break;
                case "back":
                    break;


            }


        } catch (SQLException e) {
            System.out.println("info");
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

    }

    /**
     * Esborra un pebrot i la seva informació a les diferents taules
     *
     * @param id Parametre, id del pebrot que volem esborrar
     */
    private void dropRow(int id) {

        try {
            Statement st = conn.createStatement();
            st.executeUpdate("delete from pimientos where id='" + id + "';");
            st.executeUpdate("delete from cultivo where id='" + id + "';");
            st.executeUpdate("delete from caracteristicas where id='" + id + "';");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Modifica el contingutde la columna descripció d'un pebrot concret a partir del seu id
     *
     * @param id       Identificador del pebrot a cambiar
     * @param resposta Nou text a introduir enlloc de l'anterior
     */
    private void modificaPebrotDescripcio(int id, String resposta) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("update pimientos set descripcion='" + resposta + "' where id='" + id + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Actualitza el text de la columna nom d'un pebrot concret
     *
     * @param id       identificador del pebrot
     * @param resposta Text nou a cambiar
     */
    private void modificaPebrotName(int id, String resposta) {

        try {
            Statement st = conn.createStatement();
            st.executeUpdate("update pimientos set nombre='" + resposta + "' where id='" + id + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Busca els pebrots que continguin la cadena de caràcters que li passem per paràmetre i els mostra
     *
     * @param resposta text a buscar
     */
    public void getPeppersByStringSearch(String resposta) {

        try {

            // inserts de la taula pimientos
            PreparedStatement pst = conn.prepareStatement("Select id from pimientos p where  p.nombre LIKE ?");


            pst.setString(1, "%" + resposta + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                showPepperInfo(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("info");
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }


    }
}
