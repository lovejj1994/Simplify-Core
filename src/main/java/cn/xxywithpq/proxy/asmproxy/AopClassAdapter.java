package cn.xxywithpq.proxy.asmproxy;


import cn.xxywithpq.proxy.asmproxy.asm.*;
import org.more.classcode.ASMEngineTools;

import java.lang.reflect.InvocationHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
class AopClassAdapter extends ClassVisitor implements Opcodes {

    public final static String AopPrefix = "$simplify"; //生成的Aop方法前缀
    int ACC_PRIVATEFINAL = 0x0012; // private final
    private String superClassName = null;      //父类类名
    private String subClassName = null;      //子类类名
    private InvocationHandler invocationHandler;


    public AopClassAdapter(int api, ClassVisitor cv, InvocationHandler invocationHandler) {
        super(api, cv);
        this.invocationHandler = invocationHandler;
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//cn/xxywithpq/proxy/asmproxy/bean/Asm
//        System.out.println("this class name:" + name);

        superClassName = name;
        subClassName = name + "$simplify";
        //更改类名，并使新类继承原有的类。
        super.visit(version, access, subClassName, signature, name, interfaces);

        super.visitField(ACC_PRIVATE, "invocationHandler", "Lcn/xxywithpq/proxy/jdkProxy/Proxy;",
                null, null);
//
////        写setInvocationHandler方法
        {
            MethodVisitor mv = super.visitMethod(ACC_PROTECTED, "setInvocationHandler", "(Lcn/xxywithpq/proxy/jdkProxy/Proxy;)V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitFieldInsn(PUTFIELD, subClassName, "invocationHandler", "Lcn/xxywithpq/proxy/jdkProxy/Proxy;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
    }

    @Override
    public void visitEnd() {

    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        //        PRIVATE FINAL 不处理
        if (ACC_PRIVATE != access && ACC_FINAL != access && ACC_PRIVATEFINAL != access) {
            //        给新类加上构造器
            if ("<init>".equals(name)) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                visitConstruction(mv, name, desc);
                //        给新类加上方法
            } else {
                MethodVisitor mv$simplify = super.visitMethod(access, name + AopPrefix, desc, signature, exceptions);
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                visitAOPMethod(mv, name, desc);
                visitSupperMethod(mv$simplify, name, desc);
            }
        }
        return null;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return null;
    }

    //输出构造器方法
    private void visitConstruction(MethodVisitor mv, String name, String desc) {
        //1.准备输出方法数据
        mv.visitCode();
        Pattern p = Pattern.compile("\\((.*)\\)(.*)");
        Matcher m = p.matcher(desc);
        m.find();
        String[] asmParams = ASMEngineTools.splitAsmType(m.group(1));//"IIIILjava/lang/Integer;F[[[ILjava/lang.Boolean;"
        int paramCount = asmParams.length;
        //
        mv.visitVarInsn(ALOAD, 0);
        for (int i = 0; i < paramCount; i++) {
            String asmType = asmParams[i];
            mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
        }
        mv.visitMethodInsn(INVOKESPECIAL, this.superClassName, name, desc, false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(paramCount + 1, paramCount + 1);
        mv.visitEnd();
    }

    //输出Aop逻辑的方法
    private void visitAOPMethod(MethodVisitor mv, String name, String desc) {
//        //1.准备输出方法数据
        Pattern p = Pattern.compile("\\((.*)\\)(.*)");
        Matcher m = p.matcher(desc);
        m.find();
        String[] asmParams = ASMEngineTools.splitAsmType(m.group(1));//"IIIILjava/lang/Integer;F[[[ILjava/lang.Boolean;"
        String asmReturns = m.group(2);
        int paramCount = asmParams.length;
        int maxStack = 8;//方法最大堆栈大小
        int maxLocals = paramCount + 8;//本地变量表大小
        //
        Label tryBegin = new Label();
        Label tryEnd = new Label();
        Label tryCatch = new Label();

//        mv.visitTryCatchBlock(tryBegin, tryEnd, tryCatch, "java/lang/Throwable");
        {//try {
            mv.visitTryCatchBlock(tryBegin, tryEnd, tryCatch, "java/lang/Throwable");
            mv.visitLabel(tryBegin);
//            mv.visitLabel(tryBegin);
            this.codeBuilder_2(mv, asmParams);//Class<?>[] pTypes = new Class[] { int.class, Object.class, boolean.class, short.class };
            mv.visitVarInsn(ASTORE, paramCount + 2);
            this.codeBuilder_1(mv, asmParams);//Object[] pObjects = new Object[] { abc, abcc, abcc };
            mv.visitVarInsn(ASTORE, paramCount + 3);
//
//
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, subClassName, "getClass", "()Ljava/lang/Class;", false);
            mv.visitLdcInsn(name + AopPrefix);
            mv.visitVarInsn(ALOAD, paramCount + 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
            mv.visitVarInsn(ASTORE, paramCount + 4);
//
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, subClassName, "invocationHandler", "Lcn/xxywithpq/proxy/jdkProxy/Proxy;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, paramCount + 4);
            mv.visitVarInsn(ALOAD, paramCount + 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "cn/xxywithpq/proxy/jdkProxy/Proxy", "invoke", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", false);
            mv.visitVarInsn(ASTORE, paramCount + 5);
            mv.visitVarInsn(ALOAD, paramCount + 5);
            this.codeBuilder_3(mv, asmReturns);
            mv.visitLabel(tryEnd);
            mv.visitLabel(tryCatch);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, paramCount + 6);
            mv.visitTypeInsn(NEW, "java/lang/RuntimeException");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, paramCount + 6);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/Throwable;)V", false);
            mv.visitInsn(ATHROW);
//            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
        mv.visitMaxs(maxStack, maxLocals);
    }

    //输出调用父类方法的方法
    private void visitSupperMethod(MethodVisitor mv, String name, String desc) {
        //1.准备输出方法数据
        Pattern p = Pattern.compile("\\((.*)\\)(.*)");
        Matcher m = p.matcher(desc);
        m.find();
        String[] asmParams = ASMEngineTools.splitAsmType(m.group(1));//"IIIILjava/lang/Integer;F[[[ILjava/lang.Boolean;"
        String asmReturns = m.group(2);
        int paramCount = asmParams.length;
        //
        mv.visitVarInsn(ALOAD, 0);
        for (int i = 0; i < paramCount; i++) {
            String asmType = asmParams[i];
            mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
        }
        mv.visitMethodInsn(INVOKESPECIAL, this.superClassName, name, desc, false);
        mv.visitInsn(ASMEngineTools.getReturn(asmReturns));
        mv.visitMaxs(paramCount + 1, paramCount + 1);
    }

    //Code Builder “new Object[] { abc, abcc, abcc };”
    private void codeBuilder_1(MethodVisitor mv, String[] asmParams) {
        int paramCount = asmParams.length;
        mv.visitIntInsn(Opcodes.BIPUSH, paramCount);
        mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
        for (int i = 0; i < paramCount; i++) {
            String asmType = asmParams[i];
            mv.visitInsn(Opcodes.DUP);
            mv.visitIntInsn(Opcodes.BIPUSH, i);
            if (asmParams[i].equals("B") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
            } else if (asmParams[i].equals("S") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
            } else if (asmParams[i].equals("I") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            } else if (asmParams[i].equals("J") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
            } else if (asmParams[i].equals("F") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
            } else if (asmParams[i].equals("D") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
            } else if (asmParams[i].equals("C") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
            } else if (asmParams[i].equals("Z") == true) {
                mv.visitVarInsn(ASMEngineTools.getLoad(asmType), i + 1);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
            } else {
                mv.visitVarInsn(Opcodes.ALOAD, i + 1);
            }
            mv.visitInsn(Opcodes.AASTORE);
        }
    }

    //Code Builder “new Class[] { int.class, Object.class, boolean.class, short.class };”
    private void codeBuilder_2(MethodVisitor mv, String[] asmParams) {
        int paramCount = asmParams.length;
        mv.visitIntInsn(Opcodes.BIPUSH, paramCount);
        mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Class");
        for (int i = 0; i < paramCount; i++) {
            String asmType = asmParams[i];
            mv.visitInsn(Opcodes.DUP);
            mv.visitIntInsn(Opcodes.BIPUSH, i);
            if (asmParams[i].equals("B") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Byte", "TYPE", "Ljava/lang/Class;");
            } else if (asmParams[i].equals("S") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Short", "TYPE", "Ljava/lang/Class;");
            } else if (asmParams[i].equals("I") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
            } else if (asmParams[i].equals("J") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Long", "TYPE", "Ljava/lang/Class;");
            } else if (asmParams[i].equals("F") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Float", "TYPE", "Ljava/lang/Class;");
            } else if (asmParams[i].equals("D") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Double", "TYPE", "Ljava/lang/Class;");
            } else if (asmParams[i].equals("C") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Character", "TYPE", "Ljava/lang/Class;");
            } else if (asmParams[i].equals("Z") == true) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
            } else {
                mv.visitLdcInsn(Type.getType(asmType));//  Ljava/lang/Object;
            }
            mv.visitInsn(Opcodes.AASTORE);
        }
    }

