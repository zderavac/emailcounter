
Assumptions


	1. URL will not be longer than 2048 character (Get service maximum URL length is 2048 characters)
			Number of URLs to process will fit in GET URL length limits
				If we need to process larger batch of urls we can provide additional endpoint 
				Which will have query param web location of the file to process
				This is not implemented 
				
	2. Subdomains resolve to different email addresses 
	 		Ex: me@me.com is not equal to me@me.me.com 
			
	3. Same rules will apply for subdomains 
			Ex: me@me.com will have same rules as me@me.me.com
			
	4. Emails are valid unless they are null 
			validation of email address format is out of the scope)
			
	5. Application will run on Web Container so we will be able to process concurrent request utilizing web container threads
			If we expect huge number of calls or want to throttle request to service layer or if we implement batch file download and processing
				We can implement async calls form controller and return CompletableFuture
	6. All coming URLs are in valid URL format
	
			Validation of URL format is not implemented (except check URL for null value)
		
			
	To run and test
	1.Run Unit tests
	2. mvn clean install
		Run EmailcounterApplication as spring boot application 
		Go to browser and hit URL
			http://localhost:8080/count?emails=test.email@gmail.com,test.email%2Bspam@gmail.com,testemail@gmail.com
