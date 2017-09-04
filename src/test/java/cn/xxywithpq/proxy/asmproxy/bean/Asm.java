package cn.xxywithpq.proxy.asmproxy.bean;

public class Asm {
    int i;

    public Asm() {
        i = 1;
    }

    public Asm(int i) {
        i = 1;
    }

    private final int halloAop2(int i) {
        int j = 0;
        return i + j;
    }

    protected int halloAop3(int i) {
        int j = 0;
        return i + j;
    }

    public int halloAop4(Asm asm) {
        int j = 0;
        return i + j;
    }

    public void halloAop() {
        System.out.println("Hello Aop");
    }

    public int halloAop1() {
        return 1 + 1;
    }
}
