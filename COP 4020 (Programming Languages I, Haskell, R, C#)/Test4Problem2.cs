using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test4
{
    public class Test4Problem2
    {
        public static IEnumerable<int> merge(IEnumerable<int> input1, IEnumerable<int> input2, IEnumerable<int> input3)
        {
            var inall3 = input1.Intersect(input2).Intersect(input3);
            var mult3union = input1.Union(input2).Union(input3).Where(x => x % 3 == 0);
            return inall3.Union(mult3union);
        }

        static void Main(string[] args)
        {
            Random rnd = new Random();
            var list1 = Enumerable.Range(1, 10).Select(i => (rnd.Next() % 15) + 1);
            var list2 = Enumerable.Range(1, 10).Select(i => (rnd.Next() % 15) + 1);
            var list3 = Enumerable.Range(1, 10).Select(i => (rnd.Next() % 15) + 1);
            var answer = Test4Problem2.merge(list1, list2, list3);
            foreach (int i in answer)
            {
                Console.WriteLine(i);
            }
        }
    }
}
