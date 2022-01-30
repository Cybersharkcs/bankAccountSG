#US 1:
#In order to save money
#As a bank client
#I want to make a deposit in my account

#US 2:
#In order to retrieve some or all of my savings
#As a bank client
#I want to make a withdrawal from my account

#US 3:
#In order to check my operations
#As a bank client
#I want to see the history (operation, date, amount, balance) of my operations

#Author: maximepopelin@gmail.com
Feature: API of account
  
  This API get informations from account
  - Deposit money on my account
  - Withdraw money from my account
  - List of account s operations from a client

###################################################################################################################################
###################################################################################################################################		  
  ### #####################################################
  #   Deposit money on my account                         #
  #   - Case with no errors                               #
  ### #####################################################
	Scenario Outline: Deposit money on my account - <commentaire>
	  Given url 'http://localhost:8080/api/account/<idAccount>/makedeposit/<amount>'
	  When method post
	  Then status 200
	  And match $.codeRetour == 'OK'
		And match $.listMessage == '#[0]'   
	  And match $.resultat contains deep 
		"""
			{
			   "balance":1100,
			   "listTransaction":[
			      {
			         "amount":<amount>,
			         "typeOperation":<operation>,
			         "balanceAfterOperation":1100,
			         "transactionDate":"#notnull",
			         "idTransaction":1
			      }
			   ],
			   "customer":{
			      "name":"Maxime"
			   }
			}
		"""
		And match $.resultat.listTransaction =='#[1]'
   
   Examples: 
	  | idAccount | amount    | operation        | commentaire    		         |
	  |  1        | 100       | 'DEPOSIT'        | Maxime can deposit money    | 
 
  ### ##################################################
  #   Deposit money on my account                      #
  #   - Case errors wrong account number               #   
  #    - Case errors too much money for deposit        #                        
  ### ##################################################
 	Scenario Outline: Deposit money on my account - <commentaire>
	  Given url 'http://localhost:8080/api/account/<idAccount>/makedeposit/<amount>'
	  When method post
	  Then status <httpCode>
	  And match $.codeRetour == 'KO'
	  And match $.listMessage[0] contains {typeMessage : 'ERREUR' , libelleMessage : <libelleMessage>}
	  
   Examples: 
	  | idAccount | amount       |  httpCode    | commentaire    		                | libelleMessage |
	  |  2        | 100          |   500        | Wrong account number              | 'erreur'               |
	  |  1        | -10          |   403        | Amount inferior from zero         | 'The amount must be superior to zero'    |
	  |  1        | 100000       |   403        | Too much money to deposit         | 'The amount is too high, please go to agency'    |
	
###################################################################################################################################
###################################################################################################################################		  
  ### #####################################################
  #   withdraw money from my account                      #
  #   - Case with no errors                               #
  ### #####################################################
	Scenario Outline: Withdraw money on my account - <commentaire>
	  Given url 'http://localhost:8080/api/account/<idAccount>/makedwithdrawal/<amount>'
	  When method post
	  Then status 200
	  And match $.codeRetour == 'OK'
		And match $.listMessage == '#[0]'   
	  And match $.resultat contains deep 
		"""
			{
			   "balance":800,
			   "listTransaction":[
			      {
			         "amount":100,
			         "typeOperation":"DEPOSIT",
			         "balanceAfterOperation":1100,
			         "transactionDate":"#notnull",
			         "idTransaction":1
			      },
			      {
			         "amount":<amount>,
			         "typeOperation":<operation>,
			         "balanceAfterOperation":800,
			         "transactionDate":"#notnull",
			         "idTransaction":2
			      }
			   ],
			   "customer":{
			      "name":"Maxime"
			   }
			}
		"""
		And match $.resultat.listTransaction =='#[2]'
   
   Examples: 
	  | idAccount | amount      |  operation        | commentaire    		          |
	  |  1        | 300         |  'WITHDRAWAL'     | Maxime can withdraw money   | 
 
  ### ##################################################
  #   withdraw money from my account                   #
  #   - Case errors wrong account numbe                #
  #   - Case errors too much money to withdraw         #
  ### ##################################################
 	Scenario Outline: Withdraw money on my account - <commentaire>
	  Given url 'http://localhost:8080/api/account/<idAccount>/makedwithdrawal/<amount>'
	  When method post
	  Then status <httpCode>
	  And match $.codeRetour == 'KO'
	  And match $.listMessage[0] contains {typeMessage : 'ERREUR' , libelleMessage : <libelleMessage>}
	  
   Examples: 
	  | idAccount | amount     |  httpCode         | commentaire    		                         | libelleMessage |
	  |  2        | 100        |  500              | Maxime cannot withdraw money                | 'erreur'       |
	  |  1        | -10        |   403             | Amount inferior from zero         | 'The amount must be superior to zero'    |
	  |  1        | 3000       |  403              | Maxime have unsuficient money to withdraw   | 'Insufficient founds' |

###################################################################################################################################
###################################################################################################################################		  
  ### #####################################################
  #   List of account's operations                        #
  #   - Case with no errors                               #
  ### #####################################################
	Scenario Outline: List of account's operations - <commentaire>
	  Given url 'http://localhost:8080/api/account/transactionslist/<idAccount>'
	  When method get
	  Then status 200
	  And match $.codeRetour == 'OK'
		And match $.listMessage == '#[0]'   
	  And match $.resultat contains deep 
		"""
			{
			   "balance":800,
			   "listTransaction":[
			      {
			         "amount":100,
			         "typeOperation":"DEPOSIT",
			         "balanceAfterOperation":1100,
			         "transactionDate":"#notnull",
			         "idTransaction":1
			      },
			      {
			         "amount":300,
			         "typeOperation":"WITHDRAWAL",
			         "balanceAfterOperation":800,
			         "transactionDate":"#notnull",
			         "idTransaction":2
			      }
			   ],
			   "customer":{
			      "name":<user>
			   }
			}
		"""
		And match $.resultat.listTransaction =='#[2]'
   
   Examples: 
	  | idAccount | user             | balance   | commentaire    		                   |
	  |  1        | 'Maxime'         | 1005      | Maxime can consult account balance    | 
 
  ### ##################################################
  #   List of account's operations                     #
  #   - Case with error : wrong account number         #                                 
  ### ##################################################
 	Scenario Outline: List of account's operations  - <commentaire>
	  Given url 'http://localhost:8080/api/account/transactionslist/<idAccount>'
	  When method get
	  Then status <httpCode>
	  And match $.codeRetour == 'KO'
	  And match $.listMessage[0] contains {typeMessage : 'ERREUR' , libelleMessage : 'erreur'}
	  
   Examples: 
	  | idAccount | user         |  httpCode     | balance   | commentaire    		                   |
	  |  2        | 'Maxime'     |  500          | 1005      | Maxime can consult account balance    |  