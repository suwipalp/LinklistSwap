# LinklistSwap

The API will work as below :

1. API received based64 encoded parameter named "linklist"  
2. Decode the based64 to plain string  
3. The String once decoded will be this format : "1->2->3->4"  
4. What API has to process is to swap every 2 adjacent digit and return to the caller

	Ex 1.

		Received : "1->2->3->4"
		Return :  "2->1->4->3"
	
	Ex 2.

		Received : "3->2->9->7->6"
		Return : "2->3->7->9->6"
	
5. Return result must be encoded with based64  


# Calling the API
This API was deployed on Google App Engine. You can try to call it by making a GET request (using POSTMAN, browser, curl, etc.) to the following URL:  

Ex 1. Request to to API with parameter "1->2->3->4"  
(Note: the parameter is base64 encoded and URL encoded)

	https://linklistswap.appspot.com/linklistSwap?linklist=MS0%2BMi0%2BMy0%2BNA%3D%3D

The response would be

	Mi0+MS0+NC0+Mw==

which is base64 decoded to "2->1->4->3"