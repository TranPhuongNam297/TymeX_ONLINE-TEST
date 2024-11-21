using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TymeX_OnlineTest_2
{
    internal class MissingNumber
    {
        public int FindMissingNumber(int[] arr, int n)
        {

            int expectedSum = (n + 1) * (n + 2) / 2;

            int actualSum = arr.Sum();

            return expectedSum - actualSum;
        }
    }
}
