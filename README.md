# IBM News Service
Spring Boot with JPA integration with one to many relationship tables (employees, employee_languages, news, recommended_news)

[Create a Web Server and an Amazon RDS Database](https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/TUT_WebAppWithRDS.html)


Set user data script in EC2 instance [./data/user-data.sh](./data/user-data.sh)


Application is deployed to http://www.ibm.serghienco.com/

API:
```
{
  "_links" : {
    "employees" : {
      "href" : "http://www.ibm.serghienco.com/employees{?page,size,sort}",
      "templated" : true
    },
    "news" : {
      "href" : "http://www.ibm.serghienco.com/news{?page,size,sort}",
      "templated" : true
    },
    "recommended-news" : {
      "href" : "http://www.ibm.serghienco.com/recommended-news{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://www.ibm.serghienco.com/profile"
    }
  }
}
```  
