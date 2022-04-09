# Runtime-Health-Monitoring-System

This Repository consists of source code for Android Interfaces and their corresponding backend logics, verifying a timed policy expressed using DSL and an algorithm to translate a given timed policy into its corresponding timed automata.

- Verifying whether a policy is syntactically correct or not is done using a predictive top-down LL(1) parser.
- TA generation algorithm for a given timed policy is done by following a stack-based algorithm proposed in our paper. 

## Folder Structure


- `app/src/main/java/com/example/app`: the folder that consists of source code for android interfaces and LL(1) parser.
- `taAlgo`:  this folder consists of source code for TA generation Algorithm
- `app/src/main/res/raw`: this folder consists the proposed DSL in a text document  





