# jira-excel-importer
Personal Mini Project for JIRA Excel Importer

THE IDEA:
I would like to save my task summary from Atlassian JIRA into a database.

Firstly, we hit the rest API that processes the excel (filtering out empty cells) and insert the data into the database. Here is the example CURL:

curl --location 'localhost:8080/taskSummary/addFromExcel' \
--header 'Content-Type: application/json' \
--data '{
    "excelBase64": "base64 of excel document"
}'

It is essential to adhere to a specific Excel column format when submitting data. This standardized format ensures the system can accurately interpret and process the information provided. The prescribed column order is as follows:
1. Task Type
2. Task Key
3. Task Description
4. Task Status
5. Completion Estimate (in seconds)
6. Actual Completion Time Spent (in seconds)
7. Email
8. Times Returned (the number of instances where a task or issue has been returned to the developer by the checker due to deviations from specified requirements) 
9. Finish Time (Must be in Excel date format)

Then, we can hit another rest API that summarize the person's task summary that have been inserted before. Here is the example CURL:

curl --location 'localhost:8080/employee/overallSummary' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "SN123@MAIL.COM"
}'

And here is the example response for the summary:
{
    "email": "SN123@MAIL.COM",
    "productivty": "1.0191489361702128",
    "mistakesPerTask": "0.7647058823529411"
}
