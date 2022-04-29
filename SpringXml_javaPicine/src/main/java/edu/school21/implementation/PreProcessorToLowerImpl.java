package edu.school21.implementation;

import edu.school21.interfaces.PreProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String process(String msg) {
        return msg.toLowerCase();
    }
}
