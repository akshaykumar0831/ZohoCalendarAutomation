# Automated Testing Framework for Zoho Calendar Application

## Overview
This repository contains an automated testing framework for the Zoho Calendar application. The framework is built using Selenium WebDriver and Java, following the Page Object Model (POM) design pattern. TestNG is used for test execution, and Extent Report Listener is utilized for generating test result reports. WebDriver Listeners are implemented for logging purposes, and Log4j is integrated for saving and managing logs.

## Tools Used
IntelliJ IDEA: Integrated Development Environment (IDE) used for project setup and development.
Java: Programming language used for writing test scripts and framework implementation.
Selenium WebDriver: Automation tool used for interacting with web elements and simulating user actions.
TestNG: Testing framework used for organizing and executing tests, generating reports, and managing test dependencies.
Extent Report Listener: TestNG listener used for generating detailed HTML reports with screenshots.
WebDriver Listeners: Implemented for capturing and logging WebDriver events.
Log4j: Logging framework used for recording test execution logs and saving them to files.
GitHub: Version control system used for code management and collaboration.

## Setup Instructions
To set up the project locally, follow these steps:

Clone the Repository:
bash
git clone https://github.com/akshaykumar0831/ZohoCalendarAutomation.git

Import Project into IntelliJ IDEA:
Open IntelliJ IDEA.
Select "Import Project".
Choose the directory where you cloned the repository.
Select "Maven" as the external model.
Click "Next" and follow the prompts to import the project.

Install Dependencies:
Maven will automatically download the project dependencies specified in the pom.xml file.

Configure WebDriver:
Download the appropriate WebDriver executable for your browser (e.g., ChromeDriver).
Place the WebDriver executable in a directory included in your system's PATH environment variable.

Run Tests:
Navigate to the src/test/java directory.
Open the TestNG XML file (testng.xml, testng2.xml) in IntelliJ IDEA.
Right-click on the file and select "Run 'testng.xml/testng2.xml'" to execute the test suite.

Project Structure
The project follows a standard Maven project structure:

src/main/java: Contains the implementation of the framework, including page objects, utilities, and configuration files.
src/test/java: Contains test classes where actual test scripts are written.
testng.xml: TestNG suite XML file specifying the test execution order and configurations.

## Test Execution
TestNG is configured to execute the tests defined in the test classes. Test results are generated in HTML format using Extent Report Listener and can be found in the test-output directory. WebDriver Listeners capture and log WebDriver events, while Log4j is utilized for saving and managing detailed test execution logs.

