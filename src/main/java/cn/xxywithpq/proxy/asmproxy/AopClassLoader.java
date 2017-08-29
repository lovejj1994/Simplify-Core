package cn.xxywithpq.proxy.asmproxy;

import cn.xxywithpq.proxy.asmproxy.asm.ClassReader;
import cn.xxywithpq.proxy.asmproxy.asm.ClassWriter;
import cn.xxywithpq.proxy.asmproxy.asm.Opcodes;

import java.io.InputStream;

public class AopClassLoader extends ClassLoader implements Opcodes {
    public AopClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.contains("Asm_Tmp"))
            return super.loadClass(name);
        try {
            ClassWriter cw = new ClassWriter(0);
            //cn.xxywithpq.proxy.jdkProxy.bean.Asm
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("cn/xxywithpq/proxy/asmproxy/bean/Asm.class");
            ClassReader reader = new ClassReader(is);
            reader.accept(new AopClassAdapter(ASM4, cw), ClassReader.SKIP_DEBUG);
            //

            byte[] code = cw.toByteArray();
//            FileOutputStream fos = new FileOutputStream(Asm.class.getResource("").getPath().toString() + "/Asm_Tmp.class");
//            fos.write(code);
//            fos.flush();
//            fos.close();
            return this.defineClass(name, code, 0, code.length);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}