package com.day02.ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SignatureWorker implements AutoCloseable{

    private final FileInputStream SIGNATURESFILE;
    private final Map<String, String> SIGNATURES;
    private FileOutputStream outputStream;
    private int maxSigLength = 0;

    public SignatureWorker(String signaturesFile) throws Exception {
        SIGNATURESFILE = new FileInputStream(signaturesFile);
        SIGNATURES = new HashMap<>();
        loadSignatures();
    }

    private void loadSignatures() throws IOException {
        String str = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(SIGNATURESFILE));
        while((str = br.readLine()) != null)
        {
            str = str.replaceAll("\\s+", "");
            String[] strings = str.split(",");

            byte[] bytes = new byte[strings[1].length() / 2];

            for (int i = 0; i < strings[1].length(); i += 2) {
                bytes[i / 2] = (byte) (Byte.parseByte("" + strings[1].charAt(i), 16) * 16 + Byte.parseByte("" + strings[1].charAt(i + 1), 16));
            }

            String sig = new String(bytes);

            if (strings.length != 2)
                throw new IOException("wrong file format");
            SIGNATURES.put(strings[0], sig);
            if (strings[1].length() / 2 > maxSigLength)
                maxSigLength = strings[1].length() / 2;
        }
    }

    public void showSignatures(){
        System.out.println(SIGNATURES);
    }

    public void openOutputFile(String outputPath) throws IOException{
        if (outputStream != null)
            outputStream.close();
        outputStream = new FileOutputStream(outputPath, true);
    }

    public void identifyFileToOutput(String path) throws IOException{
        if (outputStream == null)
            throw new IOException("there is no file to write to!");
        byte[] buff = new byte[maxSigLength];

        FileInputStream input = new FileInputStream(path);
        input.read(buff);
        String inputString = new String(buff);
        input.close();

        boolean found = false;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        for (String key : SIGNATURES.keySet()) {
            String val = SIGNATURES.get(key);
            String sub = inputString.substring(0, val.length());
            if (val.equals(inputString.substring(0, val.length()))){
                found = true;
                bw.append(key);
                bw.append("\n");
                bw.flush();
                break;
            }
        }
        if (found)
            System.out.println("PROCESSED");
        else
            System.out.println("UNDEFINED");
    }

    @Override
    public void close() throws IOException {
        SIGNATURESFILE.close();
        if (outputStream != null)
            outputStream.close();
    }
}
