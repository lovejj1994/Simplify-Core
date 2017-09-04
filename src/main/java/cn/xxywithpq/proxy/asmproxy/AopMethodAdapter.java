package cn.xxywithpq.proxy.asmproxy;


import cn.xxywithpq.proxy.asmproxy.asm.MethodVisitor;
import cn.xxywithpq.proxy.asmproxy.asm.Opcodes;

class AopMethodAdapter extends MethodVisitor implements Opcodes {

    public AopMethodAdapter(int api, MethodVisitor mv) {
        super(api, mv);
    }

//    public void visitCode() {
//        super.visitCode();
////        this.visitMethodInsn(INVOKESTATIC, "cn/xxywithpq/proxy/asmproxy/interceptor/AsmAopInterceptor", "beforeInvoke", "()V", false);
//    }


//    @Override
//    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
//    }
//
//    @Override
//    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
//
//    }

    //    public void visitInsn(int opcode) {
////        if (opcode == RETURN) {  //在返回之前安插after 代码。
////            mv.visitMethodInsn(INVOKESTATIC, "cn/xxywithpq/proxy/asmproxy/interceptor/AsmAopInterceptor", "afterInvoke", "()V", false);
////        }
//        super.visitInsn(opcode);
//    }
}