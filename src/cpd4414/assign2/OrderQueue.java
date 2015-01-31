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

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.List;

/**
 *
 * @author BIBY MICHAEL <c0644696>
 */
public class OrderQueue {

    Queue<Order> orderQueue = new ArrayDeque<>();

    public void add(Order order) throws Exception {
        List<Purchase> listOfPurchase = order.getListOfPurchases();

        if (order.getCustomerId().isEmpty() || order.getCustomerName().isEmpty()) {
            throw new Exception("No CustomerID,Customer Name");
        } else if (listOfPurchase.isEmpty()) {
            throw new Exception();
        } else {
            orderQueue.add(order);
            order.setTimeReceived(new Date());
        }
    }

    public Order next() {
        return orderQueue.element();
    }

}
