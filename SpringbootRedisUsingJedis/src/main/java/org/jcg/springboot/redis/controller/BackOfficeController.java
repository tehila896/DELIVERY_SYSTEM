package org.jcg.springboot.redis.controller;

import java.util.Map;
import org.jcg.springboot.redis.model.Package;
import org.jcg.springboot.redis.model.Customer;
import org.jcg.springboot.redis.model.DeliveryMen;
import org.jcg.springboot.redis.service.CustomerService;
import org.jcg.springboot.redis.service.DeliveryMenService;
import org.jcg.springboot.redis.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/redis/backOffice")
public class BackOfficeController {
	private static final Logger LOG = LoggerFactory.getLogger(BackOfficeController.class);

	@Autowired
	CustomerService serviceCustomer;

	@Autowired
	PackageService servicePackage;

	@Autowired
	DeliveryMenService serviceDeliveryMen;
	
	// Delete deliveryMen_ by id.
	// Url - http://localhost:10091/api/redis/deliveryMen_/delete_deliveryMen/<deliveryMen_id>	
	 @DeleteMapping("/delete_deliveryMen/{id}")
	 public ResponseEntity<String> delete(@PathVariable("id") final String id) {
		try {
			DeliveryMen deliveryMen=serviceDeliveryMen.findById(id);
			serviceDeliveryMen.delete(deliveryMen.getId());
			LOG.info("Deleting deliveryMen with id= " + id);
			return ResponseEntity.ok("deliveryMen is deleted!!!");
		 }
		 catch (Exception e) {
			return ResponseEntity.ok("deliveryMen id not found!!!");
		 }
	   } 

		// Delete deliveryMen_ by id.
		// Url - http://localhost:10091/api/redis/deliveryMen_/delete_deliveryMen/<deliveryMen_id>	
		 @DeleteMapping("/delete_deliveryMenpackage/{id}")
		 public ResponseEntity<String> delpac(@PathVariable("id") final String id) {
			try {
				Package deliveryMen=servicePackage.findById(id);
				servicePackage.delete(deliveryMen.getId());
				LOG.info("Deleting deliveryMen with id= " + id);
				return ResponseEntity.ok("deliveryMen is deleted!!!");
			 }
			 catch (Exception e) {
				return ResponseEntity.ok("deliveryMen id not found!!!");
			 }
		   } 
	    // Get all customers.
		// Url - http://localhost:10091/api/redis/backOffice/getall/customer
		@GetMapping("/getall/customer")
		public Map<String, Customer> findAll_customers() {
			LOG.info("Fetching all customers from the redis.");
			final Map<String, Customer> customerMap = serviceCustomer.findAll();
			// Todo - If developers like they can sort the map (optional).
			return customerMap;
		}
		 // Get all deliveryMens
		// Url - http://localhost:10091/api/redis/backOffice/getall/deliveryMen
		@GetMapping("/getall/deliveryMen")
		public Map<String, DeliveryMen> findAll_deliveryMens() {
			LOG.info("Fetching all DeliveryMens from the redis.");
			final Map<String, DeliveryMen> deliveryMenMap = serviceDeliveryMen.findAll();
			// Todo - If developers like they can sort the map (optional).
			return deliveryMenMap;
		}
		// Get all packages
		// Url - http://localhost:10091/api/redis/backOffice/getall/package
		@GetMapping("/getall/package")
		public Map<String, Package> findAll_packages() {
			LOG.info("Fetching all Packages from the redis.");
			final Map<String, Package> packageMenMap = servicePackage.findAll();
			// Todo - If developers like they can sort the map (optional).
			return packageMenMap;
		}
		// Get all packages group by customer id
		// Url - http://localhost:10091/api/redis/backOffice/getall/package_group_by_customer_id
		//@GetMapping("/getall/package_group_by_customer_id")
		//public Map<Object, java.util.List<Object>> findAll_packages_group_by_customer_id() {
		//	LOG.info("Fetching all DeliveryMen from the redis.");
		//	final Map<String, Package> packageMenMap = servicePackage.findAll();
		//	ArrayList<Package> list_package=new ArrayList<>();
		//	Map<Object, java.util.List<Object>> result = list_package.stream()
		//		    .collect(Collectors.groupingBy(map -> map.getId().toString(),
		//		                                   Collectors.mapping(map -> map,
		//		                                                      Collectors.toList())));
		//	return result;
		//		}
}





