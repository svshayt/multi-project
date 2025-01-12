package com.svshayt.multithreading;

/**
 * Пример 2: Управление двумя потоками
 * Этот пример демонстрирует, как два потока обмениваются сообщениями, используя wait() и notify().
 * Объяснение:
 * 	1.	isPingTurn: Переключатель, определяющий, чей ход сейчас.
 * 	2.	ping() и pong(): Каждый поток выполняет свою задачу, чередуясь с другим.
 * 	3.	wait() и notify(): Управляют чередованием потоков.
 * 	Примечания:
 * 	-	Важно: wait() и notify() должны вызываться внутри блока synchronized.
 * 	-	Синхронизация: Используйте общий объект для синхронизации.
 * 	-	Управление потоками: Не забудьте обработать InterruptedException в методах wait() и sleep().
 */
public class WaitNotifyExampleTwoThreads {
    private final Object lock = new Object();
    private boolean isPingTurn = true;

    public static void main(String[] args) {
        WaitNotifyExampleTwoThreads example = new WaitNotifyExampleTwoThreads();
        Thread pingThread = new Thread(example::ping, "Ping");
        Thread pongThread = new Thread(example::pong, "Pong");

        pingThread.start();
        pongThread.start();
    }

    public void ping() {
        while (true) {
            synchronized (lock) {
                while (!isPingTurn) { // Ждём своей очереди
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Ping");
                isPingTurn = false; // Передаём ход другому потоку
                lock.notify();
            }
        }
    }

    public void pong() {
        while (true) {
            synchronized (lock) {
                while (isPingTurn) { // Ждём своей очереди
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Pong");
                isPingTurn = true; // Передаём ход другому потоку
                lock.notify();
            }
        }
    }
}
