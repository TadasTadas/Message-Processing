package sale;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
* <h1>Sales class</h1>
* All Sales are stored in this class
*
* @author  Tadas Poskus
* @version 1.0
* @since   2017-06-25
*/

public class Sales {
	private static ArrayList<Sale> saleList = new ArrayList<Sale>();
	private static ArrayList<String> salesEvents = new ArrayList<String>();
	
	public static void addSale(String product, int price){
		Sale sale = new Sale();
		sale.setProduct(product);
		sale.setPrice(price);
		saleList.add(sale);
		salesEvents.add(new StringBuilder(product+" - new sale added").toString());
	}
	
	public static void addToCurrentSale(String product, int cof){
		salesEvents.add(new StringBuilder(product+" - sales price changed by +"+cof).toString());
		saleList.stream()
				.filter(s -> s.getProduct().equals(product))
				.forEach(s -> s.setPrice(s.getPrice()+cof));
	}
	
	public static void subtractCurrentSale(String product, int cof){
		salesEvents.add(new StringBuilder(product+" - sales price changed by -"+cof).toString());
		saleList.stream()
				.filter(s -> s.getProduct().equals(product))
				.forEach(s -> s.setPrice(s.getPrice()-cof));
	}
	
	public static void multiplyCurrentSale(String product, int cof){
		salesEvents.add(new StringBuilder(product+" - sales price changed by *"+cof).toString());		
		saleList.stream()
				.filter(s -> s.getProduct().equals(product))
				.forEach(s -> s.setPrice(s.getPrice()*cof));
	}
	
	public static void printTempStatistic(){
		System.out.println("Number of sales processed: [product] - [number of sales], [total price]");
		Map<String, int[]> grouped = saleList
				  .stream()
				  .collect(Collectors.groupingBy(Sale::getProduct,
				                                 Collector.of(
				                                     () -> new int[2],
				                                     (a, t) -> { a[0] += 1; a[1] += t.getPrice(); },
				                                     (a, b) -> { a[0] += b[0]; a[1] += b[1]; return a; })));
		for (String key : grouped.keySet()) {
		    System.out.println(key + " - " + grouped.get(key)[0] + ", " + grouped.get(key)[1]);
		}
	}
	
	public static void printSalesStatistic(){
		salesEvents.stream().forEach(s -> System.out.println(s));
	}
	
	public static void printSales(){
		saleList.stream().forEach(s -> System.out.println(s.getProduct()+" "+s.getPrice()));
	}
}
