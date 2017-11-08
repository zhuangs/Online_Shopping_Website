//import java.util.HashSet;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import com.myapp.finalproject.dao.DAO;
//import com.myapp.finalproject.pojo.Email;
//import com.myapp.finalproject.pojo.Order;
//import com.myapp.finalproject.pojo.Payment;
//import com.myapp.finalproject.pojo.User;
//import com.myapp.finalproject.pojo.WishList;
//
//public class Test {
//
//	public static void main(String args[]){
//		SessionFactory sessionFactory = DAO.getSessionfactory();
//		
//		Session session = sessionFactory.openSession();
//		
//		User user = new User();
//		//set of orders
//		//email
//		// payment
//		HashSet orders = new HashSet<Order>();
//		Email email = new Email();
//		email.setEmailId("aaaa");
//		Payment payment = new Payment();
//		payment.setCardNumber("11");
//		payment.setExpiredDate(null);
//		payment.setNameOnCard("aaa");
//		payment.setPaymentId("1");
//		payment.setUser(user);
//		WishList wishList = new WishList();
//		
//		//user.setPayment
//		//user.setemail
//		//user.setorders
//		user.setAddress("222");
//		user.setEmail(email);
//		user.setName("sss");
//		user.setOrders(orders);
//		user.setPassword("2222");
//		user.setPayment(payment);
//		user.setPhoneNumber("22222");
////		user.setWishList(wishList);
//		
//		session.save(user);
//	}
//}
