/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cpd4414.assign2;

import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.Purchase;
import cpd4414.assign2.Order;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author BIBY MICHAEL <c0644696>
 */
public class OrderQueueTest {
    
    public OrderQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(1, 450));
        order.addPurchase(new Purchase(2, 250));
        orderQueue.add(order);
        
        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }
    
    @Test
    public void testWhenCustomerIDCustomerNameNotExistThenException() {
        boolean check = false;
        OrderQueue orderQueue = new OrderQueue();
        Order ord = new Order(null, null);
        try {
            orderQueue.add(ord);
        } catch (Exception ex) {
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void testWhenListOfPurchasesNotExistThenException() throws Exception {
        boolean check = false;
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00002", "DEF Construction");
        try {
            orderQueue.add(order);
        } catch (Exception ex) {
            check = true;
        }
        assertTrue(check);
    }

     @Test
    public void testWhenThereAreOrdersInSystemThenReturnEarliestTimeRecievedOrder() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00003", "GHI Construction");
        order.addPurchase(new Purchase(3, 4));
        order.addPurchase(new Purchase(4, 2));
        order.addPurchase(new Purchase(5, 5));
        orderQueue.add(order);
        
        Order order1 = new Order("CUST00004", "JKL Construction");
        order1.addPurchase(new Purchase(6, 5));
        order1.addPurchase(new Purchase(7, 5));
        order1.addPurchase(new Purchase(8, 7));
        orderQueue.add(order1);
        
        Order order2 = new Order("CUST00005", "MNO Construction");
        order2.addPurchase(new Purchase(9, 5));
        order2.addPurchase(new Purchase(10, 5));
        order2.addPurchase(new Purchase(11, 2));
        orderQueue.add(order2);
        
        assertEquals(order, orderQueue.next());
    }
    
    @Test
    public void testWhenNoOrdersInSystemThenReturnNull() {
        OrderQueue queue = new OrderQueue();
        Order expectedResult = null;
        String result = "";
        try {
            queue.next();
        } catch (Exception ex) {
            result = null;
        }
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testWhenOrderTimeRecievedNotSetException() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order(null, null);
        boolean check = false;
        try {
            orderQueue.process_order(order);
        } catch (Exception ex) {
            check = true;
        }
        assertTrue(check);
    }

    @Test
    public void testWhenOrderTimeRecievedAndOrderQuantityInInventoryThenSetTimeToNow() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00004", "JKL Construction");
        order.addPurchase(new Purchase(9, 5));
        order.addPurchase(new Purchase(11, 2));
        orderQueue.add(order);
        orderQueue.process_order(order);
        Date expectedResult = new Date();
        Date result = order.getTimeProcessed();
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testWhenOrderDoesNotHaveQuantityInInventoryThenThrowException() throws Exception {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00005", "MNO Construction");
        order.addPurchase(new Purchase(003, 5));
        order.addPurchase(new Purchase(002, 2));
        boolean check = false;
        try {
            orderQueue.process_order(order);
        } catch (Exception ex) {
            check = true;
        }
        assertTrue(check);
    }

}
