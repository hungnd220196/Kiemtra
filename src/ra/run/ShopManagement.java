package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ShopManagement {
    static Scanner scanner = new Scanner(System.in);
    static List<Categories> categoriesList = new ArrayList<>();
    static List<Product> productList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("******************SHOP MENU*******************\n" +
                    "1. Quản lý danh mục sản phẩm\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Thoát\n");
            System.out.println("mời bạn nhập lựa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayCategoryManagement();
                    break;
                case 2:
                    displayProductManagement();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("không hợp lệ, mời nhập lại");
                    break;
            }
        }
    }

    public static void displayCategoryManagement() {
        while (true) {
            System.out.println("********************CATEGORIES MENU***********\n" +
                    "1. Nhập thông tin các danh mục\n" +
                    "2. Hiển thị thông tin các danh mục\n" +
                    "3. Cập nhật thông tin danh mục\n" +
                    "4. Xóa danh mục\n" +
                    "5. Cập nhật trạng thái danh mục\n" +
                    "6. Thoát\n");
            System.out.println("mời bạn nhập lựa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    displayCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory(scanner);
                    break;
                case 5:
                    updateCategorieStatus(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("số không hợp lệ mời nhập lại");
                    break;
            }
        }
    }

    // thêm danh mục
    public static void addCategory() {
        System.out.println("Nhap số danh mục muốn thêm");
        byte count = new Scanner(System.in).nextByte();
        for (int i = 1; i <= count; i++) {
            System.out.println("Nhập danh mục thứ " + i);
            Categories categories = new Categories();
            categories.inputData(scanner, categoriesList);
            categoriesList.add(categories);
        }
        System.out.println("Đã thêm mới thành công " + count + " danh mục");
    }

    // in danh mục
    public static void displayCategory() {
        if (categoriesList.isEmpty()) {
            System.err.println("Danh mục trống");
        } else {
            for (int i = 0; i < categoriesList.size(); i++) {
                categoriesList.get(i).displayData();
            }
        }
    }

    // update danh mục
    public static void updateCategory() {
        System.out.println("mời bạn nhập id danh mục muốn update");
        int idEdit = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() == idEdit) {
                System.out.println("thông tin chưa sửa là ");
                categoriesList.get(i).displayData();

                System.out.println("mời bạn nhập thông tin cần sửa");
                categoriesList.get(i).inputData(scanner, categoriesList);

                System.out.println("thông tin sau khi sửa là ");
                categoriesList.get(i).displayData();
            } else {
                System.out.println("không tìm thấy id danh mục cần update");
                break;
            }
        }
    }

    //xóa danh mục
    public static void deleteCategory(Scanner scanner) {
        System.out.println("mời bạn nhập id danh mục cần delete");
        int idDelete = Integer.parseInt(scanner.nextLine());
        //kiem tra danh muc co san pham chua
        boolean check = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getCatalogId() == idDelete) {
                check = true;
                System.out.println("danh mục có sản phẩm không thể xóa");
                break;
            }
        }
        if (!check) {
            for (int i = 0; i < categoriesList.size(); i++) {
                if (categoriesList.get(i).getCatalogId() == idDelete) {
                    categoriesList.remove(categoriesList.get(i));
                    System.out.println("đã xóa danh mục có mã id " + idDelete);
                }
            }

        }

    }

    //cập nhật trạng thái
    public static void updateCategorieStatus(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() == catalogId) {
                categoriesList.get(i).setCatalogStatus(!categoriesList.get(i).isCatalogStatus());
                System.out.println("đã cập nhật trạng thái ");
            } else {
                System.err.println("id danh mục không tồn tại");
            }

        }
    }

    public static void displayProductManagement() {
        while (true) {
            System.out.println("*******************PRODUCT MANAGEMENT*****************\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn\n" +
                    "phím)\n" +
                    "8. Thoát\n");
            System.out.println("mời bạn nhập lựa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    sortProductByPrice();
                    break;
                case 4:
                    updateProductById(scanner);
                    break;
                case 5:
                    deleteProductById();
                    break;
                case 6:
                    searchProductByName();
                    break;
                case 7:
                    searchProductInRange();
                    break;
                case 8:
                    return;
                default:
                    break;
            }
        }
    }

    private static void searchProductInRange() {
        System.out.println("Nhập khoảng giá bạn muốn tìm kiếm (a – b)");
        System.out.print("Nhập giá thấp nhất (a): ");
        float priceLow = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhập giá cao nhất (b): ");
        float priceHigh = Float.parseFloat(scanner.nextLine());
        boolean foundProduct1 = false;
        for (Product p : productList) {
            if (p != null && p.getPrice() >= priceLow && p.getPrice() <= priceHigh) {
                p.displayData(categoriesList);
                foundProduct1 = true;
            }
        }
        if (!foundProduct1) {
            System.out.println("Không tìm thấy sản phẩm nào trong khoảng giá từ " + priceLow + " đến " + priceHigh);
        }
    }

    private static void searchProductByName() {
        System.out.println("mời bạn nhập tên cần tìm kiếm ");
        String nameSearch = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().toLowerCase().contains(nameSearch.toLowerCase())) {
                check = true;
                productList.get(i).displayData(categoriesList);
                break;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy tên sản phẩm");
        }
    }

    private static void deleteProductById() {
        System.out.println("mời bạn nhập id sp cần delete");
        String idDelete = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(idDelete)) {
                check = true;
                productList.remove(productList.get(i));
                System.out.println("đã xóa thành công sản phẩm có mã id " + idDelete);
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id sp cần xóa " + idDelete);
        }
    }


    private static void updateProductById(Scanner scanner) {
        System.out.println("mời bạn nhập id sp cần update");
        String idEdit = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(idEdit)) {
                check = true;
                System.out.println("thông tin ban đầu sản phẩm");
                productList.get(i).displayData(categoriesList);
                System.out.println("mời bạn nhập thông tin cần sửa");
                productList.get(i).inputData(scanner, productList, categoriesList);
                System.out.println("thông tin sau khi sửa");
                productList.get(i).displayData(categoriesList);
            }
            check = false;
            break;

        }
        System.out.println("không tìm thấy sp có id " + idEdit);
    }

    public static void addProduct(Scanner scanner) {
        System.out.println("bạn muốn thêm mấy sản phẩm");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= n; i++) {
            System.out.println("mời bạn nhập sản phẩm thứ " + i);
            Product products = new Product();
            products.inputData(scanner, productList, categoriesList);
            productList.add(products);
        }

    }

    private static void displayProduct() {
        if (productList.isEmpty()) {
            System.err.println("sản phẩm trống");
        } else {
            for (int i = 0; i < productList.size(); i++) {
                productList.get(i).displayData(categoriesList);
            }
        }
    }

    private static void sortProductByPrice() {
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        for (Product pro : productList) {
            pro.displayData(categoriesList);
        }
    }

}

