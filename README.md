# Connecting-A-Java-API-to-PostgreSQL-DB-practice

Part 1: Creating a java api that interacts w/a PostgreSQL DB that has one table (not that we were tied to just working w/1 table or anything. 
It's just that easiest way to do this was to take the app that we already created in "Go Java Full Stack" and just swap the db from h2 to postgreSQL.
Having said that I did not copy paste code but created it from scratch to reinforce what I learned about creating web services / apis in java)

Some instructions for running this app that we ended up creating: We got it to work! as in the java api interacts w/a postgresql db 

How to start front end React app: in terminal go to the “growth-team-interview-prep-app” -> execute ‘npm run start’

How to start java back end more specifically java web service / api: honestly not sure for people that will be just downloading this from GitHub but… just find a way to open the project and then run the main file (in my eclipse app it’s saved as ToDoAppUsingPostgreSQLDB)
	- For backend to successfully start up it needs to connect to a db. So need to create a db w/the name that java backend is referencing in it’s application.properties file. (Thankfully don’t need to do anything beyond that like creating tables and stuff because upon starting… the java project will automatically create tables for the classes marked as @Entity. Though of course if you want to prepopulate the db then sure go ahead. You can prepopulate by running commands like “CREATE TABLE todos (id SERIAL PRIMARY KEY, username VARCHAR(20) NOT NULL, description VARCHAR(50) NOT NULL, is_completed BOOLEAN NOT NULL, target_date TIMESTAMP WITH TIME ZONE NOT NULL)” and then….. “insert into todos(username, description,is_completed, target_date) values ('M', 'Plan VV Date', false, CURRENT_TIMESTAMP), ('M', 'Prep for Interview', false, CURRENT_TIMESTAMP), ('M', 'Clean room', false, CURRENT_TIMESTAMP);” ) 

When Trying to just test if endpoints work the way you want them can use postman (already have some sample requests created in ‘Java Api That Interacts With PostgreSQLDB’ folder; also have some sample requests in ‘Useful code snippets’ file)

Also note that when trying to login to the page you have to use hardcoded values: “M” for username and “v” for password

Also note that I didn’t create endpoints to support the special welcome stuff on the app (it’s really not important)

Part 2: Creating a java api that interacts w/a PostgreSQL DB that has multiple tables
Easiest way to go about doing this is to create a java api that interacts w/instagram-clone postgresql db I already created in Stephen Grider's SQL class.
