package com.svshayt.multithreading;

/**
 * Пример 3: Таймаут с wait()
 * Этот пример показывает, как использовать таймаут для предотвращения бесконечного ожидания.
 * Объяснение:
 * 	1.	lock.wait(3000): Поток ждёт уведомления или истечения 3 секунд.
 * 	2.	Thread.sleep(2000): Уведомляющий поток вызывает notify() через 2 секунды.
 * 	Примечания:
 * 	-	Важно: wait() и notify() должны вызываться внутри блока synchronized.
 * 	-	Синхронизация: Используйте общий объект для синхронизации.
 * 	-	Управление потоками: Не забудьте обработать InterruptedException в методах wait() и sleep().
 */
public class WaitNotifyTimeoutExample {
    private final Object lock = new Object();

    public static void main(String[] args) {
        WaitNotifyTimeoutExample example = new WaitNotifyTimeoutExample();

        Thread waitingThread = new Thread(() -> example.waitWithTimeout(), "WaitingThread");
        Thread notifyingThread = new Thread(() -> example.notifyAfterDelay(), "NotifyingThread");

        waitingThread.start();
        notifyingThread.start();
    }

    public void waitWithTimeout() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting for notification...");
                lock.wait(3000); // Ждём 3 секунды
                System.out.println(Thread.currentThread().getName() + " resumed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void notifyAfterDelay() {
        try {
            Thread.sleep(2000); // Имитируем задержку перед уведомлением
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " sending notification...");
            lock.notify();
        }
    }
}