    //Code Builder “return ...”
    private void codeBuilder_3(MethodVisitor mv, String asmReturns) {
        if (asmReturns.equals("B") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Byte");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
            mv.visitInsn(ASMEngineTools.getReturn("B"));
        } else if (asmReturns.equals("S") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Short");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
            mv.visitInsn(ASMEngineTools.getReturn("S"));
        } else if (asmReturns.equals("I") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Integer");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
            mv.visitInsn(ASMEngineTools.getReturn("I"));
        } else if (asmReturns.equals("J") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Long");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
            mv.visitInsn(ASMEngineTools.getReturn("J"));
        } else if (asmReturns.equals("F") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Float");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
            mv.visitInsn(ASMEngineTools.getReturn("F"));
        } else if (asmReturns.equals("D") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Double");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
            mv.visitInsn(ASMEngineTools.getReturn("D"));
        } else if (asmReturns.equals("C") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Character");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Character", "charValue", "()C", false);
            mv.visitInsn(ASMEngineTools.getReturn("C"));
        } else if (asmReturns.equals("Z") == true) {
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Boolean");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
            mv.visitInsn(ASMEngineTools.getReturn("Z"));
        } else if (asmReturns.equals("V") == true) {
            mv.visitInsn(Opcodes.POP);
            mv.visitInsn(Opcodes.RETURN);
        } else {
            mv.visitTypeInsn(Opcodes.CHECKCAST, ASMEngineTools.asmTypeToType(asmReturns));
            mv.visitInsn(Opcodes.ARETURN);
        }
    }
}
