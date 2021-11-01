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
package models;

import static org.junit.Assert.*;
import org.junit.Test;
import org.solent.oodd.pos.model.dto.Card;

/**
 * Test Methods for the card class
 * @author rgaud
 */
public class CardTest {
    /**
     * Tests the setting and getting of a card number
     */
    @Test
    public void setBasicCardNumberTest()
    {
        Card card = new Card();
        assertEquals(true, card.setCardNumber("0000 0000 0000 0000"));
        assertEquals("0000000000000000", card.getCardNumber());
    }
    
    /**
     * Tests the setting and getting of a card name
     */
    @Test
    public void setNameTest()
    {
        Card card = new Card();
        assertEquals(true, card.setName("Joe Bloggs"));
        assertEquals("Joe Bloggs", card.getName());
    }
    
    /**
     * Tests the setting and getting of an invaliud Card Number (should return false)
     */
    @Test
    public void setInvalidCardNumberTest()
    {
        Card card = new Card();
        assertEquals(false, card.setCardNumber("000000000000000"));
        assertEquals("", card.getCardNumber());
    }
    
    /**
     * Tests the setting and getting of a Card CVV Number
     */
    @Test
    public void setCVVTest()
    {
        Card card = new Card();
        assertEquals(true, card.setCVV("846"));
        assertEquals("846", card.getCVV());
    }
    
    /**
     * Tests the setting and getting of a Card Issue Number
     */
    @Test
    public void setIssueNumberTest()
    {
        Card card = new Card();
        assertEquals(true, card.setIssueNumber("02"));
        assertEquals("02", card.getIssueNumber());
    }
    
    /**
     * Tests the setting and getting of a 4 digit Card CVV Number
     */
    @Test
    public void setCVV4DigitTest()
    {
        Card card = new Card();
        assertEquals(true, card.setCVV("8461"));
        assertEquals("8461", card.getCVV());
    }
    
    /**
     * Tests the setting and getting of an invalid length CVV Number
     */
    @Test
    public void setInvalidCVVTest()
    {
        Card card = new Card();
        assertEquals(false, card.setCVV("84615"));
        assertEquals("", card.getCVV());
    }
    
    /**
     * Tests the setting and getting of a card expiry date
     */
    @Test
    public void setExpiryDateTest()
    {
        Card card = new Card();
        assertEquals(true, card.setExpiryDate("05/21"));
        assertEquals("05/21", card.getExpiryDate());
    }
    
    /**
     * Tests the setting and getting of multiple invalid expiry dates
     */
    @Test
    public void setInvalidExpiryDate(){
        Card card = new Card();
        assertEquals(false, card.setExpiryDate("abcde123"));        
        assertEquals(false, card.setExpiryDate("14/-0"));        
        assertEquals(false, card.setExpiryDate("14/24"));        
        assertEquals(true, card.setExpiryDate("11/24"));
    }
    
}