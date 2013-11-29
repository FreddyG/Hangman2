package com.example.hangman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Context;

import android.util.Log;


public class Parser{
	
	public Context ParserContext = null;
	public Parser(Context context){
		  
	      this.ParserContext = context;
	      Activity logicActivity = (context instanceof Activity)? (Activity) context:null;
	      Data data = _parseXml();  
	}
	
	
	public Data _parseXml() {
		Data data = null;
		
		  try { 
			    SAXParserFactory spf = SAXParserFactory.newInstance(); 
			    SAXParser sp = spf.newSAXParser(); 
			 
			    XMLReader xr = sp.getXMLReader(); 
			 
			    DataHandler dataHandler = new DataHandler(); 
			    xr.setContentHandler((ContentHandler) dataHandler); 
			    InputStream is = ParserContext.getApplicationContext().getAssets().open("smallword.xml");
			    if(is==null){
			    	Log.e("DEBUG", "NULLLLLLLLLLLLL");
			    }
			    xr.parse(new InputSource(is)); 
			 
			    //data = dataHandler.getData(); 
			 
			  } catch(ParserConfigurationException pce) { 
			    Log.e("SAX XML", "sax parse error", pce); 
			  } catch(SAXException se) { 
			    Log.e("SAX XML", "sax error", se); 
			  } catch(IOException ioe) { 
			    Log.e("SAX XML", "sax parse io error", ioe); 
			  } 
		return data;
	}
}