package com.example.app;
import java.io.*;
import java.util.*;
@SuppressWarnings("unused")
public class mapper {
    public static void readTokens(){
        File file = new File("./Tokens.txt");

        ArrayList<String> tokens = new ArrayList<>();
        String st;
        String s="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null){
                tokens= new ArrayList<>(Arrays.asList(st.split(" ")));
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Iterator<String> iter= tokens.iterator();
        ArrayList<String> temp= new ArrayList<>();
        for(int i=0;i<tokens.size();i++)
        {
            if(!tokens.get(i).equals("LEFTPAR") || !tokens.get(i).equals("RIGHTPAR") || !tokens.get(i).equals("COMMA"))
            {
                if(tokens.get(i).equals("HR"))
                    temp.add("EventHR"+ " := "+tokens.get(i+2));
                if(tokens.get(i).equalsIgnoreCase("timegap"))
                    temp.add("time "+ tokens.get(i));
            }
        }
    }


    public static String dictionary(String key){
        HashMap<String,String> map = new HashMap<>();
        map.put("HR","( Event, HR )");
        map.put("BP","(Event , BP )");
        map.put("TEMP","(Event ,Temp)");
        map.put("TIMEGAP","t");
        map.put("","");


        return map.get(key);
    }
}