1.קבלת שליח ראשון לעבודה (Alexander)

*נריץ את הפקודה הבאה:
curl -X POST "http://localhost:10091/api/redis/deliveryMen/add_deliveryMen" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"communication_way\": \"bank transfer\", \"firstName\": \"Alexander\", \"lastName\": \"kohen\", \"getSalary_way\": \"email\", \"id\": \"313506711\", \"position\": { \"x\": 200, \"y\": 600 }, \"statosCurrentlyWorking\": false}"

*המערכת תאשר את הכנסת העובד בתנאי שלא קים במערכת עובד בעל id זהה וכן רק כאשר קלט פרטי העובד תקין.

במקרה וכן קיים עובד כזה נקבל הודעות שגיאה 
api:
The deliveryMen id is already in the database,you can update him!

במקרה והפרטים אינם תקינים נקבל הודעות שגיאה עם פרטי השדה שאינו תקין.
לדוגמא:
הכנסתי למערכת שם פרטי שיש בו ספרות וקיבלתי את הודעת השגיאה הזו:
קבלתי שגיאה קוד 400 error
פרטי השגיאה:
{
  "timestamp": "2021-08-08T22:58:44.206+00:00",
  "message": "Validation Failed",
  "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'deliveryMen' on field 'firstName': rejected value [Alexander12];
 codes [Pattern.deliveryMen.firstName,Pattern.firstName,Pattern.java.lang.String,Pattern]; 
arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [deliveryMen.firstName,firstName]; 
arguments []; default message [firstName],[Ljavax.validation.constraints.Pattern$Flag;@20b64c38,^[a-zA-Z ]+$]; 
default message [firstName must be a string]"
}

במקרה ומסד הנתונים  redis סירב לשמור את הנתונים נקבל הודעת שגיאה:
api:
An error occured!!!

במקרה ופרטי העובד תקינים וכן הוא אינו קיים במערכת נקבל את אישור המערכת להכנסת העובד החדש.
api:
A new deliveryMen is saved!!!
log:
Saving the new deliveryMen to the redis.

2.קבלת שליח שני לעבודה (Itzik)

*נריץ את הפקודה הבאה:
curl -X POST "http://localhost:10091/api/redis/deliveryMen/add_deliveryMen" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"communication_way\": \"Check\", \"firstName\": \"Itzik\", \"lastName\": \"levi\", \"getSalary_way\": \"email\", \"id\": \"36880391\", \"position\": { \"x\": 12, \"y\": 45 }, \"statosCurrentlyWorking\": false}"

*במקרה וענינו על הדרישות שפורטו בסצנריו הראשון (תקינות הנתונים ובדיקה שלא קיים עובד בעל id כזה במערכת) נקבל את אישור המערכת להכנסת השליח החדש.
api:
A new deliveryMen is saved!!!
log:
Saving the new deliveryMen to the redis.

3.אלכסנדר מעדכן את מיקומו ל X,Y

*נריץ את הפקודה הבאה:
curl -X PUT "http://localhost:10091/api/redis/deliveryMen/updadate_Delverymen_Position/position_Y/400/position_X/150.5/id/313506711" -H "accept: */*"

*המערכת תבדוק אם עובד זה אכן קיים במערכת, אם כן תאשר את השינויים אחרת תדפיס הודעות שגיאה מתאימות

במקרה ועובד זה אינו קיים במערכת נקבל הודעת שגיאה:
api:
you have a mistake, The deliveryMen id is not in the database

