package com.example.btp_app;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.google.android.gms.common.util.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ReadFileToString {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String readLineByLine(InputStream is) throws IOException {
       /*StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, encoding);
        String theString = writer.toString();
        String s="";
        try{
            //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            //constructs a string buffer with no characters
            String line;
            String remove="\t";
            while((line=br.readLine())!=null)
            {

                s+=line.replaceAll(remove, "").replaceAll("\n", "")+" ";
            }
            fr.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }*/
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        String s=textBuilder.toString();
        return s.substring(0,s.length()-1).replaceAll("   "," ").replaceAll("  "," ");
    }
}
