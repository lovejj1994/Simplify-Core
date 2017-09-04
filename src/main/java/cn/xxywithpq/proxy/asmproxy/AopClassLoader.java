package cn.xxywithpq.proxy.asmproxy;

import cn.xxywithpq.proxy.asmproxy.asm.ClassReader;
import cn.xxywithpq.proxy.asmproxy.asm.ClassWriter;
import cn.xxywithpq.proxy.asmproxy.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;

public class AopClassLoader extends ClassLoader implements Opcodes {

    public AopClassLoader(ClassLoader parent) {
        super(parent);
    }

    public AopClassLoader() {
        super();
    }

    public Class<?> defineClass(Class clazz, InvocationHandler invocationHandler) throws ClassNotFoundException {
        String clazzName = clazz.getName().replace(".", "/") + ".class";
        try {
            ClassWriter cw = new ClassWriter(0);
            //读取 被代理类
            InputStream is = clazz.getClassLoader().getResourceAsStream(clazzName);
            ClassReader reader = new ClassReader(is);
            reader.accept(new AopClassAdapter(ASM5, cw, invocationHandler), ClassReader.SKIP_DEBUG);

            byte[] code = cw.toByteArray();
            FileOutputStream fos = new FileOutputStream(AopClassLoader.class.getResource("").getPath().toString() + "/Asm_Tmp.class");
            fos.write(code);
            fos.flush();
            fos.close();
            return super.defineClass(clazz.getName() + "$simplify", code, 0, code.length);
        } catch (Throwable e) {
            System.out.println(e);
            throw new ClassNotFoundException();
        }
    }
}