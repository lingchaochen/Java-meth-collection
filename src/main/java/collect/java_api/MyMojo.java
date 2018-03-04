package collect.java_api;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.objectweb.asm.ClassReader;
import collect.java_api.Collectmeth;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Goal which touches a timestamp file.
 *
 * @deprecated Don't use!
 */

@Mojo(name = "collect_class")
public class MyMojo extends AbstractMojo {
	
	static String baseDIR = System.getProperty("user.dir");
	
    public void execute() throws MojoExecutionException, MojoFailureException {
    	try {
			get_Classes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
               
           }
    
    public void get_Classes() throws Exception {
    	
    	String test="()Lorg/testng/annotations/Test;";
    	String regex = "(L)(.*)";
    	Pattern r = Pattern.compile(regex);
    	Matcher m = r.matcher(test);
    	if (m.find()) {
    		System.out.println("Found: "+ m.group(0));
    	}
    	
    	
    	
    	HashSet meth = new HashSet();
        System.out.println(baseDIR);
        File fp = new File(baseDIR+"/meth_Collect");
        if (!fp.exists()) {  
            fp.mkdir();// 目录不存在的情况下，会抛出异常  
        }
//        File file = new File(baseDIR+"/meth_Collect/meth_results.txt"); 
//        FileOutputStream fos = new FileOutputStream(file);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        //    search class file  
        String fileName = "*.class";      
        List resultList = new ArrayList();
        Map m1 = new HashMap();
        FileSearch.findFiles(baseDIR, fileName,resultList);  
        if (resultList.size() == 0) {     
            System.out.println("No File Fount.");     
        } else {     
            for (int i = 0; i < resultList.size(); i++) {     
            	Collectmeth printer = new Collectmeth();
                String tmp=resultList.get(i).toString();

                System.out.println(tmp);
                byte[] buff = null;
            	try (FileInputStream fis = new FileInputStream(tmp)) {
         		buff = new byte [fis.available()];
         		fis.read(buff);         		
         	} catch (Exception e) {
         		e.printStackTrace();
         	}
             ClassReader cr = new ClassReader(buff);
             cr.accept(printer,0);
             Iterator it = printer.value().iterator();
             List list = new ArrayList(printer.value());
             meth=printer.value();
//             try {
//            	 System.out.println(printer.mv.test);
//             }
//             finally{
//            	 
//             }
//             if (printer.mv != null && printer.mv.Method_meth != null) {
//            	 Iterator iterator = printer.mv.Method_meth.iterator(); 
//            	  
//            	 while (iterator.hasNext()) {  
//            		 meth.add(iterator.next());         
//            		}  
//            	}  
             
//       	  
//        	 while (iterator.hasNext()) {  
//        		 meth.add(iterator.next());         
//        		}
            	 
            	             
//             System.out.println(printer.value());  
             System.out.println(meth);
             m1.put(tmp, meth);
         }
        	}
        writeresults(m1);
    	
    	
    }
    
    public void writeresults(Map m1) throws Exception {

    		FileWriter fstream = new FileWriter(baseDIR+"/meth_Collect/meth_results.txt");
    		BufferedWriter out = new BufferedWriter(fstream);
    		Iterator<Entry<String, HashSet>> it = m1.entrySet().iterator();
    		while(it.hasNext()) {
    			Map.Entry<String, HashSet> pairs = it.next();
    			out.write(pairs.getKey()+":"+pairs.getValue() + "\n");
    		}
    		out.close();
    }
    
    
    
}
    
    

