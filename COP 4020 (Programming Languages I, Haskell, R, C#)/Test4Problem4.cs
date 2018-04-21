using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace Test4
{
    public class Test4Problem4
    {
        static int[] data = new int[10000000];
        static bool quitflag = false;
        static void calc(int startingIndex, int reps)
        {
            DateTime startTime, endTime;
            startTime = DateTime.Now;
                for (int i = startingIndex; i < startingIndex + reps; i++)
                {
                    data[i] = (int)(Math.Atan(i) * Math.Acos(i) * Math.Cos(i) * Math.Sin(i));
                }
                endTime = DateTime.Now;
                int elapsedMillisecs = (int)((TimeSpan)(endTime - startTime)).TotalMilliseconds;
                Console.WriteLine("Sequentially, calc takes {0} milliseconds to run", elapsedMillisecs);
            startTime = DateTime.Now;
            Parallel.For(startingIndex, startingIndex + reps, new ParallelOptions { MaxDegreeOfParallelism = 4 }, i => data[i] = (int)(Math.Atan(i) * Math.Acos(i) * Math.Cos(i) * Math.Sin(i)));
            endTime = DateTime.Now;
            elapsedMillisecs = (int)((TimeSpan)(endTime - startTime)).TotalMilliseconds;
            Console.WriteLine("Sequentially, calc takes {0} milliseconds to run", elapsedMillisecs);

        }
       
        static void Main(string[] args)
        {

            calc(0, data.Length);
            Console.ReadKey();
        }

    }
}
