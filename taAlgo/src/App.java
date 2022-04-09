import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class App {
    public static HashMap<String,String> stateMap = new HashMap<>();
    public static HashMap<String,State> Nodes = new HashMap<>();

    public static State makeNode(String location)
    {
        State ste= new State();
        ste.setParent(null);
        ste.setLocation(location);
        ArrayList<Transition> transitions= new ArrayList<>();
        ste.setTransitions(transitions);
        ste.setIsAccepting(false);

        return ste;
    }


    
    public static void main(String[] args) throws Exception {
        
        
        Stack<State> TA= new Stack<>();
        State start= makeNode("Location-0");
        TA.push(start);
        Nodes.put("Location-0", start);

        int counter=0;
        Stack<String> stack= new Stack<>();       
        stack.push("Location-"+counter);
        counter++;
        
        ArrayList<String> accepting = new ArrayList<>(); 
        ArrayList<String> nonAccepting = new ArrayList<>(); 
        Stack<String> elseStack = new Stack<>();
        try  
        {  
            File file=new File("../policy1.txt");    
            FileReader fr=new FileReader(file);   
            BufferedReader br=new BufferedReader(fr);  
            String line;  
            while((line=br.readLine())!=null)  
            {  
                
                if(line.toLowerCase().startsWith("if")) 
                {
                    stack.push(line.toLowerCase());
                    
                    elseStack.push(line.toLowerCase().replace("if (", "else !("));
                    if(!stateMap.containsKey(line.toLowerCase()))
                    {
                        stateMap.put(line.toLowerCase(), "Location-"+counter);
                        counter++;
                    }
                    if(!stateMap.containsKey(line.toLowerCase().replace("if (", "else !(")))
                    {
                        stateMap.put(line.toLowerCase().replace("if (", "else !("), "Location-"+counter);
                        counter++;
                    }
                    TA.push(makeNode(stateMap.get(stack.peek())));
                    Nodes.put(stateMap.get(stack.peek()), TA.peek());
                }

                else if(line.toLowerCase().startsWith("else"))
                {
                    
                    if(stack.peek().startsWith("if"))
                    {

                        State ns= TA.pop();
                        ns.setParent(TA.peek());
                        // State state = new State();
                        // state.setLocation(stateMap.get(stack.peek()));
                        String condition ="";
                        if(stack.peek().startsWith("if"))
                            condition=  stack.peek().replace("if", "").replace("then", "").trim();
                        else
                            condition = stack.peek().replace("else", "").replace("then", "").trim();
                        stack.pop();

                        // State parent= new State();
                        // parent.setLocation(stateMap.get(stack.peek()));

                        Transition transition = new Transition();
                        transition.setSource(ns.Parent);
                        transition.setDestination(ns);
                        transition.setTransition(condition);

                       
                        ns.getTransitions().add(transition) ;
                        Nodes.put(ns.getLocation(), ns);
                        stack.push(elseStack.pop());
                        TA.push(makeNode(stateMap.get(stack.peek())));
                        Nodes.put(stateMap.get(stack.peek()), TA.peek());
                    }

                    else{
                        stack.push(elseStack.pop());
                        TA.push(makeNode(stateMap.get(stack.peek())));
                        Nodes.put(stateMap.get(stack.peek()), TA.peek());
                    }

                }

                else if (line.toLowerCase().startsWith("endif"))
                {
                    while(!stack.peek().equals("Location-0"))
                    {
                        State ns= TA.pop();
                        ns.setParent(TA.peek());

                        String condition ="";
                        if(stack.peek().startsWith("if"))
                            condition=  stack.peek().replace("if", "").replace("then", "").trim();
                        else
                            condition = stack.peek().replace("else", "").replace("then", "").trim();
                        
                        stack.pop();

                        Transition transition = new Transition();
                        transition.setSource(ns.Parent);
                        transition.setDestination(ns);
                        transition.setTransition(condition);

                       
                        ns.getTransitions().add(transition) ;
                        Nodes.put(ns.getLocation(), ns);
                    }
                }

                else if(line.toLowerCase().startsWith("return"))
                {
                    if(line.split(" ")[1].equalsIgnoreCase("safe"))
                    {
                        State state=TA.pop();
                        state.setIsAccepting(true);
                        
                        TA.push(state);
                        Nodes.put(state.getLocation(), state);
                        accepting.add(stateMap.get(stack.peek()));
                    }
                    else
                    {
                        State state=TA.pop();
                        state.setIsAccepting(false);
                        Transition t= new Transition();
                        t.setDestination(state);
                        t.setSource(state);
                        t.setTransition("All Events");
                        state.getTransitions().add(t);
                        TA.push(state);
                        Nodes.put(state.getLocation(), state);
                        nonAccepting.add(stateMap.get(stack.peek()));
                    }
                }

                else
                    continue;

                // System.out.println("*"+ line);
                // printStack(stack);
                // System.out.println("--------------------------------------");
            } 

            
            fr.close();  
            
            for(String s:Nodes.keySet())
            {
                State state= Nodes.get(s);
                if(state.isAccepting)
                {
                    Transition t= new Transition();
                    t.setSource(state);
                    t.setDestination(state.getParent());
                    t.setTransition("All Events");
                    state.getTransitions().add(t);
                    Nodes.put(s, state);
                }
            }
            
            String acc="Accepting Locations: [ ";
            
            for(int i=0;i< accepting.size();i++)
            {
                if(i<accepting.size()-1)
                    acc+= accepting.get(i)+", ";
                else
                    acc+= accepting.get(i)+" ]";
            } 
            System.out.println(acc);

            
            String nacc="NonAccepting Locations : [ ";
            for(int i=0;i< nonAccepting.size();i++)
            {
                if(i<nonAccepting.size()-1)
                    nacc+=nonAccepting.get(i)+", ";
                else
                    nacc+=nonAccepting.get(i)+" ]";
            }
            System.out.println(nacc);


            
            
          for(String keyId: Nodes.keySet())
          {
              System.out.println("\n");
              printLocations(keyId);
              System.out.println("\n");
          }
        }  
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    public static void printStack(Stack<String> stack) {
        String values = Arrays.toString(stack.toArray());
        System.out.println(values);
    }

    public static void printLocations(String id){
        System.out.println("Location-id: "+id+"\nTransitions: [");

        //if(Nodes.get(id).getTransitions())
        for(int i= 0;i<Nodes.get(id).getTransitions().size();i++)
        {
            
            System.out.println("{");
            System.out.println("Source Location: "+Nodes.get(id).getTransitions().get(i).source.location+"");
            System.out.println("Destination Location: "+Nodes.get(id).getTransitions().get(i).destination.location+"");
            System.out.println("Transition: "+Nodes.get(id).getTransitions().get(i).transition+"");
            System.out.println("isAccepting: "+Nodes.get(id).getIsAccepting());
            System.out.println("},\n");
        }
        System.out.println("]");
    }
}
