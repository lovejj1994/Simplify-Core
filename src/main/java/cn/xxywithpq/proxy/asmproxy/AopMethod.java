package cn.xxywithpq.proxy.asmproxy;


import cn.xxywithpq.proxy.asmproxy.asm.MethodVisitor;
import cn.xxywithpq.proxy.asmproxy.asm.Opcodes;

class AopMethod extends MethodVisitor implements Opcodes {
    //cn.xxywithpq.proxy.jdkProxy.bean.AsmAopInterceptor
    public AopMethod(int api, MethodVisitor mv) {
        super(api, mv);
    }

    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "cn/xxywithpq/proxy/asmproxy/interceptor/AsmAopInterceptor", "beforeInvoke", "()V");
    }

    public void visitInsn(int opcode) {
        if (opcode == RETURN) {
            mv.visitMethodInsn(INVOKESTATIC, "cn/xxywithpq/proxy/asmproxy/interceptor/AsmAopInterceptor", "afterInvoke", "()V");
        }
        super.visitInsn(opcode);
    }
}