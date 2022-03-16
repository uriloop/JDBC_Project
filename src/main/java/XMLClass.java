import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

public class XMLClass {

    File file;

    /**
     * Constructor de la classe. Inicia el file on tenim l'xml
     */
    public XMLClass() {

        file = new File("src/data/pebrots.xml");


    }


    /**
     * Retorna el node del pebrot corresponent
     * @param id La id del pebrot que busquem
     * @return
     */
    private Node getPebrotNodeById(int id) {
        try {
            XMLClass xml = new XMLClass();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            db = dbFactory.newDocumentBuilder();
            Document doc = db.parse(xml.file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("pebrot");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                NamedNodeMap idPebrotAtr = node.getAttributes();
                String idPebrot=idPebrotAtr.getNamedItem("id").getNodeValue();
                if ( idPebrot.equals(String.valueOf(id))) {
                    return node;
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Busca en el xml el total de elementos que existen de tipo pimiento
     *
     * @return the elements count
     */
    public int getNumOfElements() {
        try {
            XMLClass xml = new XMLClass();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            db = dbFactory.newDocumentBuilder();
            Document doc = db.parse(xml.file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("pebrot");
            return nList.getLength();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Torna el nom d'un pebrot
     *
     * @param id La id del pebrot a mapejar
     * @return el nom del pebrot que correspon amb la id
     */
    public String getNombre(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(0);
        NodeList nList = node.getChildNodes();

            node=nList.item(0);
            return node.getTextContent();


    }

    /** Retorna la descripció del pebrot
     * @param id La id del pebrot
     * @return  retorna la descripció
     */
    public String getDescripcion(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(0);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("descripcion")) return nList.item(i).getTextContent();
        }
        return null;
    }

    /**
     * @param id id del pebrot
     * @return retorna la familia
     */
    public String getFamilia(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(0);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("familia")) {
                return nList.item(i).getTextContent();
            }
        }
        return null;
    }

    /**
     * @param id
     * @return retorna l'origen
     */
    public String getOrigen(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(0);
        NodeList nList = node.getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("origen")) {
                nList = nList.item(i).getChildNodes();
                break;
            }
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < nList.getLength(); i++) {
            sb.append(nList.item(i).getTextContent()).append(" ");
        }
        return sb.toString();

    }


    /**
     * @param id id del pebrot
     * @return retorna la url de la imatge
     */
    public String getImg(int id) {
        Node node = getPebrotNodeById(id);
        NamedNodeMap atributs = node.getAttributes();
        return atributs.getNamedItem("img").getTextContent();

    }

    /**
     * @param id id del pebrot
     * @return retorna l'alçada màxima de la planta
     */
    public int getAlturaMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("alturPlanta")) return Integer.parseInt(nList.item(i).getFirstChild().getTextContent());
        }
        return 0;
    }

    /**
     * @param id
     * @return retorna l'alçada minima de la planta
     */
    public int getAlturaMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("alturPlanta")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent());
        }
        return 0;
    }

    /**
     * @param id
     * @return retorna l'ample màxim de la planta
     */
    public float getAnchoMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        if(Float.parseFloat(nList.item(0).getLastChild().getTextContent())==0) return -1;

        return Float.parseFloat(nList.item(0).getFirstChild().getTextContent());

    }

    /**
     * @param id
     * @return retorna l'ample mínim de la planta
     */
    public float getAnchoMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();

        if(Float.parseFloat(nList.item(0).getLastChild().getTextContent())==0) return -1;
        return Float.parseFloat(nList.item(0).getLastChild().getTextContent());

    }

    /**
     * @param id
     * @return retorna el numero d'scoville minim
     */
    public int getScovilleMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        String scoville= nList.item(5).getFirstChild().getTextContent();
        if (scoville.equals("NotFound")) return -1;
        return Integer.parseInt(nList.item(5).getFirstChild().getTextContent());

    }

    /**
     * @param id
     * @return retorna el maxim d'scoville El que pica
     */
    public int getScovilleMin(int id) {
        int scoville=-1;
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        try {
            scoville=Integer.parseInt(nList.item(5).getLastChild().getTextContent());

        }catch(Exception e){}
       return scoville;


    }

    /**
     * @param id
     * @return retorna els dies maxims per a la recolñecció
     */
    public int getDiesCultMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        return Integer.parseInt(nList.item(2).getFirstChild().getTextContent());
    }

    /**
     * @param id
     * @return retorna els dies mínims per a la collita
     */
    public int getDiesCultMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
    if (Integer.parseInt(nList.item(2).getLastChild().getTextContent())== -100) return -1;
        return Integer.parseInt(nList.item(2).getLastChild().getTextContent());

    }

    /**
     * @param id
     * @return retorna el color dels pebrots
     */
    public String getColor(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("hexColors")) {
                nList = nList.item(i).getChildNodes();
                break;
            }
        }
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < nList.getLength(); i++) {
            sb.append(nList.item(i).getTextContent()).append(" ");
        }
        return sb.toString();
    }

    /**
     * @param id
     * @return retorna el rendiment de la pebrotera
     */
    public String getRendiment(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        return nList.item(4).getTextContent();


    }

    /**
     * @param id
     * @return retorna la profunditat de la llavor
     */
    public float getProfSemilla(int id) {
        Node node = getPebrotNodeById(id);
        Node node2 = node.getLastChild();
        NodeList nList = node2.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("profLlavor")) {
                //System.out.println(nList.item(i).getTextContent());
                return Float.parseFloat(nList.item(i).getTextContent());
            }
        }
        return 0;
    }

    /**
     * @param id
     * @return retorna la distància entre llavors
     */
    public float getDistSemilla(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getLastChild();
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("distLlavors")) return Float.parseFloat(nList.item(i).getTextContent());
        }
        return 0;
    }


    /**
     * @param id
     * @return retorna la distància recomanada entre plantes
     */
    public int getDistPlantas(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getLastChild();
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("distPlantes")) return Integer.parseInt(nList.item(i).getTextContent());
        }
        return 0;
    }

    /**
     * @param id
     * @return retorna temperatura màxima per al creixement
     */
    public int getTempCrecMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempCreixement")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent().substring(2,4));
        }
        return 0;
    }

    /**
     * @param id
     * @return retorna la temperatuira mínima en creixement
     */
    public int getTempCrecMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempCreixement")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent().substring(0,2));
        }
        return 0;
    }


    /**
     * @param id
     * @return retorna la temperatura minima de germinació
     */
    public int getTempGermMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempGerminacio")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent().substring(0,2));
        }
        return 0;

    }

    /**
     * @param id
     * @return retorna la temperatura màxima de germinació
     */
    public int getTempGermMax(int id) {

        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempGerminacio")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent().substring(2,4));
        }
        return 0;

    }

    /**
     * @param id
     * @return retorna un string amb la llum que requereix la planta
     */
    public String getLuz(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getLastChild();
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("llum")) return nList.item(i).getTextContent();
        }
        return null;
    }
}
