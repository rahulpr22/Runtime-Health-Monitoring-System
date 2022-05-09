package com.example.app;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.lang.String.*;
public class automata {




    /*@RequiresApi(api = Build.VERSION_CODES.O)
    public static void constructAutomata(String path){
        try
        {
            String content = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            String[] eventproperties = content.replaceAll("\n\n", "\n").replaceAll("\t\t", "").split("::");
            System.out.println(eventproperties[1]);
            TANode node=new TANode(-1);
            TANode ta=node;
            Object[] Lines;
            Lines = eventproperties[1].replace("{", "").replace("}", "").lines().toArray();
            for(Object o:Lines){
                if(o.toString().startsWith("State")){
                    TANode x= new TANode(Integer.parseInt(o.toString().charAt(8)+""));
                    x.setNext(new TANode(Integer.parseInt(o.toString().split("Next State: state-")[1].trim())));
                    x.setGuard(o.toString().split("guard: ")[1].split("]")[0]);
                    x.setAction(o.toString().split("Action : ")[1].split(",")[0]);
                    x.setAccepting(isAcceptingState(Lines, x.getCurrentid()));
                    x.setNextid(Integer.parseInt(o.toString().split("Next State: state-")[1].trim()));
                    // x.setClock(Integer.parseInt(o.toString().split(";")[1].split("clock: x=")[1]));
                    ta.next=x;
                    ta=ta.next;
                }
            }
            node=node.next;
            while(node.next!=null)
            {
                node.printNode(node);
                node=node.next;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }*/
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isAcceptingState(Object[] lines, int id){
        String[] temp={};
        for(Object o : lines){
            if(o.toString().startsWith("Accepting"))
            {
                temp=o.toString().split("--> ")[1].trim().replace("[","").replace("]","").split(",");
            }
        }
        int[] array = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();
        boolean contains = IntStream.of(array).anyMatch(x -> x == id);
        return contains;

    }
    
}
