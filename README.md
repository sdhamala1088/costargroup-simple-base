# CoStarGroup Automation Framework (UI + API + DB)

This project is a **simple, modular test automation framework** designed for SDET interviews or entry-level automation projects. It showcases individual test capabilities across **UI, API, and Database layers** using:

- ✅ Selenium WebDriver with TestNG (UI)
- ✅ RestAssured with Jackson for JSON-based API validation
- ✅ JDBC with SQL assertions for database testing

> ⚠️ Note: There is **no Allure reporting integration** or **environment profile switching** yet, and the test layers (UI/API/DB) are designed to run **independently**, not as a fully integrated end-to-end suite. This is intended as a **starting point** for larger frameworks.

---

## 📁 Project Structure

<img width="691" height="630" alt="image" src="https://github.com/user-attachments/assets/db7dfab8-bad0-4ad4-8838-26778b1e321b" />

---

## 🔧 Tools & Libraries

- **Java 17**
- **TestNG** (test runner)
- **RestAssured** (API testing)
- **Selenium WebDriver** (UI testing)
- **Jackson / JsonMapper** (API JSON validation)
- **PostgreSQL JDBC** (DB layer)
- **Log4j2** (logging)
- **Maven** (build & dependency management)

---

## 🚀 How to Run

### Prerequisites:
- Java 17+
- Maven
- PostgreSQL running locally (for DB tests)
- Chrome installed (for UI tests)
- Update `chromedriver` path in `DriverFactory.java` or use WebDriverManager

### Run All Tests:
```bash
mvn clean test
Run Specific Tests:
Configure TestNG XMLs

🔍 Feature Highlights
📄 API JSON Matching: Compares live API responses with saved JSON files using Jackson JsonNode

🌐 UI Base Class: Selenium WebDriver setup with thread-local driver handling
🗄️ DB Test Support: Validates referential integrity and business rules via SQL assertions
🔄 Thread-Safe Design: All drivers and connections are thread-local for parallel test readiness
🔧 Simple Config Management: .properties files are loaded dynamically and cached

🧱 Future Improvements (Ideas)
✅ Add Allure Reporting
✅ Add environment-specific config support (dev, qa, prod)
✅ Integrate API → UI → DB validation into a single end-to-end test
✅ Switch to WebDriverManager for driver management
✅ Use enums or builders for API path and request construction

👤 Author
Sanket Dhamala
SDET Candidate | Test Automation Engineer
