Basic token  и  mail url прокидываются параметрами при запуске
-DbasicToken
-DapiUrl
-DuiUrl

Все вместе:
mvn clean test -DbasicToken="ZnJvbnRfMmQ2YjBhODM5MTc0MmY1ZDc4OWQ3ZDkxNTc1NWUwOWU6" -DapiUrl="http://test-api.d6.dev.devcaz.com" -DuiUrl="http://test-app.d6.dev.devcaz.com"

Только UI тесты
mvn clean test -Dtest=RunnerTest  -DuiUrl="http://test-app.d6.dev.devcaz.com"

Только Api тесты
mvn clean test -Dtest=ApiTest -DbasicToken="ZnJvbnRfMmQ2YjBhODM5MTc0MmY1ZDc4OWQ3ZDkxNTc1NWUwOWU6" -DapiUrl="http://test-api.d6.dev.devcaz.com"