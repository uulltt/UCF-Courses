using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Globalization;
//Aaron Varkonyi, COP 4020, C# Caesar Cipher


namespace CaesarCipher
{
    static class StringExtensions
    {
       
        public static byte let2nat(char a)
        {
            return (byte)(a.ToString().ToLower()[0] - 97); //we could use char.tolower but we can't test that in the immediate window.
        }

        public static char nat2let(int i)
        {
            return (char)(i + 97);
        }

        public static char shift(int i, char a)
        {
            return char.IsLetter(a) ? nat2let((((let2nat(a) + i) % 26) + 26) % 26) : a;
        }

        public static string encode(int i, string s)
        {
            string str = "";
            foreach(char c in s)
            {
                str += shift(i, c);
            }
            return str;
        }

        public static string decode(int i, string s)
        {
            return encode(-i, s);
        }

        static float[] table = { 8.2f, 1.5f, 2.8f, 4.3f, 12.7f, 2.2f, 2.0f, 6.1f, 7.0f, 0.2f, 0.8f, 4.0f, 2.4f, 6.7f, 7.5f, 1.9f, 0.1f, 6.0f, 6.3f, 9.1f, 2.8f, 1.0f, 2.4f, 0.2f, 2.0f, 0.1f };

        public static int lowers(string s)
        {
            int i = 0;
            foreach(char c in s)
            {
                if (char.IsLower(c))
                {
                    i++;
                }
            }
            return i;
        }

        public static int count(char a, string s)
        {
            int i = 0;
            foreach (char c in s)
            {
                if (c == a)
                {
                    i++;
                }
            }
            return i;
        }

        public static float percent(int i, int j)
        {
            return 100f * (float)i / (float)j;
        }

        public static float[] freqs(string s)
        {
            float[] fs = new float[26];
            for(char c = 'a'; c < 'z'; c++)
            {
                fs[c - 'a'] = percent(count(c, s), lowers(s));
            }
            return fs;
        }

        public static T[] rotate<T>(int i, T[] arr)
        {
            Queue<T> q = new Queue<T>(arr);
            Queue<T> s;
            while (i > 0)
            {
                s = new Queue<T>(q);
                T x = s.Dequeue();
                s = new Queue<T>(s);
                s.Enqueue(x);
                q = new Queue<T>(s);
                i--;
            }
            return q.ToArray();
        }

        public static float chisqr(float[] os, float[] es)
        {
            float f = 0f;
            for(int i = 0; i < Math.Min(es.Length, os.Length); i++)
            {
                f += ((os[i] - es[i])*(os[i] - es[i]))/es[i];
            }
            return f;
        }

        public static int position<T>(T t, T[] arr)
        {
            int i = 0;
            for(; i < arr.Length; i++)
            {
                if (arr[i].Equals(t))
                {
                    break;
                }
            }
            return i;
        }

        public static T minimum<T>(T[] arr) where T : IComparable<T>
        {
            T t = arr[0];
            for(int i = 1; i < arr.Length; i++)
            {
                if (arr[i].CompareTo(t) < 0)
                {
                    t = arr[i];
                }
            }
            return t;
        }

        public static string Cracked (string str)
        {
            float[] f = new float[26];
            for(int i = 0; i < 26; i++)
            {
                f[i] = chisqr(rotate<float>(i, freqs(str)), table);
            }
            return decode(position<float>(minimum<float>(f), f), str);
        }

        

    }
}
