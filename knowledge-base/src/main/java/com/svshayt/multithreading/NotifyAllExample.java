package com.svshayt.multithreading;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Пример: Использование notifyAll() для пробуждения всех ожидающих потоков
 * В этом примере несколько потоков (Producer) добавляют элементы в очередь,
 * а несколько потоков (Consumer) извлекают их. Мы используем notifyAll()
 * для уведомления всех ожидающих потоков, чтобы избежать “голодания”.
 * Объяснение:
 * 	1.	lock.notifyAll()
 * 	-	Используется вместо notify() для пробуждения всех потоков, ожидающих на объекте lock.
 * 	-	Это гарантирует, что все потоки (и производители, и потребители) проверят своё условие и продолжат работу, если оно выполнено.
 * 	2.	Производители и потребители
 * 	-	Производители (produce) ждут, если очередь заполнена, а затем уведомляют всех, когда добавляют новый элемент.
 * 	-	Потребители (consume) ждут, если очередь пуста, а затем уведомляют всех, когда извлекают элемент.
 * 	3.	Множественные потоки
 * 	-	Здесь участвуют несколько потоков для обоих ролей (произведение и потребление), что демонстрирует координацию через notifyAll().
 * 	Когда использовать notifyAll()
 * 	-	Когда вы ожидаете, что несколько потоков могут быть разбужены одновременно.
 * 	-	Чтобы избежать ситуаций, когда поток продолжает ожидать, хотя условие для его работы уже выполнено (например, из-за использования notify() только для одного потока).
 */
public class NotifyAllExample {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5; // Максимальный размер очереди
    private final Object lock = new Object();

    public static void main(String[] args) {
        NotifyAllExample example = new NotifyAllExample();

        // Создаём несколько производителей
        for (int i = 0; i < 2; i++) {
            new Thread(example::produce, "Producer-" + i).start();
        }

        // Создаём несколько потребителей
        for (int i = 0; i < 3; i++) {
            new Thread(example::consume, "Consumer-" + i).start();
        }
    }

    public void produce() {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) { // Ждём, если очередь полна
                    try {
                        System.out.println(Thread.currentThread().getName() + " waiting (queue full)");
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                queue.offer(value);
                System.out.println(Thread.currentThread().getName() + " produced: " + value);
                value++;
                lock.notifyAll(); // Уведомляем всех потребителей
            }
        }
    }

    public void consume() {
        while (true) {
            synchronized (lock) {
                while (queue.isEmpty()) { // Ждём, если очередь пуста
                    try {
                        System.out.println(Thread.currentThread().getName() + " waiting (queue empty)");
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                int value = queue.poll();
                System.out.println(Thread.currentThread().getName() + " consumed: " + value);
                lock.notifyAll(); // Уведомляем всех производителей
            }
        }
    }
}
