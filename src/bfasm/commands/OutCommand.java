package bfasm.commands;

import bfasm.generators.AddrGen;

public class OutCommand extends Command {

    private int arg0;

    public OutCommand(int[] args) {
        super(args);
    }

    private OutCommand() {
        super(new int[]{0, 0});
    }

    @Override
    public String getBf(AddrGen ag) {
        StringBuilder sb = new StringBuilder();

        ag.doNext(sb, ".", AddrGen.getDataCell(arg0));

        return sb.toString();
    }

    @Override
    public int[] getArgs() {
        return new int[]{arg0};
    }

    @Override
    public Command setArgs(int[] args) {
        arg0 = args[0];
        return this;
    }

    @Override
    public Command getClone(int[] args) {
        return new OutCommand(args);
    }

    @Override
    public String getMnemonic() {
        return "OUT";
    }

    public static void register() {
        Command.registerCommand(new OutCommand());
    }

}
