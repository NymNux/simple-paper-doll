package dev.nymnux.simplepaperdoll.gui;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class ModIntegerSlider extends SliderWidget {

    private final int min;
    private final int max;
    private final ApplyInteger apply;
    private final Text title;

    public ModIntegerSlider(int x, int y, int width, int height, Text text, int value, int min, int max, ApplyInteger apply) {
        super(x, y, width, height, text, (double) (value - min) / (max - min));
        this.min = min;
        this.max = max;
        this.apply = apply;
        this.title = text;
        updateMessage();
    }

    @Override
    protected void updateMessage() {
        int mappedValue = (int) (min + (max - min) * value);
        setMessage(title.copy().append(": " + mappedValue));
    }

    @Override
    protected void applyValue() {
        int mappedValue = (int) (min + (max - min) * value);
        apply.apply(mappedValue);
    }

    public interface ApplyInteger {
        void apply(int mappedValue);
    }

}
