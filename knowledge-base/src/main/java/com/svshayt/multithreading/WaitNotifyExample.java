package com.svshayt.multithreading;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Пример 1: Простейшая реализация wait() и notify()
 * В этом примере поток “Producer” добавляет данные в очередь, а поток “Consumer” извлекает данные.
 * Объяснение:
 * 	1.	Очередь queue: Используется для хранения данных между потоками.
 * 	2.	synchronized(lock): Монитор используется для синхронизации потоков.
 * 	3.	wait(): Поток ожидает, пока другой не вызовет notify(), если очередь заполнена/пуста.
 * 	4.	notify(): Пробуждает поток, ожидающий в wait().
 * 	Примечания:
 * 	-	Важно: wait() и notify() должны вызываться внутри блока synchronized.
 * 	-	Синхронизация: Используйте общий объект для синхронизации.
 * 	-	Управление потоками: Не забудьте обработать InterruptedException в методах wait() и sleep().
 */
public class WaitNotifyExample {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 5; // Максимальный размер очереди
    private final Object lock = new Object();

    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();
        Thread producer = new Thread(example::produce, "Producer");
        Thread consumer = new Thread(example::consume, "Consumer");

        producer.start();
        consumer.start();
    }

    // Метод для добавления элементов в очередь
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
                lock.notify(); // Уведомляем поток, ожидающий в методе consume()
            }
        }
    }

    // Метод для извлечения элементов из очереди
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
                lock.notify(); // Уведомляем поток, ожидающий в методе produce()
            }
        }
    }
}
