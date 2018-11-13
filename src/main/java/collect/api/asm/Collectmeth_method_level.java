package collect.api.asm;

import java.util.HashSet;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Collectmeth_method_level extends MethodVisitor{
    public Collectmeth_method_level() {
        super(Opcodes.ASM6);
    }
    HashSet meth = new HashSet();
    
    @Override
    public void visitCode(){
    	
    }
    
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible){
    	System.out.println(desc);
		return null;
 
    }
    
  
	@Override
    public void visitEnd() {
        
    }
	
	public  Object filter(String desc){
		String test=desc;
		if (test.contains("Ljava")||test.contains("Lcom")){
			String a[] = test.split("L");
        	meth.add(a[1].split(";")[0]);
		}
//		System.out.println(meth);
		return null;
//		return meth;		
	}
	
	
    
    public HashSet value() {
//    	System.out.println(meth);
//    	System.out.println("***********************************************************");
    	return meth;
    }
}
