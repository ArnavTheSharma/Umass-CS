// // package umass.cics160.Assignments.HW6;
// import org.junit.Test;
// import static org.junit.Assert.assertEquals;
// import java.util.LinkedList;


// public class CarFleetTest {
//     Car car1 = new Car(1,1,1.1f);
//     Car car2 = new Car(2,2,1.2f);
//     Car car3 = new Car(3,3,1.3f);
//     Car car4 = new Car(4,3,1.4f);
//     Car car5 = new Car(5,2,1.5f);


//     @Test
//     public void testConstructor() {
        
//         Queue<Car> queue1 = new Queue<>();
//         queue1.enqueue(car1);

//         Queue<Car> queue2 = new Queue<>();
//         queue2.enqueue(car2);
//         queue2.enqueue(car5);

//         Queue<Car> queue3 = new Queue<>();
//         queue3.enqueue(car3);
//         queue3.enqueue(car4);

//         CarFleet fleet = new CarFleet(Queue<Car> queue1, Queue<Car> queue2, Queue<Car> queue3);
//         assertEquals(fleet.queue1, queue1);
//         assertEquals(fleet.queue2, queue2);
//         assertEquals(fleet.queue3, queue3);
//     }   

//     public void addCar() {
//         Queue<Car> queue0 = new Queue<>();
//         Car car0 = new Car(0,1,1.01f);

//         queue0.enqueue(car0);
//         assertEquals(queue0.peek(), car0);
//     }


//     public void testProcessRequests() {
//         LinkedList<Integer> correctList = new LinkedList<>();
//         correctList.add(car1);
//         correctList.add(car2);
//         correctList.add(car5);
//         correctList.add(car3);
//         correctList.add(car4);
        
//         Queue<Integer> queue = new Queue<>();
//         queue.add(1);
//         queue.add(2);
//         queue.add(3);
//         queue.add(3);
//         queue.add(2);

//         assertEquals(fleet.processRequests(queue), correctList);        
//     }
// }
