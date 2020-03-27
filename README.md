# LinklistSwap
The API will work as below :
1 API received based64 encoded parameter named “linklist”
2 Decode the based64 to plain string
3 The String once decoded will be this format :“1->2->3->4”
4 What API has to process is to swap every 2 adjacent digit and return to the caller
Ex 1.
	Received : “1->2->3->4”
	Return :  “2->1->4->3”
Ex 2.
	Received : “3->2->9->7->6”
	Return : “2->3->7->9->6”
5 Return result must be encoded with based64