במקרה ומסד הנתונים  redis סירב לעדכן את הנתונים נקבל הודעת שגיאה:
api:
An error occured!!!

במקרה ועובד זה קים במערכת והוא התעדכן בהצלחה, נקבל הודעת שגיאה:
api:
postion is updated!!!


4.איציק מעדכן את מיקומו ל X,2Y

*נריץ את הפקודה הבאה:
curl -X PUT "http://localhost:10091/api/redis/deliveryMen/updadate_Delverymen_Position/position_Y/800/position_X/301/id/36880391" -H "accept: */*"

*במקרה וענינו על הדרישות שפורטו בסצנריו הקודם(בדיקה שלא קיים עובד בעל id כזה במערכת) נקבל את אישור המערכת לעדכון המיקום החדש.
api:

A new deliveryMen is saved!!!

5.לקוח נרשם למערכת (yosef)

*נריץ את הפקודה הבאה:
curl -X POST "http://localhost:10091/api/redis/customer/add_customer" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email\": \"yosef@gmail.com\", \"firstName\": \"yosef\", \"id\": \"212675433\", \"lastName\": \"lando\"}"

*המערכת תאשר את הכנסת הלקוח בתנאי שלא קים במערכת לקוח בעל id זהה וכן רק כאשר קלט פרטי הלקוח תקין.

במקרה וכן קיים לקוח כזה נקבל הודעות שגיאה 
api:
The customer id is already in the database,you can update him!

במקרה והפרטים אינם תקינים נקבל הודעות שגיאה עם פרטי השדה שאינו תקין.
לדוגמא:
הכנסתי למערכת כתובת מייל לא תקינה וקיבלתי את הודעת השגיאה הזו:
קבלתי שגיאה קוד 400 error
פרטי השגיאה:
{
  "timestamp": "2021-08-09T07:12:31.950+00:00",
  "message": "Validation Failed",
  "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'customer' on field 'email': rejected value [yosefgmail.com]; codes [Email.customer.email,Email.email,Email.java.lang.String,Email]; 
arguments [org.springframework.context.support.DefaultMessageSourceResolvable: 
codes [customer.email,email]; arguments []; default message [email],[Ljavax.validation.constraints.Pattern$Flag;@17a812a9,.*]; 
default message [email should be valid]"
}}

במקרה ומסד הנתונים  redis סירב לשמור את הנתונים נקבל הודעת שגיאה:
api:
An error occured!!!

במקרה ופרטי הלקוח תקינים וכן הוא אינו קיים במערכת נקבל את אישור המערכת להכנסת הלקוח החדש.
api:
A new customer is saved!!!
log:
Saving the new customer to the redis.


6.לקוח מזמין שליחות ממיקום X,2Y אל 2X,Y

*נריץ את הפקודה הבאה
curl -X POST "http://localhost:10091/api/redis/customer/order_package" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"customer_id\": \"212675433\", \"date_orde\": \"2021-08-09T08:11:14.431Z\", \"delivery_id\": \"\", \"id\": \"my_firstPackage\", \"list_states\": [ { \"date\": \"2021-08-09T08:11:14.431Z\", \"description\": \"ordered\", \"position\": { \"x\": 150, \"y\": 800 } } ], \"p_destninon\": { \"x\": 300, \"y\": 400 }, \"p_source\": { \"x\": 150, \"y\": 800 }, \"payment\": { \"paymentType\": \"visa\", \"value\": 908 }, \"statosCompletedPackage\": false}"

*המערכת תאשר הכנסת שליחות בתנאי שהלקוח קיים במערכת וכן שלא קיימת במערכת שליחות בעלת id זהה וכן רק כאשר קלט פרטי השליחות תקינים.
בעת ביצוע פעולה זו, המערכת תשלח לkafka את id השליחות בכדי למצוא שליח לטיפול בשליחות זו

