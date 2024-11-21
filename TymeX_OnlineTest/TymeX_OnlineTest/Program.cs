using System;
using System.Collections.Generic;
using TymeX_OnlineTest;

namespace ProductInventoryManagement
{
    class Program
    {
        static void Main(string[] args)
        {
            // Tạo danh sách sản phẩm
            List<Product> products = new List<Product>
            {
                new Product("Laptop", 999.99, 5),
                new Product("Smartphone", 499.99, 10),
                new Product("Tablet", 299.99, 0),
                new Product("Smartwatch", 199.99, 3)
            };

           
            ProductManager manager = new ProductManager(products);

           
            double totalValue = manager.CalculateTotalInventoryValue();
            Console.WriteLine($"Total inventory value: {totalValue:F2}");
            Console.WriteLine();
    
            string mostExpensiveProduct = manager.FindMostExpensiveProduct();
            Console.WriteLine($"Most expensive product: {mostExpensiveProduct}");
            Console.WriteLine();

          
            bool isHeadphonesInStock = manager.CheckProductInStock("Headphones");
            Console.WriteLine($"Headphones in stock: {isHeadphonesInStock}");
            Console.WriteLine();

          
            Console.WriteLine("Products sorted by price (descending):");
            List<Product> sortedByPrice = manager.SortProducts("price", descending: true);
            manager.PrintProductList(sortedByPrice);
            Console.WriteLine();

            Console.WriteLine("Products sorted by quantity (ascending):");
            List<Product> sortedByQuantity = manager.SortProducts("quantity", descending: false);
            manager.PrintProductList(sortedByQuantity);

            Console.ReadLine();
        }
    }
}
