package dev.nymnux.simplepaperdoll.gui;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class ModDoubleSlider extends SliderWidget {

    private final double min;
    private final double max;
    private final ApplyDouble apply;
    private final Text title;

    public ModDoubleSlider(int x, int y, int width, int height, Text text, double value, double min, double max, ApplyDouble apply) {
        super(x, y, width, height, text, (value - min) / (max - min));
        this.min = min;
        this.max = max;
        this.apply = apply;
        this.title = text;
        updateMessage();
    }

    @Override
    protected void updateMessage() {
        double mappedValue = min + (max - min) * value;
        setMessage(title.copy().append(": " + String.format("%.2f", mappedValue)));
    }

    @Override
    protected void applyValue() {
        double mappedValue = min + (max - min) * value;
        apply.apply(mappedValue);
    }

    public interface ApplyDouble {
        void apply(double mappedValue);
    }

}
