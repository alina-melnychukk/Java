package org.example.pz1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Product> productsList = new ArrayList<>();
    private static Cart cart = new Cart();
    private static OrderHistory orderHistory = new OrderHistory();

    public static void main(String[] args) {
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19, "Ноутбук .....", electronics);
        Product product2 = new Product(2, "Смартфон", 194, "Смартфон....", smartphones);
        Product product3 = new Product(3, "Навушники", 196, "Навушники....", accessories);

        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів за назвою або категорією");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Список товарів:");
                    for (Product product : productsList) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();
                    if (id == 1) cart.addProduct(product1);
                    else if (id == 2) cart.addProduct(product2);
                    else if (id == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;
                case 3:
                    System.out.println("Товари в кошику:");
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.addOrder(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 5:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    id = scanner.nextInt();
                    if (id == 1) cart.removeProduct(product1);
                    else if (id == 2) cart.removeProduct(product2);
                    else if (id == 3) cart.removeProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;
                case 6:
                    if (orderHistory.getOrders().isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("Історія замовлень:");
                        for (Order order : orderHistory.getOrders()) {
                            System.out.println(order);
                            System.out.println("----------");
                        }
                    }
                    break;
                case 7:
                    searchOption(scanner);
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
    private static void searchOption(Scanner scanner) {
        System.out.println("Виберіть тип пошуку:");
        System.out.println("1 - Пошук за назвою товару");
        System.out.println("2 - Пошук за категорією товару");
        int searchOption = scanner.nextInt();
        switch (searchOption) {
            case 1:
                searchByName(scanner);
                break;
            case 2:
                searchByCategory(scanner);
                break;
            default:
                System.out.println("Невідома опція пошуку. Спробуйте ще раз.");
                break;
        }
    }

    private static void searchByName(Scanner scanner) {
        System.out.println("Введіть назву товару для пошуку:");
        String name = scanner.next();
        System.out.println("Результати пошуку за назвою '" + name + "':");
        for (Product product : productsList) {
            if (product.getName().contains(name)) {
                System.out.println(product);
            }
        }
    }

    private static void searchByCategory(Scanner scanner) {
        System.out.println("Введіть категорію товару для пошуку:");
        String categoryName = scanner.next();
        System.out.println("Результати пошуку за категорією '" + categoryName + "':");
        for (Product product : productsList) {
            if (product.getCategory().getName().contains(categoryName)) {
                System.out.println(product);
            }
        }
    }
}
