# 🧪 Form Automation Framework

This project automates a dynamic HTML form using Selenium WebDriver, Java, and Cucumber BDD. It’s designed for clarity, scalability, and ease of refactoring—built around dynamic, label-driven XPath locators and modular page object classes.

---

## 📦 Features

- ✅ Modular Page Object Model (POM) for clean separation of concerns  
- ✅ Dynamic XPath locators based on form labels  
- ✅ Cucumber BDD for readable, behavior-driven test scenarios  
- ✅ Centralized error handling to avoid cluttered try-catch blocks  
- ✅ Utility methods for reusable interactions  
- ✅ Code structure mirrors HTML layout for intuitive maintenance

---

## 🧩 Tech Stack

| Tool            | Purpose                          |
|-----------------|----------------------------------|
| Java 11+        | Programming language             |
| Selenium WebDriver | Browser automation             |
| Cucumber BDD    | Test scenario definition         |
| Maven           | Dependency management & build    |
| JUnit/TestNG    | Test execution (configurable)    |


---



## 📁 Project Structure

```
form-automation-framework/
├── features/                         # Gherkin feature files
│   └── form.feature                  # Scenario for form submission
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pageobjects/          # Modular page object classes
│   │       │   └── FormPage.java     # Page methods using dynamic XPath
│   │       └── utils/                # Utility classes
│   │           └── DriverManager.java# WebDriver setup and teardown
│
│   └── test/
│       └── java/
│           ├── stepdefinitions/      # Cucumber step definitions
│           │   └── FormSteps.java    # Maps steps to page object methods
│           └── runners/              # Test runner configuration
│               └── TestRunner.java   # Cucumber options and hooks
│
├── pom.xml                           # Maven build configuration
├── README.md                         # Project documentation
└── .gitignore                        # Git ignore rules
```


---

## 🛠️ Setup Instructions

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/form-automation.git
   cd form-automation


**install dependencies : 
mvn clean install

Locator Strategy

All form fields are located using dynamic XPath expressions based on their visible labels. This ensures resilience against layout changes and improves readability.

public WebElement getInputFieldByLabel(String label) {
    String xpath = String.format("//label[text()='%s']/following-sibling::input", label);
    return driver.findElement(By.xpath(xpath));
}

Code Philosophy

    Minimalist: No redundant try-catch blocks—errors are handled centrally.

    Modular: Each method does one thing and does it well.

    Refactor-friendly: Code mirrors HTML structure for intuitive updates.

    Readable: Step definitions map directly to page object methods.

    📬 Contributing

Pull requests are welcome! If you spot a bug or want to suggest improvements, feel free to open an issue or submit a PR.


Author Notes

This framework is built with a focus on maintainability and clarity. It’s ideal for scaling across multiple forms and modules with minimal effort. If you’re customizing it, keep the locator strategy and modular design intact—they’re the backbone of its flexibility.



