import java.util.Random;

public class ArrayProcessor {
    int array [][];
    public ArrayProcessor(int m, int n, boolean init_random, int bound)
    {
        this.array = new  int [m][n];
        if(init_random) this.initRandom(bound);
        else this.initSnake();
    }

    public void printMaxes()
    {
        int maxes [] = this.maxesInStrings();
        for(int i = 0 ; i < maxes.length ; i ++)
        {
            System.out.println("Строка  " + i + " max = " + maxes[i]);
        }
    }

    public void printArray()
    {
        for(int i = 0 ; i < this.array.length ; i++)
        {
            for(int j = 0 ; j < this.array[i].length ; j++)
            {
                System.out.print(this.array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] maxesInStrings()
    {
        int maxes[] = new int[this.array.length];
        for(int i = 0 ; i < this.array.length ; i++)
        {
            maxes[i] = this.array[i][0];
            for(int j = 0 ; j < this.array[i].length ; j++)
            {
                if(maxes[i] < this.array[i][j]) maxes[i] = this.array[i][j];
            }
        }
        return maxes;
    }

    public int[] maxSumInStrings()
    {
        int max_sum = 0;
        int index_max_sum = 0;
        for(int i = 0 ; i < this.array.length ; i++)
        {
            int sum = 0;
            for(int j = 0 ; j < this.array[i].length ; j++)
            {
                sum += this.array[i][j];
            }
            if(max_sum < sum)
            {
                max_sum = sum;
                index_max_sum = i;
            }
        }
        int result [] = {index_max_sum, max_sum};
        return result;
    }

    public void printStringMax()
    {
        int max [] = this.maxSumInStrings();
        System.out.println("Индекс строки с максимальной суммой элементов = " + max[0] + " Сумма = " + max[1]);
    }

    public void initSnake()
    {
        int num = 0;
        for(int i = 0 ; i < this.array.length ; i++)
        {
            if(i%2 == 0)
            {
                for(int j = 0 ; j < this.array[i].length ; j++)
                {
                    this.array[i][j] = ++num;
                }
            }
            else
            {
                for(int j = this.array[i].length - 1 ; j >=0 ; j--)
                {
                    this.array[i][j] = ++num;
                }
            }
         }
    }

    public void initRandom(int bound)
    {
        Random r = new Random();
        for(int i = 0 ; i < this.array.length ; i++)
        {
            for(int j = 0 ; j < this.array[i].length ; j++)
            {
                this.array[i][j] = r.nextInt(bound);
            }
        }
    }
}
class Execute {
    public static void main(String[] args) {
        ArrayProcessor ap = new ArrayProcessor(10, 5, true, 10);
        ap.printArray();
        ap.printMaxes();
        ap.printStringMax();
        ap = new ArrayProcessor(10,7,false, 10);
        ap.printArray();
    }
}
