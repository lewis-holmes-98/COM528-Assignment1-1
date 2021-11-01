/*
 * Copyright 2021 rgaud.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.solent.oodd.pos.model.dto;

import java.text.ParseException;
import java.time.YearMonth;  
import java.time.format.DateTimeFormatter;  
import java.time.DateTimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author rgaud
 * This class holds the information about a single Card
 */
public class Card {
    private String cardNumber = "";
    private String cvv = "";
    private String expiryDate = "";
    private String name = "";
    private String issueNumber = "";
    
    final static Logger LOG = LogManager.getLogger(Card.class);

    
    //Get Methods
    /**
     * Gets the card number
     * @return cardNumber
     */
    public String getCardNumber(){
        return cardNumber;
    }
    
    /**
     * Gets the card CVV
     * @return cvv
     */
    public String getCVV(){
        return cvv;
    }
    
    /**
     * Gets the card name/ card user's name
     * @return name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Get's the card Expiry Date as a String in the format MM/yy 
     * @return expiryDate
     */
    public String getExpiryDate(){
        return expiryDate;
    }
    
    //Set Methods
    /**
     * Removes whitespace from the input, checks for 16 digits 
     * and then sets the card number property
     * @param CardNumber a 16 digit card number
     * @return True if validation was success, valse if not
     */
    public Boolean setCardNumber(String CardNumber){
        //Removes all whitespace from String
        CardNumber = CardNumber.replaceAll("\\s+","");
        //Valdiates for a 16 digit card number
        if(CardNumber.length() == 16){
            this.cardNumber = CardNumber;
            LOG.info("Set Card Number Validation - Success: " + CardNumber);
            return true;
        }
        LOG.info("Set Card Number Validation - Failed: " + CardNumber);
        return false;
    }
    
    /**
     * Sets the name of the card
     * @param Name String for the card user's name
     * @return true (unless there was an error)
     */
    public Boolean setName(String Name){
        this.name = Name;
        return true;
    }
    
    /**
     * Checks the CVV is 3 or 4 digits and then passes it in
     * @param Cvv A 3 or 4 digit String of the CVV
     * @return True if validation was success or false
     */
    public Boolean setCVV(String Cvv){
        //Vallidates for a 3 or 4 digit CVV number
        if(Cvv.length() == 3 || Cvv.length() == 4){
            this.cvv = Cvv;
            LOG.info("Set CVV Validation - Success: ");
            return true;
        }
        LOG.info("Set CVV Validation - Failed: " + Cvv);
        return false;
    }
    
    //MM/yy

    /**
     * Checks the String is in the format of MM/yy and then set's the endDate proeprty
     * @param endDate the expiry Date of the card in the format of MM/yy
     * @return Returns true if validation was successful, if not returns false
     */
    public Boolean setExpiryDate(String endDate){        
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth date = YearMonth.parse(endDate, formatter);
            
            if(date.getYear() < 1000){
                throw new ParseException("invalid date string", 0);
            }
            //If Parse was successful
            LOG.info("Set Expiry Date Validation - Success: " + endDate);
            this.expiryDate = endDate;
            return true;
            
        }
        catch(ParseException ex){
            LOG.info("Set Expiry Date Validation - Failed (Parse): " + endDate);
            return false;
        }
        catch(DateTimeException ex){
            LOG.info("Set Expiry Date Validation - Failed (DateTime): " + endDate);
            return false;
        }
    }
    
    /**
     * Get's the Card's Issue Number
     * @return Issue Number of the Card
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets the Card's Issue Number
     * @param issueNumber String of the Card's issue number
     * @return True unless there were any issues
     */
    public boolean setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
        return true;
    }
    
    /**
     * @return Returns a custom JSON representation of the Card Object
     */
    @Override
    public String toString() {
        return "Card{" + "name=" + name + ", expiryDate=" + expiryDate + ", cardNumber=" + cardNumber + ", cvv=NOT PRINTED" + '}';
    }
}
