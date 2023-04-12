package com.company;

public class ThreadMin extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final ArrClass arrClass;

    public ThreadMin(int startIndex, int finishIndex, ArrClass arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
    }

    @Override
    public void run() {
        long min = arrClass.partMin(startIndex, finishIndex);//Знаходимо мінімальний елемент в межах start i finish
        arrClass.collectMin(min); // Передаємо мінімальне для запису
        arrClass.incThreadCount();//Отмічаєм, що поток завершено
    }
}
