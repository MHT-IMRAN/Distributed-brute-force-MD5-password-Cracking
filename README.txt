Steps to run the code:
1. Take all java files to same folder.
2. Compile all file via java compilor. E.g. javac filename.java
3. Run all file by following steps:
 Step 1: java LoadBalancer localhost to start Load Balancer. E.g. java LoadBalancer 192.168.0.140
 Step 2: java Server localhost Load Balancer ip address (if another machine start remiresgitry 5000) 
         (run 2 servers). E.g. java Sever1 192.168.0.145 192.168.0.140
 Step 3: java Client 192.168.0.140 (Same ip of Load Balancer) on the Client PC.

