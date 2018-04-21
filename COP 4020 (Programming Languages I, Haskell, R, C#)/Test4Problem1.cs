using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test4
{
    public class Test4Problem1
    {
        public static IEnumerable<int> myFilter(IEnumerable<int> input)
        {
            return input.Where(x => x <= 50 || x % 5 != 0).Select(y => y * y * y).Where(z => z % 2 != 0);

        }
        static void Main(string[] args)
        {
            Random rnd = new Random();
            var listForProblem = Enumerable.Range(1, 100).Select(i => rnd.Next() % 101);
            var answer = Test4Problem1.myFilter(listForProblem);
            foreach(int i in answer)
            {
                Console.WriteLine(i);
            }
        }
    }

   
}
