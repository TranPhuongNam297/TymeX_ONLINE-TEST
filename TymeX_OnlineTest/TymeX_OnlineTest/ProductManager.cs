using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TymeX_OnlineTest
{
    internal class ProductManager
    {
        private List<Product> products; // Danh sách sản phẩm

        public ProductManager(List<Product> products)
        {
            this.products = products;
        }

        // Tính tổng giá trị hàng tồn kho
        public double CalculateTotalInventoryValue()
        {
            return products.Sum(product => product.Price * product.Quantity);
        }

        // Tìm sản phẩm có giá cao nhất
        public string FindMostExpensiveProduct()
        {
            Product mostExpensive = products.OrderByDescending(p => p.Price).First();
            return mostExpensive.Name;
        }

        // Kiểm tra xem một sản phẩm có trong kho không
        public bool CheckProductInStock(string productName)
        {
            return products.Any(product => product.Name.Equals(productName, StringComparison.OrdinalIgnoreCase));
        }

        // Sắp xếp sản phẩm theo thuộc tính (giá hoặc số lượng)
        public List<Product> SortProducts(string sortBy, bool descending)
        {
            switch (sortBy.ToLower())
            {
                case "price": // Sắp xếp theo giá
                    return descending ? products.OrderByDescending(p => p.Price).ToList() : products.OrderBy(p => p.Price).ToList();
                case "quantity": // Sắp xếp theo số lượng
                    return descending ? products.OrderByDescending(p => p.Quantity).ToList() : products.OrderBy(p => p.Quantity).ToList();
                default:
                    return products;
            }
        }

        // In danh sách sản phẩm ra màn hình
        public void PrintProductList(List<Product> productList)
        {
            foreach (var product in productList)
            {
                Console.WriteLine($"{product.Name}: price {product.Price:F2}, quantity {product.Quantity}");
            }
        }
    }
}