package edu.school21;

import edu.school21.implementation.PreProcessorToUpperImpl;
import edu.school21.implementation.PrinterWithPrefixImpl;
import edu.school21.implementation.RendererErrImpl;
import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Renderer;

public class Main {
    public static void main(String[] args) {

        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello!");

    }
}
