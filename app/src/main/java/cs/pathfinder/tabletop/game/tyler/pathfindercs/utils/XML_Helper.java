package cs.pathfinder.tabletop.game.tyler.pathfindercs.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XML_Helper {

    private static XmlSerializer serializer;
    private static XML_Helper helper;

    // Class with only static helper methods. Doesn't store any data.
    private XML_Helper() {
        serializer = Xml.newSerializer();
    }
    public static XML_Helper getInstance() {
        if (helper == null) {
            helper = new XML_Helper();
        }
        return helper;
    }

    public void saveCharacter(CharacterInfo character, int index, Context context) {
        saveInternal(character, index, context);
    }

    private void saveInternal(CharacterInfo character, int index, Context context) {
        String[] files = context.fileList();
        Log.e("XML_Helper", "Files index: " + files.length);
        Log.e("XML_Helper", context.getFilesDir().getAbsolutePath());

        for (int i = 0; i < files.length; i++) {
            Log.e("XML_Helper Files", "File " + i + ": " + files[i]);
        }

        try {
            String filename = index + "_character.xml";
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            Log.e("XML_Helper", filename);

            serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startDocument(null, true);

            serializer.startTag(null, "resource");
            
            tag("string", Stats.NAME.name(), character.get(Stats.NAME));
            tag("string", "abilities", character.get(Stats.STR) + ":" + character.get(Stats.DEX)
                    + ":" + character.get(Stats.CON) + ":" + character.get(Stats.INT)
                    + ":" + character.get(Stats.WIS) + ":" + character.get(Stats.CHA));
            tag("string", Stats.RACE.name(), character.get(Stats.RACE));
            tag("string", Stats.CLASS.name(), character.get(Stats.CLASS));
            tag("string", "level", character.get(Stats.LVL) + ":" + character.get(Stats.XP)
                    + ":" + character.get(Stats.NEXT_LVL));
            tag("string", Stats.GENDER.name(), character.get(Stats.GENDER));
            tag("string", Stats.AGE.name(), character.get(Stats.AGE));
            tag("string", Stats.ALLI.name(), character.get(Stats.ALLI));
            tag("string", Stats.HEIGHT.name(), character.get(Stats.HEIGHT));
            tag("string", Stats.WEIGHT.name(), character.get(Stats.WEIGHT));
            tag("string", Stats.SIZE.name(), character.get(Stats.SIZE));
            tag("string", "hp", character.get(Stats.CURR_HP) + ":" + character.get(Stats.MAX_HP));
            tag("string", Stats.INIT.name(), character.get(Stats.INIT));
            tag("string", "saves", character.get(Stats.FORT) + ":" + character.get(Stats.REFLEX)
                    + ":" + character.get(Stats.WILL));
            tag("string", "combat", character.get(Stats.BAB) + ":" + character.get(Stats.CMD)
                    + ":" + character.get(Stats.CMB));
            tag("string", Stats.FLAT.name(), character.get(Stats.FLAT));
            tag("string", Stats.TOUCH.name(), character.get(Stats.TOUCH));

            serializer.endTag(null, "resource");
            serializer.endDocument();
            serializer.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void saveExternal(CharacterInfo character, int index, Context context) {
//        try {
//            // Save the directory "Characters" on the external media.
////            File file = new File(context.getExternalFilesDir(null), "Characters");
////            if (!file.mkdir()) {
////                Log.e("XML_Helper", "Directory not created");
////            } else {
////                Log.e("XML_Helper", "Directory successfully created");
////            }
//            // Save character file inside the "Characters directory.
//
//            String filename = index + "_character_" + character.get(Stats.NAME) + ".xml";
//            //File dir = new File(context.getExternalFilesDir(null), "Characters");
//            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//            File file = new File(path, "test.svg");
//
//            if (path.mkdirs()) {
//                Log.e("XML_Helper", "Directory successfully created");
//            } else {
//                Log.e("XML_Helper", "Directory not created");
//            }
//            Log.e("XML_Helper", "Path: " + path.getAbsolutePath());
//
////            FileOutputStream fileos = new FileOutputStream(file);
////            StringWriter writer = new StringWriter();
////            serializer = Xml.newSerializer();
////            serializer.setOutput(writer);
////            serializer.startDocument("UTF-8", true);
////            serializer.startTag(null, "resource");
////
////            tag("string", "name", character.get(Stats.NAME));
////            tag("string", "abilities", character.get(Stats.STR) + ":" + character.get(Stats.DEX)
////                    + ":" + character.get(Stats.CON) + ":" + character.get(Stats.INT)
////                    + ":" + character.get(Stats.WIS) + ":" + character.get(Stats.CHA));
////            tag("string", "race", character.get(Stats.RACE));
////            tag("string", "class", character.get(Stats.CLASS));
////            tag("string", "level", character.get(Stats.LVL) + ":" + character.get(Stats.XP)
////                    + ":" + character.get(Stats.NEXT_LVL));
////            tag("string", "gender", character.get(Stats.GENDER));
////            tag("string", "age", character.get(Stats.AGE));
////            tag("string", "alli", character.get(Stats.ALLI));
////            tag("string", "height", character.get(Stats.HEIGHT));
////            tag("string", "weight", character.get(Stats.WEIGHT));
////            tag("string", "size", character.get(Stats.SIZE));
////            tag("string", "hp", character.get(Stats.CURR_HP) + ":" + character.get(Stats.MAX_HP));
////            tag("string", "init", character.get(Stats.INIT));
////            tag("string", "saves", character.get(Stats.FORT) + ":" + character.get(Stats.REFLEX)
////                    + ":" + character.get(Stats.WILL));
////            tag("string", "combat", character.get(Stats.BAB) + ":" + character.get(Stats.CMD)
////                    + ":" + character.get(Stats.CMB));
////            tag("string", "flat", character.get(Stats.FLAT));
////            tag("string", "touch", character.get(Stats.TOUCH));
////
////            serializer.endTag(null, "resource");
////            serializer.endDocument();
////            serializer.flush();
////            String dataWrite = writer.toString();
////            fileos.write(dataWrite.getBytes());
////            fileos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.e("XML_Helper", e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public boolean isExternalStorageWritable(Context context) {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public ArrayList<ArrayList<String>> getSavedCharacters(Context context) {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        try {
            String[] files = context.fileList();
            if (files.length == 0) {
                return null;
            }

            for (int i = 0; i < files.length; i++) {
                System.out.println("######## file " + i + ": " + files[i]);
            }

            for (String file1 : files) {
                FileInputStream fis = context.openFileInput(file1);
                InputStreamReader isr = new InputStreamReader(fis);
                char[] inputBuffer = new char[fis.available()];
                isr.read(inputBuffer);
                String data = new String(inputBuffer);

                isr.close();
                fis.close();

                /*
                 * converting the String data to XML format
                 * so that the DOM parser understand it as an XML input.
                 */
                InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));
                System.out.println("##### xml_helper data: " + data);
                System.out.println("##### xml_helper inputStream: ");

                Document dom = DocumentBuilderFactory
                        .newInstance()
                        .newDocumentBuilder()
                        .parse(is);

                // normalize the document
                dom.getDocumentElement().normalize();

                NodeList items = dom.getElementsByTagName("string");

                ArrayList<String> arr = new ArrayList<String>();
                System.out.println("############### XML_Helper: file: " + file1);
                for (int i = 0; i < items.getLength(); i++) {
                    Node item = items.item(i);
                    arr.add(item.getTextContent());
                    System.out.println("############### XML_Helper: node " + i + ": " + item.getTextContent());
                }
                list.add(arr);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateCharacter(CharacterInfo character, Stats stat, int index, Context context) {
        saveCharacter(character, index, context);
        //updateCharacter2(character, stat, index, context);
    }

    private void updateCharacter2(CharacterInfo character, Stats stat, int index, Context context) {
        Log.e("XML_Helper", "updateCharacter2");
        try {
            String[] files = context.fileList();

            for (String file1 : files) {
                FileInputStream fis = context.openFileInput(file1);
                InputStreamReader isr = new InputStreamReader(fis);
                char[] inputBuffer = new char[fis.available()];
                isr.read(inputBuffer);
                String data = new String(inputBuffer);

                isr.close();
                fis.close();

                /* converting the String data to XML format
                 * so that the DOM parser understand it as an XML input.
                 */
                InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));
                System.out.println("##### xml_helper data: " + data);
                System.out.println("##### xml_helper inputStream: ");

                Document dom = DocumentBuilderFactory
                        .newInstance()
                        .newDocumentBuilder()
                        .parse(is);

                // Normalize the document.
                dom.getDocumentElement().normalize();

                // Change single node value???
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void deleteCharacter(int index, Context context) {
        String[] files = context.fileList();
        context.deleteFile(files[index]);
        files = context.fileList();

        // Rename the other files with appropriate index.
        for (int i = index; i < files.length; i++) {
            String newFilename = files[i].replaceFirst(String.valueOf(files[i].charAt(0)), i + "");
            Log.e("XML_Helper", "Old filename: " + files[i] + "    New filename: " + newFilename);
            File oldName = new File(context.getFilesDir().getPath(), files[i]);
            File newName = new File(context.getFilesDir().getPath(), newFilename);
            oldName.renameTo(newName);
        }
    }

    private static void tag(String tag, String value) throws IOException {
        serializer.startTag(null, tag);
        serializer.text(value);
        serializer.endTag(null, tag);
    }

    private void tag(String tag, String attr, String value) throws IOException {
        serializer.startTag(null, tag);
        serializer.attribute(null, "id", attr);
        serializer.text(value);
        serializer.endTag(null, tag);
        serializer.text("\n   ");
    }

    private void tag2(String tag, String attr, String value) throws IOException {
        serializer.startTag(null, tag);
        serializer.attribute(null, "id", attr);
        serializer.text(value);
        serializer.endTag(null, tag);
        serializer.text("\n   ");
    }
}
