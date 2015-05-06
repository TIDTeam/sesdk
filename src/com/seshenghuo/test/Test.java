/**
 * 
 */
package com.seshenghuo.test;


/**
 * @author carlli
 * 
 */
public class Test {

	/**
	 *  
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void toInt() throws NumberFormatException {
		Integer.parseInt("ds");
	}
	
	public static void bytes2hex(){
		byte[] bs = {2, 67, 90, 127, -122, -87, 20};
		
		StringBuilder sb = new StringBuilder(bs.length * 2);  
		  
		for (int i = 0; i < bs.length; i++)     
	    {     
	        int low = bs[i] & 0xF;  
	        int high = (bs[i] >> 4) & 0xF;  
	        sb.append(Character.forDigit(high, 16));  
	        sb.append(Character.forDigit(low, 16));  
	    }  
		
		String hex = sb.toString();
	    System.out.println(hex);
	    
	    for(int j = 0; j < hex.length(); j+=2){
	    	System.out.println((byte)((Character.digit(hex.charAt(j), 16) << 4) ) + (Character.digit(hex.charAt(j + 1), 16) ));
	    	
	    }
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(Config.getItem("annexcode.width"));
		// TaoBaoKeAPI tbk = new TaoBaoKeAPI("stweb");
		// TbkItemsDetailGetRequest param = new TbkItemsDetailGetRequest();
		//
		// param.setNumIids("20305060961");
		// System.out.println(tbk.getProductDetails(param));
		//
		// ToolsAPI t = new ToolsAPI("stweb");
		// System.out.println(t.getTaobaoTime(new TimeGetRequest()));
		//
		// GoodsAPI product = new GoodsAPI("stweb");
		// ItemGetRequest param1 = new ItemGetRequest();
		// param1.setFields("num_iid,title,price,desc_modules,sell_point");
		// param1.setNumIid(37035789846L);
		// System.out.println(product.getItemDetail(param1));

		// String code = Message.createCode("system", "sql", "sql", "1002");
		// String msg = Message.getMessage(code);
		// System.out.println(Message.formatMessage(msg, "Unknown MySQL error",
		// 2000));
		// System.out.println(NumberUtil.bytes2long(NumberUtil.long2bytes(System.currentTimeMillis())));
		// System.out.println(NumberUtil.bytes2int(NumberUtil.int2bytes(-123456789)));

		// System.out.println(System.getProperties());
		// System.out.println(System.getProperty("os.arch"));

		// System.out.println(Util.getRuntimePath());
		// System.out.println("cmd = " + SQL.getSQLCommand(32));
		// System.out.println("type = " + SQL.getSQLType(32));
		// System.out.println("sql = " + SQL.getSQL(32));

		// UserBasicBean bean = new UserBasicBean();

		// java.lang.reflect.Method[] methods = bean.getClass().getMethods();
		// java.lang.reflect.Field[] fields =
		// bean.getClass().getDeclaredFields();
		//
		// for(int i = 0; i < fields.length; i++){
		// System.out.println(fields[i].getName() + "," +
		// fields[i].getGenericType());
		// System.out.println(methods[i].getName() + "," +
		// methods[i].getReturnType());
		// }

		// try {
		// System.out.println(Util.class.getMethod("getRuntimePath").getReturnType());
		// } catch (NoSuchMethodException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SecurityException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// UserProfileBean bb = new UserProfileBean();
		//
		//
		// System.out.println(bb.toString());

		// UserProfile profile = new UserProfile();
		//
		// System.out.println(profile.query(123).toString());

		// try{
		// Test.toInt();
		// }catch(NumberFormatException e){
		// System.out.println(e.getMessage());
		// }

		// System.out.println(Message.getMessage("30410010000"));
//		 System.out.println("test start");
//		 String origin = "test@1234$%&^$&^&%./\\`~&23+_)(*4234234234234";
//		 String e = RSA.encrypt(origin);
//		 String d = RSA.decrypt(e);
//		 String sign = RSA.sign(origin);
//		 boolean verify = RSA.verify(origin, sign);
//		 System.out.println("o: " + origin);
//		 System.out.println("e: " + e);
//		 System.out.println("s: " + e.length());
//		 System.out.println("d: " + d);
//		 System.out.println("sign: " + sign);
//		 System.out.println("verify: " + verify);
//		 System.out.println("test end");
		// System.out.println(RSA.decrypt("euc/Bwe5peq2vICz7n3hJygkPVh7LyyHUuP8kMHGVTLfohpXWqJ2xtEkBlXdlEGzGZyBcJdmg6f2 zlLxwdnC1dZSuRy8ku6KlOsk7aQQa0zRMWdauaeGfaqZgcXu2IopMV9DM6RiynvpMuOcQQdF0387 xapQl7MuXgx1N5fqULk=".replaceAll(" ",
		// "+")));

		// Properties props = System.getProperties();
		// Enumeration names = props.propertyNames();
		// while ( names.hasMoreElements() ) {
		// String key = (String)names.nextElement();
		// String value = props.getProperty(key);
		// System.out.println(key+":"+value);
		// }

//		 System.out.println("MAC ADDR: " + INet.getFirstMacAddress());
		//
		// PostsBean bean = new PostsBean();
		// BasicPosts basic = new BasicPosts();
		// Response<Object> resp = basic.query(bean, 0, 0);
		// System.out.println(resp.toString());
		
//		byte[] bs = {2, 67, 90, 127, -122, -87, 20};
//		String hex = Convert.bytes2hex(bs);
//		
//		System.out.println(hex);
//		System.out.println(Convert.hex2bytes(hex));
//		UserWorkBean uw = new UserWorkBean();
//		System.out.println(uw.toString());
//		Response<Object> r = new Response<Object>();
//		System.out.println(r.toString());
	}

}
