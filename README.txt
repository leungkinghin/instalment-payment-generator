Introduction
------------
In order to inform borrowers about the final repayment schedule, we need to have pre-calculated
repayment plans throughout the lifetime of a loan.
To be able to calculate a repayment plan specific input parameters are necessary:
• duration (number of instalments in months)
• nominal interest rate
• total loan amount ("total principal amount")
• Date of Disbursement/Payout

These four parameters need to be input parameters.
The goal is to calculate a repayment plan for an annuity loan. Therefore the amount that the
borrower has to pay back every month, consisting of principal and interest repayments, does not
change (the last instalment might be an exception).
The annuity amount has to be derived from three of the input parameters (duration, nominal
interest rate, total loan amount) before starting the plan calculation.
(use http://financeformulas.net/Annuity_Payment_Formula.html as reference)

The API is designed to generate installation plan for repayment. System consume following four parameters as request body in JSON format
{
	"duration": <positive integer>,
	"loanAmount": <positive decimal number>,
	"nominalRate": <positive decimal number>,
	"startDate": <UTC date format>
}

And the API would produce a list of installment detail in JSON pattern as below :
{
  [
    {
      "borrowerPaymentAmount": 25.83,
      "date": "2019-07-14T13:36:27.312",
      "initialOutstandingPrincipal": 5,
      "interest": 20.83,
      "principal": 5,
      "remainingOutstandingPrincipal": 0
    },
	....
	{
      "borrowerPaymentAmount": 25.83,
      "date": "2019-07-14T13:36:27.312",
      "initialOutstandingPrincipal": 5,
      "interest": 20.83,
      "principal": 5,
      "remainingOutstandingPrincipal": 0
    }
	]
}

Validator is supported in API. When input parameter violates the validation, system would return 400 Bad Request with error message.
	
Prerequisite
------------
1. Installed Apache Maven.
https://maven.apache.org/install.html

2. Installed curl command tool. 
https://curl.haxx.se/download.html

Run Test Case
--------------
- 	Under project folder, please run 'mvn test'
- 	Totally 9 test case has been definied as below

	--	testGeneratePlan_Full_Criteria(With all parameters as criteria and return 200 OK status with plan generated.)
	--	testGeneratePlan_MonthEnd_StartDate(With all parameters as criteria, start the plan at month-end and return 200 OK status with plan generated.)
	--	testGeneratePlan_Without_Duration(Without duration value in criteria, return 400 Bad Request)
	--	testGeneratePlan_With_Negative_Duration(With negative duration value in criteria, return 400 Bad Request)
	--	testGeneratePlan_Without_Interest(Without nominal rate in criteria, return 400 Bad Request)
	--	testGeneratePlan_Zero_Interest(With zero nominal rate in criteria,  return 400 Bad Request)
	--	testGeneratePlan_Negative_Interest(With negative nominal rate in criteria, return 400 Bad Request)
	--	testGeneratePlan_Without_LoanAmount(Without loan amount in criteria, return 400 Bad Request)
	--	testGeneratePlan_Zero_LoanAmount(With loan amount in criteria, return 400 Bad Request)

Run program
--------------
1. Startup server
-	In command prompt, please enter the project folder and run 'mvn spring-boot:run'.

2. Invoke API call
-	In command prompt, run following curl command (Please assign corresponding input values in JSON request body) 
		curl -X POST "http://localhost:8080/plans" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"duration\": 24, \"loanAmount\": 5000, \"nominalRate\": 5, \"startDate\": \"2019-01-31T00:00:00\"}"
		
Assumption
-----------
1. For infinity decimal place during division operation in formula, the resultant is rounded up to 10 decimal place.
2. The calculated interest, nominal rate, principal is rounded up to 2 decimal place.
		
Remarks
-------
Swagger-UI has been supported in the project.
For more details about the Plan-Generation API, developers can following link once backend server is started up :
http://localhost:8080/swagger-ui.html