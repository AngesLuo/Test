package com.example.xmlserializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.example.pojo.PersonInfo;

public class MainActivity extends Activity {
	private List<PersonInfo> userdata;//�������ݼ���
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //�����������ݵļ��ϣ�ģ�������
        userdata=new ArrayList<PersonInfo>();
        for (int i = 0; i < 3; i++) {
			userdata.add(new PersonInfo("��"+i,100-i,80-i));
		}
    }
    public void Serializer(View v){
    	try {
			XmlSerializer serializer=Xml.newSerializer();
			File file=new File(Environment.getExternalStorageDirectory(),"person.xml");
			FileOutputStream os=new FileOutputStream(file);
			serializer.setOutput(os, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag(null, "persons");
			int count=0;
			for (PersonInfo person : userdata) {
				serializer.startTag(null, "person");
				serializer.attribute(null, "id",count+"");
				//��person�����name����д��xml�ļ�
				serializer.startTag(null, "name");
				serializer.text(person.getName());
				serializer.endTag(null, "name");
				//��person�����age����д��xml�ļ�
				serializer.startTag(null, "age");
				serializer.text(String.valueOf(person.getAge()));
				serializer.endTag(null, "age");
				//��person�����score����д��xml�ļ�
				serializer.startTag(null, "score");
				serializer.text(String.valueOf(person.getScore()));
				serializer.endTag(null, "score");
				serializer.endTag(null, "person");
				count++;
			}
			serializer.endTag(null,"persons");
			serializer.endDocument();
			serializer.flush();
			os.close();
			Toast.makeText(this, "�����ɹ�", 0).show();
    	} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "����ʧ��", 0);
		}
    }

  
}
