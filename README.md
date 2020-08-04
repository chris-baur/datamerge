# Data sorting and filtering

Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

- The same column order and formatting as reports.csv
- All report records with packets-serviced equal to zero should be excluded
- records should be sorted by request-time in ascending order

Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.

Please provide source, documentation on how to run the program and an explanation on why you chose the tools/libraries used.

## Submission

You may fork this repo, commit your work and let us know of your project's location, or you may email us your project files in a zip file.

## Submission Notes

How to run
1. Open CMD.
2. Change current directory to root of the repository
3. Execute the following command `java -jar datamerge.jar`. (Make sure you are up to date with the JRE on your system). 
4. Output in the console is the summary. Open the csv found in `datamerge\output\results.csv` to see the other data.

Tools and Lirbaries Used
- *OpenCSV* was used in order to facilitate writing to a CSV file
- *JSON.Simple* was used in order to manipulate the JSON data in a OO way and make it simpler to use. 
