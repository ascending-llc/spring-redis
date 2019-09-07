# ASCENDING sprint-redis demo

## What is cache
A cache server is a dedicated network server or service acting as a server that saves Web pages or other Internet request/content locally. 
By placing previously requested information in temporary storage, or cache, 
a cache server both speeds up access to data and reduces demand on bandwidth and repeatable computing cost.

## why use cache
1. saving computing power
1. reducing system hotspot
1. reducing response pressure
1. durability

## [cache strategy](https://codeahoy.com/2017/08/11/caching-strategies-and-how-to-choose-the-right-one/)
1. Cache-Aside
1. Read-Through Cache
1. Write-Through Cache
1. Write-Around
1. Write-Back


## SpringBoot Demo

### Run a redis server from docker

1. docker [image](https://hub.docker.com/_/redis)

        docker pull redis
        docker run -p 6379:6379 --name some-redis redis
        docker exec -it some-redis /bin/bash

1. redis commandline

        redis-cli keys *
        redis-cli FLUSHDB
        redis-cli info stats | grep 'keyspace_*'
        
### database persistent layer

1. choose h2 database as in memory database versus postgres as part of demo. [reference](https://www.springboottutorial.com/spring-boot-and-h2-in-memory-database)
   
   -  manage h2 database http://localhost:8080/h2-console 
   -  database url jdbc:h2:mem:testdb

### enable application logging for verification

1. enable sql log for cache demo
    
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true


### springboot

1. dependency

        <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-data-redis</artifactId>
          </dependency>
          
1. Controller layer cache setup (other configuration [reference](https://www.baeldung.com/spring-cache-tutorial))

        @Cacheable(value = "cars")
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public Car getCars(@PathVariable(name="id") Long Id) {
            ....
        }
        
        @CachePut(value = "cars", key = "#car.id", unless = "#car.brand == null")
        @RequestMapping(method = RequestMethod.POST) {
            ....
        }
          
1. controller layer cache validation
    
        localhost:8080/api/cars/1
        localhost:8080/api/cars/1

        ... : Getting user with ID 1.
        ... : 

1. redis server memory configuration

    [reference](https://medium.com/@MatthewFTech/spring-boot-cache-with-redis-56026f7da83a)



### About ASCENDING
ASCENIDNG is an AWS Consulting Select Partner and focuses on AWS with experts having deployed AWS solutions since 2012. We have successfully worked with startups, Mid-size businesses, Non-Profit and Education organizations to meet their needs of AWS solutions, custom training and support. 
* Our recent case study at [our blog](https://blog.frugalops.com)
* Our training content [here](https://www.ascendingdc.com)
* AWS Select Partner capabilities [here](https://aws.amazon.com/partners/find/partnerdetails/?n=ASCENDING%20LLC&id=0010L00001v2JNtQAM)

![AWS Select Partner](https://www.ascendingdc.com/images/aws.png)

