# Queues-Management
The main objectiv of this project is to design and implement a queues management application which assigns clients to queues such that the waiting time is minimized. 

## Requirements
IntelliJ IDEA

## Usage
### Requirements
The queues management application should simulate (by defining a simulation time 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛) a series of N clients arriving for service, entering Q queues, waiting, being served and finally leaving 
the queues. All clients are generated when the simulation is started, and are characterized by three
parameters: ID (a number between 1 and N), 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 (simulation time when they are ready to enter the queue) and 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 (time interval or duration needed to serve the client; i.e. waiting time when the client is in front of the queue. The application tracks the total time spent by every client in the queues and computes the average waiting time. Each client is added to the queue with the minimum waiting time when its 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 time is greater than or equal to the simulation time (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 ≥ 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛). 

### Implementation
There are 2 types of scenario: success and negative.

Success scenario:
1. The user enters all the required data in the interface
2. Press the START button
3. Start the simulation using the time method
- This method picks up one customer at a time based on arrival time
- Places it in the queue that has the least number of customers
- Removes it at the end of the customer's service time
- In the meantime he can take over another client

Negative scenario:
- There are too few queues that fail to handle all customers
- There is a possibility that the number of customers is the same in all queues and thus, the next customer islocated at the front of the queue, having to wait for the previous customer to finish their work

### Results
![results](https://user-images.githubusercontent.com/93877610/232538852-11b34f18-31ec-4e1f-8fd0-2174760d0f93.jpg)

## Visuals
![interface](https://user-images.githubusercontent.com/93877610/232539110-c8e95c5d-35ec-4e23-a98f-23b21133a849.jpg)

