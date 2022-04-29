package edu.school21.implementation;

import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String msg) {
        renderer.render(prefix.concat(msg));
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
