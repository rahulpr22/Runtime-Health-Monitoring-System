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
 
- When a line starting with *if statement* is encountered push that line into stack

  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/2.png)
 
- When a line starting with *if statement* is encountered push that line into stack

  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/3.png)
 
- When a line starting with *return safe statement* is encountered mark the state corresponding to the stack top as accepting

  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/4.png)
 
 - When a line starting with *else statement* is encountered, if the current stack top is a line starting with *if statement* we pop the current stack top and make the next stack top as is parent and then we push the *else statement* into the stack 
 
  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/5.png)

- When a line starting with *return unsafe statement* is encountered mark the state corresponding to the stack top as non-accepting 

  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/6.png)

- When a line starting with *endif statement* is encountered, we pop the current stack top and make its parent as next stack top. Continue this step until the stack top is start node q0.

  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/7.png)
  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/8.png)
 
- When a line starting with *else statement* is encountered, and the current stack top is not if statement, push the *else statement* in to the stack.

 ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/9.png)

- When a line starting with *return safe statement* is encountered mark the state corresponding to the stack top as accepting

  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/10.png)
 
 - When a line starting with *endif statement* is encountered, we pop the current stack top and make its parent as next stack top. Continue this step until the stack top is start node q0.

  ![alt text](https://github.com/rahulpr22/Runtime-Health-Monitoring-System/blob/master/taAlgo/images/11.png)
  
 The resulting state diagram obtained by following this algorithm is the TA corresponding to policy-1.
 
 The output of this algorithm when executed for policy-1 is as follows<br />
 
Accepting Locations: [ Location-3, Location-2 ]__
NonAccepting Locations : [ Location-4 ]__
Location-id: Location-0__
isAccepting: false__
Transitions: [__
]__
__

Location-id: Location-1__
isAccepting: false__
Transitions: [__
{__
Source Location: Location-0__
Destination Location: Location-1__
Transition: ( hr = high , time = 0 )__
},

]


Location-id: Location-2
isAccepting: true
Transitions: [
{
Source Location: Location-0
Destination Location: Location-2
Transition: !( hr = high , time = 0 )
},

{
Source Location: Location-2
Destination Location: Location-0
Transition: All Events
},

]


Location-id: Location-3
isAccepting: true
Transitions: [
{
Source Location: Location-1
Destination Location: Location-3
Transition: ( hr = high , time >= 10 )
},

{
Source Location: Location-3
Destination Location: Location-1
Transition: All Events
},

]


Location-id: Location-4
isAccepting: false
Transitions: [
{
Source Location: Location-4
Destination Location: Location-4
Transition: All Events
},

{
Source Location: Location-1
Destination Location: Location-4
Transition: !( hr = high , time >= 10 )
},

]
