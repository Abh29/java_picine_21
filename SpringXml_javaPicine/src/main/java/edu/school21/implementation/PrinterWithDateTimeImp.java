package edu.school21.implementation;

import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImp implements Printer {

    private Renderer renderer;

    public PrinterWithDateTimeImp(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String msg) {
        renderer.render(getDateTime().concat(" ").concat(msg));
    }

    private String getDateTime(){
        return LocalDateTime.now().toString();
    }
}
