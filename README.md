#Running steps.
Please replace the property "csv.file.location" in application.properties
Please run BootStarter.java, it will automatically start the tomcat server.
#Please hit the url for geting unique tags.
	http//127.0.0.1:8080/tags
	
#Get Teds by ted name with pagination and sorting.
	http//127.0.0.1:8080/tags/ted_name/page?page=page_number&size=size_on_one_page&sortByDate=false&sortByViews=true
	
# If you want to sort basis on view set sortByViews = true
# If you want to sort basis on date set sortByDate = true
# Select both true if you want sorting based on both.

#Ted search
	http//127.0.0.1:8080//ted/search?name=<value>&speakerOccupation=<value>&event=<value>&title=<value>&page=page_number&size=size_on_one_page&sortByDate=false&sortByViews=true
	
	
Send the corresponding search parameter only. Don't send param which is not applicable while searching.
	