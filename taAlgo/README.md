## TA construction Algorithm

This directory consists of source code to translate a given policy into its corresponding Timed Automata.

## Folder Structure


- `src`: the folder to maintain sources
- `policy1.txt` is a sample healthcare policy

> If you want to run and test the Algorithm just run `src/App.java` file.


## Explanation of the TA generation Algorithm for Policy-1

The Algorithm for constructing a TA for a given timed policy expressed using DSL is presented in our paper. We shall discuss the algorithm to generate a TA via an example. Let us consider policy-1 presented in policy-1.txt file.


 ` BEGIN := `<br />
  `IF ( HR = HIGH , TIME = 0 ) THEN `<br />
  `IF ( HR = HIGH , TIME >= 10 ) THEN `<br />
  `RETURN SAFE ;` <br />
  `ELSE `<br />
  `RETURN UNSAFE ;` <br />
  `ENDIF ; `<br />
  `ELSE `<br />
  `RETURN SAFE ;` <br />
 ` ENDIF ; `<br />
 ` END ; `<br />

- Intially a start state q0 is pushed int the stack following line-1 of the algorithm
  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/1.png)
