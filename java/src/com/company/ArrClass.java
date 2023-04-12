package com.company;
import java.util.Random;
public class ArrClass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;


    public ArrClass(int dim, int threadNum) { //Заповнюєм масив, і рандомне число множемо на -1, щоб потом його вловити
        this.dim = dim;
        arr = new int[dim];
        this.threadNum = threadNum;
        for(int i = 0; i < dim; i++){
            arr[i] = i;
        }
        Random random = new Random();
        arr[random.nextInt(dim)]*=-1;
    }

    public long partMin(int startIndex, int finishIndex){ //Знаходження мінімального в цих межах
        long min =Long.MAX_VALUE;
        for(int i = startIndex; i < finishIndex; i++){
            if(min>arr[i]){
                min=arr[i];
            }
        }
        return min;
    }

    private long min = 0;

    synchronized private long getMin() {//Чекаємо поки виведуться всі потоки, потом виведем min
        while (getThreadCount()<threadNum){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return min;
    }

    synchronized public void collectMin(long min){//Находимо min
        if(this.min>min){
            this.min = min;
        }
    }

    private int threadCount = 0;//Кількість завершених потоків
    synchronized public void incThreadCount(){
        threadCount++;
        notify();//завершаємо while в getmin()
    }

    private int getThreadCount() {
        return threadCount;
    }

    public long threadMin(){
        ThreadMin[] threadMins = new ThreadMin[threadNum];//Создаємо масив для потоків
        int len = dim/threadNum;//Знаходимо крок між межами масиву
        for (int i=0;i<threadNum-1;i++) {
            threadMins[i] = new ThreadMin(len*i, len*(i+1), this);//Создаєм поток в масиві
            threadMins[i].start();//Запускаємо поток
        }
        threadMins[threadNum-1]= new ThreadMin(len*(threadNum-1), dim, this);//Создаєм послєдній поток в масиві, щоб останні елементи не втрачались
        threadMins[threadNum-1].start();//Запускаємо поток
        return getMin();
    }
}
