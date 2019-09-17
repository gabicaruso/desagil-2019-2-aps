package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GateView extends FixedPanel implements ItemListener {

    private final Switch[] switches;
    private final Gate gate;
    private final JCheckBox[] inputBoxes;
    private final JCheckBox outputBox;

    public GateView(Gate gate) {
        super(100,100);
        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];
        outputBox = new JCheckBox();

        JLabel inputLabel = new JLabel("Input:");
        JLabel outputLabel = new JLabel("Output:");

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();
            gate.connect(i, switches[i]);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(inputLabel);
        for (JCheckBox inputBox : inputBoxes) {
            add(inputBox);
            inputBox.addItemListener(this);
        }

        add(outputLabel);
        add(outputBox);

        outputBox.setEnabled(false);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        boolean result = gate.read();

        outputBox.setSelected(result);

    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }
}

