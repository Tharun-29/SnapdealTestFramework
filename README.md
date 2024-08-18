# Snapdeal Automation Test Cases

This repository contains automated test cases for the Snapdeal website, focusing on critical functionalities like product search, sorting, filtering, and cart operations. The tests are written in Java using Selenium WebDriver and TestNG, following the Page Object Model (POM) design pattern to make the code easier to maintain and scale.

## Project Structure

- **src/main/java**: Contains the page object classes representing different pages of Snapdeal.
  
  ![image](https://github.com/user-attachments/assets/c37f6694-e13d-4f83-903c-fb6edf15910f)
- **utilities**: The utilities package includes the ConfigReader class for managing configuration settings from the GlobalData.properties file, as well as WaitHelper and WindowSwitcher classes that provide methods for handling application synchronization and managing window interactions during tests.
- **Resources**: The Resources package contains GlobalData.properties file for managing Automation Suite global configuration properties.
- **PageObjects**: The PageObjects package contains classes representing different webpages of the application, encapsulating their elements and behaviors.

- **src/test/java**: Contains the test classes where each test case is defined.

  ![image](https://github.com/user-attachments/assets/c51e7f51-3eb1-477c-9d49-32fb69e4a068)

- **TestComponents**: The TestComponents package contains foundational classes like BaseTest for WebDriver initialization and configuration, and Listeners for handling test events and reporting.
- **SnapdealAutomationSuite**: The SnapdealAutomationSuite package contains classes for automated test cases designed to run the test and validate application functionality.

### Configuration
- **global.properties**: Contains key-value pairs for configurations such as base URL,browser, implicit wait time, product and brand name to be selected during Test Run.

## Tools and Technologies

- **Java**: The primary programming language used for writing test scripts.
- **Selenium WebDriver**: Used for automating web interactions on the Snapdeal site.
- **TestNG**: Chosen for test management, offering advanced features like parallel execution, test configuration, and data-driven testing.
- **Maven**: For project management and dependency handling.
- **ExtentReports**: For generating comprehensive test execution reports.

## Reasoning Behind Tool and Design Choices

- **Java**: Chosen for its robustness and wide community support in the automation domain.
- **Selenium WebDriver**: Provides powerful browser automation capabilities, making it ideal for web testing.
- **TestNG**: Offers a flexible and easy-to-use framework for managing and running test cases.
- **Page Object Model (POM)**: This design pattern was implemented to separate test logic from page logic, ensuring cleaner code and easier maintenance.

## Jenkins Setup

The project is integrated with Jenkins for continuous integration and continuous testing. Below are the details:

- **Pipeline Configuration**: Automated execution of test cases after every code commit or on a scheduled basis.
- **Browser Setup**: Configured to use Chrome browser for test execution.


## Test Report

The automated test run generates detailed reports, providing insights into the execution process. The report includes:

- **Execution Summary**: Overview of the total tests, passed tests, and failed tests.
- **Screenshots**: Captured at points of Success, failure to help diagnose issues quickly.
![image](https://github.com/user-attachments/assets/a86c5874-2eb5-4ce5-9e39-2dc6e09d370b)

![image](https://github.com/user-attachments/assets/1f8b9fe2-dde2-4561-9c62-f729aaed8403)

![image](https://github.com/user-attachments/assets/9761f53a-dfdd-48dd-a8ff-7cdbc99b61cd)

![image](https://github.com/user-attachments/assets/9521fa01-cb89-4c41-9fb8-80a2a6c662d0)

![image](https://github.com/user-attachments/assets/3a6c224c-bc4b-438d-bcfc-056b4b58e314)


## How to Run Tests Locally

1. **Clone the Repository**:
   ```sh
   https://github.com/Tharun-29/SnapdealTestFramework.git
2. **Navigate to Project Directory**:
   ```sh
   cd SnapdealTestFramework
3. **Run Tests**:
   ```sh
   mvn test -PSnapDealTestPack -Dbrowser="chrome"

**License**
This project is licensed under the MIT License - see the LICENSE file for details.

This `README.md` is structured for GitHub, providing a clear overview of project, the tools used, and how to set it up and run it.


