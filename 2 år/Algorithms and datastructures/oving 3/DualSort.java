public class DualSort{

    public static void main(String[] args)
    {
        int[] arr = { 24, 8, 42, 75, 29, 77, 38, 57 };

        dualPivotQuickSort(arr, 0, 7);

        System.out.print("Sorted array: ");
        for (int i = 0; i < 8; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }
    static void dualPivotQuickSort(int[] arr,
                                   int low, int high)
    {
        if (low < high)
        {
            //avoiding O(n^2) if arr is already sorted
            ArrayManager.swap(arr, arr[low], arr[low+(high-low)/3]);
            ArrayManager.swap(arr, arr[high], arr[high-(high-low)/3]);
            // piv[] stores left pivot and right pivot.
            // piv[0] means left pivot and
            // piv[1] means right pivot
            int[] piv;
            piv = partition(arr, low, high);

            dualPivotQuickSort(arr, low, piv[0] - 1);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1);
            dualPivotQuickSort(arr, piv[1] + 1, high);
        }
    }

    static int[] partition(int[] arr, int low, int high)
    {
        if (arr[low] > arr[high])
            ArrayManager.swap(arr, low, high);
        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
                p = arr[low], q = arr[high];

        while (k <= g)
        {

            // If elements are less than the left pivot
            if (arr[k] < p)
            {
                ArrayManager.swap(arr, k, j);
                j++;
            }

            // If elements are greater than or equal
            // to the right pivot
            else if (arr[k] >= q)
            {
                while (arr[g] > q && k < g)
                    g--;

                ArrayManager.swap(arr, k, g);
                g--;

                if (arr[k] < p)
                {
                    ArrayManager.swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // Bring pivots to their appropriate positions.
        ArrayManager.swap(arr, low, j);
        ArrayManager.swap(arr, high, g);

        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[] { j, g };
    }


    }
