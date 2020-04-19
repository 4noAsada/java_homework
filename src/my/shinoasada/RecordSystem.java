package my.shinoasada;

import java.time.format.DateTimeFormatter;
import java.awt.print.Book;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


class BookSaleItem {
   private String number;
   private String name;
   private int amount;
   private double price;
   private String publisher;
   private double total;

   public void setNumber(String number) {
      this.number = number;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setAmount(int amount) {
      this.amount = amount;
      this.setTotal(this.getAmount() * this.getPrice());
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public void setPublisher(String publisher) {
      this.publisher = publisher;
   }

   public void setTotal(double total) {
      this.total = total;
   }

   public String getNumber() {
      return number;
   }

   public String getName() {
      return name;
   }

   public int getAmount() {
      return amount;
   }

   public double getPrice() {
      return price;
   }

   public String getPublisher() {
      return publisher;
   }

   public double getTotal() {
      return total;
   }

   public BookSaleItem(String number, String name, int amount, double price, String publisher) {
      System.out.println("[LOG] Constructing BookSaleItem(" + number + ", " + amount + ", " + price + ", " + publisher + ")");
      this.number = number;
      this.name = name;
      this.amount = amount;
      this.price = price;
      this.publisher = publisher;
      this.total = amount * price;
      System.out.println("[LOG] Constructed [" + toString() + "]");
   }

   @Override
   public String toString() {
       return number + "," + name + "," + amount + "," + price + "," + total + "," + publisher;
   }

   public static void main(String[] args) {
      BookSaleItem item = new BookSaleItem("101", "The Model Thinker", 10, 22.5, "Apress");
      System.out.println(item);
   }
}

class BookSaleRecordSystem {
   private ArrayList<BookSaleItem> sale_items;
   private File file;
   public BookSaleRecordSystem() throws IOException {
      System.out.println("[LOG] Constructing BookSaleRecordSystem ... ");
      LocalDate date_today = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
      String filename_suffix = date_today.format(formatter);
      System.out.println("[LOG] File Suffix: " + filename_suffix);

      file = new File("销售记录" + filename_suffix + ".csv");
      System.out.println("[LOG] Filename: " + file.getName());
      sale_items = new ArrayList<BookSaleItem>();
      if (file.exists()) {
         System.out.println("[LOG] File exsited, reading data ... ");
         BufferedReader reader = new BufferedReader(new FileReader(file.getName()));
         String line = reader.readLine();
         while (line != null) {
            String[] info = line.split(",");
            sale_items.add(new BookSaleItem(info[0], info[1], Integer.valueOf(info[2]), Double.valueOf(info[3]), info[4]));
            line = reader.readLine();
         }
         reader.close();
         System.out.println("[LOG] Totally " + sale_items.size() + " read");
      }
   }

   private int search(BookSaleItem item) {
       System.out.println("[LOG] Searching in " + sale_items.size() + " items");
       for (int i = 0; i < sale_items.size(); ++i) {
          if (sale_items.get(i).getNumber().compareTo(item.getNumber()) == 0) {
             System.out.println("[LOG] Found at index " + i);
             return i;
          }
       }
       System.out.println("[LOG] Not found");
       return -1;
   }

   public void add(BookSaleItem item) {
      System.out.println("[LOG] Adding " + item);
      int index = search(item);
      if (index == -1) {
         System.out.println("[LOG] Not found, create new");
         sale_items.add(item);
      } else {
         System.out.println("[LOG] Before merging: " + sale_items.get(index));
         sale_items.get(index).setAmount(sale_items.get(index).getAmount() + item.getAmount());
         System.out.println("[LOG] After merging: " + sale_items.get(index));
      }
   }

   public void save() throws IOException {
      System.out.println("[LOG] Saving ...");
      if (!file.exists()) {
         System.out.println("[LOG] " + file.getName() + " doesn't exist, create new file");
         file.createNewFile();
      }
      BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
      for (BookSaleItem item : sale_items) {
         System.out.println("[LOG] Writting " + item);
         writer.write(item.toString() + "\n");
      }
      System.out.println("[LOG] Totally " + sale_items.size() + " saved");
      writer.close();
   }
}

public class RecordSystem {
   public static void main(String[] args) throws IOException {
       BookSaleRecordSystem system = new BookSaleRecordSystem();
       system.add(new BookSaleItem("101", "The Model Thinker", 30, 25.5, "Apress"));
       system.add(new BookSaleItem("102", "Think in Java", 100, 125.9, "Apress"));
       system.save();
   }
}