package collect.java_api;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Collectmeth extends ClassVisitor {
    public Collectmeth() {
        super(Opcodes.ASM4);
    }
     HashSet meth = new HashSet();
//    @Override
//    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        System.out.println(name + " extends " + superName + " {");
//    }
//    @Override
//    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
//        System.out.println("    "+desc+" "+ name+";");
//        return null;
//    }
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//    		if (desc.startsWith("L")) {
//    			System.out.println("    "+desc);
//    		}
    	if(desc.contains("L")) {
        	String a[] = desc.split("L");
        	meth.add(a[1].split(";")[0]);
//        	System.out.println(a[1].split(";")[0]);
     		
    	}

    
        return null;
    }
	
    @Override
    public void visitEnd() {
        
    }
    
    public HashSet value() {
//    	System.out.println(meth);
//    	System.out.println("***********************************************************");
    	return meth;
    }
}
