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
	private List<PersonInfo> userdata;//保存数据集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建保存数据的集合，模拟假数据
        userdata=new ArrayList<PersonInfo>();
        for (int i = 0; i < 3; i++) {
			userdata.add(new PersonInfo("王"+i,100-i,80-i));
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
				//将person对象的name属性写入xml文件
				serializer.startTag(null, "name");
				serializer.text(person.getName());
				serializer.endTag(null, "name");
				//将person对象的age属性写入xml文件
				serializer.startTag(null, "age");
				serializer.text(String.valueOf(person.getAge()));
				serializer.endTag(null, "age");
				//将person对象的score属性写入xml文件
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
			Toast.makeText(this, "操作成功", 0).show();
    	} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "操作失败", 0);
		}
    }

  
}
