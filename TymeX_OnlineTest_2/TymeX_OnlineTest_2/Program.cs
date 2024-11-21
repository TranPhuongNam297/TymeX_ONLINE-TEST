using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TymeX_OnlineTest_2
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int[] numbers = { 3, 7, 1, 2, 6, 4 }; 
            int n = 6; 
            Console.WriteLine("The array is: ");
            foreach (int number in numbers)
            {
                Console.Write(number + " "); 
            }
            Console.WriteLine(); 
            MissingNumber mN = new MissingNumber();
            int missingNumber = mN.FindMissingNumber(numbers, n);
            Console.WriteLine("The missing number is: " + missingNumber);
            Console.ReadLine();
        }
    }
}
