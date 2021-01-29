A. Overview
The purpose of this assignment is to strengthen your understanding towards implementing and speeding up a computation intensive problem using distributed system concept.
In this assignment, your team is to design and implement a distributed brute force MD5 password searching system. MD5 algorithm is a widely used cryptographic hash function producing a 128-bit hash. An MD5 hash is typically expressed as a 32 digits hexadecimal number. For this assignment, few MD5 hash values will be generated using 3 to 6 characters passwords which the character is limited from the list of ASCII characters having the value between 32 and 126. However, this still produce a search space of 7.35x1011 different combinations
B. Requirement
The password search can be speed up by distributing the search effort using numbers of process and/or thread, which can be located within a machine and/or spread to different machines, see Figure 1. Each of the processes and/or threads starts the search at different search space, for example process/thread 1 starts the search with all the possible combination of 6-character password start with character ‘!’, process/thread 2 start with character ‘A’. Each search should be timed and average search time should be reported according to different password length, number of processes/threads per machine and number of physical machine used.
Virtual machine can be used for this assignment. However, the result could be different compared to physical machine as virtual machine is sharing limited computational resources with the host machine.
Each group will be assigned with 5 different MD5 hash values generated from each 3-, 4- and 5-character passwords (5*3 = 15 MD5 hash), and 3 hash values generated from 6-character passwords. In total, each group have to search/crack 18 passwords. Please refer to your respective lecturer for the assigned MD5 hash values.
The allowed programming language is Java RMI ONLY and use the MD5 class as attached.
-splau -
C. Assessment
The assessment of the assignment will be break into 2 phases:
Phase 1: Demonstration of multi-threading and RMI.
For this phase, each team is required to present a simple Java system with the following functions, and this will be the base for MD5 cracking system in Phase 2.
a) The system provides a command line interface (CLI) which take user’s input on number of threads to be created by the remote servers.
b) The input is passed to at least TWO (2) remote servers, and the servers will create the threads. For example, if 1 thread is selected, then each of the remote server creates ONE thread per server.
c) The threads display their Thread ID, time created, and return this information back to the server that created them.
d) The IDs and time are collected by the servers and return to the client’ terminal.
The assessment of the phase will be conducted in demonstration/presentation mode. Each group needs to demonstrate the base system according to the functions listed above. The Client-Server architecture, user interface and demonstration/presentation skill are the focus of this phase.
Phase 2: MD5 Cracking
At this phase, each team is required to use the code from Phase 1 as the base and upgrade them to perform brute force cracking on different MD5 hash code generated from 2-, 3-, 4-, and 5-character password. The updated Java system should have following functions:
a) An updated CLI that take in the MD5 hash code and number of threads to be created for brute force cracking. The system has the option for 1, 2, 4, 5, 7, and 10 as the input for number of threads to be created by the servers.
b) The MD5 hash code is passed to remote servers, and servers initiate the number of threads according to user input.
c) The system search for the matching MD5 password using 1, 2, 4, 5, 7, and 10 threads per server. Please do note that the search duration can be very lengthy for 4- and 5-characher passwords, and by having more thread can speed up the search processes.
d) The search space is divided to all threads.
e) The search stops once corresponding password is found by any thread, and then reports the password and duration spent (in second).
-splau -
f) The server reports the corresponding password and duration spent in cracking the password back to user.
