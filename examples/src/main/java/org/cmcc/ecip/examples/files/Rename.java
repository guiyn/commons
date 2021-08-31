package org.cmcc.ecip.examples.files;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.mp3.MP3FileReader;
import org.jaudiotagger.audio.mp3.MP3FileWriter;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.reference.GenreTypes;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Rename {

	static String filepath = "F:\\music\\Various.Artists.-.[100.Greatest.Rock.Guitar.Solos([最佳摇滚吉它Solos100])]专辑.(MP3)";

	public static void main(String[] args) throws Exception {

//		File f = new File(filepath);
//
//		for (File sf : f.listFiles()) {
//
//			readfilemp3(sf);
//		}
//
		File f = new File("F:\\music\\music\\ACDC - You Shook Me All Night Long [mqms2].mp3");
		readfilemp3(f);
	}

 

	static void readfilemp3(File sf) throws Exception {
 System.out.println("file name >>"+sf.getName());
		MP3FileReader reader = new MP3FileReader();
		AudioFile af = reader.read(sf);
		Tag t =af.getTag();
		for (FieldKey fk : FieldKey.values()) {
			if (t.getFirst(fk) != null && !"".equals(t.getFirst(fk).trim()))
				System.out.println(fk.name() + " >> " + t.getFirst(fk));
			
			GenreTypes.getInstanceOf();
		}
		
//		t.setField(FieldKey.ARTIST,new String("王菲".getBytes(),"utf-8"));
//		t.setField(FieldKey.ALBUM,new String("阿菲正传".getBytes(),"utf-8"));
//		t.setField(FieldKey.TITLE,sf.getName().substring(0,sf.getName().length()-4));
		
		MP3FileWriter writer=new MP3FileWriter();
		writer.write(af);

	}
}