נוכל לראות בlog שkafka קיבל את id השליחות
2021-08-09 10:47:18.473  INFO 20656 --- [io-10091-exec-7] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 2.5.0
2021-08-09 10:47:18.474  INFO 20656 --- [io-10091-exec-7] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 66563e712b0b9f84
2021-08-09 10:47:18.474  INFO 20656 --- [io-10091-exec-7] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1628495238473
2021-08-09 10:47:18.480  INFO 20656 --- [ad | producer-1] org.apache.kafka.clients.Metadata        : [Producer clientId=producer-1] Cluster ID: XdESqlc8TMiRdnHOuJttCg

במקרה ולקוח זה אינו קיים במערכת, נקבל הודעת שגיאה
api:
you have a mistake, The customer id is not in the database

במקרה וכן קיימת שליחות כזו נקבל הודעות שגיאה 
api:
This Package id is already in the database

במקרה והפרטים אינם תקינים נקבל הודעות שגיאה עם פרטי השדה שאינו תקין.
לדוגמא:
הכנסתי למערכת דרך לתשלום לא תקינה וקיבלתי את הודעת השגיאה הזו:
קבלתי שגיאה קוד 400 error
פרטי השגיאה:
{
  "timestamp": "2021-08-09T07:34:02.159+00:00",
  "message": "Validation Failed",
  "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'package' on field 'payment.paymentType': rejected value [77]; 
codes [Pattern.package.payment.paymentType,Pattern.payment.paymentType,Pattern.paymentType,Pattern.java.lang.String,Pattern]; 
arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [package.payment.paymentType,payment.paymentType]; arguments [];
 default message [payment.paymentType],[Ljavax.validation.constraints.Pattern$Flag;@2d1d7a1c,^[a-zA-Z ]+$]; 
default message [paymentType must be a string]"
}

במקרה ומסד הנתונים  redis סירב לשמור את הנתונים נקבל הודעת שגיאה:
api:
An error occured!!!

במקרה ופרטי הלקוח תקינים וכן הוא קיים במערכת נקבל את אישור המערכת להכנסת השליחות החדשה.
api:
A new package is saved!!!
log:
Saving the new package to the redis.

7.השליח המתאים מקבל הודעה על השליחות ומאשר אותה

לאחר שבסצנריו הקודם הוזמנה שליחות ונשלח לkafka את id השליחות,
kafka listner האזין וקיבל את id השליחות כעת הוא יבדוק מיהו השליח הקרוב ביותר שמתאים למשימה
ויקבע אותו כשליח השליחות הנ"ל ע"י השמת id השליח בשדה id שליח בשליחות, וכן הוא יעדכן את ססטוס השליח להיות תפוס.

נוכל לראות בlog שkafka האזין ושלף את id השליחות
Proccessing message with thread id: org.springframework.kafka.KafkaListenerEndpointContainer#0-0-C-1
Received Message in group packages: my_first_package

כעת נוכל  לגשת לשליחות הנ"ל ולראות ששדה id של השליח השתנה ואותחל להיות  id השליח שנקבע כשליח הקרוב ביותר.
נריץ את הפקודה הבאה
:curl -X GET "http://localhost:10091/api/redis/customer/get/PackageById/my_firstPackage" -H "accept: */*"
api:
{
  "id": "my_firstPackage",
  "customer_id": "212675433",
  "delivery_id": "36880391",
  "p_source": {
    "x": 150,
    "y": 800
  },
  "p_destninon": {
    "x": 300,
    "y": 400
  },
  "date_orde": "2021-08-09T08:11:14.431+00:00",
  "list_states": [
    {
      "description": "ordered",
      "date": "2021-08-09T08:11:14.431",
      "position": {
        "x": 150,
        "y": 800
      }
    }
  ],
  "statosCompletedPackage": false,
  "payment": {
    "paymentType": "visa",
    "value": 908
  }
}

