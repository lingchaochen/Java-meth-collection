package collect.java_api;

import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

class newMethodVisitor extends MethodVisitor {
    private String methodName;
    public newMethodVisitor(MethodVisitor mv, String methodName) {
        super(Opcodes.ASM6, mv);
    }
    public void visitCode() {
    }
    
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible){
//    	System.out.println(desc);
//    	filter(desc);
    	
    	return null;
    }
    @Override
    public void	visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs){
//    	System.out.println(desc);
//    	filter(desc);
    	
    }
    
    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible){
//    	System.out.println(desc);
//    	filter(desc);
    	return null;
    }
    
    
    
    @Override
    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible){
//    	System.out.println(desc);
//    	filter(desc);
    	return null;
    }
    
    
    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible){
//    	System.out.println(desc);
//    	filter(desc);
    	return null;
    }
    
    
    
    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc){
    	
//    	System.out.println(owner);
//    	System.out.println("1111111");
//    	System.out.println(desc);
//    	filter(desc);
    	
    }
    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index){
//    	System.out.println(desc);
//    	System.out.println("|");
//    	filter(desc);
    }
    
    
    @Override public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf){
//    	System.out.println("name"+name);
//    	System.out.println("owner"+owner);
//    	String regex_results=regex(desc);
//    	MethodInsn_filter(desc);
    	filter(owner,name);
    }
    
    
    @Override
    public void visitMultiANewArrayInsn(String desc, int dims){
//    	System.out.println(desc);
//    	filter(desc);
    }
    
    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible){
//    	System.out.println(desc);
//    	filter(desc);
    	return null;
    	
    }
    

    
    public void visitEnd() {

    }
    

	public  Object filter_jdk(String desc){
		String test=desc;
		if (test.contains("Ljava")||test.contains("Lcom/sun")||test.contains("Ljdk")||test.contains("Lsun")){
//			System.out.println("test"+test);
			Collectmeth.Method_meth.add(test.substring(1));
		}
//		System.out.println(Method_meth.size());
		

		return null;
//		return meth;		
	}
	
	public Object filter(String owner, String name){
//		String test[]=desc.split(";");
//		  for (int i = 0; i < test.length; i++){
//			  regex(test[i]);		   
//		  }

		if((owner.startsWith("java")||owner.startsWith("com/sun")||owner.startsWith("sun"))&&name.indexOf("<init>")==-1){
//			System.out.println(owner+"/"+name);
			Collectmeth.Method_meth.add(owner+"/"+name);
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
    

}
