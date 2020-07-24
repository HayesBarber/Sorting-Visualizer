import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JPanel{

    Color c = new Color(255,255,255);
    Shape[] rec = new Shape[50];
    Random random = new Random();
    int y= 125 , width = 3;
    int[] height = new int[50];
    int[] x = new int[50];
    int speed = 20;


    public Frame() {
        x[0] = 100;
        for(int i= 1; i < x.length; i++) {
            x[i] = x[i-1] += 10;
        }

        for(int i= 0; i < height.length; i++) {
            height[i] = random.nextInt(225);
        }
        setBackground(Color.DARK_GRAY);
    }

    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D) g2;


        for(int i = 0; i < rec.length; i++) {

            rec[i] = new Rectangle(x[i],y,width,height[i]);
            g.draw(rec[i]);
            g.setColor(c);
            g.fill(rec[i]);

        }


    }

    public void bubble() {
        int n = height.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (height[j] > height[j+1]){
                    int temp = height[j];
                    height[j] = height[j+1];
                    height[j+1] = temp;
                }
                try {
                    Thread.sleep(speed);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int [n1];
        int[] R = new int [n2];

        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]){
                arr[k] = L[i];
                i++;
            }
            else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void mergeSort(int[] arr, int l, int r) {

        if (l < r)
        {
            int m = (l+r)/2;

            mergeSort(height, l, m);
            try {
                Thread.sleep(speed);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mergeSort(arr , m+1, r);
            try {
                Thread.sleep(speed);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(arr, l, m, r);
            try {
                Thread.sleep(speed);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void insertion() {
        int n = height.length;
        for (int i = 1; i < n; ++i) {
            int key = height[i];
            int j = i - 1;
            while (j >= 0 && height[j] > key) {
                height[j + 1] = height[j];
                j = j - 1;
                try {
                    Thread.sleep(speed);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            height[j + 1] = key;
        }
    }

    public void selection() {
        int n = height.length;

        for (int i = 0; i < n-1; i++){
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (height[j] < height[min_idx])
                    min_idx = j;
                try {
                    Thread.sleep(speed);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int temp = height[min_idx];
            height[min_idx] = height[i];
            height[i] = temp;
        }
    }

    public int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (arr[j] < pivot)
            {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    public void quick(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            repaint();
            quick(arr, low, pi-1);
            repaint();
            quick(arr, pi+1, high);
            try {
                Thread.sleep(speed);
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset() {
        for(int i= 0; i < height.length; i++) {
            height[i] = random.nextInt(150);
        }
        repaint();

    }

}