package collect.java_api;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

public class Collectmeth extends ClassVisitor {
    public Collectmeth() {
        super(Opcodes.ASM6);
    }
     public static HashSet Method_meth = new HashSet();
     HashSet meth = new HashSet();
//     public newMethodVisitor mv;
     
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//    	filter(superName);
//    	System.out.println(superName);
//    	for (int i=0;i<interfaces.length;i++){
//        	filter(interfaces[i]);
//        }
    }
     
    
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
//    	System.out.println(desc);

//    	filter(desc);
        return null;
    }
     
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

//    	if(desc.contains("L")) {
//        	String a[] = desc.split("L");
//        	meth.add(a[1].split(";")[0]);
//     		
//    	}
//    	System.out.println(desc);

//    	filter(desc);
    	MethodVisitor superMV = super.visitMethod(access, name, desc, signature, exceptions);
//    	MethodVisitor mv=new newMethodVisitor(superMV, name);
//    	System.out.println((newMethodVisitor)superMV.test);
//    	this.mv = new newMethodVisitor(superMV, name); 
//    	System.out.println("1111111111"+this.mv.value());
        return new newMethodVisitor(superMV, name);
    	    
    }
	
    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access){
//    	System.out.println("1111111");
//    	System.out.println(outerName);
    	
    }

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// TODO Auto-generated method stub
//		filter(desc);
		return null;
	}



	@Override
    public void visitEnd() {
		
    	       
    }
	
	public  Object filter_jdk(String desc){
		String test=desc;
		if (test.contains("Ljava")||test.contains("Lcom/sun")||test.contains("Ljdk")||test.contains("Lsun")){
//			System.out.println("test"+test);
//			System.out.println(test.substring(1));
			meth.add(test.substring(1));
		}
//		System.out.println(Method_meth.size());
		

		return null;
//		return meth;		
	}
	
	public Object filter(String desc){
		String test[]=desc.split(";");
		  for (int i = 0; i < test.length; i++){
			  regex(test[i]);		   
		  }
		return null;
	}
	
	public void regex(String desc){
		String regex = "(L)(.*)";
    	Pattern r = Pattern.compile(regex);
    	Matcher m = r.matcher(desc);
    	if (m.find()) {
//    		String results=m.group(0);
//    		System.out.println("Found: "+ m.group(0));
//    		Collectmeth.Method_meth.add(filter_jdk(m.group(0)));
    		filter_jdk(m.group(0));
    	}		
	}
	   
    public HashSet value() {
//    	System.out.println(meth);
//    	System.out.println("***********************************************************");
   	 	Iterator iterator = Method_meth.iterator(); 
  	  
   	 while (iterator.hasNext()) {  
   		 meth.add(iterator.next());         
   		}
   	 	Method_meth.clear();
    	return meth;
    	
    }
}
