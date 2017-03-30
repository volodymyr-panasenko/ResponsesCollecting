Web-application "Responses collecting" consists of 4 pages:
1. List of field page;
2. Field create/edit page;
3. Responses page;
4. Response collecting page.

On the main page of the application (Response collecting page) an anonymous user can make a response filling the fields offered to him on the page.
Page is available on default path localhost:9000/
After submission user is be redirected to congratulation page. Submission action is done via AJAX.

Only the administrator has access to the first three pages of application.
To log in as an administrator user must enter a login and password in the upper right corner of the homepage. 
The password is encrypted using the MD5 algorithm.
Login: vova128
Password: infostroy

On the List of field page displayed a table with Fields stored in database. 
Admin is able to create and delete fields. Removing fields is carried with custom confirmation dialog and AJAX. 
Field editing function is not implemented.
Page is available on path localhost:9000/fields

Field create/edit page. Here admin is able to create new fields with definite number of properties. All properties are required. 
After saving new field admin is redirected to List of field page. 
Page is available on path localhost:9000/fields/FIELDUUID. 
Form is submitted with AJAX.

Responses page (is not implemented).
Page is available on path localhost:9000/responses

WebSockets are not implemented.

The following entities have been created for web-application using the framework Hibernate 4.x:
1. Admin (describes the entity of the user with Administrator role);
2. Field (describes the field);
3. FieldType - enumeration (describes field type (eg. Combo box, Radio button));
4. Option (describes the fields options with multiple choice (Radio button, Check box and Combo box));
5. Response (describes the response of an anonymous user).

The application code has been written in IDE: Eclipse on Java 8 using Play Framework 2.4.x (scala-templating).

HTML-pages are designed using the following technologies: Bootstrap, HTML, CSS, JavaScript/JQuery.

As database management system was chosen PostgreSQL 9.x.

The SQL-script for the initial filling of the database was added to the file 'init.sql'.
Database name is 'response_collecting'.