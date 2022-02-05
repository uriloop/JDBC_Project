
import org.postgresql.core.ResultCursor;

import java.sql.*;

public class DataBase {
    Connection conn;

    /**
     * Constructor de la base de dades on linkem amb la base de dades postgres allotjada a la màquina virtual vagrant
     * @hidden  Les dades de conexió no haurien d'estar al codi font
     */
    public DataBase() {

        String dbURL="jdbc:postgresql://192.168.33.10/biblioteca";
        try {
            Class.forName( "org.postgresql.Driver" );
            conn = DriverManager.getConnection( dbURL,"usuario","password");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Elimina les taules de la base de dades
     */
    public void eliminarTaules() {

        int taulesEliminades=0;
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

        System.out.println(" Eliminades "+taulesEliminades+ " de 3 taules amb exit");

        try {
            ResultSet i=st.executeQuery("SELECT count(*) FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema';");
            System.out.println("Queden "+(i.getInt(1))+" taules per esborrar");
        } catch (SQLException e) {
            System.out.println("Queden 0 taules per esborrar ----- ");        }
    }

    /**
     * Esborra totes les dades de totes les taules
     */
    public void eliminarDades() {

        int taulesBuides=0;
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

        System.out.println(" S'han buidat "+taulesBuides+ " de 3 taules amb exit");

        try {
            ResultSet i= st.executeQuery("select count(*) from pimientos  p,cultivo cu ,caracteristicas ca where p.id=cu.id cu.id=ca.id  ;");
            System.out.println("Queden "+i.getInt("count")+" rows per esborrar");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Omple de dades les taules a partir d'un File en format xml
     */
    public void insertFromXML() {
    }

    /**
     * Executa les querys pertinents de les taules que necessitem
     */
    public void crearTaules() {

        // si no té entrada de dades és segur el statement? en comptes del prepared statement?
        int taulesCreades=0;
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.executeUpdate("create table pimientos (id smallint primary key unique, nombre varchar(20),descripcion text, familia varchar(10),origen varchar(20), img varchar(50));");
            System.out.println("Taula creada amb exit!");
            taulesCreades++;
        } catch (SQLException e) {
            System.out.println("Error: creant una taula");
        }
        try {
            st.executeUpdate("create table caracteristicas (id smallint primary key unique, altura_max smallint, altura_min smallint, ancho_max smallint, ancho_min smallint, scoville_max integer, scoville_min integer, dies_cult_max smallint, dies_cult_min smallint, color varchar(20),rendimiento varchar(20)); ");
            System.out.println("Taula creada amb exit!");
            taulesCreades++;
        } catch (SQLException e) {
            System.out.println("Error: creant una taula");
        }
        try {
            st.executeUpdate("create table cultivo (id smallint primary key unique, prof_semilla decimal, dist_semillas decimal, dist_plantas smallint, temp_cre_max smallint, temp_cre_min smallint , temp_germ_max smallint,temp_germ_min smallint , luz varchar(10));");
            System.out.println("Taula creada amb exit!");
            taulesCreades++;
        } catch (SQLException e) {
            System.out.println("Error: creant una taula");
        }

        try {
            long i = st.executeQuery("SELECT count(*) FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema';").getLong(1);
            System.out.println("Hi ha "+i+" taules creades");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(" Creades "+taulesCreades+ " de 3 taules amb exit");


    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error:  Al tancar la conexió.");
        }
    }
}
