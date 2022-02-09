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


    public String getnombre(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            node=nList.item(i);
            if (node.getNodeName().equals(String.valueOf(id))) return node.getNodeValue();
        }
        return null;
    }

    public String getDescripcion(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("descripcion")) return nList.item(i).getTextContent();
        }
        return null;
    }

    public String getFamilia(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("familia")) return nList.item(i).getTextContent();
        }
        return null;
    }

    public String getOrigen(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(1);
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
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("alturaPlanta")) return Integer.parseInt(nList.item(i).getFirstChild().getNodeValue());
        }
        return 0;
    }

    public int getAlturaMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("alturaPlanta")) return Integer.parseInt(nList.item(i).getLastChild().getNodeValue());
        }
        return 0;
    }

    public int getAnchoMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("anchoPlanta")) return Integer.parseInt(nList.item(i).getFirstChild().getNodeValue());
        }
        return 0;
    }

    public int getAnchoMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("anchoPlanta")) return Integer.parseInt(nList.item(i).getLastChild().getNodeValue());
        }
        return 0;
    }

    public int getScovilleMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("scoville")) return Integer.parseInt(nList.item(i).getFirstChild().getNodeValue());
        }
        return 0;
    }

    public int getScovilleMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("scoville")) return Integer.parseInt(nList.item(i).getLastChild().getNodeValue());
        }
        return 0;
    }

    public int getDiesCultMax(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("diesCultiu")) return Integer.parseInt(nList.item(i).getFirstChild().getNodeValue());
        }
        return 0;
    }

    public int getDiesCultMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("diesCultiu")) return Integer.parseInt(nList.item(i).getLastChild().getNodeValue());
        }
        return 0;
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
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("rendimiento")) return nList.item(i).getTextContent();
        }
        return null;
    }

    public float getProfSemilla(int id) {
        Node node = getPebrotNodeById(id);
        Node node2 = node.getLastChild();
        NodeList nList = node2.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("profLlavor")) {
                System.out.println(nList.item(i).getTextContent());
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
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempCreixement")) return Integer.parseInt(nList.item(i).getFirstChild().getNodeValue());
        }
        return 0;
    }

    public int getTempCrecMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempCreixement")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent());
        }
        return 0;
    }


    public int getTempGermMin(int id) {
        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
        NodeList nList = node.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            if (nList.item(i).getNodeName().equals("tempGerminacio")) return Integer.parseInt(nList.item(i).getLastChild().getTextContent());
        }
        return 0;

    }

    public int getTempGermMax(int id) {

        Node node = getPebrotNodeById(id);
        node = node.getChildNodes().item(2);
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
