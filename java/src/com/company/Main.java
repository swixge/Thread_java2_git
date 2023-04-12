package com.company;
public class Main {
    public static void main(String[] args) {
        int dim = 100000000;
        int threadNum = 4;//Кількість потоків
        long time = System.nanoTime();//Знаходимо теперішній час
        ArrClass arrClass = new ArrClass(dim, threadNum);//Створення класу, де буду всі обраховування
        long minIndex = arrClass.partMin(0, dim);//Знаходження minIndex у всьому масиві
        time = System.nanoTime() - time;// знаходимо час виконання потоку
        System.out.println(minIndex + " time:" + time);//виведення індекса і час за який найшло в 1 потоці
        time = System.nanoTime();//Знаходимо теперішній час
        minIndex = arrClass.threadMin();//Знаходимо minIndex в n-кількостях потоків
        time = System.nanoTime() - time;// знаходимо час виконання потоку
        System.out.println(minIndex + " time:" + time);//виведення індекса і час за який найшло в n-потоках
    }
}