וכן נוכל לגשת לשליח שנקבע כשליח השליחות ולראות שאכן השתנה לו הסטטוס והוא תפוס כרגע.
נריץ את הפקודה הבאה:
curl -X GET "http://localhost:10091/api/redis/backOffice/getall/deliveryMen" -H "accept: */*"
ונקבל את פלט 2 המשלוחנים הקיימים כרגע, נוכל לראות שהסטטוס של איציק שהוא השליח יותר קרוב השתנה וכרגע הוא אינו פנוי לשליחות נוספת.
api:
{
  "36880391": {
    "id": "36880391",
    "firstName": "Itzik",
    "lastName": "levi",
    "statosCurrentlyWorking": true,
    "communication_way": "Check",
    "getSalary_way": "email",
    "position": {
      "x": 301,
      "y": 800
    }
  },
  "313506711": {
    "id": "313506711",
    "firstName": "Alexander",
    "lastName": "kohen",
    "statosCurrentlyWorking": false,
    "communication_way": "bank transfer",
    "getSalary_way": "email",
    "position": {
      "x": 150,
      "y": 400
    }
  }
}


8.השליח מבצע את השליחות

1.השליח יכול לעדכן את הstate של השליחות בכל פעם שישנו שינוי, קלט הstate יתווסף לרשימת מצבי השליחות של השליחות הנ"ל. 
המערכת תאפשר פעולה זו רק בתנאי שהשליחות אכן קיימת במערכת.

לדוגמא:ברגע שהשליחות נמצאת כרגע בטרמינל נעדכן את state, די שהלקוח יוכל לראות את מצב התקדמויות השליחות
נריץ את הפקודה הבאה:
curl -X PUT "http://localhost:10091/api/redis/deliveryMen/update_State_Package/id/my_firstPackage" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"date\": \"2021-08-09T09:00:43.265Z\", \"description\": \"in_terminal\", \"position\": { \"x\": 900, \"y\": 3454 }}"

במקרה ושליחות זו אינה קיימת במערכת, נקבל הודעת שגיאה
api:
you have a mistake, The package id is not in the database

במקרה ומסד הנתונים  redis סירב לשמור את הנתונים נקבל הודעת שגיאה:
api:
An error occured!!!

במקרה והשליחות קיימת במערכת נקבל את אישור המערכת לשינוי מצב השליחות.
api:
state is updated!!!

2.במקרה והשליחות הגיעה ליעדה, השליח צריך לבצא 2 פעולות:
*שינוי ססטוס השליחות להושלמה.
פעולה זו תתאפשר רק כאשר שליחות זו אכן קיימת במערכת.
נריץ את הפקודה הבאה:
curl -X PUT "http://localhost:10091/api/redis/deliveryMen/updadate_Delverymen_Position/statos/true/id/my_firstPackage" -H "accept: */*"

במקרה ושליחות זו אינה קיימת במערכת, נקבל הודעת שגיאה
api:
you have a mistake, The package id is not in the database

במקרה ומסד הנתונים  redis סירב לשמור את הנתונים נקבל הודעת שגיאה:
api:
An error occured!!!

במקרה והשליחות קיימת במערכת נקבל את אישור המערכת לשינוי מצב השליחות.
api:
statos is updated!!!

*שינוי סטטוס השליח לפנוי.
פעולה זו תתאפשר רק כאשר שליח זה אכן קיים במערכת.
נריץ את הפקודה הבאה:
curl -X PUT "http://localhost:10091/api/redis/deliveryMen/update_DeliveryMen_statosCurrentlyWorking/statosCurrentlyWorking/false/id/36880391" -H "accept: */*"

במקרה ושליח זה אינו קיים במערכת, נקבל הודעת שגיאה
api:
you have a mistake, The deliveryMen id is not in the database

במקרה ומסד הנתונים  redis סירב לשמור את הנתונים נקבל הודעת שגיאה:
api:
An error occured!!!

במקרה והשליח קיים במערכת נקבל את אישור המערכת לשינוי סטטוס השליח.
api:
statos Currently Working is updated!!!
