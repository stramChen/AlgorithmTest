package per.stram.algorithmtest;

import org.junit.Test;

/**
 * des:
 *
 * @author: chensichuang@jd.com
 * @date: 2019/10/31 5:30 PM
 */
public class Sort {

    @Test
    public void main() {
        int arr[] = new int[]{0, 8, 3, 4, 1, 9, 11, 2, 2, 14, 65, 22};

//        heapSort(arr);

//        insertSort(arr);

//        binaryInsertSort(arr,arr.length);

//        shellSort(arr,arr.length);

//        shellSort2(arr,arr.length);

//        bubleSort(arr, arr.length);

//        quickSort(arr,0,arr.length-1);

//        mergeSort(arr,0,arr.length-1);

        radixSort(arr, 2);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public void heapSort(int val[]) {
        //create heap
        creatMaxHeap(val);

        int length = val.length - 1;
        //adjust heap
        for (int i = length; i > 0; i--) {
            int temp = val[1];
            System.out.print(temp + " ");
            val[1] = val[i];
            val[i] = temp;
            adjustDown(val, 1, i - 1);
        }

    }

    public void creatMaxHeap(int val[]) {
        //the number of the node
        int length = val.length - 1;

        for (int i = length / 2; i > 0; i--) {
            adjustDown(val, i, length);
        }
    }

    /**
     * put min number down to the tree
     *
     * @param val the array to adjust
     * @param i   from where to adjust
     * @param len the scale of adjust array
     */
    public void adjustDown(int val[], int i, int len) {
        //put the value whick to ajust in the temporary space.
        val[0] = val[i];
        for (int j = i * 2; j <= len; j = j * 2) {
            if (j < len && val[j] < val[j + 1]) j++;

            if (val[i] >= val[j]) break;

            if (val[i] < val[j]) {
                val[i] = val[j];
                i = j;
            }
            val[j] = val[0];
        }
    }

    /**
     * @param a
     */
    public void insertSort(int a[]) {
        int i, j;
        for (i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                //move a[j] unitl a[j]>temp,then the j+1 is the location you want to insert
                for (j = i - 1; temp < a[j]; j--) {
                    a[j + 1] = a[j];
                }
                a[j + 1] = temp;
            }
        }
    }

    /**
     * time = o(n^2)
     * space = o(1)
     * steady
     *
     * @param a
     * @param n
     */
    public void binaryInsertSort(int a[], int n) {
        int i, j, temp, high, low, mid;
        for (i = 1; i < n; i++) {
            low = 0;
            high = i - 1;
            temp = a[i];
            while (low <= high) {
                mid = (low + high) / 2;
                if (a[mid] > temp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            for (j = i - 1; j > low - 1; j--) {
                a[j + 1] = a[j];
            }

            a[low] = temp;
        }
    }

    /**
     *
     */
    public void shellSort(int a[], int n) {
        int i, j, k, dk, temp;
        //use 2 as delta,you can also use 3 or any other word
        for (dk = n / 2; dk >= 1; dk = dk / 2) {
            //traverse every group
            for (i = 0; i < dk; i++) {
                for (j = i + dk; j < n; j += dk) {
                    //execute insertSort with every group
                    if (a[j] < a[j - dk]) {
                        temp = a[j];
                        for (k = j - dk; k > 0 && temp < a[k]; k -= dk) {
                            a[k + dk] = a[k];
                        }
                        a[k + dk] = temp;
                    }
                }
            }
        }
    }

    /**
     * time:o(n^1.3)-o(n^2))
     * sapce:o(1)
     * unsteady
     */
    public void shellSort2(int a[], int n) {
        int i, j, dk, temp;
        //use 2 as delta,you can also use 3 or any other word
        for (dk = n / 2; dk >= 1; dk = dk / 2) {
            //traverse every group,
            // but in this way the order of traversal  may not accroding the order in every group.
            for (i = dk; i < n; i++) {
                //execute insertSort with every group
                if (a[i] < a[i - dk]) {
                    temp = a[i];
                    for (j = i - dk; j > 0 && temp < a[j]; j -= dk) {
                        a[j + dk] = a[j];
                    }
                    a[j + dk] = temp;
                }
            }
        }
    }

    /**
     * time:o(n^2))
     * sapce:o(1)
     * steady
     *
     * @param a
     * @param n
     */
    public void bubleSort(int a[], int n) {
        int i, j, temp;
        boolean flag;
        for (i = 0; i < n - 1; i++) {
            flag = false;
            for (j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) return;
        }
    }

    public void quickSort(int a[], int low, int high) {
        if (low < high) {
            int pivotPos = partition(a, low, high);
            quickSort(a, low, pivotPos - 1);
            quickSort(a, pivotPos + 1, high);
        }
    }

    /**
     * it use The idea of partition.
     * select a value as the central(default use first value )
     * traverse all value ,put the large one on the right of the central,the small one on the left of the central
     *
     * @param a
     * @param low
     * @param high
     */
    public int partition(int a[], int low, int high) {
        int temp = a[low];
        while (low < high) {
            while (low < high && a[high] >= temp) {
                --high;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                ++low;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }

    /**
     *
     * @param a
     * @param low
     * @param high
     */
    public void mergeSort(int a[], int low, int high) {
        //use recursion to decompose the array into single group until every group just has one value
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            //when the recursion is returning,combine two group into one group every time.
            merge(a, low, mid, high);
        }
    }

    int[] b = new int[100];

    public void merge(int a[], int low, int mid, int high) {
        int i, j, k;
        //copy the the group which to sort int to array b
        for (k = low; k <= high; k++) b[k] = a[k];
        //if the group one > group two,move b's group two ing a's group one ,
        //else move b's group one into a's group one
        for (i = low, j = mid + 1, k = i; i <= mid && j <= high; k++) {
            if (a[i] < a[j]) {
                a[k] = b[i++];
            } else {
                a[k] = b[j++];
            }
        }
        //move the left group into  a
        while (i <= mid) {
            a[k++] = b[i++];
        }
        while (j <= high) {
            a[k++] = b[j++];
        }
    }

    /**
     * time:(t*(r+n))  int numberRadixSort r=10,mean that 0,1,2,3,4,5,6,7,8,9
     * space:r
     * steady
     * @param a arr
     * @param t the number or digits,for example 100,t=3.
     */
    public void radixSort(int a[], int t) {

        //the index location of digit,数字下标所在的位置
        int m = 1;
        //collect times,收集的次数
        int n=1;
        int bucket[][] = new int[10][a.length];
        int count[] = new int[10];
        //collect t times in total 一共收集了t 次
        while (n <= t) {
            //allocte 分配
            for (int i = 0; i < a.length; i++) {
                //use lsd to find which bucket we shout put in,the result is the number of  the bucket
                //按照低位优先原则，来寻找哪个桶我们需要放置，比如100的是t=1时，因为个位数是0，所以放在0号桶
                int lsd = a[i] / m % 10;
                bucket[lsd][count[lsd]] = a[i];
                //the nex index of bucket we should put
                count[lsd]++;
            }
            //collcet 收集
            for (int i = 0, k = 0; i < 10; i++) {
                if (count[i] != 0) {
                    for (int j = 0; j < count[i]; j++) {
                        a[k] = bucket[i][j];
                        k++;
                    }
                }
                count[i]=0;
            }
            n++;
            m*=10;
        }
    }
}

