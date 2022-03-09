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

    public String getDescripcion(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(0);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("descripcion")) return nList.item(i).getTextContent();
        }
        return null;
    }

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


    public String getImg(int id) {
        Node node = getPebrotNodeById(id);
        NamedNodeMap atributs = node.getAttributes();
        return atributs.getNamedItem("img").getTextContent();

    }

    public int getAlturaMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("alturPlanta")) return Integer.parseInt(nList.item(i).getFirstChild().getTextContent());
        }
        return 0;
    }

    public int getAlturaMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("alturPlanta")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent());
        }
        return 0;
    }

    public float getAnchoMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        return Float.parseFloat(nList.item(0).getFirstChild().getTextContent());

    }

    public float getAnchoMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();

        return Float.parseFloat(nList.item(0).getLastChild().getTextContent());

    }

    public int getScovilleMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        String scoville= nList.item(5).getFirstChild().getTextContent();
        if (scoville.equals("NotFound")) return -1;
        return Integer.parseInt(nList.item(5).getFirstChild().getTextContent());

    }

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

    public int getDiesCultMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        return Integer.parseInt(nList.item(2).getFirstChild().getTextContent());
    }

    public int getDiesCultMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();

        return Integer.parseInt(nList.item(2).getLastChild().getTextContent());

    }

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

    public String getRendiment(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        return nList.item(4).getTextContent();


    }

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

    public float getDistSemilla(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getLastChild();
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("distLlavors")) return Float.parseFloat(nList.item(i).getTextContent());
        }
        return 0;
    }


    public int getDistPlantas(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getLastChild();
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("distPlantes")) return Integer.parseInt(nList.item(i).getTextContent());
        }
        return 0;
    }

    public int getTempCrecMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempCreixement")) return Integer.parseInt(nList.item(i).getFirstChild().getTextContent());
        }
        return 0;
    }

    public int getTempCrecMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempCreixement")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent());
        }
        return 0;
    }


    public int getTempGermMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempGerminacio")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent());
        }
        return 0;

    }

    public int getTempGermMax(int id) {

        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempGerminacio")) return Integer.parseInt(nList.item(i).getFirstChild().getTextContent());
        }
        return 0;

    }

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
