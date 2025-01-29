// // package umass.cics160.Assignments.HW6;
// import java.util.LinkedList;
// import java.util.List;

// public class CarFleet {
//     public Queue<Hello> queue1 = new Queue<>(); // gasoline
//     public Queue<Hello> queue2 = new Queue<>(); // hybrid 
//     public Queue<Hello> queue3 = new Queue<>(); // electric

//     public CarFleet(Queue<Hello> queue1, Queue<Hello> queue2, Queue<Hello> queue3){
//         this.queue1 = queue1;
//         this.queue2 = queue2;
//         this.queue3 = queue3;
//     }

//     public boolean addCar(Hello c) {
//         if (c.getPowerSource() == 1) {
//             this.queue1.enqueue(c);
//         }
//         else if (c.getPowerSource() == 2) {
//             this.queue2.enqueue(c);
//         }
//         else if (c.getPowerSource() == 3) {
//             this.queue3.enqueue(c);
//         }
//         else {
//             return false;
//         }
//         return true;
//     }

//     public LinkedList<Hello> processRequests(Queue<Integer> q) {
//         LinkedList<Hello> list = new LinkedList<>();
//         Queue<Hello> currentQueue;

//         while (q.isEmpty() == false) {
//             int req = q.dequeue();
            
//             if (req == 1) {
//                 currentQueue = this.queue1;
//             }
//             else if (req == 2) {
//                 currentQueue = this.queue2;
//             }
//             else {
//                 currentQueue = this.queue3;
//             }

//             Hello currentCar = currentQueue.peek();
//             if (currentCar == null) {
//                 currentCar = new Hello(0,req,0);         
//             }
//             else {
//                 currentQueue.dequeue();
//             }
            
//             list.add(currentCar);
//         }
//         return list;
//     }

//     public static void main(String args[]) {
//     }

//     public CarFleet() {
//     }
// }
