# Restaurant Tinder
Restaurant Tinder is a web application that helps friends choose which restaurants to visit during an outing. You can send timed invites to your friends so that they can vote for which restaurants to visit in the area of your choosing. You can then use their votes to come to a restaurant finalist list.

## How to run the front end
type `npm install` and then `npm start` into the front end terminal

## How to run the back end 
1. Run sql scripts in this order:
    - dropdb.sql 
    - schema.sql 
    - user.sql 
    - restaurant.sql 

2. Run Spring Boot application

3. Copy the contents of `restaurant info.txt` and send a POST request to http://localhost:8081/restaurant/save

4. Run `timeopen.sql`