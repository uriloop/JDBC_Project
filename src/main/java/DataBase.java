import org.postgresql.core.ResultCursor;
import org.w3c.dom.ls.LSOutput;

import javax.sql.rowset.spi.XmlReader;
import java.io.File;
import java.sql.*;

public class DataBase {
    Connection conn;


    /**
     * Constructor de la base de dades on linkem amb la base de dades postgres allotjada a la màquina virtual vagrant
     *
     * @param file
     * @hidden Les dades de conexió no haurien d'estar al codi font
     */
    public DataBase(File file) throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:postgresql://192.168.33.10/biblioteca";

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
                pst.setString(2, xml.getnombre(id));
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
                pst.setInt(4, xml.getAnchoMax(id));
                pst.setInt(5, xml.getAnchoMin(id));
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
                st.executeUpdate("create table pimientos (id smallint primary key unique, nombre varchar(40),descripcion text, familia varchar(20),origen varchar(150), img varchar(150));");
                System.out.println("Taula creada amb exit!");
                taulesCreades++;
            } catch (SQLException e) {
                System.out.println("Error: creant una taula");
            }
            try {
                st.executeUpdate("create table caracteristicas (id smallint primary key unique, altura_max smallint, altura_min smallint, ancho_max smallint, ancho_min smallint, scoville_max integer, scoville_min integer, dies_cult_max smallint, dies_cult_min smallint, color varchar(25),rendimiento varchar(30)); ");
                System.out.println("Taula creada amb exit!");
                taulesCreades++;
            } catch (SQLException e) {
                System.out.println("Error: creant una taula");
            }
            try {
                st.executeUpdate("create table cultivo (id smallint primary key unique, prof_semilla decimal, dist_semillas decimal, dist_plantas smallint, temp_cre_max smallint, temp_cre_min smallint , temp_germ_max smallint,temp_germ_min smallint , luz varchar(20));");
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


    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error:  Al tancar la conexió.");
        }
    }

    public void printAllPimientosAllInfo() {

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from pimientos p, caracteristicas c, cultivo u where p.id=c.id and p.id=u.id;");
            ResultSetMetaData md = rs.getMetaData();
            int columnesNum = md.getColumnCount();
            rs.next();
            while (rs.next()) {

                System.out.println(rs.getInt("id")+" | "+rs.getInt("scoville_max"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
