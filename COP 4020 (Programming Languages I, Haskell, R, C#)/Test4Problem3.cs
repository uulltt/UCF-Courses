using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Test4
{
    public class Test4Problem3
    {
        public static IEnumerable<int> getInfo()
        {
            NORTHWNDEntities db = new NORTHWNDEntities();
            return db.Products.Where(x => (x.CategoryID + 1)/2 == 2).Select(y => y.UnitsInStock * 7).Cast<int>().Where(z => z <= 100);
           
        }
        static void Main(string[] args)
        {
            var answer = Test4Problem3.getInfo();
            foreach(int i in answer)
            {
                Console.WriteLine(i);
            }
        }
    }
}
