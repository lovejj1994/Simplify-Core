package cn.xxywithpq.proxy.asmproxy;

public class ConstructorParams {
    int opcode;
    String owner;
    String name;
    String desc;
    boolean itf;

    public ConstructorParams(int opcode, String owner, String name, String desc, boolean itf) {
        this.opcode = opcode;
        this.owner = owner;
        this.name = name;
        this.desc = desc;
        this.itf = itf;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isItf() {
        return itf;
    }

    public void setItf(boolean itf) {
        this.itf = itf;
    }
}
