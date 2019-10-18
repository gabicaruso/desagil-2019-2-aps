package br.pro.hashi.ensino.desagil.aps.model;

public class TripleInputOrGate extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;
    private final NandGate nand5;
    private final NandGate nand6;

    public TripleInputOrGate() {
        super("TripleInputOR", 3);

        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();

        nand4 = new NandGate();
        nand4.connect(0, nand1);
        nand4.connect(1, nand2);

        nand5 = new NandGate();
        nand5.connect(0, nand4);
        nand5.connect(1, nand4);

        nand6 = new NandGate();
        nand6.connect(0, nand5);
        nand6.connect(1, nand3);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nand6.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nand1.connect(0, emitter);
                nand1.connect(1, emitter);
                break;
            case 1:
                nand2.connect(0, emitter);
                nand2.connect(1, emitter);
                break;
            case 2:
                nand3.connect(0, emitter);
                nand3.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
