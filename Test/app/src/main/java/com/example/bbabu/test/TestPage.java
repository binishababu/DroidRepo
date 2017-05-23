package com.example.bbabu.test;

/**
 * Created by bbabu on 4/17/17.
 */


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class TestPage {

    public static void main(String[] args) throws FileNotFoundException {


        String sampleXml = readData();
        System.out.println("Sample Xml"+sampleXml);

        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(sampleXml);
            jsonObj.put("BuildNumber","6.71");
            jsonObj.put("TestType", "SmokeTest");

        } catch (JSONException e) {
            System.out.println("JSON exception"+ e.getMessage());
            e.printStackTrace();
        }
        System.out.println("JSON"+ jsonObj.toString());
        System.out.println("XML"+sampleXml);



        try {
            FileWriter file = new FileWriter(".test.json");
            file.write(jsonObj.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }



        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonObj.toString());
        String prettyJsonString = gson.toJson(je);



        try {
            FileWriter file = new FileWriter("TestResult.json");
            file.write(prettyJsonString);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public  static String readData() throws FileNotFoundException {

        File xmlFile = new File("info.xml");
        // Let's get XML file as String using BufferedReader
        // FileReader uses platform's default character encoding
        // if you need to specify a different encoding, use InputStreamReader
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            line = bufReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while( line != null){ sb.append(line).append("\n");
            try {
                line = bufReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String xml2String = sb.toString();
        System.out.println("XML to String using BufferedReader : ");
        System.out.println(xml2String);
        try {
            bufReader.close(); // parsing XML file to get as String using DOM Parser DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); DocumentBuilder docBuilder = dbFactory.newDocumentBuilder(); Document xmlDom = docBuilder.parse(xmlFile); String xmlAsString = xmlDom.toString(); // this will not print what you want System.out.println("XML as String using DOM Parser : "); System.out.println(xmlAsString); // Reading XML as String using jCabi library XML xml = new XMLDocument(new File("info.xml")); String xmlString = xml.toString(); System.out.println("XML as String using JCabi library : " ); System.out.println(xmlString); }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml2String;

    }


}
