package com.example.projectr;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CategoryManager {
        private String filePath = "path_to_your_categories.xml";

    public CategoryManager(String filePath) {
    }

    public List<String> getAllCategories() throws Exception {
        List<String> categories = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("Category");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String name = element.getElementsByTagName("Name").item(0).getTextContent();
            categories.add(name);
        }
        return categories;
    }

        public void addCategory(String name) throws Exception {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filePath);
            doc.getDocumentElement().normalize();

            Node categoriesNode = doc.getElementsByTagName("Categories").item(0);

            Element newCategory = doc.createElement("Category");
            Attr attr = doc.createAttribute("id");
            attr.setValue(getNextCategoryId(doc));
            newCategory.setAttributeNode(attr);
            Element categoryName = doc.createElement("Name");
            categoryName.appendChild(doc.createTextNode(name));
            newCategory.appendChild(categoryName);

            categoriesNode.appendChild(newCategory);

            saveDocument(doc);
        }

        public void deleteCategory(String categoryId) throws Exception {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filePath);
            doc.getDocumentElement().normalize();

            NodeList categories = doc.getElementsByTagName("Category");
            for (int i = 0; i < categories.getLength(); i++) {
                Node node = categories.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("id").equals(categoryId)) {
                        element.getParentNode().removeChild(element);
                        break;
                    }
                }
            }

            saveDocument(doc);
        }

        public void editCategory(String categoryId, String newName) throws Exception {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filePath);
            doc.getDocumentElement().normalize();

            NodeList categories = doc.getElementsByTagName("Category");
            for (int i = 0; i < categories.getLength(); i++) {
                Node node = categories.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("id").equals(categoryId)) {
                        element.getElementsByTagName("Name").item(0).setTextContent(newName);
                        break;
                    }
                }
            }

            saveDocument(doc);
        }

        private String getNextCategoryId(Document doc) {
            NodeList categories = doc.getElementsByTagName("Category");
            int maxId = 0;
            for (int i = 0; i < categories.getLength(); i++) {
                Element category = (Element) categories.item(i);
                int id = Integer.parseInt(category.getAttribute("id"));
                if (id > maxId) {
                    maxId = id;
                }
            }
            return String.valueOf(maxId + 1);
        }

        private void saveDocument(Document doc) throws Exception {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        }

    }

